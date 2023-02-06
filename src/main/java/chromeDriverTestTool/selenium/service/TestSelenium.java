package chromeDriverTestTool.selenium.service;

import org.json.simple.JSONArray;

public interface TestSelenium {
	
	// 셀레니움 접속
	public void connectDriver();
	
	// 셀레니움 실행
	public void runTest(JSONArray jsonArray);
	
	// 셀레니움 실행 결과 가져오기
	public boolean getResult() throws Exception;
	
	// 성공결과 관리?
	public void setSucceedCondition(String cond);
	
}
