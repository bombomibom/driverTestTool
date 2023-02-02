package chromeDriverTestTool.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chromeDriverTestTool.core.ServletMapping;

/**
 * 
 *
 * @author bomi, Kim
 * @since 2022.12.21
 * 
 */
public class ServletDefault extends HttpServlet {

	ServletMapping mapping = new ServletMapping();
	
	/**
	 * 초기화
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("SeleniumTest init");
        super.init();
	}
	
	/**
	 * servlet Get 통신
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Default GET");
		
		String uri = request.getRequestURI();
		Object controller = mapping.controllerMapping.get(uri);
		Method method = mapping.methodMapping.get(uri);

		try {
			if(controller == null || method == null) throw new Exception("no controller or method found for uri: " + uri);
			
			String jsp = (String) method.invoke(controller, request, response);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/" + jsp + ".jsp");
			rd.include(request, response);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * servlet Post 통신
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Default POST");

		String uri = request.getRequestURI();
		System.out.println("uri: " + uri);
		Object controller = mapping.controllerMapping.get(uri);
		Method method = mapping.methodMapping.get(uri);
		
		System.out.println(controller);
		System.out.println(method);
		
		try {
			if(controller == null || method == null) throw new Exception("no controller or method found for uri: " + uri);
			
			String jsp = (String) method.invoke(controller, request, response);
			System.out.println(jsp);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/" + jsp + ".jsp");
			System.out.println(rd);
			
			rd.include(request, response);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
