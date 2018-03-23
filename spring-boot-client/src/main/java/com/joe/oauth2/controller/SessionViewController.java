/*******************************************************************************
 * Licensed to the OKChem
 *
 * http://www.okchem.com
 *
 *******************************************************************************/
package com.joe.oauth2.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Joe.Li
 *
 */
@RestController
public class SessionViewController {
	@GetMapping("/sessions")
	public ResponseEntity<VO> viewSession(HttpServletRequest request){
		HttpSession httpSession = request.getSession();
		Enumeration<String> attrNames = httpSession.getAttributeNames();
		while(attrNames.hasMoreElements()) {
		  String attrName = (String)attrNames.nextElement();
		  System.out.println("Header Name - " + attrName + ", Value - " + httpSession.getAttribute(attrName));
		}
		return ResponseEntity.ok(new VO());
	}
}

class VO{
	private String msg = "Just For testing";

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
