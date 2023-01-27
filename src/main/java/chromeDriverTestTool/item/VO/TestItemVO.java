package chromeDriverTestTool.item.VO;

public class TestItemVO {
	private String dataText;
	private String dataName;
	private String dataCategory;
	
	// 일단 생성자로 바로 저장하게 진행. set 유효성검사는 고민
	public TestItemVO(String dataText, String dataName, String dataCategory) {
		this.dataText = dataText;
		this.dataName = dataName;
		this.dataCategory = dataCategory;
	}
	
	public String getDataText() {
		return dataText;
	}
	public void setDataText(String dataText) {
		this.dataText = dataText;
	}
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public String getDataCategory() {
		return dataCategory;
	}
	public void setDataCategory(String dataCategory) {
		this.dataCategory = dataCategory;
	}
}
