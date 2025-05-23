package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DashboardMapper {

	List<Map<String, Object>> getMyCommuteHistory(Map<String, Object> map);
	
	Map<String, Object> getMyCommuteHistoryMinMaxDate(Map<String, Object> map);

	List<Map<String, Object>> getMyAttendanceHistory(Map<String, Object> map);

	List<Map<String, Object>> getMyCommuteTime(Map<String, Object> map);

	Map<String, Object> getMyCommuteTimeMinMaxDate(Map<String, Object> map);

	List<Map<String, Object>> getSelectBoxList(Map<String, Object> map);

	List<Map<String, Object>> getMemberSelectBoxList(Map<String, Object> map);
	
	// ====================================================================================

	List<Map<String, Object>> getMyAttendanceHistoryDonutChart(Map<String, Object> map);

	List<Map<String, Object>> getAttendanceHistoryStackedBarChart(Map<String, Object> map);
	
	// ====================================================================================
	
	// 설비 가동률
	List<Map<String, Object>> selectEquipDonutChart(Map<String, Object> map);
	
	// 발주 상태 - 일별/주별/월별
	List<Map<String, Object>> selectPoBarChart(Map<String, Object> map);
	
	// 거래처별 발주량 및 불량률
	List<Map<String, Object>> selectClientPo(Map<String, Object> map);
	
	// 거래처별 불량률
	List<Map<String, Object>> selectClientInboundChart(Map<String, Object> map);
	
	// 발주 불량률 - 일별/주별/월별
	List<Map<String, Object>> selectInboundChart(Map<String, Object> map);
	
	// 생산량 - 일별/주별/월별
	List<Map<String, Object>> selectProductBarChart(Map<String, Object> map);
	
	// 생산 불량률 - 일별/주별/월별
	List<Map<String, Object>> selectProductDefectChart(Map<String, Object> map);
	
	// 생산 현황
	List<Map<String, Object>> selectProductionLotDetail(Map<String, Object> map);
	
	// 전체 LOT 추적
	List<Map<String, Object>> selectLotTracking(Map<String, Object> map);
}
