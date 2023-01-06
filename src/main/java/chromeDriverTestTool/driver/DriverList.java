package chromeDriverTestTool.driver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 크롬 드라이버 리스트 출력 (수정 필요 testItem 처럼)
 *
 * @author bomi, Kim
 * @since 2022.12.21
 * 
 */
public class DriverList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ArrayList<File> fileList = new ArrayList<File>();
	
	/**
	 * 초기화
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("DriverList init");
        super.init();
	}
	
	/**
	 * 화면을 출력한다.
	 *
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		// url 가져오기
		request.setAttribute("driverUrlList", getDriverUrl());

		// url 출력
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * chrome driver url을 가져온다.
	 *
	 */
	public static String getDriverUrl() { // try catch 추가
		String url = "https://chromedriver.chromium.org/downloads";
		Document doc = null;

		try {
        	// URL에 해당하는 HTML 전체 문서 가져오기
			doc = Jsoup.connect(url).get();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		String title = "";
		List<Element> elements = doc.select("span > a > strong");
		for(Element element : elements) {
			title = title + "<tr><td><a href='" + element.parent().attr("href") + "'>" + element.text() + "</a></td></tr>";
		}
		
		System.out.println(title);
		
		return title;
		
	}

}
