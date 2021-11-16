package com.prateekb.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.prateekb.service.UrlShrtService;

@Controller
@CrossOrigin(origins = "http://http://localhost:8080/cybrgurlsht/", maxAge = 12000)
public class MyAppController {
	
	@Autowired
	UrlShrtService service ;
	
    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView visitHome() {
        return new ModelAndView("index");
    }
         
    @RequestMapping(value="/redirect", method = RequestMethod.POST)
    public ResponseEntity getFullPath(HttpServletRequest request,HttpServletResponse response) {
        System.out.println(request.getParameter("shortpath"));
    	String path = service.decodeUrlPath(request.getParameter("shortpath"));
        return ResponseEntity.accepted().body(path);
    }
    @RequestMapping(value="/shorten", method = RequestMethod.POST)
    public ModelAndView getshorten(HttpServletRequest request,HttpServletResponse response) {
    	ModelAndView model = new ModelAndView("shorten");
    	String path = request.getParameterValues("UrlBox")[0];
    	System.out.println(path);
    	String shrtPath = service.encodeUrlPath(path);
    	model.addObject("shortpath", shrtPath);
		return model;
    	
    }
    

}
