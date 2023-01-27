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
