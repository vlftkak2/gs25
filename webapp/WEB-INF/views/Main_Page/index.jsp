<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인</title>
<link href="/gs25/assets/css/manage.css" rel="stylesheet" type="text/css">
<link href="/gs25/assets/css/index.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="/gs25/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=c0be589b60311ceeea226dd7d2e0e990"></script>
<link href="/gs25/assets/css/menubar.css" rel="stylesheet" type="text/css">

 <!-- 지도 -->
 <script>
$(function(){
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
	    center: new daum.maps.LatLng(37.5547992, 126.9684953),
	    level: 4 // 지도의 확대 레벨
	};
	var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
	$("#maptab1").click(function (){
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = { 
		    center: new daum.maps.LatLng(37.5547992, 126.9684953),
		    level: 4 // 지도의 확대 레벨
		};
		var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		
		var positions = [
          	<c:forEach var = 'vo' items='${mapvo }' varStatus='s'>
          	{
          	   content: '${vo.name}', 
          	   latlng: new daum.maps.LatLng('${vo.localx}', '${vo.localy}')
          	},
          	</c:forEach>
          ];
          for (var i = 0; i < positions.length; i ++) {
              // 마커를 생성합니다
          	  var marker = new daum.maps.Marker({
          	        map: map, // 마커를 표시할 지도
          	        position: positions[i].latlng // 마커의 위치
          	        
          	    });
          	    // 마커에 표시할 인포윈도우를 생성합니다 
          	    var infowindow = new daum.maps.InfoWindow({
          	        content: positions[i].content // 인포윈도우에 표시할 내용
          	    });
          	    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
          	    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
          	    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
          	    daum.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
          	    daum.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
          	}
	});
	
	$("#maptab2").click(function (){
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = { 
		    center: new daum.maps.LatLng(37.503463, 126.72378),
		    level: 4 // 지도의 확대 레벨
		};
		var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		
		var positions = [
           	<c:forEach var = 'vo' items='${mapvo }' varStatus='s'>
           	{
           	   content: '${vo.name}', 
           	   latlng: new daum.maps.LatLng('${vo.localx}', '${vo.localy}')
           	},
           	</c:forEach>
           ];
           for (var i = 0; i < positions.length; i ++) {
               // 마커를 생성합니다
           	  var marker = new daum.maps.Marker({
           	        map: map, // 마커를 표시할 지도
           	        position: positions[i].latlng // 마커의 위치
           	        
           	    });
           	    // 마커에 표시할 인포윈도우를 생성합니다 
           	    var infowindow = new daum.maps.InfoWindow({
           	        content: positions[i].content // 인포윈도우에 표시할 내용
           	    });
           	    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
           	    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
           	    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
           	    daum.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
           	    daum.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
           	}
	});
	
	$("#maptab3").click(function (){
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = { 
		    center: new daum.maps.LatLng(37.3800181, 126.9264755),
		    level: 4// 지도의 확대 레벨
		};
		var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		
		var positions = [
         	<c:forEach var = 'vo' items='${mapvo }' varStatus='s'>
         	{
         	   content: '${vo.name}', 
         	   latlng: new daum.maps.LatLng('${vo.localx}', '${vo.localy}')
         	},
         	</c:forEach>
         ];
         for (var i = 0; i < positions.length; i ++) {
             // 마커를 생성합니다
         	  var marker = new daum.maps.Marker({
         	        map: map, // 마커를 표시할 지도
         	        position: positions[i].latlng // 마커의 위치
         	        
         	    });
         	    // 마커에 표시할 인포윈도우를 생성합니다 
         	    var infowindow = new daum.maps.InfoWindow({
         	        content: positions[i].content // 인포윈도우에 표시할 내용
         	    });
         	    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
         	    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
         	    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
         	    daum.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
         	    daum.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
         	}
	});
		
//마커를 표시할 위치와 title 객체 배열입니다 
var positions = [
	<c:forEach var = 'vo' items='${mapvo }' varStatus='s'>
	{
	   content: '${vo.name}', 
	   latlng: new daum.maps.LatLng('${vo.localx}', '${vo.localy}')
	},
	</c:forEach>
];
for (var i = 0; i < positions.length; i ++) {
    // 마커를 생성합니다
	  var marker = new daum.maps.Marker({
	        map: map, // 마커를 표시할 지도
	        position: positions[i].latlng // 마커의 위치
	        
	    });
	    // 마커에 표시할 인포윈도우를 생성합니다 
	    var infowindow = new daum.maps.InfoWindow({
	        content: positions[i].content // 인포윈도우에 표시할 내용
	    });
	    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
	    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
	    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
	    daum.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
	    daum.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
	}
// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
function makeOverListener(map, marker, infowindow) {
    return function() {
        infowindow.open(map, marker);
    };
}
// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
function makeOutListener(infowindow) {
    return function() {
        infowindow.close();
    };
}
});
</script>
 
</head>
<body>
<div id="container">

<jsp:include page="/WEB-INF/views/include/header.jsp" />
<div id="content">
		<jsp:include page="/WEB-INF/views/include/Mainmenubar.jsp" />
	
		<div class="container_index">
		
		<div id="seperate">
		<img src="/gs25/assets/images/index/gs25_03.gif"/>
		</div>
		<div id="map"></div>
		<div id="text_index">
		<h4 id="index_map">GS25 매장</h4>
<!-- 		<p id="tit"></p> -->
		</div>
		
			<div id="tap_content" style="height: 200px;">
			    <ul class="tabs" style="height: 57px;">
			    	<c:forEach var='regionvo' items='${regionvo}' varStatus='status'>
						<c:choose>
							<c:when test="${status.index == 0}">
								<li rel="tab1" class="on" id="maptab1">${regionvo.name}</li>
							</c:when>
							<c:otherwise>
								<li rel="tab${status.index + 1}" id="maptab${status.index + 1}">${regionvo.name}</li>
							</c:otherwise>
					</c:choose>
				</c:forEach>
			    </ul>
			    <div class="tab_container">
			        <div id="tab1" class="tab_content" style="height: 100px;">
	       				<div class="market_list">
						<div class="markey_list_sub">
		       			<ul class="market_list_point" style="margin: 20px 5px;">
		       				<c:forEach var='vo' items='${storevo}' varStatus='status'>
		       					<c:if test="${vo.region_name  == '서울'}">
			       				<c:choose>
									<c:when test='${vo.no  == 0}'>
									 	<li id="nopad"><a href="/gs25/main?" class="on">${vo.name }</a></li>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${status.index == 0}">
												<li id="nopad"><a href="/gs25/sub/main?store_no=${vo.no }" class="on">${vo.name }</a></li>
											</c:when>
											<c:otherwise>
												<li id="nopad"><a href="/gs25/sub/main?store_no=${vo.no }">${vo.name }</a></li>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
								</c:if>
							</c:forEach>
						</ul>
						</div>
						</div>
					</div>
					<div id="tab2" class="tab_content" style="height: 100px;">
						<div class="market_list">
						<div class="markey_list_sub">
		       			<ul class="market_list_point" style="margin: 20px 5px;">
		       				<c:forEach var='vo' items='${storevo}' varStatus='status'>
		       					<c:if test="${vo.region_name  == '인천'}">
			       				<c:choose>
									<c:when test='${vo.no  == 0}'>
									 	<li id="nopad"><a href="/gs25/main?" class="on">${vo.name }</a></li>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${status.index == 0}">
												<li id="nopad"><a href="/gs25/sub/main?store_no=${vo.no }" class="on">${vo.name }</a></li>
											</c:when>
											<c:otherwise>
												<li id="nopad"><a href="/gs25/sub/main?store_no=${vo.no }">${vo.name }</a></li>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
								</c:if>
							</c:forEach>
						</ul>
						</div>
						</div>
					</div>
					<div id="tab3" class="tab_content" style="height: 100px;">
						<div class="market_list">
						<div class="markey_list_sub">
		       			<ul class="market_list_point" style="margin: 20px 5px;">
		       				<c:forEach var='vo' items='${storevo}' varStatus='status'>
		       					<c:if test="${vo.region_name  == '안양'}">
			       				<c:choose>
									<c:when test='${vo.no  == 0}'>
									 	<li id="nopad"><a href="/gs25/main?" class="on">${vo.name }</a></li>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${status.index == 0}">
												<li id="nopad"><a href="/gs25/sub/main?store_no=${vo.no }" class="on">${vo.name }</a></li>
											</c:when>
											<c:otherwise>
												<li id="nopad"><a href="/gs25/sub/main?store_no=${vo.no }">${vo.name }</a></li>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
								</c:if>
							</c:forEach>
						</ul>
						</div>
						</div>
					</div>
				</div>
			</div>
			<div class="sns_sect">

	<div class="fb_box">
		<div id="happy">
		
		<iframe src="http://www.facebook.com/plugins/likebox.php?href=http%3A%2F%2Fwww.facebook.com%2FfunGS25&width=300&colorscheme=light&show_faces=true&
		stream=true&header=false&height=380" scrolling="no" frameborder="0" style="border:none; width:300px; height:380px;" allowtransparency="true">
		</iframe>
		</div>
		<div id="happy2">
		<p align="middle">
		<iframe width="600" height="330" src="https://www.youtube.com/embed/TOwRyMilFGQ" frameborder="0" allowfullscreen></iframe>
		</p>
		</div>
		
	</div>

</div>
	</div>
</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
<script>
$(function () {
    $(".tab_content").hide();
    $(".tab_content:first-child").show();
    $("ul.tabs li").click(function () {
        $("ul.tabs li").removeClass("on").css("color", "#333");
        $(this).addClass("on").css("color", "#397ca8");
        $(".tab_content").hide();
        var activeTab = $(this).attr("rel");
    	console.log(activeTab);
        $("#" + activeTab).fadeIn();
    });
});
</script>

<script>
	$( window ).scroll( function() {
	    if ( $( this ).scrollTop() > 200 ) {
	      $( '.top' ).fadeIn();
	    } else {
	      $( '.top' ).fadeOut();
	    }
	  } );
	  $( '.top' ).click( function() {
	    $( 'html, body' ).animate( { scrollTop : 0 }, 400 );
	    return false;
	  } );
</script>
</html>