package chromeDriverTestTool.selenium.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import chromeDriverTestTool.item.VO.TestItemVO;
import chromeDriverTestTool.selenium.service.TestSelenium;

public class TestSeleniumImpl implements TestSelenium {
	// WebDriver fields
	WebDriver driver = null;
	String driverId = "webdriver.chrome.driver";
	String driverPath = "";
	String testURL = "";
	WebElement element = null;
	

	// 셀레니움 정보 세팅
	public TestSeleniumImpl(String driverPath, String testURL){
		this.driverPath = driverPath;
		this.testURL = testURL;
	} 
	
	public TestSeleniumImpl(String driverPath, String testURL, WebDriver driver, WebElement element){
		this.driverPath = driverPath;
		this.testURL = testURL;
		this.driver = driver;
		this.element = element;
	} 
	
	public TestSeleniumImpl() {
		
	}
	
	// 셀레니움 접속
	@Override
	public void connectDriver() {
		driver = new ChromeDriver();
		System.setProperty(driverId, driverPath);
		driver.navigate().to(testURL);
	}
	
	// 셀레니움 실행
	@Override
	public void runTest(JSONArray jsonArray) {
		// 20230206
		// controller에서 for문 돌리던 거 impl로 다시 뺀 상태
		// WebElement 저장하고 다음 JSON에서 확인하기 위함
		// 근데 두 번째 clickEnter 부분에서 element 확인 안 돼서 조치 취해야 함 element.sendKeys() 이렇게!
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObj = (JSONObject)jsonArray.get(i);
			TestItemVO item = new TestItemVO((String)jsonObj.get("dataText"), (String)jsonObj.get("dataName"), (String)jsonObj.get("dataCategory"));
			
			System.out.println(jsonObj);
			System.out.println(item.getDataText());
			System.out.println(item.getDataCategory());
			System.out.println(item.getDataName());
			
			String functionName = item.getDataName();
			String parameterValue = item.getDataText();
			System.out.println(this.driverPath);
			System.out.println(this.testURL);
			System.out.println(this.driver);
			System.out.println(this.element);
			
			WebElement result = null;
			
			Object obj = new TestSeleniumImpl(this.driverPath, this.testURL, this.driver, this.element);
			try {
				Class<?> cls = Class.forName(obj.getClass().getName());
				Method m = cls.getDeclaredMethod(functionName, String.class);
				result = (WebElement) m.invoke(obj, parameterValue);
			} 
			catch (ClassNotFoundException e) {
				System.out.println("ClassNotFoundException from Class.forName()");
			}
			catch (NoSuchMethodException e) {
				System.out.println("NoSuchMethodException from getDeclaredMethod()");
			}
			catch (InvocationTargetException e) {
				System.out.println("InvocationTargetExceptionn from invoke()");
				e.getTargetException().printStackTrace();
			}
			catch (IllegalAccessException e) {
				System.out.println("IllegalAccessException from invoke()");
			}
			finally {
				System.out.println("functionA test success!");
			}
		}
	}
	
	public WebElement targetXpath(String target) {
		this.element = driver.findElement(By.xpath(target));
		System.out.println(this.element);
		return this.element; // 조건 달아야 함
	} 
	
	public WebElement clickEnter(String target) {
		this.element.sendKeys(Keys.ENTER);
		return this.element; 
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
