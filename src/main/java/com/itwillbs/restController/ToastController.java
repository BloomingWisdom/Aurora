package com.itwillbs.restController;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.service.MemberService;
import com.itwillbs.service.TestService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RequiredArgsConstructor
@Log
@RestController
public class ToastController {
	
	private final String URL = "/ajax/toastTest";
	
	private final TestService testService;
	
	@GetMapping(URL)
	public Map<String, Object> getToastTest() {
		return testService.selectToastTest();
	}	
	
	@PostMapping(URL)
	public Map<String, Object> insertToastTest(@RequestBody Map<String, Object> requestData) {
	    List<Map<String, Object>> createdRows = (List<Map<String, Object>>)requestData.get("createdRows");
		
        return testService.insertToastTest(createdRows);
	}	
	
	@DeleteMapping(URL)
	public Map<String, Object> deleteToastTest(@RequestHeader("X-Delete-IDs") String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        
        return testService.deleteToastTest(idList);
	}	
	
	@PutMapping(URL)
	public Map<String, Object> updateToastTest(@RequestBody Map<String, Object> requestData) {
		List<Map<String, Object>> updatedRows = (List<Map<String, Object>>)requestData.get("updatedRows");
		
		return testService.updateToastTest(updatedRows);
	}	
	
}


