package chromeDriverTestTool.driver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 내 PC 드라이버 경로 확인
 *
 * @author bomi, Kim
 * @since 2022.12.21
 * 
 */
public class DriverPath extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ArrayList<File> fileList = new ArrayList<File>();
	
	/**
	 * 초기화
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("DriverPath init");
        super.init();
	}
	
	/**
	 * 크롬 드라이버 경로 출력
	 * 참고 : 일단 doGet으로 진행 Show 관련 Method 따로 안 보여서 !
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		fileList.clear();
		// 특정 폴더 내 파일 있는지 확인
		String path = request.getParameter("path");
		System.out.println(path);
		File f = new File(path);
		// "c:\project_testTool\chromeDriver\"
		if(search(f) == true) {
			String fileListStr = "";
			for(int i = 0; i < fileList.size(); i++) {
				fileListStr = fileListStr + "<div>" + fileList.get(i) + "</div>";
			}
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(fileListStr);
		} else {
			response.getWriter().print("조회된 결과가 없습니다.");
		}
	}
	
	// 하위 전체 검색
	public static boolean search(File f) { // try catch 추가
		boolean isExist = false;

		if(f.listFiles() != null) {
			for(File file : f.listFiles()) {
				if(file.isDirectory()) { // 폴더일 때 재귀
					search(file);
				}
				if(file.getName().contains("chromedriver.exe")) {
					isExist = true;
					System.out.println(file);
					fileList.add(file);
					System.out.println(fileList.size());
				}
			}
		}
		
		return isExist;
		
	}
	
}
