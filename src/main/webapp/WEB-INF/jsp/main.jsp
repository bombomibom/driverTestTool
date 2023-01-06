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
		width: calc(100% - 33%);
		margin: 0 10px;
		height: 100px;
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
	
	#driverTestContainer .firstContents a, td a {
		background-color: #F9F9F9;
		text-align: center;
		font-size: 14px;
	}
	
	#driverTestContainer table {
		width: 100%;
		border-collapse: collapse;
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
	
	#driverTestContainer section .btnBox a {
		width: 50%;
		text-align: center;
		padding: 7px;
		background-color: #efefef;
	}
	
	#driverTestContainer section:nth-child(3) h1, .thirdContents {
		display: none;
	}
	
	#driverTestContainer .thirdContents .testList {
		flex: 1;
		background-color: lightgray;
	}
	
	#driverTestContainer .thirdContents .testBoard {
		background-color: darkgray;
		flex: 2;
	}
	
	#driverTestContainer .thirdContents .testBoard>div.execBoard {
		background-color: gray;
	}
	
	#driverTestContainer .thirdContents .contentBox {
		display: flex;
		height: 700px; /* 임시 */
	}
	
	#driverTestContainer .thirdContents li span {
		text-align: center;
		font-weight: bold;
	}
	
	#driverTestContainer .thirdContents .variableList, .actionList {
		cursor: pointer;
	}
	
	#driverTestContainer .thirdContents .testBox input {
		width: auto
	}
	
	#driverTestContainer .thirdContents .testList a {
		width: 100%;
		display: block;
	}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script>

	$(document).ready(function(){
		// main 화면 출력시 request.setAttribute로 받은 내용 변수 저장
		var driverUrlList = "${driverUrlList}";
		var commandList = JSON.parse('${commandList}');
		var targetList = JSON.parse('${targetList}');

		// 드라이버 리스트 출력
		$(".driverUrlList").append(driverUrlList);

		// 셀레니움 테스트 리스트 출력
		appendTestList(targetList, ".testList .target");
		appendTestList(commandList, ".testList .movement");
	})
	
	/* 
	 * 셀레니움 테스트 리스트를 출력한다.
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
	 * 테스트 항목 지정을 보여준다.
	 */
	function showTestBoard(){
		$("#driverTestContainer section:nth-child(3) h1, .thirdContents").css({
			"display":"block"
		})
	}
	
	/* 
	 * 추가할 부모 콘텐츠를 찾는다.
	 */
	function findAppendTarget(itemType){
		var appendTarget = "";
		
		// 추가할 부모 콘텐츠 찾기
		if($(".testBoard").find(".testBox").length >= 1){
			var lastContents = $(".testBoard").find(".testBox").last().children().last();
			//console.log(lastContents);
			
			if(lastContents.hasClass("target")){
				if(itemType == "movement"){
					result = lastContents.parent();
				} else {
					result = "동작부터 지정해주세요.";
				}
			} else if(lastContents.hasClass("movement")){
				if(itemType == "target"){
					result = $(".testBoard");
				} else {
					result = "경로부터 지정해주세요.";
				}
			}
		} else {
			if(itemType == "target"){
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
		var itemType = clickedItem.parent().parent().attr("class");
		var result = findAppendTarget(itemType);
		
		if(typeof(result) == "object"){
			if(itemType == "target"){
				result.append("<div class='testBox'><input type='button' value='x' class='closeBtn' onclick='removeItems($(this));' /><div class='target'><input type='text' class='" + clickedItem.attr("class") + "' placeholder='" + clickedItem.text() + "' /></div></div>");
			} else if(itemType == "movement"){
				if(clickedItem.attr("class").includes("click")){ // 230106 여기부터 진행!! 동작 클릭이냐 인풋이냐에 따라 다르다!!
					result.append("<div class='movement'><input type='button' class='" + clickedItem.attr("class") + "' value='" + clickedItem.text() + "' /></div>");
				} else if(clickedItem.attr("class").includes("input")){
					result.append("<div class='movement'><input type='text' class='" + clickedItem.attr("class") + "' placeholder='" + clickedItem.text() + "' /></div>");
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
				str += "<div class='item'>" + $(this).children("input").attr("class") + " - " + $(this).children("input").val() + "</div><div class='status'><span>--상태바--</span></div>";
			})
		})
		
		$(".testItems").html("");
		$(".testItems").append(str);
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
				item[$(this).children("input").attr("class")] = $(this).children("input").val();
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
	
	// 3. 항목 지정 - Drag & Drop
// 	function dragover_handler(event){
// 		console.log("DragOver");
// 		event.preventDefault(); // 드롭을 허용하기 위해 기본 동작 취소
// 	}
	
// 	// 타이틀 배치
// 	function drop_title(event){
// 		console.log("drop title!");
// 		event.preventDefault();
// 		$(".testBoard").append('<div class="testBox"><input type="checkbox" /><ul class="testList"><li class="title"><input type="text" placeholder="테스트명" /></li></ul><div class="btnBox"><a onclick="removeBox($(this));">삭제</a><a onclick="checkTestBox($(this));">선택</a></div></div>');
// 	}
	
// 	// 드래그 시작시
// 	function drag_start(thisLi){
// 		console.log("drag start");
// 		console.log(thisLi);
// 	}
	
	/* function drag_enter(event){
		if(event.target.className == "testBoard"){
		}
	} */
	
	// jquery 방식
	/* $(function(){
		$("#drag").bind('dragstart', function(event){
			event.originalEvent.dataTransfer.setData('text', event.target.id);
		})
		$("#drop")
			.bind('dragenter dragover', false) 
			.bind('drop', function(event){ 
				var $li = $('<li>').text(event.originalEvent.dataTransfer.getData('text'));
				$(this).append($li);
				return false; 
			});
	}); */
	
	
	
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
                    	<!-- <a href="">클릭</a> -->
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
                                <td class="testItems">
                                	<a href="#none" onclick="showTestBoard();">클릭</a>
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
        <section>
			<h1>항목 지정</h1>
            <div class="thirdContents">
            	<div class="contentBox">
            		<div class="testList">
            			<div class="liBox">
            				<h3>1. 타겟</h3>
        					<ul class="target">
        						
        					</ul>
            			</div>
            			<div class="liBox">
            				<h3>2. 동작</h3>
           					<ul class="movement">
           					
           					</ul>
            			</div>
	                </div>
	            	<div class="testBoard">
	            	
	                </div>		
            	</div>
            	<div class="btnBox">
                    <a href="">취소</a> <!-- 만들어야 함 -->
                    <a onclick="addTestList();">확인</a>
                </div>
            </div>
        </section>
    </div>
</body>
</html>