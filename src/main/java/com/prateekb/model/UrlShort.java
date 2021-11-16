package com.prateekb.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="urlshort")
public class UrlShort implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="lId")
	Integer lId;
	@Column(nullable = false)
	String urlpath;
	String trmurl;
	Timestamp created_on;
	public UrlShort() {
		
	}
	public UrlShort(String urlPath,String trmUrl,Timestamp created_on) {
		this.urlpath = urlPath;
		this.trmurl = trmUrl;
		this.created_on = created_on;
	}
	public Integer getlId() {
		return lId;
	}
	public String getUrlPath() {
		return urlpath;
	}
	public String getTrmUrl() {
		return trmurl;
	}
	public Timestamp getCreated_on() {
		return created_on;
	}
}
