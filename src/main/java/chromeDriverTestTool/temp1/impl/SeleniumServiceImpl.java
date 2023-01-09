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
		// Locators
		// id, name, className, tagName, linkText(링크가 걸려있는 text. click here 같은. 두 개 이상 있으면 첫 번째 걸로 실행됨), partialLinkText(부분적으로 일치하면 찾음), xpath
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
		
		// target 필요한 메서드, target 필요없는 메서드, 값을 저장해서 가져와야 하는 메서드 
		
		// Browser Commands
		// driver.get(): url 페이지 오픈 
		// driver.getTitle(); // 현재 페이지의 제목
		// driver.findElement("").getText(); // 텍스트 가져오기
		// driver.getCurrentUrl(); // 현재 url 
		// driver.getPageSource(); // 페이지 소스코드를 String 값으로 반환
		// driver.close(): 현재 보고 있는 탭만 닫고 나머지 탭은 그대로 유지
		// driver.quit(): 모든 탭 닫기
		// driver.manage().window().maximize(); // this code may not work with Selenium 3 jars
		// driver.manage().window().setPosition(new Point(0, -1000)); // minimize
		
		// Navigation Commands
		// driver.navigate.to(): url 페이지 오픈. get과 다른 점은 히스토리와 쿠키가 저장되어 앞으로 가기, 뒤로 가기 등이 된대!
		// driver.navigate().back(); // 뒤로가기
		// driver.navigate().forward(); // 앞으로가기
		// driver.navigate().refresh(); // 새로고침
		
		// Find Element
		// driver.findElement().sendKeys(); : 검색어의 경우 그냥 str, button은 Keys.ENTER 이런식
		// driver.findElement().clear(); : 특정 input 내용 삭제
		
		// iFrame
		// driver.switchTo().frame("frameName"); // frame 이름으로 가져오기
		// driver.switchTo().frame(1); // 인덱스로 가져오기
		// driver.switchTo().frame(ParentFrame).switchTo().frame(ChildFrame); // frame 두 번 이동하기
		// driver.switchTo().defaultContent(); // get back to the main (or most parent) frame
		
		// Window
		// driver.switchTo().window("windowName"); // 열려있는 다른 창으로 이동. 이때 getTitle로 해당 창이 가지고 있는 타이틀을 확인해서 이동하는 방법이 있음. 참고 url: https://coronasdk.tistory.com/779
		
		// Event Commands
		// driver.findElement().submit(); // 양식제출
		// driver.findElement().getAttribute("id"); // attr 가져오기
		// WebElement element = driver.findElement(By.id("SubmitButton")); Dimension dimensions = element.getSize(); -> 이후 dimensions.height, dimensions.width  
		// WebElement element = driver.findElement(By.id("SubmitButton")); Point point = element.getLocation(); -> point.x, point.y
		
		// Wait
		// 1. Thread.sleep
		// 	  Thread.sleep(5000);
		//    try {
        //		Thread.sleep(5000);
		// 	  } catch (InterruptedException e) {
        //		e.printStackTrace();
		//    } finally {
        //		driver.quit();
    	//	  }
		// 2. WebDriverWait
		// 	  WebDriver driver = new ChromeDriver();
		// 	  WebDriverWait myWaitVar = new WebDriverWait(driver, 10);
		//    myWaitVar.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName"))); // 위 기다리는 명령어를 userName 입력 후에 진행되게!. 이 클래스가 로딩될 때까지(화면이 로딩될 때까지) 대기! 똑같은 대기이나 sleep이 드는 시간이 더 많음
		//    driver.findElement(By.id("userName")).sendKeys("tutorial");
		// 3. PageLoadTimeout 
		//    driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		// 4. SetScriptTimeout
		//    driver.manage().timeouts().setScriptTimeout(500, TimeUnit.SECONDS);
		
		// ExpectedConditions(WebDriverWait.until() 메서드와 함께 사용할 수 있는 조건)
		// myWaitVar.until(ExpectedConditions.alertIsPresent()) != null { }  // 경고 상자가 표시 될 때까지 대기
		// myWaitVar.until(ExpectedConditions.elementToBeClickable(By.id("userName")) // element 활성화 되기 전까지 대기
		// myWaitVar.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt() // 지정된 프레임이 이미 사용 가능할 때까지 대기 후 자동 전환
		// 그외에도 아래 참고 url에 많음
		// 참고 url: https://stqatools.com/selenium-wait-commands/
		
		// Conditions(조건)
		// driver.findElement().isDisplayed(); // display 여부 확인 활성화시 true
		// driver.findElement().isSelected(); // 라디오 버튼, 체크박스, 드롭다운 같은 형대에서 선택되어있는지 확인
		// driver.findElement().isEnabled(); // 웹 요소가 활성화 또는 비활성화 되었을 떄 boolean 반환 --> 근데 element에 enabled or disabled 요소가 있어야 사용 가능
		
		// DragAndDrop
		// WebElement element = driver.findElement(By.name("source"));  
		// WebElement target = driver.findElement(By.name("target"));  
		// Actions act = new Actions(driver);  
		// act.dragAndDrop(from,to).build().perform();   
		// 참고 url: https://testmanager.tistory.com/170, https://stqatools.com/selenium-drag-and-drop/
		
		// Dropdown
		// Select dropdown = new Select(driver.findElement(By.id("testingDropdown")));  
		// dropdown.selectByVisibleText("Database Testing"); // Text() 안 요소 선택
		// dropdwon.deselectByVisibleText(); 				 // 취소
		// dropdown.selectByIndex(1); 						 // 인덱스로 선택
		// dropdown.selectByValue() or deselectByValue()	 // 값으로?
		// 이 외에도 여러 개 있음
		// 참고 url: https://stqatools.com/selenium-dropdown/
		
		// CheckBox or RadioBtn
		// int a = driver.findElements(By.xpath()).size();
		// for문{ driver.findElements(By.xpath()).get(2).sendKeys() }    // 1. 한 개 - 인덱스 지정 (driver.switchTo().window(~.get(0));
		// By.xpath("//input[@value='Banana']") 						// 2. 한 개 - 해당하는 것 하나만 지정
		// boolean isSelected_status = 변수명.isSelected(); 				// 3. 전체 선택 - 체크 안 되어 있으면!
		// for(WebElement el : els){ if(!el.isSelected){el.check()}}
		// isSelected()로 체크!
		// 참고 url: https://stqatools.com/selenium-checkbox-radio-button/
		
		// Alerts
		// 이거 쓸 때 wait.until(ExpectedConditions.alertIsPresent()); 이렇게 wait 걸어줘야 에러 없이 부드럽게 진행
		// driver.switchTo().alert().dismiss(); // 창닫기
		// driver.switchTo().alert().accept(); // 확인
		// driver.switchTo().alert().getText(); // 창 text 가져오기 
		// driver.switchTo().alert().sendKeys("Text"); // 값 입력
		// 참고 url: https://yukifox.tistory.com/entry/selenium%EC%97%90%EC%84%9C-Alert-%EC%A0%9C%EC%96%B4-%EB%B0%A9%EB%B2%95

		// if, else, for 
		// 위 alert 같은 애들 있을 때 필요!
		// 또, 이럴 때도 사용 페이지 내 a링크 전부 가져와서 돌려보기 
		// List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		// for(WebElement link:allLinks){
		// 		if(link.getText().equals("Create a new account"){ System.out.println(link.getText()); })
		// 		또는 if 문에 공사중인 것과 활성화된 것 등 구분해서 가져올 수도 있지
		// }
		// 참고 url: https://testmanager.tistory.com/118
		
		// Scrolling
		// JavascriptExecutor js = (JavascriptExecutor) driver; 
		// js.executeScript("window.scrollBy(0, 1000)"); 					   // 1. scroll down the page by 1000 pixel vertical
		// WebElement Element = driver.findElement(By.linkText("Linux"));
		// js.executeScript("arguments[0].scrollIntoView();", Element); 	   // 2. scrollIntoView()는 위에서 언급한 요소가 전체보기로 나타날 때까지 페이지를 스크롤
		// js.executeScript("window.scrollTo(0, document.body.scrollHeight)"); // 3. 페이지 끝까지 스크롤. 뒤 body는 웹 페이지 전체 높이를 반환 
		// 참고 url: https://testmanager.tistory.com/173, https://stqatools.com/selenium-scroll-page/
		
		// Double Click
		// Actions action = new Actions(driver);
		// WebElement element = driver.findElement(By.id("elementId"));
		// action.doubleClick(element).perform();
		
		// Right Click
		// https://stqatools.com/selenium-right-click/
		
		// Keyboard Event
		// keyDown, keyUp, sendKeys
		// 아래 로봇하고도 연관해서 할 수 있음
		// action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
		// https://stqatools.com/selenium-keyboard-events/
		
		// RobotClass
		// method: keyPress, mousePress, mouseMove, keyRelease, mouseRelease
		// https://stqatools.com/selenium-robot-class/
		
		// MouseHover
		// WebElement elementToHover = driver.findElement(By.id("elementToHover "));
		// WebElement elementToClick = driver.findElement(By.id("elementToClick "));
		// Actions action = new Actions(driver);
		// action.moveToElement(elementToHover).click(elementToClick).build().perform();
		// https://stqatools.com/selenium-mouse-hover-action/
		
		// ToolTip
		// mousehover와 비슷하게 처리
		// builder.moveToElement(Del_btn_tooltip).perform();
		// https://stqatools.com/selenium-tool-tip/
		
		// Catching Exceptions
		// isEnabled, isDisplayed, isSelected는 요소가 이미 페이지에 있다고 가정. 없을 때 에러 뜨니까 캐치 타입은 옆과 같음 try{} catch(NoSuchElementException nsee){nsee.toString();}
		// 대기의 경우엔 catch(TimeoutException toe){toe.toString()}
		
		// AssertAndVerify
		// 해당 element 있는지 없는지 체크용도. 일단 헷갈리니까 이건 나중에 추가하자
		// assertTrue(driver.getPageSource().contains("stqatools"));
		// https://stqatools.com/selenium-assert-and-verify/
		
		// Tab
		// 1번 방법
		// driver.switchTo().window(tabs.get(1));
		// driver.close();
		// driver.switchTo().window(tabs.get(0));
		// 2번 방법
		// left -> right
		// Actions action = new Actions(driver);
		// action.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).build().perform();
		// right -> left
		// action.KeyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendKeys(Keys.TAB).build().perform();
		
		// ScreenShot
		// maximize -> use File library, FileUtils library 
		// https://stqatools.com/selenium-screenshot/
		
		// JavaScriptExecutor
		// alert 생성, innerText 가져오기, location 이동, value 넣기, click 하기, 새로고침, title 가져오기, scroll 하기, 끝까지 scroll 하기, 특정부분까지 scroll 하기, 뒤로가기, 앞으로가기
		// https://stqatools.com/selenium-javascriptexecutor/
		
		// Desired Capability 
		// 이해 안 됨 https://stqatools.com/selenium-desired-capability-marionette/
		
		// Cookie
		// getCookies, GetCookieNamed, addCookie, deleteCookie, deleteCookieNamed, deleteAllCookies
		// https://stqatools.com/selenium-cookie/
		
		// Option
		//   ChromeOptions options = new ChromeOptions(); 
		// - options.addArguments("--start-maximized"); 전체화면으로 실행
		// - options.addArguments("--disable-popup-blocking"); 팝업 무시
		// - options.addArguments("--disable-default-apps"); 기본앱 사용안함
		// - options.setExperimentalOption("mobileEmulation", map);
		// 참고 url: http://dbcafe.co.kr/wiki/index.php/%EC%9E%90%EB%B0%94_%ED%81%AC%EB%A1%A4%EB%A7%81#.EC.97.98.EB.A6.AC.EB.A8.BC.ED.8A.B8_.EC.B2.B4.ED.81.AC_.ED.95.98.EA.B8.B0
		
		// 그외 참고 url: https://testmanager.tistory.com/115
		
		// 동작들 찾아서 정리 & vo 저장 & 화면 뿌리기 + selenium IDE 참고
		// 화면에 뿌린 거 가져오는 것까지 진행 후 run() 부분 어떻게 처리할지 고민
		
		// 테스트 순서
		// Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/chromedriver"); // 1. 크롬 드라이버 경로 가져오기 
		// System.setProperty("webdriver.chrome.driver", path.toString()); 							 // 2. WebDriver 경로 설정
		// ChromeOptions options = new ChromeOptions(); 											 // 3. WebDriver 옵션 설정    ... Browsers Setup
		// - options.addArguments("--start-maximized"); 전체화면으로 실행
		// - options.addArguments("--disable-popup-blocking"); 팝업 무시
		// - options.addArguments("--disable-default-apps"); 기본앱 사용안함
		// ChromeDriver driver = new ChromeDriver(options); 												 // 4. WebDriver 객체 생성
		// 동작 이후 driver sleep 되게
		
		// Calendar Date Picker Handler 
		// https://stqatools.com/selenium-calendar-handling/
		
		// Dynamic WebTable 
		// https://stqatools.com/selenium-dynamic-webtable/
		
		// 크롤링 예제
		// http://dbcafe.co.kr/wiki/index.php/%EC%9E%90%EB%B0%94_%ED%81%AC%EB%A1%A4%EB%A7%81#.EC.97.98.EB.A6.AC.EB.A8.BC.ED.8A.B8_.EC.B2.B4.ED.81.AC_.ED.95.98.EA.B8.B0
		
	}
	
}
