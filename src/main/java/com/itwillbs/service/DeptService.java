package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.DeptMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeptService {

	private final DeptMapper deptMapper;
	
	//상위부서 리스트
	public List<Map<String, Object>> getUpperDept(Map<String, Object> map) {
		return deptMapper.getUpperDept(map);
	}
	
	//상위부서 등록
	public Map<String, Object> insertUpperDept(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();

		String result = "이미 등록된 코드입니다.";
		String resultCode = "0";

		try {
			int duplicateCnt = deptMapper.isDuplicateUpperDept(map);
			System.out.println(duplicateCnt);
			if (duplicateCnt == 0) {
				int resultCnt = deptMapper.insertUpperDept(map);
				deptMapper.updateInUpperAdmin(map);
				if (resultCnt > 0) {
					result = "등록 되었습니다.";
					resultCode = "1";
				} else {
					result = "등록 실패.";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "등록 실패.";
		}

		message.put("result", result);
		message.put("resultCode", resultCode);

		return message;
	}

	// 공통코드 부서 조회
	public List<Map<String, Object>> getCcodeList(Map<String, Object> map) {

		return deptMapper.getCcodeList(map);
	}

	// 부서장 조회
	public List<Map<String, Object>> getDepMngList(Map<String, Object> map) {
		return deptMapper.getDepMngList(map);
	}
	
	// 상위부서 삭제
	public Map<String, Object> deleteUpperDept(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();

		String result = "하위코드가 존재합니다.";
		String resultCode = "0";

		try {
			int existCnt = deptMapper.isExistUpperDeptChild(map);
			if (existCnt == 0) {
				int resultCnt = deptMapper.deleteUpperDept(map);
				if (resultCnt > 0) {
					result = "삭제 되었습니다.";
					resultCode = "1";
				}
			}
		} catch (Exception e) {
			result = "삭제 실패.";
			resultCode = "0";
		}

		message.put("result", result);
		message.put("resultCode", resultCode);

		return message;
	}
	
	// 상위부서 수정
	public Map<String, Object> updateUpperDept(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();

		String result = "";
		String resultCode = "";

		try {
			deptMapper.updateUpperUser(map);
			int resultCnt = deptMapper.updateUpperDept(map);
			deptMapper.updateUpperAdmin(map);
			
			if (resultCnt > 0) {
				result = "수정이 완료되었습니다.";
				resultCode = "1";
			}
		} catch (Exception e) {
			result = "수정 실패.";
			resultCode = "0";
		}

		message.put("result", result);
		message.put("resultCode", resultCode);

		return message;
	}
	
	//하위부서 리스트
	public List<Map<String, Object>> getLowerDept(Map<String, Object> map) {
		System.out.println("Input Map for SQL: " + map);
		return deptMapper.getLowerDept(map);
	}
	
	// 하위부서 등록
	public Map<String, Object> insertLowerDept(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();

		String result = "이미 등록된 코드입니다.";
		String resultCode = "0";

		try {
		int duplicateCnt = deptMapper.isDuplicateLowerDept(map);
		if (duplicateCnt == 0) {
			int resultCnt = deptMapper.insertLowerDept(map);
			deptMapper.updateInLowerAdmin(map);
			if (resultCnt > 0) {
				result = "등록 되었습니다.";
				resultCode = "1";
			} else {
				result = "등록 실패.";
			}
		}
		} catch (Exception e) {
			result = "등록 실패.";
		}

		message.put("result", result);
		message.put("resultCode", resultCode);

		return message;
	}
	
	// 하위부서 삭제
	public Map<String, Object> deleteLowerDept(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();

		String result = "";
		String resultCode = "";

		try {
		int resultCnt = deptMapper.deleteLowerDept(map);
		if (resultCnt > 0) {
			result = "삭제 되었습니다.";
			resultCode = "1";
		}
		} catch (Exception e) {
			result = "삭제 실패.";
			resultCode = "0";
		}

		message.put("result", result);
		message.put("resultCode", resultCode);

		return message;
	}
	
	// 하위부서 수정
	public Map<String, Object> updateLowerDept(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();
		
		String result = "";
		String resultCode = "";
		
//		try {
			deptMapper.updateLowerUser(map);
			int resultCnt = deptMapper.updateLowerDept(map);
			deptMapper.updateLowerAdmin(map);
			if (resultCnt > 0) {
				result = "수정이 완료되었습니다.";
				resultCode = "1";
			}
//		} catch (Exception e) {
//			System.out.println(e);
//			result = "수정 실패.";
//			resultCode = "0";
//		}
		
		message.put("result", result);
		message.put("resultCode", resultCode);
		
		return message;
	}
	
	// 부서목록
	public List<Map<String, Object>> getDepartmentList(Map<String, Object> map) {
		return deptMapper.getDepartmentList(map);
	}
}
