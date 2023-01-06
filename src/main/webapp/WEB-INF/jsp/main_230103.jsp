<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%
	String driverUrlList = (String)request.getAttribute("driverUrlList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<style>
    *{
        padding: 0;
        margin: 0;
        text-decoration: none;
        color: #333;
    }
    li{
    	list-style: none;
    }
    input{
    	width: 100%;
    }
    #driverTestContainer{
        display: flex;
        margin: 10px 10px 0 10px;
    }
    #driverTestContainer section{
        width: calc(100% - 33%);
        margin: 0 10px;
        height: 100px;
    }
    #driverTestContainer h1{
        font-size: 18px;    
        text-align: center;
        margin: 20px 0;
    }
    #driverTestContainer h2, th{
        background: #efefef;
        font-size: 14px;
        text-align: center;
        padding: 7px;
    }
    #driverTestContainer .firstContents a, td a{
        background-color: #F9F9F9;
        text-align: center;
        font-size: 14px;
    }
    #driverTestContainer table{
        width: 100%;
        border-collapse: collapse;
    }
    #driverTestContainer th:first-child{
        width: 30%;
        border-right: 1px solid lightgray; 
    }
    #driverTestContainer td{
        font-size: 14px;
        
        border-bottom: 1px solid lightgray;
    }
    #driverTestContainer td:first-child{
        text-align: center;
    	border-right: 1px solid lightgray;
    }
    #driverTestContainer .btnBox{
        display: flex;
    }
    #driverTestContainer section .btnBox a{
        width: 50%;
        text-align: center;
        padding: 7px;
        background-color: #efefef;
    }
    #driverTestContainer section:nth-child(3) h1, .thirdContents{
    	display: none;
    }
    #driverTestContainer .thirdContents .testList{
    	flex: 1;
    	background-color: lightgray;
    }
    #driverTestContainer .thirdContents .testBoard{
    	background-color: darkgray;
    	flex: 2;
    }
    #driverTestContainer .thirdContents .testBoard>div.execBoard{
		background-color: gray;
    }
    #driverTestContainer .thirdContents .contentBox{
    	display: flex;
    	height: 700px; /* 임시 */
    }
    #driverTestContainer .thirdContents li span{
    	text-align: center;
    	font-weight: bold;
    }
    #driverTestContainer .thirdContents .variableList, .actionList{
    	cursor: pointer;
    }
    #driverTestContainer .thirdContents .testBox input{
    	width: auto
    }
    
    /* 팝업 */
    #driverTestContainer .chooseVariablePopup{
    	position: absolute;
	    width: 600px;
	    background-color: #FFF;
	    top: 50%;
	    left: 50%;
	    transform: translate(-50%, -50%);
	    height: 400px;
	    border: 1px solid black;
	    display: none;
    }
    
    #driverTestContainer .popup .btnBox a{
    	padding: 10px;
    	cursor: pointer;
    }
    
    #driverTestContainer .popup .variableBox{
		display: flex;
		justify-content: space-around;
    }
    
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script>
	
	/*
	 * 입력받은 경로를 확인한다.
	 */
	function confirmPath(){
		var path = $(".path").val();
		console.log(path); 
		
		if(path == "") {
			$(".resultPath").html("");
			alert("경로가 비었습니다.");
		} else {
			ajaxPost("/getDriverPath", path, "successConfirmFile");
		}
	}

	function ajaxPost(requestURL, dataValue, successFunName){
		$.ajax({
	        type:"POST",
	        url: requestURL,
	        data:{
	        	path: dataValue
	        },
	        success: function(res){
	        	window[successFunName](res);
	        },
	        error: function(request,status,error){
	            console.log('code:'+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error); 
	        }
	    })
	}
	
	function successConfirmFile(res){
		alert("찾기 완료!");
    	$(".resultPath").html("");
		$(".resultPath").append(res);
	}
	
	
	// 2. 테스트 항목 출력
	function showTestBoard(){
		$("#driverTestContainer section:nth-child(3) h1, .thirdContents").css({
			"display":"block"
		})
	}
	
	// 3. 버튼 관련
	
	// 닫기버튼 클릭시 부모 제거
	function removeList(clickedBtn){
		clickedBtn.parent().remove();
	}
	
	// 닫기버튼 클릭시 부모의 부모 제거
	function removeBox(clickedBtn){
		clickedBtn.parent().parent().remove();
	}

	// 선택 클릭시 체크박스 동작
	function checkTestBox(clickedBtn){
		var checkbox = clickedBtn.parent().parent().find("input[type='checkbox']");
		if(checkbox.is(":checked")){
			checkbox.attr("checked", false);
		} else {
			checkbox.attr("checked", true);
		}
	}
	
	// 3. 항목 지정 - 클릭 이벤트
	
	// 타이틀 추가
	function addTitle(thisLi){
		$(".testBoard").append('<div class="testBox"><input type="checkbox" /><ul class="testList"><li class="title"><input type="text" placeholder="테스트명" /></li></ul><div class="btnBox"><a onclick="removeBox($(this));">삭제</a><a onclick="checkTestBox($(this));">선택</a></div></div>');
	}

	// 팝업 x버튼 눌렀을 때 닫기
	function hidePopup(clickedBtn){
		clickedBtn.next().find("*").remove();
		clickedBtn.parent().css("display","none");
	}
	
	// 팝업 자동으로 닫기
	function autoHidePopup(){
		var popup = $(".chooseVariablePopup");
		
		popup.css("display","none");
		popup.find(".variableBox>*").remove();
	}
	
	// 팝업띄우기
	function showPopup(thisLi){
		var popup = $(".chooseVariablePopup");
		var str = "";
		var actionParentClass = thisLi.parent().attr("class");
		var actionClass = thisLi.attr("class");
		var actionName = thisLi.text();

		$(".testBox").each(function(){
			str = str + "<div onclick='addTestBox(" + '"' + actionParentClass + '","' + actionClass + '","' + actionName + '","' + $(this).find(".title").children().val() + '"' + ");'>" + $(this).find(".title").children().val() + "</div>";
		})
		
		popup.find(".variableBox").append(str);
		popup.css("display","block");
	}
	
	// 테스트 항목 추가   
	function addTestBox(actionParentClass, actionClass, actionName, titleName){ // 클래스명, 아이디명, 타이틀명
		var str = "";	
		var lastContentClass = "";
		
		// step1: testbox 쭉 돈다.
		$(".testBox").each(function(){
			// step2: 내가 체크한 타이틀과 같은 testbox를 찾는다.
			if($(this).find(".title").children().val() == titleName){ 
				// 이후 switch문으로 바꿔볼까 생각 중
				if($(this).find(".testList li").hasClass("contents")){
					lastContents = $(this).find(".testList").children().last().children().last();
					if(actionParentClass == "path"){
						if(lastContents.attr("class") == "path"){
							alert("경로 뒤에 동작부터 지정해주세요.");
						} else if(lastContents.attr("class") == "movement") {
							str = str + "<li class='contents'><div class='" + actionParentClass + "'><input type='text' class='" + actionClass + "' placeholder='" + actionName + "' /><span onclick='removeList($(this))'> X</span></div></li>";
							$(this).find(".testList").append(str);
						}
					} else if(actionParentClass == "movement") {
						if(lastContents.attr("class") == "path") {
							if(actionClass == "keyword"){
								str = str + "<div class='" + actionParentClass + "'><input type='text' class='" + actionClass + "' placeholder='" + actionName + "'>" + "<span onclick='removeList($(this))'> X</span></div>";
							} else {
								str = str + "<div class='" + actionParentClass + "'><input type='button' class='" + actionClass + "' value='" + actionName + "'>" + "<span onclick='removeList($(this))'> X</span></div>";
							}
							$(this).find(".testList").children().last().append(str);
						} else if(lastContents.attr("class") == "movement") {
							alert("경로부터 지정해주세요.");
						}
					}
				} else { 
					if(actionParentClass == "path"){
						str = str + "<li class='contents'><div class='" + actionParentClass + "'><input class='" + actionClass + "' type='text' placeholder='" + actionName + "' /><span onclick='removeList($(this))'> X</span></div></li>";		
						$(this).find(".testList").append(str);
					} else if(actionParentClass == "movement") {
						alert("경로부터 지정해주세요.");
					}
				}
			}
		})
		
		autoHidePopup();
	}
	
	var testList = [];
	
	// 4. 리스트 추가 
	function addTestList(){
		var str = "";
		var title = "";	
		var parentClass = "";
		var parentName = "";
		var childrenClass = "";
		var childrenValue = "";
		var tmpJson = {};
		
		testList = [];
		$(".testBox").each(function(){
			if($(this).find("input[type='checkbox']").is(":checked")){
				$(this).find("li").each(function(){
					
					// title key 추가
					if($(this).attr("class") == "title"){
						title = $(this).children().val();
						str = str + "<h4 class='testTitle'>" + title + "</h4>";
					} 
					
					// value 안 {key:value} 추가
					if($(this).attr("class") == "contents"){
						tmpJson = {};
						$(this).children().each(function(){
							parentClass = $(this).attr("class");
							parentName = parentClass + "Name";
							childrenValue = $(this).children().first().val();
							
							// 아래 부분 통일감있게 처리할지 고민 중
							if($(this).children().first().attr("type") == "button"){
								childrenClass = $(this).children().first().attr("type");
							} else {
								childrenClass = $(this).children().first().attr("class");
							}
							
							// json 방식으로 저장
							tmpJson[parentName] = childrenClass;
							tmpJson[parentClass] = childrenValue;
							str = str + "<div>" + childrenClass + " - " + childrenValue + "</div>"
						})
						testList.push(tmpJson);
						str = str + "<div> -------- 상태바 ------- </div> <br>"
					} 	
				})
			}			
		})
		
		$(".testItems").html("");
		$(".testItems").append(str);
		
	}

	// 5. 셀레니움 테스트
	function seleniumTest(){
		var testURL = $("#testURL").val();
		var driverPath = $("#driverPath").val();
		//console.log(testList[0].pathName);
	
		$.ajax({
	        type:"POST",
	        url: "/seleniumTest",
	        data: {
	        	testURL: testURL,
	        	driverPath: driverPath,
	        	testList: JSON.stringify(testList)
	        },
	        success: function(data){
	        	console.log("testSuccess");
	        },
	        error: function(request,status,error){
	            console.log("testFail");
	        }
	    })
	}
	
	// 3. 항목 지정 - Drag & Drop
	function dragover_handler(event){
		console.log("DragOver");
		event.preventDefault(); // 드롭을 허용하기 위해 기본 동작 취소
	}
	
	// 타이틀 배치
	function drop_title(event){
		console.log("drop title!");
		event.preventDefault();
		$(".testBoard").append('<div class="testBox"><input type="checkbox" /><ul class="testList"><li class="title"><input type="text" placeholder="테스트명" /></li></ul><div class="btnBox"><a onclick="removeBox($(this));">삭제</a><a onclick="checkTestBox($(this));">선택</a></div></div>');
	}
	
	// 드래그 시작시
	function drag_start(thisLi){
		console.log("drag start");
		console.log(thisLi);
	}
	
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
	
	/// alt + 복사 function
	
	
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
                    			<input type="text" class="path" placeholder="확인할 경로를 입력해주세요. ex) c:\project_testTool\chromeDriver\"/>
                    			<a href="javascript:;" class="confirmPathBtn" onclick="confirmPath();">찾기</a>
                    			<div class="resultPath"></div>
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
                	<tbody>
                		<%=driverUrlList%>
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
            				<h3>1. 테스트</h3>
            				<ul>
            					<li>
            						<span>타이틀</span>
            						<ul>
            							<li class="title" onclick="addTitle($(this));" draggable="true" ondragstart="drag_start($(this));">타이틀 생성</li>
            						</ul>
            					</li>
            				</ul>
            			</div>
            			<div class="liBox">
            				<h3>2. 경로</h3>
            				<ul>
            					<li>
            						<span>경로</span>
            						<ul class="path">
            							<li class="xPath" onclick="showPopup($(this));" draggable="true">X경로</li>
            						</ul>
            					</li>
            				</ul>
            			</div>
            			<div class="liBox">
            				<h3>3. 동작</h3>
            				<ul>
	            				<li>
	            					<span>클릭</span>
	            					<ul class="movement">
	            						<li class="enter" onclick="showPopup($(this));" draggable="true">ENTER</li>
	            						<li class="esc" onclick="showPopup($(this));" draggable="true">ESC</li>
	            					</ul>
	            				</li>
	            				<li>
	            					<span>입력</span>
	            					<ul class="movement">
	            						<li class="keyword" onclick="showPopup($(this));" draggable="true">검색어</li>
	            					</ul>
	            				</li>
	            			</ul>	
            			</div>
	                </div>
	            	<div class="testBoard" ondragover="dragover_handler(event);" ondrop="drop_title(event);" ondragenter="drag_enter(event);">
	            	
	                </div>		
            	</div>
            	<div class="btnBox">
                    <a href="">취소</a> <!-- 만들어야 함 -->
                    <a onclick="addTestList();">확인</a>
                </div>
            </div>
        </section>
        
        <!-- 변수 붙일 동작 팝업(위치는 바뀔 수 있음. 일단 기능만 우선적으로 보기 위함) -->
        <div class="popup chooseVariablePopup">
			<a onclick="hidePopup($(this))">X</a>
        	<div class="variableBox"></div>
        </div>
        
    </div>
</body>
</html>