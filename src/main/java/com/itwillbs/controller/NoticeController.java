package com.itwillbs.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itwillbs.service.NoticeService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class NoticeController {
	
	private final NoticeService noticeService;
       
	// 목록 가져오기
    @GetMapping("/notice/noticeList")
    public String getNoticeList(@AuthenticationPrincipal User user, @RequestParam Map<String, Object> map, Model model) {
    	
    	if (user == null) {
            return "redirect:/login"; 
        }
        
        String id = user.getUsername();
    	
    	
    	map.put("memberId", id);
    	List<Map<String, Object>> noticeList = noticeService.getNoticeList(map);
    	
    	 // noticeList가 null이면 확인
	    if (noticeList == null) {
	        System.out.println("noticeList is null");
	    } else {
	    }
    	
    	model.addAttribute("noticeList", noticeList);
    	
    	return "/notice/noticeList";
    }
    
// 목록 가져오고 ADMIN 추가 
    @PostMapping("/getNoticeList")
    @ResponseBody
    public List<Map<String, Object>> getNoticeList(@AuthenticationPrincipal User user, @RequestParam Map<String, Object> map) {
    	String id = user.getUsername();
    	String authority = user.getAuthorities().iterator().next().getAuthority();
    	
    	map.put("memberId", id);
    	List<Map<String, Object>> noticeList = noticeService.getNoticeList(map);
    	
    	// 관리자인 경우만 editable, deletable 필드 추가
    	boolean isAdmin = "ROLE_ADMIN".equals(authority);
        for (Map<String, Object> notice : noticeList) {
            notice.put("editable", isAdmin);
            notice.put("deletable", isAdmin);
        }
    	return noticeList;
    }    
       
    @GetMapping("/session")
    public ResponseEntity<Map<String, Object>> getSessionData(@AuthenticationPrincipal User user) {
        // 세션에서 사용자 ID와 역할(role)을 가져옴
        String userId = user.getUsername();
        String userRole = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority) // 권한 이름 가져오기
                .findFirst() // 첫 번째 권한 가져오기
                .orElse(null);

        // 세션 값이 없는 경우, 기본 응답 처리
        if (userId == null || userRole == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "로그인이 필요합니다."));
        }

        // 세션 데이터 응답
        Map<String, Object> response = new HashMap<>();
        response.put("id", userId);
        response.put("authority", userRole);

        return ResponseEntity.ok(response);
    }          
    
    // 공지 수정 정보
    @PostMapping("/getNoticeDetail")
    @ResponseBody
    public Map<String, Object> getNoticeDetail(@RequestParam Map<String, Object> param) throws JsonProcessingException {
    	System.out.println("====================== 공지 정보 ======================");
    	System.out.println(param);
    	Map<String, Object> notice = noticeService.getNoticeDetail(param);
    	    	
    	return notice;
    }
      
//  공지사항 작성 (관리자 전용)
    @PostMapping("/createNotice")
    @ResponseBody
    public Map<String, Object> createNotice(@AuthenticationPrincipal User user, @RequestParam Map<String, Object> map, Model model) {
    	String id = user.getUsername();
        map.put("memberId", id);
        noticeService.createNotice(map);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        return response;
    }
       
//    공지사항 수정 (관리자 전용)
    @PostMapping("/updateNotice")
    public String updateNotice(@AuthenticationPrincipal User user,@RequestParam Map<String, Object> map, Model model) {
    	String id = user.getUsername();
		map.put("memberId", id);
	    noticeService.updateNotice(map);
	    return "redirect:/notice/noticeList";
    }
    
//    공지사항 삭제 (관리자 전용)
    @PostMapping("/deleteNotice")
    public String deleteNotice(@AuthenticationPrincipal User user, @RequestParam Map<String, Object> map) {
    	String id = user.getUsername();
		map.put("memberId", id);
		System.out.println(map);
		noticeService.deleteNotice(map);
		return "redirect:/notice/noticeList";
	}
}
