package chromeDriverTestTool.item.impl;

import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import chromeDriverTestTool.condition.TestSucceedCondition;
import chromeDriverTestTool.item.service.TestItem;
import net.sf.json.JSONObject;

public class TestItemImpl implements TestItem {
	
	// WebDriver fields
	WebDriver driver;
	String driverId = "webdriver.chrome.driver";
	String driverPath = "";
	String testURL = "";

	public JSONObject getTestList(Object fieldObj) {
		JSONObject jsonObj = new JSONObject();

		for(Field field : fieldObj.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				jsonObj.put(field.getName(), field.get(fieldObj));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		System.out.println(jsonObj);
		return jsonObj;
	}
	
	public void setDriverInfo(String driverPath, String testURL) {
		this.driverPath = driverPath;
		this.testURL = testURL;
	}
	
	public TestItem getCurrentItem() {
		return null;
	}

	public TestItem getPrevItem() {
		return null;
	}
	
	public TestItem getNextItem() {
		System.out.println("getPrev");
		return null;
	};
	
	public void run() throws Exception {
		System.out.println("run");
	};
	
	public boolean getResult() throws Exception {
		System.out.println("getResult");
		return false;
	};
	
	public void setSucceedCondition(TestSucceedCondition cond) {
		System.out.println("setSucceed");
	};
}
