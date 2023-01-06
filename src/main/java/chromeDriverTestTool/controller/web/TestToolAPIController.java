package chromeDriverTestTool.controller.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class TestToolAPIController {
	
	public String doSelenium(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "";
	}
	
//	private static ArrayList<File> fileList = new ArrayList<File>();
//
//	public String getDriverPath(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		System.out.println("getDriverPath 통과");
//		System.out.println("4");
//		
//		String path = request.getParameter("path");
//		System.out.println(path);
//		
//		File f = new File(path);
//		// "c:\project_testTool\chromeDriver\"
//		if(search(f) == true) {
//			String fileListStr = "";
//			for(int i = 0; i < fileList.size(); i++) {
//				fileListStr = fileListStr + "<div>" + fileList.get(i) + "</div>";
//			}
//			return fileListStr;
//		} else {
//			return "조회된 결과가 없습니다.";
//		}
//	}
//
//	// 하위 전체 검색
//	public static boolean search(File f) { // try catch 추가
//		boolean isExist = false;
//
//		if(f.listFiles() != null) {
//			for(File file : f.listFiles()) {
//				if(file.isDirectory()) { // 폴더일 때 재귀
//					search(file);
//				}
//				if(file.getName().contains("chromedriver.exe")) {
//					isExist = true;
//					System.out.println(file);
//					fileList.add(file);
//					System.out.println(fileList.size());
//				}
//			}
//		}
//		
//		return isExist;
//		
//	}
	
}
