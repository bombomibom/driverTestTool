package chromeDriverTestTool.selenium.impl;

import java.lang.reflect.Field;
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
	}
	
	public TestSeleniumImpl() {
		
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
		
		TestSeleniumImpl testImpl = new TestSeleniumImpl();
		System.out.println("getName()      : " + testImpl.getClass().getName());
	    System.out.println("getSimpleName()  : " + testImpl.getClass().getSimpleName());
	    System.out.println("===========getMethods()===========");
	    for(Method method : testImpl.getClass().getMethods()){
	        System.out.println("method          : " + method.getName());
	    }
	    System.out.println("===========getFields()===========");
	    for(Field field : testImpl.getClass().getFields()){
	        System.out.println("field           : " + field.getName());
	    }
	    
	    Class c = TestSeleniumImpl.class;
	    try {
			Method m = c.getDeclaredMethod(item.getDataName());
			System.out.println(m);
			
		    m = c.getDeclaredMethod("anotherMethod", int.class);
	        System.out.println(m);
			
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
//		String functionNameA = item.getDataName();
//		System.out.println(functionNameA);
//		
//		Object obj = new TestSeleniumImpl();
//		try {
//			Class<?> cls = Class.forName(obj.getClass().getName());
//			System.out.println(cls);
//			Method m = cls.getDeclaredMethod(functionNameA);
//			System.out.println(m);
//			m.invoke(obj);
//		} 
//		catch (ClassNotFoundException e) {
//			System.out.println("ClassNotFoundException from Class.forName()");
//		}
//		catch (NoSuchMethodException e) {
//			System.out.println("NoSuchMethodException from getDeclaredMethod()");
//		}
//		catch (InvocationTargetException e) {
//			System.out.println("InvocationTargetExceptionn from invoke()");v
//		}
//		catch (IllegalAccessException e) {
//			System.out.println("IllegalAccessException from invoke()");
//		}
//		finally {
//			System.out.println("functionA test success!");
//		}
		
	}
	
	// 일단 테스트 항목 아래에 쭉 늘어놓자!
	public void targetXpath(String target) {
		driver.findElement(By.xpath(target));
		System.out.println("ee");
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
