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
	}

	@Override
	public void clickEnter(String xPath) {
		driver.findElement(By.xpath(xPath)).sendKeys(Keys.ENTER); 
	}
	
	@Override
	public void clickEscape(String xPath) {
		driver.findElement(By.xpath(xPath)).sendKeys(Keys.ESCAPE); 
	}

}
