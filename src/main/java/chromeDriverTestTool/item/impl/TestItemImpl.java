package chromeDriverTestTool.item.impl;

import java.util.HashMap;
import java.util.Map;

import chromeDriverTestTool.item.service.TestItem;

public class TestItemImpl implements TestItem {

	// 테스트 아이템 가져오기(DB 연동 후 진행)
//	public JSONObject getItemList(Object fieldObj) {
//		JSONObject jsonObj = new JSONObject();
//
//		for(Field field : fieldObj.getClass().getDeclaredFields()) {
//			field.setAccessible(true);
//			try {
//				jsonObj.put(field.getName(), field.get(fieldObj));
//			} catch (IllegalArgumentException e) {
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			}
//		}
//		System.out.println(jsonObj);
//		return jsonObj;
//	}

	// 테스트 매핑 항목 가져오기
	public Map<String, String> getTestMap(String addItemCategory, String addItemDataType, String addItemDataName, String addItemDataText, boolean existItem, String lastItem) {
		
		Map<String, String> testMap = new HashMap<String, String>(); 
		
		if(existItem) {
			if(addItemCategory.equals("NeedElements")) {
				if(lastItem.equals("NeedElements") || lastItem.equals("FreeNeedElements")) {
					testMap.put("method", "alert");
					testMap.put("value", "타겟을 먼저 지정해주세요.");
				} else {
					testMap.put("method", "append");
					testMap.put("value", this.makeTestHTML(addItemCategory, addItemDataType, addItemDataName, addItemDataText));
				}
			} else if(addItemCategory.equals("FreeNeedElements")){
				if(lastItem.equals("target")) {
					testMap.put("method", "alert");
					testMap.put("value", "타겟 필요 동작을 먼저 지정해주세요.");
				} else {
					testMap.put("method", "append");
					testMap.put("value", this.makeTestHTML(addItemCategory, addItemDataType, addItemDataName, addItemDataText));
				}
			} else if(addItemCategory.equals("target")){
				if(lastItem.equals("target")) {
					testMap.put("method", "alert");
					testMap.put("value", "타겟 필요 동작을 먼저 지정해주세요.");
				} else {
					testMap.put("method", "append");
					testMap.put("value", this.makeTestHTML(addItemCategory, addItemDataType, addItemDataName, addItemDataText));
				}
			}
		} else {
			if(addItemCategory.equals("NeedElements")) {
				testMap.put("method", "alert");
				testMap.put("value", "타겟을 먼저 지정해주세요.");
			} else {
				testMap.put("method", "append");
				testMap.put("value", this.makeTestHTML(addItemCategory, addItemDataType, addItemDataName, addItemDataText));
			}
		}
		
		return testMap;
	};
	
	// 테스트 항목 HTML 만들기 
	public String makeTestHTML(String addItemCategory, String addItemDataType, String addItemDataName,  String addItemDataText) {
		
		StringBuffer testString = new StringBuffer();
		String testHTML = "";
		
		testString.append("<div data-category='");
		testString.append(addItemCategory);
		testString.append("'><input type='");
		testString.append(addItemDataType);
		testString.append("' data-name='");
		testString.append(addItemDataName);
		
		if(addItemDataType.equals("button")) {
			testString.append("' value='");
		} else if(addItemDataType.equals("text")) {
			testString.append("' placeholder='");
		}
		
		testString.append(addItemDataText);
		testString.append("' />");
		testString.append("<input type='button' value='x' class='closeBtn' onclick='removeTestItems($(this));' /></div>");
		
		//System.out.println(testString);
		testHTML = testString.toString();
		
		return testHTML;
	}
	
}
