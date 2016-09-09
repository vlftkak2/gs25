(function(){

if('${map.keyword}'=='서울' || '${map.keyword}'== ""){	
	
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = { 
    center: new daum.maps.LatLng(37.5547992, 126.9684953),
    level: 6 // 지도의 확대 레벨
};

var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

}

if('${map.keyword}'=='인천'){	
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
	    center: new daum.maps.LatLng(37.503463, 126.72378),
	    level: 7 // 지도의 확대 레벨
	};

	var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	}
	
if('${map.keyword}'=='안양'){	
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
	    center: new daum.maps.LatLng(37.393538, 126.924471),
	    level: 7 // 지도의 확대 레벨
	};

	var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

	}

var list='${map.list}'
	
//마커를 표시할 위치와 title 객체 배열입니다 
var positions = {
	<c:forEach var = 'vo' items='${map.list}' varStatus='s'>
	 
	{
	   content: '${vo.name}', 
	   latlng: new daum.maps.LatLng('${vo.localx}', '${vo.localy}')
	    },
	</c:forEach>
};

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
