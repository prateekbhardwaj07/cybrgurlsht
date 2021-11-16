package com.prateekb.service;


import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Objects;
import java.util.Base64.Encoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prateekb.DAO.UrlShortRepository;
import com.prateekb.model.UrlShort;

@Service
public class UrlShrtService{

	@Autowired
	UrlShortRepository urlShortRepository;


	@Transactional
	public String encodeUrlPath(String path) {
		String urlRgx = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)";
		if (!path.matches(urlRgx)) {
			return "invalid";
		}
		else {
			String shtPath = makeShortUrl(path);
			UrlShort url = new UrlShort(path,shtPath,new Timestamp(System.currentTimeMillis())); 
			Integer savedLId = 0;
			try
			{
				savedLId = urlShortRepository.findByPath(path);
				if(savedLId != null && savedLId>0) {
					shtPath += encryptBase36(savedLId);
				}
				else {
					savedLId = urlShortRepository.save(url).getlId();
					shtPath += encryptBase36(savedLId);
					urlShortRepository.updateTrmUrl(shtPath, savedLId);
				}
				
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
			return shtPath;

		}

	}

	public String makeShortUrl(String path) {
		String subdm = "";
		String dom = "";
		String[] parts = path.split("\\.");
		if (parts[0].contains("/")) {
			subdm = parts[0].substring(parts[0].indexOf("/") + 2, parts[0].indexOf("/") + 3);
		}
		else {
			subdm = parts[0].substring(0, 1);
		}
		if (parts.length > 2) {
			for (int i = 1; i < parts.length; i++) {
				dom += parts[i].substring(0, 1);
			}
		} else {
			dom = parts[1].substring(0, 1);
		}
		String shtPath = "h://" + subdm + "." + dom + "/";
		return shtPath;

	}

	@Transactional
	public String decodeUrlPath(String path) {
		String decodedPath = path.substring(path.lastIndexOf("/") + 1);
		System.out.println(decodedPath);
		Integer lId = decryptBase36(decodedPath);
		UrlShort fetchUrl = null;
		// get the stored Long Url against this Id
		try {
			fetchUrl = urlShortRepository.findUrlByLId(lId);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		if(!Objects.isNull(fetchUrl)) {
			return fetchUrl.getUrlPath();
		}
		return "not available";
	}

	public String encryptBase36(Integer value) {
		byte[] bytes = String.valueOf(value).getBytes(StandardCharsets.UTF_8);
		String base36 = new BigInteger(1, bytes).toString(36);
		return base36;

	}

	public Integer decryptBase36(String b36Str) {
		byte[] bytes = new BigInteger(b36Str, 36).toByteArray();
		int zeroPrefixLength = zeroPrefixLength(bytes);
		String string = new String(bytes, zeroPrefixLength, bytes.length - zeroPrefixLength, StandardCharsets.UTF_8);
		return Integer.parseInt(string);
	}

	public int zeroPrefixLength(final byte[] bytes) {
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] != 0) {
				return i;
			}
		}
		return bytes.length;
	}

}

