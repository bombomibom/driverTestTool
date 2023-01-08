package chromeDriverTestTool.temp1.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import chromeDriverTestTool.temp1.SeleniumService;

/**
 * 셀레니움 활용 브라우저 테스트 서비스
 *
 * @author bomi, Kim
 * @since 2022.12.21
 * 
 */
public class SeleniumServiceImpl implements SeleniumService {

	// WebDriver
	WebDriver driver = new ChromeDriver();
	String driverId = "webdriver.chrome.driver";
	String driverPath = "";
	String testURL = "";
	
	// 생성자
	public SeleniumServiceImpl(String driverPath, String testUrl) {
		this.driverPath = driverPath;
		this.testURL = testUrl;
	}
	
	@Override
	public void connectURL() {
		System.setProperty(driverId, driverPath);
		driver.navigate().to(testURL);
	}
	
	@Override
	public void inputSearchKeyword(String xPath, String searchKeyword) {
		driver.findElement(By.xpath(xPath)).sendKeys(searchKeyword);
		//WebElement targetName = driver.findElement(By.xpath("xdlfjksd"));
		//targetName.sendKeys(searchKeyword);
	}

	@Override
	public void clickEnter(String xPath) {
		driver.findElement(By.xpath(xPath)).sendKeys(Keys.ENTER); 
	}
	
	@Override
	public void clickEscape(String xPath) {
		driver.findElement(By.xpath(xPath)).sendKeys(Keys.ESCAPE); 
	}
	
	// step 1
    // declaration and instantiation of objects/variables  
	// 사용자마다 driver 위치가 다르므로 파라미터로 받은 후, 전역변수 driver에 setting 되게 설정
	WebDriver driver2 = new ChromeDriver();
	public void setting(String driverType, String driverPath) {
		System.setProperty(driverType, driverPath);
	}
	
	// step 2
	// lanuch Website
	public void launchWebSite(String url) {
		driver.navigate().to(url);
	}
	
	// step 3
	// findElement
	public void findElement(String path) {
		// target 종류
		// id, name, className, tagName, linkText, partialLinkText, xpath
		// target 가져오는 건 String 파라미터값만 있으면 됨.
		// 그리고 By 뒤 값은 targetType으로 분류하기
		// 이 애들 동적으로 할당할 순 없는지 고민. by 뒤 값
		
		//driver.findElement(By.className(path))
		//driver.findElement(By.cssSelector(path))
		//driver.findElement(By.id(path))
		
	}

	// step 4
	// movement
	public void movement() {
		// driver.get(): url 페이지 오픈
		// driver.navigate.to(): url 페이지 오픈. get과 다른 점은 히스토리와 쿠키가 저장되어 앞으로 가기, 뒤로 가기 등이 된대!
		// driver.close(): 현재 보고 있는 탭만 닫고 나머지 탭은 그대로 유지
		// driver.quit(): 모든 탭 닫기
		// driver.findElement().sendKeys(); : 검색어의 경우 그냥 str, button은 Keys.ENTER 이런식
		// driver.findElement().clear(); : 특정 input 내용 삭제
		// driver.navigate().back(); // 뒤로가기
		// driver.navigate().forward(); // 앞으로가기
		// driver.switchTo().window("windowName"); // 다른 윈도우
		// driver.switchTo().frame("frameName");
		
		// 드래그 앤 드롭
		// WebElement element = driver.findElement(By.name("source"));  
		// WebElement target = driver.findElement(By.name("target"));  
		//(new Actions(driver)).dragAndDrop(element, target).perform(); 
		
		// dropdown
		// Select dropdown = new Select(driver.findElement(By.id("testingDropdown")));  
		// dropdown.selectByVisibleText("Database Testing");  
		
		// Handling Alerts
		
		// scrolling
		
		// 
	
		// 동작들 찾아서 정리 & vo 저장 & 화면 뿌리기 + selenium IDE 참고
		// 화면에 뿌린 거 가져오는 것까지 진행 후 run() 부분 어떻게 처리할지 고민
		
		// 금일: 동작, 화면 RUN 까지 진행
		// 상태값 가져오기
		// 가져와서 SUCCEED 처리
		// 동작 돌아가는 거까지 끝!!
		// TRY, CATCH, INTERFACE 한 번씩만 더 검토
		
		// 내일 검사?
		
		// 담주는 자바의 정석 뽀개보자!!!!
	}
	
}
