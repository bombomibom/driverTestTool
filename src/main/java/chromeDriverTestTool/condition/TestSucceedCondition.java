package chromeDriverTestTool.condition;

import org.openqa.selenium.WebDriver;

public interface TestSucceedCondition {
	public boolean doJudgement(WebDriver driver) throws Exception;
}
