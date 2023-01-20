package chromeDriverTestTool.controller.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import chromeDriverTestTool.item.impl.TestItemImpl;
import chromeDriverTestTool.selenium.impl.TestSeleniumImpl;

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
//		NeedElementsCmdVO commandVO = new NeedElementsCmdVO();
//		TargetVO targetVO = new TargetVO();
//		
//		request.setAttribute("commandList", testItemImpl.getTestList(commandVO));
//		request.setAttribute("targetList", testItemImpl.getTestList(targetVO));
		response.setContentType("text/html;charset=UTF-8");
		
		fileList.clear();
		
		return "main";
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
	
	// testBoard testItem 추가하기
	public String setTestItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// step1: 파라미터 세팅
		String addItemCategory = request.getParameter("addItemCategory");
		String addItemDataType = request.getParameter("addItemDataType");
		String addItemDataName = request.getParameter("addItemDataName");
		String addItemDataText = request.getParameter("addItemDataText");
		boolean existItem = Boolean.parseBoolean(request.getParameter("existItem"));
		String lastItem = request.getParameter("lastItem");
		
		//System.out.println(addItemCategory);
		//System.out.println(addItemDataType);
		//System.out.println(addItemDataName);
		//System.out.println(existItem);
		//System.out.println(lastItem);

		// step2: 테스트 항목 매핑 후 출력
		Map<String, String> result = testItemImpl.getTestMap(addItemCategory, addItemDataType, addItemDataName, addItemDataText, existItem, lastItem);
		JSONObject resultJson = new JSONObject(result);
		String resultTestItem = resultJson.toString();
		//System.out.println(resultTestItem);
		
		return resultTestItem;
	}
	
	// 셀레니움 테스트
	public String doSeleniumTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// step1: 정보 세팅
		String driverPath = request.getParameter("driverPath");
		String testURL = request.getParameter("testURL");
		String testListStr = request.getParameter("testList");
		
		// step2: 테스트 세팅 및 크롬 접속
		TestSeleniumImpl testSeleniumImpl = new TestSeleniumImpl(driverPath, testURL);
		testSeleniumImpl.connectURL();
		
		// step3: 테스트 실행
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(testListStr);
		JSONArray jsonArray = (JSONArray)obj;
	
		try {
			for(int i=0;i<jsonArray.size();i++){
				JSONObject jsonObj = (JSONObject)jsonArray.get(i);
				testSeleniumImpl.runTest(jsonObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
}
