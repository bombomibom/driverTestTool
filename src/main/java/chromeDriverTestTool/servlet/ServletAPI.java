package chromeDriverTestTool.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chromeDriverTestTool.core.ServletMapping;

/**
 * 셀레니움 활용 브라우저 테스트 서비스
 *
 * @author bomi, Kim
 * @since 2022.12.21
 * 
 */
public class ServletAPI extends HttpServlet {

	ServletMapping mapping = new ServletMapping();
	
	/**
	 * 초기화
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("SeleniumTest init");
        super.init();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("API GET");
	}
	
	/**
	 * POST 방식으로 데이터 받아온 후 응답
	 *
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("API POST");
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		Object controller = mapping.controllerMapping.get(uri);
		
		Method method = mapping.methodMapping.get(uri);
		
		System.out.println(controller);
		System.out.println(method);
		
		try {
			if(controller == null || method == null) throw new Exception("no controller or method found for uri: " + uri);
			
			String result = (String) method.invoke(controller, request, response);
			System.out.println(result);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(result);
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			response.getWriter().print(e.getMessage());
		}
		
	}

}
