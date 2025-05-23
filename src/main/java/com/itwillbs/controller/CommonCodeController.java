package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itwillbs.service.CommonCodeService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class CommonCodeController {
	
	private final CommonCodeService commonCodeService;
	
	@GetMapping("/admin/commonCode")
	public String commonCode(@AuthenticationPrincipal User user, HttpSession session) {
        if (user == null) {
            return "redirect:/login"; 
        }
		
		return "/commonCode/common_code";
	}
	
//	@PostMapping("getCommonCode")
//	@ResponseBody
//	public List<Map<String, Object>> getCommonCode() throws JsonProcessingException  {
//		List<Map<String, Object>> Code = commonCodeService.getCommonCodes();
//		
//		ObjectMapper mapper = new ObjectMapper();
//		String dataJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(Code);
//		
//		return Code;
//	}
	
	@PostMapping("/system/getGroupCommonCode")
	@ResponseBody
	public List<Map<String, Object>> getGroupCommonCode(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> codeList = commonCodeService.getGroupCommonCode(map);
		
		return codeList;
	}
	
	@PostMapping("/system/addCommonCodeGroup")
	@ResponseBody
	public Map<String, Object> addCommonCodeGroup(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = commonCodeService.insertCommonCodeGroup(map);
		
		return message;
	}
	
	@PostMapping("/system/deleteCommonCodeGroup")
	@ResponseBody
	public Map<String, Object> deleteCommonCodeGroup(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = commonCodeService.deleteCommonCodeGroup(map);
		
		return message;
	}
	
	@PostMapping("/system/updateCommonCodeGroup")
	@ResponseBody
	public Map<String, Object> updateCommonCodeGroup(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = commonCodeService.updateCommonCodeGroup(map);
		
		return message;
	}
	
	@PostMapping("/system/getCommonCode")
	@ResponseBody
	public List<Map<String, Object>> getCommonCode(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> codeList = commonCodeService.getCommonCode(map);
		
		return codeList;
	}
	
	@PostMapping("/system/addCommonCode")
	@ResponseBody
	public Map<String, Object> addCommonCode(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = commonCodeService.insertCommonCode(map);
		
		return message;
	}
	
	@PostMapping("/system/deleteCommonCode")
	@ResponseBody
	public Map<String, Object> deleteCommonCode(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = commonCodeService.deleteCommonCode(map);
		
		return message;
	}
	
	@PostMapping("/system/updateCommonCode")
	@ResponseBody
	public Map<String, Object> updateCommonCode(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = commonCodeService.updateCommonCode(map);
		
		return message;
	}
}

