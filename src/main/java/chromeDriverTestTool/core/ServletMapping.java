package chromeDriverTestTool.core;

import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chromeDriverTestTool.controller.web.TestToolController;

public class ServletMapping {

	public Map<String, Object> controllerMapping;
	public Map<String, Method> methodMapping;
	
	public ServletMapping() {
		controllerMapping = new Hashtable<>();
		methodMapping = new Hashtable<>();

		try {
			controllerMapping.put("/main.do", new TestToolController());
			methodMapping.put("/main.do", TestToolController.class.getDeclaredMethod("main", HttpServletRequest.class, HttpServletResponse.class));
		
			controllerMapping.put("/setTestItem.doAPI", new TestToolController());
			methodMapping.put("/setTestItem.doAPI", TestToolController.class.getDeclaredMethod("setTestItem", HttpServletRequest.class, HttpServletResponse.class));
			
			controllerMapping.put("/doSeleniumTest.doAPI", new TestToolController());
			methodMapping.put("/doSeleniumTest.doAPI", TestToolController.class.getDeclaredMethod("doSeleniumTest", HttpServletRequest.class, HttpServletResponse.class));
			
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
