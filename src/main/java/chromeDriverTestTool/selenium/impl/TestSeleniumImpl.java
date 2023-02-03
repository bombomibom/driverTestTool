package chromeDriverTestTool.selenium.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
		System.setProperty(driverId, driverPath);
	}
	
	public TestSeleniumImpl() {
		
	}
	
	@Override
	public void runTest(JSONObject jsonObj) {
		driver.get(testURL);
		
		// test시 생성자 호출되는 구간마다 페이지 뜨는 거 해결 필요! - 230203
		
		TestItemVO item = new TestItemVO((String)jsonObj.get("dataText"), (String)jsonObj.get("dataName"), (String)jsonObj.get("dataCategory"));
		System.out.println(jsonObj);
		System.out.println(item.getDataText());
		System.out.println(item.getDataCategory());
		System.out.println(item.getDataName());
		
		String functionName = item.getDataName();
		String parameterValue = item.getDataText();
		Object obj = new TestSeleniumImpl();
		int result = 0;
		
		try {
			Class<?> cls = Class.forName(obj.getClass().getName());
			Method m = cls.getDeclaredMethod(functionName, String.class);
			result = (int) m.invoke(obj, parameterValue);
		} 
		catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException from Class.forName()");
		}
		catch (NoSuchMethodException e) {
			System.out.println("NoSuchMethodException from getDeclaredMethod()");
		}
		catch (InvocationTargetException e) {
			System.out.println("InvocationTargetExceptionn from invoke()");
		}
		catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException from invoke()");
		}
		finally {
			System.out.println("functionA test success!");
		}
		
	}
	
	public int targetXpath(String target) {
		driver.findElement(By.xpath(target));
		return 1; // 조건 달아야 함
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
