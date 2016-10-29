<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/gs25/assets/css/attendance_check.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type='text/javascript' src='/gs25/assets/js/jquery.simplemodal.js'></script>
<script src="/gs25/assets/js/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/gs25/assets/css/sweetalert.css">




<title>출석 체크</title>

</head>
<body>
	<jsp:include page="/WEB-INF/views/include/subheader.jsp" />
	<div class="container">
		<div class="title">
			<h1>매일매일 출석체크</h1>
			<p>GS25 출석체크 이벤트!! 한달 내에 출결 횟수에 따라 다양한 선물이 주어집니다.</p>
		</div>
			<div class="inform">
				<ul>
					<li><p><strong id="stro1">10</strong>일 달성할 경우 : <strong id="stro2">1000</strong>원 이하 랜덤으로 선물 기프티콘으로 증정</p></li>
					<li><p><strong id="stro1">20</strong>일 달성할 경우 : <strong id="stro2">2000</strong>원 이하  랜덤으로 선물 기프티콘으로 증정</p></li>
					<li><p><strong id="stro1">30</strong>일 달성할 경우 : <strong id="stro2">5000</strong>원 GS25 상품권 증정</p></li>
				</ul>
				<div id="giftImg"></div>
			</div>
			<form class="event_cal">
				<div class="count">나의 출석횟수:<em id="count">${count }</em> &nbsp;회</div>
				<!-- <input type="button" id="goods" value="증정품"> -->
			</form>
		
		<div class="float_clear"></div>
		
		<div class="cal_box">
			<table>
				<thead>
					<tr>
						<th><div class="th_wrap">일요일</div></th>
						<th><div class="th_wrap">월요일</div></th>
						<th><div class="th_wrap">화요일</div></th>
						<th><div class="th_wrap">수요일</div></th>
						<th><div class="th_wrap">목요일</div></th>
						<th><div class="th_wrap">금요일</div></th>
						<th><div class="th_wrap">토요일</div></th>
					</tr>
				</thead>
				<!--도장  w 114px h 77px -->
				<tbody>
					<script>
					var today = new Date();
					var y = today.getFullYear(); // 현재 연도
					var m = today.getMonth(); // 현재 월
					// 현재 (y)년(m)월의 1일의 요일을 구합니다.
					var theDate = new Date(y,m,1);	//달력 1일
					var theDay = theDate.getDay();	//1일 요일 (1 = 월요일)
					/* 현재 월의 마지막 일을 구합니다. */
					// 1월부터 12월까지의 마지막 일을 배열로 만듭니다.
					var last = [31,28,31,30,31,30,31,31,30,31,30,31];
					 
					// 4년마다 있는 윤년을 계산합니다.(100년||400년 주기는 제외)
					if (y%4 && y%100!=0 || y%400===0) {
					    lastDate = last[1] = 29;
					}
					 // 현재 월의 마지막 일이 며칠인지 구합니다.
					var lastDate = last[m];
					/* 현재 월의 달력에 필요한 행의 개수를 구합니다. */
					    // theDay(빈 칸의 수), lastDate(월의 전체 일수)
					var row = Math.ceil((theDay+lastDate)/7);
					
					var date = 1;
						for (var i = 1; i <= row; i++) {
							document.write(
									"<tr>"
								)
							for(var j = 1; j <= 7; j++){
								if(i===1 && j<=theDay || date>lastDate){
									document.write("<td> <div class='td_wrap'>&nbsp</div></td>")
								}else{
								document.write(
										"<td>"+
										"<div class='td_wrap' id="+date+" style='background-color: rgb(238, 251, 248);'>"+
											"<div class='td_date'>"+date+"</div>"+
											"<div class='td_img'>"+
												"<img src='https://hpsimg.gsretail.com/medias/sys_master/images/images/h7f/h2e/8927663915038.png' width='114px' height='77px' id='giftImgNum_25' style='margin-top:7px;'>"+
											"</div>"+
											"<div class='gift_txt' id='giftTitleNum_25'>"+
											"</div>"+
											"<div class='prs_num'>3점</div>"+
										"</div>"+
										"</td>"
									)
									date++;
								}
							}
							document.write(
									"</tr>"
								)
						}
					</script>
				</tbody>
			</table>
		</div>
	</div>
				
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
<!-- 	<div class="fontTest">fontTestfontTest</div> -->
</body>
<script>
$(function() {		
	var today = new Date();
	var y = today.getFullYear(); // 현재 연도
	var m = today.getMonth(); // 현재 월
	var d = today.getDate();
	var i = 1;
	/* $("#"+d).append("<div id='toDbox'><p id='flag'></p><c:forEach var='checkeventvo' items='${checkeventvo }'><c:if test='+"dates"+ ne "+d+"'><input id='checkDate' type='button' value='출석체크'></c:if></c:forEach></div>"); */
	
	/* $("#"+d).append("<div id='toDbox'><p id='flag'></p><input id='checkDate' type='button' value='출석체크'></div>"); */
	
	//이전 date
	if( i < d){
		for(var k = i; k<d; k++){
			$("#"+k).append("<div id='black'></div>");
		}
	}
	
	//오늘 출첵 여부
	var validation = false;
	
	//출석 체크 현황
	<c:forEach var='checkeventvo' items='${checkeventvo }' varStatus='status'>
		var dates = ${checkeventvo.dates };
		$("#"+dates).append("<div id='stamp'></div>");
		
		//오늘 출첵 여부 판단
		if (dates == d ){
			validation = true;
		} else {
			validation = false;
		}
	</c:forEach>
		
		//오늘 출첵 여부 결과 별 출력 
		if(validation == true){
			$("#"+d).append("<div id='toDbox'><p id='flag'></p></div>");
		} else{
			$("#"+d).append("<div id='toDbox'><p id='flag'></p><input id='checkDate' type='button' value='출석체크'></div>");
		}
		
		//출석체크 클릭 시
		 $("#checkDate").on("click", function(){
			 
			 var store_no = ${store_no};
			 console.log(store_no);
			 console.log('click');
			 $.ajax({	
					url: "/gs25/sub/checkDate",
					type: "POST",
					/* date:, */
					dataType: "text",
					success: function(result){	//비동기식으로 진행되어 결과와 상관 없이 submit되므로 계속 refres됨(따로 동기식으로 변경해야함)
						console.log(result);
						 if(result == "0"){
							console.log(result);
							sweetAlert("죄송합니다. 다시 시도해주세요!");
							return false;
						}
						
				if(result == "1"){
					 if('${count}' == 9){
						  console.log(10);
						  $.ajax({	
								url: "/gs25/product/random1000",
								data: {"store_no":store_no},
								dataType: "json",
								type: "POST",
								/* contentType: "application/json", */
								success: function( storeproductvo ){	//비동기식으로 진행되어 결과와 상관 없이 submit되므로 계속 refres됨(따로 동기식으로 변경해야함)
										console.log(storeproductvo);
										$("#goodsimg").attr('src',storeproductvo.imageurl);
										$("#goodsname").html(storeproductvo.name);
								},
								error: function(jsXHR, status, e){
									console.error("error:"+status+":"+e);
								}
							});
							  
							$("#modal_content").modal();
								
					  } else if('${count}' == 19){
						  
						  $.ajax({	
								url: "/gs25/product/random2000",
								data: {"store_no":store_no},
								dataType: "json",
								type: "POST",
								/* contentType: "application/json", */
								success: function( storeproductvo ){	//비동기식으로 진행되어 결과와 상관 없이 submit되므로 계속 refres됨(따로 동기식으로 변경해야함)
										console.log(storeproductvo);
										$("#goodsimg").attr('src',storeproductvo.imageurl);
										$("#goodsname").html(storeproductvo.name);
								},
								error: function(jsXHR, status, e){
									console.error("error:"+status+":"+e);
								}
							});
						  
							$("#modal_content").modal(); 
							
					  } else if('${count}' == 29){
						  
					  }else{
							sweetAlert("감사합니다. 출석되셨습니다.");
					  }
				}
					$("#checkDate").hide();
					$("#count").html('${count + 1 }');
					$("#"+d).append("<div id='stamp'></div>");
				},
				error: function(jsXHR, status, e){
					console.error("error:"+status+":"+e);
				}
			});
		 });
	
		$("#goods").click(function(){
			$("#modal_content").modal(); 
		});
		$("#m_close").click(function(){
			$.modal.close();
		 });
	
});
/* var rtnValue= window.showModalDialog(url, args, option);
function action_pop(url) {
    //객체생성후 데이터 할당
    var obj = new Object();
    obj.msg1 = "test1";
    obj.msg2 ="test2";
    var rtnValue = window.showModalDialog(url, obj, "dialogWidth:980px;dialogHeight:600px;status:no;help:no;location:no");
}  */
</script>
</html>
<div id="modal_content">
	<div class="Modal_head"	>
		<h1 id="Mod_title">출석체크 랜덤 상자</h1>
		<input type="button" id="m_close">
	</div>
	<div class="container">
		<div id="m_giftImg"></div>
		<div class="title">
		<c:choose>
			<c:when test='${count == 9}'>
				<h1 class="mod_move"><em id="m_count">10회 </em> 달성!!!</h1>
				<p class="mod_move2">10회 이상 달성하셨으므로 <em id="m_money">1000원 이하 상당의 상품</em>을 기프티콘으로 드립니다.</p>
			</c:when>
			<c:when test='${count == 19}'>
				<h1 class="mod_move"><em id="m_count">20회</em> 달성!!!</h1>
				<p class="mod_move2">20회 이상 달성하셨으므로 <em id="m_money">2000원 이하 상당의 상품</em>을 기프티콘으로 드립니다.</p>
			</c:when>
			<c:when test='${count == 29}'>
				<h1 class="mod_move"><em id="m_count">30회</em> 달성!!!</h1>
				<p class="mod_move2">거의 모두 달성하셨으므로  <em id="m_money">5000원 상당 상품권</em>을 드립니다.</p>
			</c:when>
			<c:otherwise>
				<h1 class="mod_move">달성하지 못하셨습니다. ${count }</h1>
				<p class="mod_move2">조금만 더 힘내서 많은 선물 받아가세요~ ^^</p>
			</c:otherwise>
		</c:choose>
		</div>
 		<div class="giftInfo">
 			<c:choose>
				<c:when test='${count == 9}'>
					<img id="goodsimg" src="">
			 		<h2 id="goodsname"></h2>
			 		<p>GS25 편의점에서 사용 가능합니다. 매장 위치는 홈페이지를 참조해주세요.</p>
				</c:when>
				<c:when test='${count == 19}'>
					<img id="goodsimg" src="">
			 		<h2 id="goodsname"></h2>
			 		<p>GS25 편의점에서 사용 가능합니다. 매장 위치는 홈페이지를 참조해주세요.</p>
				</c:when>
				<c:when test='${count == 29}'>
					<img src="/gs25/assets/images/subindex/gs25Certi.png" style= "margin: 37px;">
			 		<h2>GS25 - 5000원 상품권</h2>
			 		<p>발급일로부터 3달 이내에 사용하시기 바랍니다. 자세한 내용은 홈페이지를 참조해주세요.</p>
				</c:when>
				<c:otherwise>
					<img src="/gs25/assets/images/login/sorryman.png" style="margin: 65px 229px;">
			 		<h3 style="margin: 11px 70px;">조금만 더 힘내서 출석체크하시며 다양한 선물을 챙겨가세요! 힘내세요!</h3>
				</c:otherwise>
			</c:choose>		
 		</div>
 		<c:if test='${count == 9}'>
			<h2 id="mod_last">위 기프티콘은 회원님의 <em id="m_empha">이메일</em>로 보내드렸습니다. 축하합니다. 고객님</h2>
		</c:if>
		<c:if test='${count == 19}'>
			<h2 id="mod_last">위 기프티콘은 회원님의 <em id="m_empha">이메일</em>로 보내드렸습니다. 축하합니다. 고객님</h2>
		</c:if>
		<c:if test='${count == 29}'>
			<h2 id="mod_last">위 기프티콘은 회원님의 <em id="m_empha">이메일</em>로 보내드렸습니다. 축하합니다. 고객님</h2>
		</c:if>
	</div>
 </div>