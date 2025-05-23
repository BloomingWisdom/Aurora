package com.itwillbs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.service.PurchaseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class PurchaseController {
	
	private final PurchaseService purchaseService;
	
	private final String purchaseClt = "/purchase/purchaseClt";
	private final String purchaseMtl = "/purchase/purchaseMtl";
	
	
	@GetMapping("/purchase")
	public String purchase(@AuthenticationPrincipal User user) {
//		if (session.getAttribute("id") == null) {
//      return "redirect:/login"; 
//  }
		return "purchase/purchaseOrder";
	}
	
		@PostMapping("getPurchase")
		@ResponseBody
		public List<Map<String, Object>> getPurchaseList(@RequestBody Map<String, Object> map) {
			List<Map<String, Object>> purchaseList = purchaseService.getPurchaseList(map);
			System.out.println("받은 리스트 :" + map.toString());
			System.out.println("purchaseList: " + purchaseList);
			return purchaseList;
		}
		
		
		@PostMapping("/savePurchase")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> savePurchase(@AuthenticationPrincipal User user, @RequestBody List<Map<String, Object>> dataList) {
		    int result = 0;
		    String id = user.getUsername();
		    
		    List<Map<String, Object>> insertList = new ArrayList<>();
		    List<Map<String, Object>> updateList = new ArrayList<>();
		    List<Map<String, Object>> deleteList = new ArrayList<>();

		    for (Map<String, Object> data : dataList) {
		    	data.put("INSERT_MEM", id);
		    	data.put("UPDATE_MEM", id);
		        String rowType = (String) data.get("rowType");
		        System.out.println("처리할 데이터: " + rowType + " → " + data); // rowType 출력

		        String status = String.valueOf(data.get("PO_STATUS")).trim(); // 발주 상태
		        // 파라미터로 가져오는 상태값에 따라 공통코드에 맞춰서 PO_STATUS 값 변경
		        if(status.equals("미결")) { // 미결
		        	data.put("PO_STATUS", "N");
		        } else if(status.equals("진행중")) { // 진행중
		        	data.put("PO_STATUS", "I");
		        } else { // 마감
		        	data.put("PO_STATUS", "Y");
		        }
		        System.out.println("dataaaaa " + data);
		        if ("insert".equals(rowType)) {
		            insertList.add(data);
		        } else if ("update".equals(rowType)) {
		            updateList.add(data);
		        } else if ("delete".equals(rowType)) {
		            deleteList.add(data);
		        }
		    }

		    result += purchaseService.insertPurchase(insertList);
		    result += purchaseService.updatePurchase(updateList);
		    result += purchaseService.deletePurchase(deleteList);
		    
		    System.out.println("최종 저장된 데이터 개수: " + result); // 처리된 개수 출력
		 // JSON 형태로 응답 반환 (undefined 방지)
		    Map<String, Object> responseMap = new HashMap<>();
		    responseMap.put("status", "success");
		    responseMap.put("message", "저장이 완료되었습니다!");
		    responseMap.put("affectedRows", result);
		    
		    return ResponseEntity.ok(responseMap);
		}
		
		
		@PostMapping("getPurchaseDetail")
		@ResponseBody
		public List<Map<String, Object>> getPurchaseDetail(@RequestBody Map<String, Object> map  ) {
			List<Map<String, Object>> purchaseDetail = purchaseService.getPurchaseDetail(map);
			System.out.println("받은 리스트 :" + map.toString());
			System.out.println("purchaseList: " + purchaseDetail);
			return purchaseDetail;
		}
		
		
		@PostMapping("/saveDetail")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> saveDetail(@AuthenticationPrincipal User user, @RequestBody List<Map<String, Object>> dataList) {
		    int result = 0;
		    String id = user.getUsername();
		    
		    List<Map<String, Object>> insertList = new ArrayList<>();
		    List<Map<String, Object>> updateList = new ArrayList<>();
		    List<Map<String, Object>> deleteList = new ArrayList<>();

		    for (Map<String, Object> data : dataList) {
		    	data.put("UPDATE_MEM", id);
		        String rowType = (String) data.get("rowType");
		        System.out.println("처리할 데이터: " + rowType + " → " + data); // rowType 출력

		        String status = String.valueOf(data.get("PO_STATUS")).trim(); // 발주 상태
		        // 파라미터로 가져오는 상태값에 따라 공통코드에 맞춰서 PO_STATUS 값 변경
		        if(status.equals("미결")) { // 미결
		        	data.put("PO_STATUS", "N");
		        } else if(status.equals("진행중")) { // 진행중
		        	data.put("PO_STATUS", "I");
		        } else { // 마감
		        	data.put("PO_STATUS", "Y");
		        }
		        
		        if ("insert".equals(rowType)) {
		            result += purchaseService.insertDetail(data);
		        } else if ("update".equals(rowType)) {
		        	result += purchaseService.updateDetail(data);
		        } else if ("delete".equals(rowType)) {
		        	result += purchaseService.deleteDetail(data);
		        }
		    }
		    
		    System.out.println("최종 저장된 데이터 개수: " + result); // 처리된 개수 출력
		 // JSON 형태로 응답 반환 (undefined 방지)
		    Map<String, Object> responseMap = new HashMap<>();
		    responseMap.put("status", "success");
		    responseMap.put("message", "저장이 완료되었습니다!");
		    responseMap.put("affectedRows", result);
		    
		    return ResponseEntity.ok(responseMap);
		}
		
		@GetMapping(purchaseClt)
		@ResponseBody
		public Map<String, Object> getClientList(@RequestParam Map<String, Object> requestData) {
			System.out.println("거래처 리스트" + requestData);
			return purchaseService.getClientList(requestData);
		}
		
		@PostMapping(purchaseMtl)
		@ResponseBody
		public List<Map<String, Object>> getMaterialList(@RequestBody Map<String, Object> requestData ) {
			System.out.println("자재리스트" + requestData.toString());
			List<Map<String, Object>> getMaterialList = purchaseService.getMaterialList(requestData);
			return getMaterialList;
		}	
}
