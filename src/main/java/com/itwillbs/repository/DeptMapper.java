package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptMapper {

	List<Map<String, Object>> getUpperDept(Map<String, Object> map);

	int isDuplicateUpperDept(Map<String, Object> map);

	int insertUpperDept(Map<String, Object> map);

	List<Map<String, Object>> getCcodeList(Map<String, Object> map);

	List<Map<String, Object>> getDepMngList(Map<String, Object> map);

	int isExistUpperDeptChild(Map<String, Object> map);

	int deleteUpperDept(Map<String, Object> map);

	int updateUpperDept(Map<String, Object> map);

	List<Map<String, Object>> getLowerDept(Map<String, Object> map);

	int isDuplicateLowerDept(Map<String, Object> map);

	int insertLowerDept(Map<String, Object> map);

	int deleteLowerDept(Map<String, Object> map);

	int updateLowerDept(Map<String, Object> map);

	void updateInUpperAdmin(Map<String, Object> map);

	void updateInLowerAdmin(Map<String, Object> map);

	void updateUpperUser(Map<String, Object> map);

	void updateUpperAdmin(Map<String, Object> map);

	void updateLowerUser(Map<String, Object> map);

	void updateLowerAdmin(Map<String, Object> map);

	List<Map<String, Object>> getDepartmentList(Map<String, Object> map);

}
