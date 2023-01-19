package chromeDriverTestTool.item.service;

import java.util.Map;

import net.sf.json.JSONObject;

/**
 * 2023.01.03
 * @author 김보미
 * 테스트 항목 인터페이스
 */
public interface TestItem {
	
	// 테스트 아이템 가져오기(DB연동 후 진행)
	public JSONObject getItemList(Object fieldObj);	

	// 테스트 매핑 항목 가져오기
	public Map<String, String> getTestMap(String addItemCategory, String addItemDataType, String addItemDataName, String addItemDataText, boolean existItem, String lastItem);
	
	// 테스트 항목 HTML 만들기
	public String makeTestHTML(String addItemCategory, String addItemDataType, String addItemDataName,  String addItemDataText);
	
	public void setDriverInfo(String driverPath, String testURL);
	
	public void run() throws Exception;
	public boolean getResult() throws Exception;
	
	public void setSucceedCondition(String cond);

}
