package chromeDriverTestTool.item.service;

import java.util.Map;

/**
 * 2023.01.03
 * @author 김보미
 * 테스트 항목 인터페이스
 */
public interface TestItem {
	// 테스트 매핑 항목 가져오기 
	public Map<String, String> getTestMap(String addItemCategory, String addItemDataType, String addItemDataName, String addItemDataText, boolean existItem, String lastItem);
	
	// 테스트 항목 HTML 만들기
	public String makeTestHTML(String addItemCategory, String addItemDataType, String addItemDataName, String addItemDataText);

	
}
