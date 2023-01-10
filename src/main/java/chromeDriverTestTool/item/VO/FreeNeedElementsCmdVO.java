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
	
	// options
	private String optStartMaximized = "전체 화면으로 실행";
	private String optDisablePopupBlocking = "팝업 무시";
	private String optDisableDefaultApps = "기본앱 사용 안 함";
	private String optMobileEmulation = "모바일 모드 확인";
	
	// iFrame
	private String inputFrameName = "iFrame 이름으로 열기";
	private String inputFrameIndex = "iFrame 인덱스로 열기";
	private String showDefaultFrame = "기본 iFrame 열기";
	
	// window
	private String inputWindowName = "window 창 이름으로 열기"; // 이건 url 여러 개 입력해도 될 때 할 예정
	
	// wait
	private String inputWaitTime = "대기하기";
	
	// alert
	private String dismissAlert = "경고창 닫기";
	private String acceptAlert = "경고창 확인버튼 클릭";
	private String getAlertText = "경고창 텍스트 추출";
	
	// if, else, for
	private String conditionIf = "if";
	private String conditionElse = "else";
	private String conditionElseIf = "elseIf";
	private String conditionfor = "for";
	
	// scroll
	private String inputScrollPixel = "지정한 픽셀까지 스크롤";
	private String scrollBodyHeight = "가장 하단까지 스크롤";
	
	// screenshot
	private String screenShot = "스크린샷";
	
	// cookie
	private String getCookie = "쿠키 추출";
	private String getCookieNamed = "쿠키 이름으로 추출";
	private String addCookie = "쿠키 추가";
	private String deleteCookie = "쿠키 삭제";
	private String deleteCookieNamed = "쿠키 이름으로 찾아 삭제";
	private String deleteAllCookies = "쿠키 전체 삭제";
}
