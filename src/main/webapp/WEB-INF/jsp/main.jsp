<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	/* 슬래시 없어지는 현상 때문에 이렇게 진행 */
	String driverPath = "";
	if((String)request.getAttribute("driverPath") != null){
		driverPath = (String)request.getAttribute("driverPath");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<!-- <link rel="stylesheet" type="text/css" href="../css/main.css"> -->
<style type="text/css">
	* {
		padding: 0;
		margin: 0;
		text-decoration: none;
		color: #333;
	}
	
	li {
		list-style: none;
	}
	
	input {
		width: 100%;
	}
	
	#driverTestContainer {
		display: flex;
		margin: 10px 10px 0 10px;
	}
	
	#driverTestContainer section {
		margin: 0 10px;
		height: 100px;
	}
	
	#driverTestContainer section:nth-child(1) {
		flex: 1;
	}
	
	#driverTestContainer section:nth-child(2) {
		flex: 2;
	}
	
	#driverTestContainer h1 {
		font-size: 18px;
		text-align: center;
		margin: 20px 0;
	}
	
	#driverTestContainer h2, th {
		background: #efefef;
		font-size: 14px;
		text-align: center;
		padding: 7px;
	}
	
	#driverTestContainer a {
		background-color: #F9F9F9;
		text-align: center;
		font-size: 14px;
	}
	
	#driverTestContainer table {
		width: 100%;
		border-collapse: collapse;
		margin-bottom: 20px;
	}
	
	#driverTestContainer tbody.driverUrlList {
		height: 300px;
		overflow: scroll;
		display: block;
	}
	
	#driverTestContainer tbody.driverUrlList tr {
		width: 100%;
		display: block;
	}
	#driverTestContainer tbody.driverUrlList td {
		display: block;
	}
	
	#driverTestContainer th:first-child {
		width: 30%;
		border-right: 1px solid lightgray;
	}
	
	#driverTestContainer td {
		font-size: 14px;
		border-bottom: 1px solid lightgray;
	}
	
	#driverTestContainer td:first-child {
		text-align: center;
		border-right: 1px solid lightgray;
	}
	
	#driverTestContainer .btnBox {
		display: flex;
	}
	
	#driverTestContainer .btnBox a {
		width: 50%;
		text-align: center;
		padding: 7px;
		background-color: #efefef;
	}
	
	
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script>

	$(document).ready(function(){
		
		// 크롬드라이버 리스트 출력
		var driverUrlList = "${driverUrlList}";
		$(".driverUrlList").append(driverUrlList);
	})

	// 테스트 항목 추가
	function appendTestItem(clickedItem){
		var clickedItemCategory = clickedItem.parent().parent().attr("class");
		var clickedItemDataType = clickedItem.attr("data-type");
		var clickedItemDataName = clickedItem.attr("data-name");
		var clickedItemDataText = clickedItem.text();
		var existTestBoard = new Boolean(false);
		var lastTestBoardItem = $(".testBoard").attr("class");
		
		if($(".testBoard").find("div").length >= 1){
			existTestBoard = Boolean(true);
			lastTestBoardItem = $(".testBoard").find("div").last().attr("data-category");
		}
		//console.log(existTestBoard);
		//console.log(lastTestBoardItem);
	
		$.ajax({
	        type:"POST",
	        url: "/setTestItem.doAPI",
	        data: {
	        	addItemCategory: clickedItemCategory,
	        	addItemDataType: clickedItemDataType,
	        	addItemDataName: clickedItemDataName,
	        	addItemDataText: clickedItemDataText,
	        	existItem: existTestBoard,
	        	lastItem: lastTestBoardItem
	        },
	        success: function(data){
	        	var result = JSON.parse(data);
	        	if(result.method == "append"){
	        		$(".testBoard").append(result.value);
	        	} else if(result.method == "alert") {
	        		alert(result.value);
	        	}
	        },
	        error: function(request,status,error){
	            console.log(error);
	        }
	    })
	}
	
	// 테스트 항목 제거
	function removeTestItems(clickedBtn){
		if(clickedBtn.parent().attr("data-category") == "NeedElements"){
			if(confirm("타겟까지 같이 삭제됩니다. 정말 삭제하시겠습니까?")){
				clickedBtn.parent().prev().remove();
				clickedBtn.parent().remove();
			} else {
				return false;
			}
		} else {
			clickedBtn.parent().remove();
		}
	}
	
	// 테스트 리스트 아이템 추가(일단 보류! 상태값 어떤식으로 들어오는지 확인 후 반영 예정)
	function addTestList(){
		var str = "";
		
		$(".testBox").each(function(){
			$(this).find("div").each(function(){
				str += "<div class='item'>" + $(this).children("input").attr("data-type") + " - " + $(this).children("input").val() + "</div><div class='status'><span>--상태바--</span></div>";
			})
		})
		
		$(".testItemList").html("");
		$(".testItemList").append(str);
	}
	
	// 셀레니움 테스트
	function doSeleniumTest(){
		var testURL = $("#testURL").val();
		var driverPath = $("#driverPath").val();
		var lastTestBoardItem = "";
		var item = {};
		var itemList = [];
		
		if($(".testBoard").find("div").length >= 1){
			lastTestBoardItem = $(".testBoard").find("div").last().attr("data-category");
			
			if(lastTestBoardItem == "target"){
				alert("타겟에 맞는 항목 선택 후 실행해야 합니다.");
				return false;
			} else {
				$(".testBoard").children().each(function(){
					// value 가져오기 및 동작 따로따로 실행되는지 확인 필요
					item["dataCategory"] = $(this).attr("data-category");
					item["dataName"] = $(this).children().eq(0).attr("data-name");
					item["dataText"] = $(this).children().eq(0).text();
					
					itemList.push(item);
					item = {};
					console.log(itemList);
				})
				
				$.ajax({
			        type:"POST",
			        url: "/doSeleniumTest.doAPI",
			        data: {
			        	testURL: testURL,
			        	driverPath: driverPath,
			        	testList: JSON.stringify(itemList)
			        },
			        success: function(data){
			        	console.log("testSuccess");
			        	console.log(data);
			        },
			        error: function(request,status,error){
			            console.log("testFail");
			        }
			    })
			}
			
		} else {
			alert("실행할 항목이 없습니다.");
		}
	}
	
</script>
</head>
<body>
	<div id="driverTestContainer">
        <section>
            <h1>STEP 1 크롬 드라이버 확인 및 설치</h1>
            <h1><c:out value="${pageContext.request.contextPath}" /></h1>
            <div class="firstContents">
                <table>
                	<thead>
                		<tr>
                			<td>
                				<h2>내 PC 크롬 드라이버 설치 경로 확인</h2>
                			</td>
                		</tr>
                	</thead>
                    <tbody>
                    	<tr>
                    		<td>
                    			<form action="/main.do" method="post">
	                    			<input type="text" name="path" class="path" placeholder="확인할 경로를 입력해주세요. ex) c:\project_testTool\chromeDriver\"/>
	                    			<input type="submit" class="confirmPathBtn" value="찾기" />
	                    			<div class="resultPath"><%=driverPath%></div>
                    			</form>
                    		</td>
                    	</tr>
                    </tbody>
                </table>
                <table>
                	<thead>
                		<tr>
                			<td>
                				<h2>크롬 드라이버 버전 확인 및 설치</h2>
                			</td>
                		</tr>
                	</thead>
                	<tbody class="driverUrlList">
                	
                	</tbody>
                </table>
            </div>
        </section>
        <section>
            <h1>STEP 2 테스트 항목 입력</h1>
            <div class="secondContents">
                <div>
                    <table>
                        <thead>
                            <tr>
                                <th>항목</th>
                                <th>내용</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>드라이버</td>
                                <td>
                                	<input type="text" id="driverPath" value="C:\\project_testTool\\chromeDriver\\chromedriver.exe" />
                                </td>
                            </tr>
                            <tr>
                                <td class="noneClick">URL</td>
                                <td>
                                	<input type="text" id="testURL" value="https://www.google.com" />
                                </td>
                            </tr>
                            <tr>
                                <td>테스트</td>
                                <td class="testItemList">
                                
                                </td>
                            </tr>
                            <tr>
                                <td>
									<div class="testList">
				            			<div class="liBox">
				            				<h3>1. 타겟</h3>
				        					<ul class="target">
				        						<li class="target">
				        							<div data-type="text" data-name="xPath" onclick="appendTestItem($(this));">xPath</div>
				        							<div data-type="text" data-name="className" onclick="appendTestItem($(this));">className</div>
				        							<div data-type="text" data-name="id" onclick="appendTestItem($(this));">id</div>
				        							<div data-type="text" data-name="name" onclick="appendTestItem($(this));">name</div>
				        							<div data-type="text" data-name="tagName" onclick="appendTestItem($(this));">tagName</div>
				        							<div data-type="text" data-name="linkText" onclick="appendTestItem($(this));">linkText</div>
				        							<div data-type="text" data-name="cssSelector" onclick="appendTestItem($(this));">cssSelector</div>
				        						</li>
				        					</ul>
				            			</div>
				            			<div class="liBox">
				            				<h3>2. 타겟 필요 동작</h3>
				           					<ul class="NeedElements">
				           						<li class="click">
				           							<h4>클릭</h4>
					           						<div data-type="button" data-name="clickEnter" onclick="appendTestItem($(this));">ENTER</div>
					           						<div data-type="button" data-name="clickESC" onclick="appendTestItem($(this));">ESC</div>
					           						<div data-type="button" data-name="clickDouble" onclick="appendTestItem($(this));">더블클릭</div>
				           							<div data-type="button" data-name="clickRight" onclick="appendTestItem($(this));">우클릭</div>
				           						</li>
				           						<li class="input">
				           							<h4>입력</h4>
				           							<div data-type="text" data-name="inputSearchKeyword" onclick="appendTestItem($(this));">검색어</div>
					           						<div data-type="text" data-name="inputValue" onclick="appendTestItem($(this));">값(value)</div>
					           						<div data-type="text" data-name="inputDropDownText" onclick="appendTestItem($(this));">선택할 드롭다운 텍스트</div>
					           						<div data-type="text" data-name="inputDropDownIndex" onclick="appendTestItem($(this));">선택할 드롭다운 인덱스</div>
					           						<div data-type="text" data-name="inputDropDownClearText" onclick="appendTestItem($(this));">선택취소할 드롭다운 텍스트</div>
					           						<div data-type="text" data-name="inputDropDownClearValue" onclick="appendTestItem($(this));">선택취소할 드롭다운 값(value)</div>
					           						<div data-type="text" data-name="inputCheckIndex" onclick="appendTestItem($(this));">선택할 체크박스 인덱스</div>
					           						<div data-type="text" data-name="inputScrollElement" onclick="appendTestItem($(this));">최상단부터 스크롤이 멈추는 지점(element)</div>
				           							<div data-type="text" data-name="inputAttr" onclick="appendTestItem($(this));">추출할 엘리먼트 속성</div>
				           						</li>
				           						<li class="get">
				           							<h4>추출</h4>
				           							<div data-type="button" data-name="getText" onclick="appendTestItem($(this));">텍스트</div>
					           						<div data-type="button" data-name="getSize" onclick="appendTestItem($(this));">너비, 높이</div>
					           						<div data-type="button" data-name="getLocation" onclick="appendTestItem($(this));">x, y 좌표</div>
				           						</li>
				           						<li class="wait">
				           							<h4>대기</h4>
					           						<div data-type="button" data-name="waitUntilAlertIsPresent" onclick="appendTestItem($(this));">알림창 표시될 때까지</div>
					           						<div data-type="button" data-name="waitUntilElementToBeClickable" onclick="appendTestItem($(this));">엘리먼트 활성화 전까지</div>
					           						<div data-type="button" data-name="waitUntilVisibilityOf" onclick="appendTestItem($(this));">엘리먼트 보일 때까지</div>
				           						</li>
												<li class="condition">
													<h4>조건</h4>
					           						<div data-type="button" data-name="isDisplayed" onclick="appendTestItem($(this));">display 활성화</div>
					           						<div data-type="button" data-name="isSelected" onclick="appendTestItem($(this));">select 활성화</div>
					           						<div data-type="button" data-name="isEnabled" onclick="appendTestItem($(this));">element 활성화</div>
												</li>
												<li class="dragAndDrop">
													<h4>드래그 앤 드롭</h4>
					           						<div data-type="button" data-name="dragElement" onclick="appendTestItem($(this));">드래그</div>
					           						<div data-type="button" data-name="dropElement" onclick="appendTestItem($(this));">드롭</div>
												</li>
												<li class="keyBoard">
													<h4>키보드 이벤트</h4>
					           						<div data-type="button" data-name="keyUp" onclick="appendTestItem($(this));">키보드에서 손 뗐을 때</div>
					           						<div data-type="button" data-name="keyDown" onclick="appendTestItem($(this));">키보드를 눌렀을 때</div>
												</li>
				           						<li class="etc">
				           							<h4>기타</h4>
					           						<div data-type="button" data-name="clearValue" onclick="appendTestItem($(this));">값(value) 삭제</div>
				           							<div data-type="button" data-name="submitForm" onclick="appendTestItem($(this));">양식(form) 제출</div>
					           						<div data-type="button" data-name="mouserHover" onclick="appendTestItem($(this));">마우스 오버</div>
				           						</li>
			           						</ul>
				            			</div>		
				            			<div class="liBox">
				            				<h3>3. 타겟 불필요 동작</h3>
				           					<ul class="FreeNeedElements">
				           						<li class="browser">
				           							<h4>브라우저</h4>
					           						<div data-type="button" data-name="getTitle" onclick="appendTestItem($(this));">타이틀 추출</div>
					           						<div data-type="button" data-name="getCurrentURL" onclick="appendTestItem($(this));">URL 추출</div>
					           						<div data-type="button" data-name="getPageSource" onclick="appendTestItem($(this));">페이지 소스 추출</div>
				           							<div data-type="button" data-name="closeThisTab" onclick="appendTestItem($(this));">현재 창 닫기</div>
				           							<div data-type="button" data-name="closeAllTab" onclick="appendTestItem($(this));">전체 창 닫기</div>
				           							<div data-type="button" data-name="showMaximize" onclick="appendTestItem($(this));">최대 크기로 창 열기</div>
				           							<div data-type="button" data-name="showMinimize" onclick="appendTestItem($(this));">최소 크기로 창 열기</div>
				           							<div data-type="button" data-name="navigateBack" onclick="appendTestItem($(this));">뒤로가기</div>
				           							<div data-type="button" data-name="navigateForward" onclick="appendTestItem($(this));">앞으로가기</div>
				           							<div data-type="button" data-name="navigateRefresh" onclick="appendTestItem($(this));">새로고침</div>
				           						</li>
				           						<li class="driverOption">
				           							<h4>드라이버 옵션</h4>
				           							<div data-type="button" data-name="optDisablePopupBlocking" onclick="appendTestItem($(this));">팝업 무시</div>
				           							<div data-type="button" data-name="optDisableDefaultApps" onclick="appendTestItem($(this));">기본 앱 미사용</div>
				           							<div data-type="button" data-name="optMobileEmulation" onclick="appendTestItem($(this));">모바일 모드로 확인</div>
				           						</li>
				           						<li class="iframeAndWindow">
				           							<h4>iFrame & window</h4>
				           							<div data-type="text" data-name="inputFrameName" onclick="appendTestItem($(this));">iFrame 이름으로 열기</div>
				           							<div data-type="text" data-name="inputFrameIndex" onclick="appendTestItem($(this));">iFrame 인덱스로 열기</div>
				           							<div data-type="button" data-name="showDefaultFrame" onclick="appendTestItem($(this));">기본 iFrame 열기</div>
				           							<div data-type="text" data-name="inputWindowName" onclick="appendTestItem($(this));">window 창 이름으로 열기</div>
				           						</li>
				           						<li class="waitingTime">
				           							<h4>대기시간</h4>
				           							<div data-type="text" data-name="inputWaitingTime" onclick="appendTestItem($(this));">대기 시간 입력</div>
				           						</li>
				           						<li class="alert">
				           							<h4>경고창</h4>
				           							<div data-type="button" data-name="dismissAlert" onclick="appendTestItem($(this));">닫기</div>
				           							<div data-type="button" data-name="acceptAlert" onclick="appendTestItem($(this));">확인버튼 클릭</div>
				           							<div data-type="button" data-name="getAlertText" onclick="appendTestItem($(this));">텍스트 추출</div>
				           						</li>
				           						<li class="conditionalStatement">
				           							<h4>조건문</h4>
				           							<div data-type="button" data-name="conditionIf" onclick="appendTestItem($(this));">if</div>
				           							<div data-type="button" data-name="conditionElse" onclick="appendTestItem($(this));">else</div>
				           							<div data-type="button" data-name="conditionElseIf" onclick="appendTestItem($(this));">else if</div>
				           							<div data-type="button" data-name="conditionFor" onclick="appendTestItem($(this));">for</div>
				           						</li>
				           						<li class="scroll">
				           							<h4>스크롤</h4>
				           							<div data-type="button" data-name="inputScrollPixel" onclick="appendTestItem($(this));">스크롤될 픽셀 입력</div>
				           							<div data-type="button" data-name="scrollBodyHeight" onclick="appendTestItem($(this));">최하단까지 스크롤</div>
				           						</li>
				           						<li class="screenshot">
				           							<h4>스크린샷</h4>
				           							<div data-type="button" data-name="screenshot" onclick="appendTestItem($(this));">스크린샷</div>
				           						</li>
				           						<li class="cookie">
				           							<h4>쿠키</h4>
				           							<div data-type="button" data-name="getCookie" onclick="appendTestItem($(this));">추출</div>
				           							<div data-type="text" data-name="inputCookieName" onclick="appendTestItem($(this));">쿠키명으로 추출</div>
				           							<div data-type="text" data-name="addCookie" onclick="appendTestItem($(this));">추가</div>
				           							<div data-type="button" data-name="deleteCookie" onclick="appendTestItem($(this));">삭제</div>
				           							<div data-type="text" data-name="deleteCookieName" onclick="appendTestItem($(this));">쿠키명으로 삭제</div>
				           							<div data-type="button" data-name="deleteAllCookies" onclick="appendTestItem($(this));">전체 삭제</div>
				           						</li>
				           					</ul>
				            			</div>
					                </div>
								</td>
                                <td class="testItems">
                                	<div class="testBoard">
	            	
	              				    </div>
	              				    <div class="btnBox">
					                    <a href="">취소</a> <!-- 만들어야 함 -->
					                    <a onclick="addTestList();">확인</a>
					                </div>	
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="btnBox">
                        <a href="">취소</a>
                        <a href="#none" onclick="doSeleniumTest();">실행</a>
                    </div>
                </div>
            </div>
        </section>
    </div>
</body>
</html>