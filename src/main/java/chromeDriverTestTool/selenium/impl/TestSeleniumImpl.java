package chromeDriverTestTool.selenium.impl;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import chromeDriverTestTool.item.VO.TestItemVO;
import chromeDriverTestTool.selenium.service.TestSelenium;

public class TestSeleniumImpl implements TestSelenium {
	// WebDriver fields
	WebDriver driver = new ChromeDriver();
	String driverId = "webdriver.chrome.driver";
	String driverPath = "";
	String testURL = "";
	
	// 생성자
	public TestSeleniumImpl(String driverPath, String testURL) {
		this.driverPath = driverPath;
		this.testURL = testURL;
	}
	
	@Override
	public void connectURL() {
		System.setProperty(driverId, driverPath);
		driver.navigate().to("https://www.naver.com");
	}
	
	@Override
	public void runTest(JSONObject jsonObj) {
		TestItemVO item = new TestItemVO((String)jsonObj.get("dataText"), (String)jsonObj.get("dataName"), (String)jsonObj.get("dataCategory"));
		System.out.println(jsonObj);
		System.out.println(item.getDataText());
		System.out.println(item.getDataCategory());
		System.out.println(item.getDataName());
		
		// 아이템에 저장까지는 했고, 그 다음 실행은 어떻게?
		// DataCategory는 안 쓸 수 있으니 일단 보류
		// 밑에 일단은 임의로 테스트 메서드 만들기
		if(item.getDataCategory().equals("target")) {
			mapTarget(item.getDataName());
		} 
	}
	
	// 테스트 진행(일단 여기에다 쭉)
	@Override
	public void mapTarget(String target) {
		System.out.println(target);
		
		switch(target) {
		case "xPath": ;
			break;
		case "id": ;
			break;
		case "className": ;
			break;
		case "name": ;
			break;
		case "tagName": ;
			break;
		case "linkText": ;
			break;
		case "cssSelector": ;
			break;
		}
	}
	
	
	@Override
	public boolean getResult() throws Exception {
		System.out.println("getResult");
		return false;
	};
	
	@Override
	public void setSucceedCondition(String cond) { // or param type TestSucceedCondition
		System.out.println("setSucceed");
	};
	
}
