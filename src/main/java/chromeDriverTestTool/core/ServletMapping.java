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
			
			controllerMapping.put("/seleniumTest.doAPI", new TestToolController());
			methodMapping.put("/seleniumTest.doAPI", TestToolController.class.getDeclaredMethod("doSelenium", HttpServletRequest.class, HttpServletResponse.class));
			
			controllerMapping.put("/seleniumItem.doAPI", new TestToolController());
			methodMapping.put("/seleniumItem.doAPI", TestToolController.class.getDeclaredMethod("doSeleniumItem", HttpServletRequest.class, HttpServletResponse.class));
			
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
