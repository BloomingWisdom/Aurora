package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManufactureMapper {
	
	List<Map<String, Object>> selectWorkcenter(Map<String, Object> requestData);
	
	int insertWorkcenter(List<Map<String, Object>> createdRows);
	
	int updateWorkcenter(List<Map<String, Object>> updatedRows);
	
	int deleteWorkcenter(List<String> idList);

	List<Map<String, Object>> selectWorkcenterManager(Map<String, Object> requestData);

	List<Map<String, Object>> selectEquipment(Map<String, Object> requestData);

	int insertEquipment(List<Map<String, Object>> createdRows);

	int deleteEquipment(List<String> equipmentIds);

	int deleteEquipmentByWorkcenterId(List<String> idList);

	List<Map<String, Object>> selectProcess(Map<String, Object> requestData);

	int checkDuplicateCode(Map<String, Object> map);

	int insertProcess(List<Map<String, Object>> createdRows);

	int updateProcess(List<Map<String, Object>> updatedRows);

	int deleteProcess(List<String> processIds);

	List<Map<String, Object>> selectRouting(Map<String, Object> requestData);

	List<Map<String, Object>> selectRoutingItem(Map<String, Object> requestData);

	int insertRouting(List<Map<String, Object>> createdRows);

	int updateRouting(List<Map<String, Object>> updatedRows);

	int deleteRouting(List<String> processIds);

	List<Map<String, Object>> selectRoutingSequence(Map<String, Object> requestData);

	int insertRoutingSequence(List<Map<String, Object>> createdRows);

	int updateRoutingProcessQuantity(Map<String, Object> map);

	int deleteRoutingSequence(List<String> processIds);

	List<Map<String, Object>> selectProductionOrder(Map<String, Object> requestData);

	List<Map<String, Object>> selectProductionOrderDetail(Map<String, Object> requestData);

	List<Map<String, Object>> selectProductionOrderItem(Map<String, Object> requestData);

	int insertProductionOrder(List<Map<String, Object>> createdRows);

	List<Map<String, Object>> selectProductionOrderBOM(Map<String, Object> requestData);

	List<Map<String, Object>> selectProductionOrderWorkcenter(Map<String, Object> requestData);

	int insertProductionOrderDetail(List<Map<String, Object>> createdRows);

	int selectTodayMaxProductionOrderDetailId();
	
	int selectTodayMaxProductionOrderId();

	List<Map<String, String>> selectProductionOrderDetailBOM(List<Map<String, Object>> createdRows);

	int insertProductionOrderDetailBOM(List<Map<String, String>> bomList);

	int selectMaxPutMaterialsId();

	int deleteProductionOrder(List<String> productionOrderIds);

	List<String> selectProductionOrderDetailDeleteIds(List<String> productionOrderIds);

	int deleteProductionOrderDetail(List<String> productionOrderIds);

	int deletePutMaterials(List<String> productionOrderDetailIds);

	int updateProductionOrderCnt(String productionOrderId);
	
	List<Map<String, Object>> selectBom(Map<String, Object> requestData);

	List<Map<String, Object>> selectBomDetail(Map<String, Object> requestData);

	int insertBom(List<Map<String, Object>> createdRows);

	int updateBom(List<Map<String, Object>> updatedRows);

	int deleteBom(List<String> idList);

	int deleteBomDetailById(List<String> idList);

	List<Map<String, Object>> selectBomDetailComponent(Map<String, Object> requestData);

	int insertBomDetail(List<Map<String, Object>> createdRows);

	int updateBomQuantity(Map<String, Object> map);

}
