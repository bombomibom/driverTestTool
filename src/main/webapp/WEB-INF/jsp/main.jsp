<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
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
<!-- <link rel="stylesheet" type="text/css" href="css/main.css"> -->
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
		// 변수 저장
		var driverUrlList = "${driverUrlList}";
		var commandList = JSON.parse('${commandList}');
		var targetList = JSON.parse('${targetList}');

		// 1. 드라이버 리스트 출력
		$(".driverUrlList").append(driverUrlList);

		// 2. 테스트 리스트 출력(일단 보류. 디비로 옮기고 다시 진행 예정)
		//appendTestList(targetList, ".testList .target");
		//appendTestList(commandList, ".testList .movement");
	})
	
	/* 
	 * 테스트 리스트를 출력한다.
	 */
	function appendTestList(jsonList, target){
		var keys = Object.keys(jsonList);
		
		for(var i = 0; i < keys.length; i++){
			var key = keys[i];
			//console.log("key : " + key + ", value : " + jsonList[key]);
			$(target).append("<li><a href='#none' class='" + key + "' onclick='appendItem($(this));'>" + jsonList[key] + "</a></li>");
		}
	}
	
	/* 
	 * 추가할 부모 콘텐츠를 찾는다.
	 */
	function findAppendTarget(itemCategory){
		var appendTarget = "";
		
		// 추가할 부모 콘텐츠 찾기
		if($(".testBoard").find(".testBox").length >= 1){
			var lastContents = $(".testBoard").find(".testBox").last().children().last();
			//console.log(lastContents);
			
			if(lastContents.attr("data-category") == "target"){
				if(itemCategory == "movement"){
					result = lastContents.parent();
				} else {
					result = "동작부터 지정해주세요.";
				}
			} else if(lastContents.attr("data-category") == "movement"){
				if(itemCategory == "target"){
					result = $(".testBoard");
				} else {
					result = "경로부터 지정해주세요.";
				}
			}
		} else {
			if(itemCategory == "target"){
				result = $(".testBoard");
			} else {
				result = "경로부터 지정해주세요.";
			}
		}
		
		return result;
	}
	
	/* 
	 * 콘텐츠 아이템을 추가한다.
	 */
	function appendItem(clickedItem){
		var itemCategory = clickedItem.parent().parent().attr("class");
		var result = findAppendTarget(itemCategory);
		
		if(typeof(result) == "object"){
			if(itemCategory == "target"){
				result.append("<div class='testBox'><input type='button' value='x' class='closeBtn' onclick='removeItems($(this));' /><div data-category='target'><input type='text' data-type='" + clickedItem.attr("class") + "' placeholder='" + clickedItem.text() + "' /></div></div>");
			} else if(itemCategory == "NeedElementsCommand"){
				if(clickedItem.attr("class").includes("click")){
					result.append("<div data-category='movement'><input type='button' data-type='" + clickedItem.attr("class") + "' value='" + clickedItem.text() + "' /></div>");
				} else if(clickedItem.attr("class").includes("input")){
					result.append("<div data-category='movement'><input type='text' data-type='" + clickedItem.attr("class") + "' placeholder='" + clickedItem.text() + "' /></div>");
				}
			}
		} else {
			alert(result);
		}
	}
	
	/* 
	 * 콘텐츠 아이템을 제거한다.
	 */
	function removeItems(clickedBtn){
		clickedBtn.parent().remove();
	}
	
	/* 
	 * 테스트 리스트에 셀레니움 아이템을 추가한다.
	 */
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

	/* 
	 * 테스트 항목을 가지고 셀레니움 테스트를 실행한다.
	 */
	function seleniumTest(){
		var testURL = $("#testURL").val();
		var driverPath = $("#driverPath").val();
		var item = {};
		var itemList = [];
		
		$(".testBox").each(function(){
			$(this).find("div").each(function(){
				item[$(this).attr("data-category") + "Type"] = $(this).children("input").attr("data-type");
				item[$(this).attr("data-category") + "Value"] = $(this).children("input").val();
			})
			itemList.push(item);
			item = {};
			console.log(itemList);
		})
		
		$.ajax({
	        type:"POST",
	        url: "/seleniumTest.doAPI",
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
	
</script>
</head>
<body>
	<div id="driverTestContainer">
        <section>
            <h1>STEP 1 크롬 드라이버 확인 및 설치</h1>
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
				        						
				        					</ul>
				            			</div>
				            			<div class="liBox">
				            				<h3>2. 타겟 필요 동작</h3>
				           					<ul class="NeedElementsCommand">
				           						<li class="clickCommand">
				           							<h4>클릭</h4>
					           						<div data-type="button" data-name="clickEnter" onclick="appendItem($(this));">ENTER</div>
					           						<div data-type="button" data-name="clickESC" onclick="appendItem($(this));">ESC</div>
					           						<div data-type="button" data-name="clickDouble" onclick="appendItem($(this));">더블클릭</div>
				           							<div data-type="button" data-name="clickRight" onclick="appendItem($(this));">우클릭</div>
				           						</li>
				           						<li class="inputCommand">
				           							<h4>입력</h4>
				           							<div data-type="text" data-name="inputSearchKeyword" onclick="appendItem($(this));">검색어</div>
					           						<div data-type="text" data-name="inputValue" onclick="appendItem($(this));">값(value)</div>
					           						<div data-type="text" data-name="inputDropDownText" onclick="appendItem($(this));"></div>
					           						<div data-type="text" data-name="inputDropDownIndex" onclick="appendItem($(this));"></div>
					           						<div data-type="text" data-name="inputDropDownTextForThePurposeClear" onclick="appendItem($(this));"></div>
					           						<div data-type="text" data-name="inputDropDownValueForThePurposeClear" onclick="appendItem($(this));"></div>
					           						<div data-type="text" data-name="inputCheckIndex" onclick="appendItem($(this));"></div>
					           						<div data-type="text" data-name="inputScrollElement" onclick="appendItem($(this));"></div>
				           						</li>
				           						<li>
				           							<h4>추출</h4>
				           							<div data-type="text" data-name="inputAttr" onclick="appendItem($(this));">엘리먼트 속성</div>
				           							<div data-type="button" data-name="getText" onclick="appendItem($(this));">텍스트</div>
					           						<div data-type="button" data-name="getSize" onclick="appendItem($(this));">너비, 높이</div>
					           						<div data-type="button" data-name="getLocation" onclick="appendItem($(this));">x, y 좌표</div>
				           						</li>
				           						<li>
				           							<h4>기타(예정)</h4>
					           						<div data-type="button" data-name="clearValue" onclick="appendItem($(this));">값 삭제</div>
				           							<div data-type="button" data-name="submitForm" onclick="appendItem($(this));"></div>
					           						<div data-type="button" data-name="waitUntilAlertIsPresent" onclick="appendItem($(this));"></div>
					           						<div data-type="button" data-name="waitUntilElementToBeClickable" onclick="appendItem($(this));"></div>
					           						<div data-type="button" data-name="waitUntilVisibilityOf" onclick="appendItem($(this));"></div>
					           						<div data-type="button" data-name="conditionIsDisplayed" onclick="appendItem($(this));"></div>
					           						<div data-type="button" data-name="isSelected" onclick="appendItem($(this));"></div>
					           						<div data-type="button" data-name="isEnabled" onclick="appendItem($(this));"></div>
					           						<div data-type="button" data-name="dragElement" onclick="appendItem($(this));"></div>
					           						<div data-type="button" data-name="dropElement" onclick="appendItem($(this));"></div>
					           						<div data-type="button" data-name="keyDown" onclick="appendItem($(this));"></div>
					           						<div data-type="button" data-name="keyUp" onclick="appendItem($(this));"></div>
					           						<div data-type="button" data-name="mouserHover" onclick="appendItem($(this));"></div>
				           						</li>
				           						
				           					</ul>
				            			</div>
				            			<div class="liBox">
				            				<h3>3. 타겟 불필요 동작</h3>
				           					<ul class="FreeNeedElementsCommand">
				           						
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
                        <a href="#none" onclick="seleniumTest();">실행</a>
                    </div>
                </div>
            </div>
        </section>
    </div>
</body>
</html>