package chromeDriverTestTool.item.service;

import chromeDriverTestTool.condition.TestSucceedCondition;
import net.sf.json.JSONObject;

/**
 * 2023.01.03
 * @author 김보미
 * 테스트 항목 인터페이스
 */
public interface TestItem {
	
	public JSONObject getTestList(Object fieldObj);	
	
	public TestItem getCurrentItem();
	public TestItem getPrevItem();
	public TestItem getNextItem();
	
	public void setDriverInfo(String driverPath, String testURL);
	
	public void run() throws Exception;
	public boolean getResult() throws Exception;
	
	public void setSucceedCondition(TestSucceedCondition cond);

}
