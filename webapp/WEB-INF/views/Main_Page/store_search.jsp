<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!doctype html>
<html>
<head>
<title>지도 검색</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/gs25/assets/css/map.css" rel="stylesheet" type="text/css">


<script type="text/javascript"
	src="//apis.daum.net/maps/maps3.js?apikey=c0be589b60311ceeea226dd7d2e0e990"></script>
<script type="text/javascript"
	src="/gs25/assets/js/jquery/jquery-1.9.0.js"></script>
<link href="/gs25/assets/css/menubar.css" rel="stylesheet" type="text/css">
	

<script>
$(function(){

if('${map.keyword}'=='서울' || '${map.keyword}'== '서울역' || '${map.keyword}'== 'GS25서울역점'  || '${map.keyword}'== '' ){	
	
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = { 
    center: new daum.maps.LatLng(37.5547992, 126.9684953),
    level: 4 // 지도의 확대 레벨
};

var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
}else if('${map.keyword}'=='파리바게트' || '${map.keyword}'=='파리' ){	
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
	    center: new daum.maps.LatLng(37.503238, 126.7204644),
	    level: 4// 지도의 확대 레벨
	};
	
	var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
}else if('${map.keyword}'=='부평고등학교'  ){	
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
	    center: new daum.maps.LatLng(37.5017369, 126.7255239),
	    level: 4// 지도의 확대 레벨
	};
	
	var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
}else if('${map.keyword}'=='아인스월드' || '${map.keyword}'=='아인스' ){	
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
	    center: new daum.maps.LatLng(37.5550432, 126.9662053),
	    level: 4// 지도의 확대 레벨
	};
	var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
}else if('${map.keyword}'=='삼성전자' || '${map.keyword}'=='삼성' ){	
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
	    center: new daum.maps.LatLng(37.556897, 126.968231),
	    level: 4// 지도의 확대 레벨
	};
	
	var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
}else if('${map.keyword}'=='명학공원' || '${map.keyword}'=='명학' ){	
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
	    center: new daum.maps.LatLng(37.384182, 126.933465),
	    level: 4// 지도의 확대 레벨
	};
	
	var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
}else if('${map.keyword}'=='안양경찰서' || '${map.keyword}'=='경찰서' ){	
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
	    center: new daum.maps.LatLng(37.391171, 126.9466219),
	    level: 4// 지도의 확대 레벨
	};

var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

}else if('${map.keyword}'=='인천' || '${map.keyword}'=='GS25래미안아파트점' || '${map.keyword}'=='래미안아파트' ){	
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
	    center: new daum.maps.LatLng(37.503463, 126.72378),
	    level: 4// 지도의 확대 레벨
	};

	var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	}else if('${map.keyword}'=='안양' || '${map.keyword}'=='GS25성결점' || '${map.keyword}'=='성결대' ){	
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
	    center: new daum.maps.LatLng(37.3800181, 126.9264755),
	    level: 4 // 지도의 확대 레벨
	};

	var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

	}else if('${map2.keyword}'==null){
		
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = { 
		    center: new daum.maps.LatLng(37.5547992, 126.9684953),
		    level: 4 // 지도의 확대 레벨
		};
	}
	
//마커를 표시할 위치와 title 객체 배열입니다 
var positions = [
	<c:forEach var = 'vo' items='${map2.list}' varStatus='s'>
	 
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

			<div class="container_map">
				<div class="container_mapsub">

					<div id="store_header">
						<div id="store">
							<h1 id="sub_h1">매장검색</h1>
						</div>
						<div id="txt_header">
							<p>전국 곳곳GS25매장을 안내해드립니다.</p>
							<p>지역별 매장 또는 매장명 검색으로 GS25를 찾아보세요</p>
						</div>

					</div>

					<div id="search">
						<form id="search_form" action="/gs25/map/list" method="get">
							<input type="text" id="kwd" name="kwd" value="${map.keyword }">
							<input type="submit" value="찾기">
						</form>
					</div>

					<div id=map_container>
						<div id="board">
							<table class="tbl-ex">
								<tr>
									<th>매장정보</th>
									<th>주소/제공서비스</th>
									<th>지점</th>

								</tr>
								<c:forEach var='vo' items='${map.list}' varStatus='status'>
									<tr>
										<td>${vo.name }</td>
										<td>${vo.address }</td>
										<td><a href="/gs25/sub/main?store_no=${vo.map_no }">바로가기</a></td>
									</tr>
								</c:forEach>
							</table>
							<c:if test='${empty map.list }'>
								<div id="map_right">
									<div id="map_risk">
										<img src="/gs25/assets/images/customcenter/risk.png">
									</div>
									<p class="map_list-right">
										검색된 결과를 찾을 수 없습니다. <br>
									</p>
								</div>
							</c:if>
							<c:if test='${not empty map.list }'>
								<!-- begin:paging -->
								<div class="pager">
									<ul>

										<c:if test="${map.prevtoPage >= 0 }">
											<li><a href="/gs25/map/list?p=${map.prevtoPage }">◀◀</a></li>
										</c:if>

										<c:if test="${map.prevPage >= 0 }">
											<li><a href="/gs25/map/list?p=${map.prevPage }">◀</a></li>
										</c:if>


										<c:forEach begin='${map.firstPage }' end='${map.lastPage }'
											step='1' var='i'>
											<c:choose>
												<c:when test='${map.currentPage == i }'>
													<li class="selected">${i }</li>
												</c:when>

												<c:when test='${i > map.pageCount }'>
													<li>${i }</li>
												</c:when>

												<c:otherwise>
													<li><a href="/gs25/map/list?p=${i }">${i }</a></li>
												</c:otherwise>
											</c:choose>
										</c:forEach>

										<c:if test='${map.nextPage > 0 }'>
											<li><a href="/gs25/map/list?p=${map.nextPage }">▶</a></li>
										</c:if>
										<c:if test='${map.nexttoPage > 0 }'>
											<li><a href="/gs25/map/list?p=${map.nexttoPage }">▶▶</a></li>
										</c:if>
									</ul>
								</div>
							</c:if>
						</div>
						<div id="map"></div>
					</div>

				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>

</body>
</html>