package chromeDriverTestTool.controller.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import chromeDriverTestTool.item.VO.NeedElementsCmdVO;
import chromeDriverTestTool.item.VO.TargetVO;
import chromeDriverTestTool.item.impl.TestItemImpl;

public class TestToolController {
	
	private static ArrayList<File> fileList = new ArrayList<File>(); // 싱글톤
	private static TestItemImpl testItemImpl = new TestItemImpl();
	
	public String main(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// 1. 크롬 드라이버 크롤링 
		Document doc = null;
		String url = "https://chromedriver.chromium.org/downloads";
		String str = "";

		try {
			doc = Jsoup.connect(url).get(); // URL에 해당하는 HTML 전체 문서 가져오기
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		List<Element> elements = doc.select("span > a > strong");
		for(Element element : elements) {
			str = str + "<tr><td><a href='" + element.parent().attr("href") + "'>" + element.text() + "</a></td></tr>";
		}
		System.out.println(str);
		request.setAttribute("driverUrlList", str);

		// 2. 내 pc 크롬 드라이버 위치 가져오기 // api가 좋지 않을까? 전체 새로고침 되니까
		String path = request.getParameter("path");
		System.out.println(path);
		str = "";

		if(path != null) {
			File f = new File(path);
			// "c:\project_testTool\chromeDriver\"
			if(search(f) == true) {
				for(int i = 0; i < fileList.size(); i++) {
					str = str + "<div>" + fileList.get(i) + "</div>";
				}
				System.out.println("dfd" + str);
				request.setAttribute("driverPath", str);
			} else {
				request.setAttribute("driverPath", "조회된 결과가 없습니다.");
			}
		}
		
		// 3. 테스트 리스트 가져오기
		NeedElementsCmdVO commandVO = new NeedElementsCmdVO();
		TargetVO targetVO = new TargetVO();
		
		request.setAttribute("commandList", testItemImpl.getTestList(commandVO));
		request.setAttribute("targetList", testItemImpl.getTestList(targetVO));
		response.setContentType("text/html;charset=UTF-8");
		
		fileList.clear();
		
		return "main";
	}
	
	public String doSelenium(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// step1: 정보 세팅
		String driverPath = request.getParameter("driverPath");
		String testURL = request.getParameter("testURL");
		String testListStr = request.getParameter("testList");
		
		try {
			testItemImpl.setDriverInfo(driverPath, testURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// step2: testArrStr 하나씩 정보 가져와서 run
		System.out.println(testListStr);
		
		// json type parsing
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(testListStr);
		JSONArray jsonArray = (JSONArray)obj;

		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObj = (JSONObject)jsonArray.get(i);
			System.out.print(jsonObj);
			
			System.out.println(jsonObj.get("movementType")); // 이런식으로 key값으로 value 조회 가능
			
		}
		
		return "hello";
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
