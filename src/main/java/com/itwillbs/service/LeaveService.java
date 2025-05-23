package com.itwillbs.service;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.LeaveMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeaveService {
	
	private final LeaveMapper leaveMapper;

	public List<Map<String, Object>> getleaveList(Map<String, Object> map) {
		return leaveMapper.getLeaveList(map);
	}

	public List<Map<String, Object>> getleaveHisList(Map<String, Object> map) {
		return leaveMapper.getLeaveHisList(map);
	}


}
