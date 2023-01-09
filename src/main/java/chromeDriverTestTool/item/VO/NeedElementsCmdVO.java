package chromeDriverTestTool.item.VO;

public class NeedElementsCmdVO {
	// click
	private String clickEnter = "ENTER 클릭";
	private String clickEsc = "ESC 클릭";
	
	// input
	private String inputSearchKeyword = "검색";
	private String inputAttribute = "속성 추출";
	
	// etc
	private String clearValue = "내용 삭제";
	private String submitForm = "양식 제출";
	
	// get element info
	private String getText = "텍스트 추출";
	private String getSize = "너비, 높이 추출";
	private String getLocation = "x, y 좌표 추출";
	
	// wait // wait 좀 더 있음
	private String waitUntilAlertIsPresent = "알림창 표시될 때까지 대기";
	private String waitUntilElementToBeClickable = "Element 활성화 되기 전까지 대기";
	private String waitUntilVisibilityOf = "Element 보일 때까지 대기"; 
	
	// condition
	private String conditionIsDisplayed = "display 활성화 확인";
	private String isSelected = "select 활성화 확인";
	private String isEnabled = "Element 활성화 확인";
	
	// drag and drop // 이거 좀 애매! !! ! json 방식으로 가져올 때 조금 애매함
	private String dragElement = "drag 하기";
	private String dropElement = "drop 하기";
	
	// dropDown
	private String inputDropDownText = "텍스트로 드롭다운 요소 선택";
	private String inputDropDownIndex = "인덱스로 드롭다운 요소 선택";
	private String inputDropDownValue = "값으로 드롭다운 요소 선택";
	private String inputDropDownTextForThePurposeClear = "텍스트로 드롭다운 요소 취소";
	private String inputDropDownValueForThePurposeClear = "값으로 드롭다운 요소 취소";
}
