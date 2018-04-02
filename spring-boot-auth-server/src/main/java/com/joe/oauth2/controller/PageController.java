package com.joe.oauth2.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {
	private RequestCache requestCache = new HttpSessionRequestCache();
	private final String PARAM_CLIENT_ID = "client_id";

	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		return getPage(request, response, "login");
	}

	private String getPage(HttpServletRequest request, HttpServletResponse response, String subfix){
		String clientId = getClientId(request, response);
		if(clientId!=null){
			return clientId+"/"+subfix;
		}
		return subfix;
	}
	private String getClientId(HttpServletRequest request, HttpServletResponse response){
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if(savedRequest!=null){
			if(savedRequest.getParameterValues(PARAM_CLIENT_ID)!=null){
				String[] ids = savedRequest.getParameterValues(PARAM_CLIENT_ID);
				if(ids.length>0){
					return ids[0];
				}
			}
		}
		return null;
	}

	@GetMapping("/principal")
	@ResponseBody
	public Principal user(Principal principal) {
		return principal;
	}

	@GetMapping("/")
	public String hello() {
		return "Hello World";
	}
}
