package com.itwillbs.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(method = RequestMethod.GET, value = "/mes/{packageName}/{urlid}")
public class MesViewController {
	
	@GetMapping
	public String redirectView(@PathVariable("packageName") String packageName
								, @PathVariable("urlid") String urlid) {
		return "/" + packageName + "/" + urlid;
	}
	
}
