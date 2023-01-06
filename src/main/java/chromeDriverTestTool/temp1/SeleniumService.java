package chromeDriverTestTool.temp1;

/**
 * 셀레니움 활용 브라우저 테스트 서비스
 *
 * @author bomi, Kim
 * @since 2022.12.21
 * 
 */
public interface SeleniumService {
	
	/**
	 * URL에 접속한다.
	 */
	public void connectURL();
	
	/**
	 * 검색어를 받아 입력한다.
	 */
	public void inputSearchKeyword(String xPath, String searchKeyword);
	
	/**
	 * ENTER를 클릭한다.
	 */
	public void clickEnter(String xPath);

	/**
	 * ESC를 클릭한다.
	 */
	public void clickEscape(String xPath);

}
