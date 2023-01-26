package chromeDriverTestTool.selenium.impl;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

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
		System.out.println(jsonObj);
		
		// target 처리
		// step1: json 가져오기 -> {dataCategory: "target or needElements...", dataName: "xpath or id or name...", dataText: "타겟내용"}
		// step2: WebElement에 담기 -> dataName에 따라 element = driver.findElement(By~~~) 달라짐 -> element를 동작 함수에 태워 보내기
		// String target = "xpath, cssSelector 등"; 
		// WebElement element = driver.findElement(By.xpath(target));
		
		// needEle 처리
		// step1: element 받기 
		// step2: json 가져오기 -> {dataCate: "needs", dataName: "inputSearchKeyword", dataText: "어쩌고"}
		// step2: dataCategory NeedElements로 연결
		// step3: dataName에 따라 분류 -> inputSearchKeyword, clickDouble 등. 이때 dataValue 등 파라미터로 가져가기
		// 		  이때, 리턴있는 애들은 상태값하고 같이 내보내기
		
		// freeNeedEle 처리
		// step1: json 가져오기 -> {dataCategory: freeNeed.., dataName: getTitle, dataText: ""}
		// step2: dataName에 맞는 함수 실행(파라미터 필요한 애들은 가져가고, 리턴있는 애들은 상태값하고 같이 내보내고)
		
		// etcEle 처리(?)
		// 드래그 앤 드롭은 기타로 빼자 변수 2개 필요함! 얘말고는 일단 안 보임!
		
		
		
		
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
