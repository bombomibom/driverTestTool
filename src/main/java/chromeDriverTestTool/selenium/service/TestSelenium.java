package chromeDriverTestTool.selenium.service;

import org.json.simple.JSONObject;

public interface TestSelenium {
	// 크롬 접속
	public void connectURL();
	
	// 셀레니움 실행
	public void runTest(JSONObject jsonObj);
	
	// 셀레니움 실행 결과 가져오기
	public boolean getResult() throws Exception;
	
	// 성공결과 관리?
	public void setSucceedCondition(String cond);
	
}
