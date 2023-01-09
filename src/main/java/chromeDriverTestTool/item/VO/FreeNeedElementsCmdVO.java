package chromeDriverTestTool.item.VO;

public class FreeNeedElementsCmdVO {
	// browser
	private String getTitle = "타이틀 추출";
	private String getCurrentUrl = "URL 추출";
	private String getPageSource = "페이지 소스 추출";
	private String closeThisTab = "현재 창 닫기";
	private String closeAllTab = "전체 창 닫기";
	private String showMaximize = "최대 크기로 창 열기";
	private String showMinimize = "최소 크기로 창 열기";
	private String navigateBack = "뒤로가기";
	private String navigateForward = "앞으로가기";
	private String navigateRefresh = "새로고침";
	
	// iFrame
	private String inputFrameName = "iFrame 이름으로 열기";
	private String inputFrameIndex = "iFrame 인덱스로 열기";
	private String showDefaultFrame = "기본 iFrame 열기";
	
	// window
	private String inputWindowName = "window 창 이름으로 열기"; // 이건 url 여러 개 입력해도 될 때 할 예정
	
	// wait
	private String inputWaitTime = "대기하기";
}
