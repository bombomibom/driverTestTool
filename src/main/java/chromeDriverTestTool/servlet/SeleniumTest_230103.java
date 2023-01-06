package chromeDriverTestTool.web;

import java.io.IOException;

import chromeDriverTestTool.service.impl.SeleniumServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 셀레니움 활용 브라우저 테스트 서비스
 *
 * @author bomi, Kim
 * @since 2022.12.21
 * 
 */
public class SeleniumTest extends HttpServlet {

	/**
	 * 초기화
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("SeleniumTest init");
        super.init();
	}
	
	/**
	 * POST 방식으로 데이터 받아온 후 응답
	 *
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 변수 저장
		String driverPath = request.getParameter("driverPath");
		String testURL = request.getParameter("testURL");
		String testArrStr = request.getParameter("testList");
		
		this.doSelenium(driverPath, testURL, testArrStr);
	}

	/**
	 * selenium 실행
	 *
	 */
	public void doSelenium(String driverPath, String testURL, String testArrStr) {
		// jsonObject 변환
		JSONArray testArr = new JSONArray();
		testArr = JSONArray.fromObject(testArrStr);

		// selenium 세팅 및 URL 접속
		SeleniumServiceImpl seleniumServiceImpl = new SeleniumServiceImpl(driverPath, testURL);
    	seleniumServiceImpl.connectURL();
		
		// selenium 동작 실행
	    for(int i = 0; i < testArr.length(); i++) {
	    	JSONObject testObj = testArr.getJSONObject(i);
	    	String actionType = (String)testObj.get("movementName");
	    	String etc = (String)testObj.get("movement");
	    	String path = (String)testObj.get("path");

	    	// 동작. 개선 필요
	    	if(actionType.equals("keyword")) {
	    		seleniumServiceImpl.inputSearchKeyword(path, etc);
			} else if(actionType.equals("button") && etc.equals("ENTER")) {
				seleniumServiceImpl.clickEnter(path);
			} else if(actionType.equals("button") && etc.equals("ESC")) {
				seleniumServiceImpl.clickEscape(path);
			}
	    }

	    // 브라우저 종료
	    //driver.close();
	}
}
