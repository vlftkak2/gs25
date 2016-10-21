<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/gs25/assets/css/manage.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/gs25/assets/js/jquery/jquery-1.9.0.js"></script>
<link href="/gs25/assets/css/menubar.css" rel="stylesheet" type="text/css">


</head>
<body>
<div id="container">
<jsp:include page="/WEB-INF/views/include/header.jsp" />

	
	<div id="content" style="padding: 80px 0;">
	<jsp:include page="/WEB-INF/views/include/Mainmenubar.jsp" />
	
	<div id="manage_wrap">
		<div id="title1">
			<h1>회원관리</h1>
			<h4>
				전체 회원수 :  <span>${total }</span>
			</h4>
		</div>
		
		<div id="tap_content">
		    <ul class="tabs">
		        <li class="active" rel="tab1">CUSTOMER</li>
		        <li rel="tab2">BRANCH</li>
		    </ul>
		    <div class="tab_container">
		        <div id="tab1" class="tab_content">
		        	<h4>
						CUSTOMER 회원수 :  <span>${map.totalCount }</span>
					</h4>
					
					<table class="tbl-ex">
						<tr>
							<th>번호</th>
							<th>이름</th>
							<th>아이디</th>
							<th>비밀번호</th>
							<th>생년월일</th>
							<th>성별</th>
							<th>주소</th>
							<th>연락처</th>
							<th>포인트</th>
							<th>직책</th>
				
						</tr>
						<c:set var="firstIndex"	value="${map.totalCount - (map.currentPage - 1)*map.sizeList }" />
						<c:forEach var='vo' items='${map.list}' varStatus='status'>
							<tr>
							<td>${countList - s.index }</td>
							<td>${vo.name }</td>
							<td>${vo.email }</td>
							<td>${vo.password }</td>
							<td>${vo.birth }</td>
							<td>${vo.gender }</td>
							<td>${vo.address }</td>
							<td>${vo.phone }</td>
							<td>${vo.point }</td>
							<td>${vo.position }</td>
							
							</tr>
						</c:forEach>
					</table>
					
					<div class="pager">
						<ul>
							<c:if test="${map.prevtoPage >= 0 }">
								<li><a href="/gs25/user/mlist?p=${map.prevtoPage }">◀◀</a></li>
							</c:if>

							<c:if test="${map.prevPage >= 0 }">
								<li><a href="/gs25/user/mlist?p=${map.prevPage }">◀</a></li>
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
										<li><a href="/gs25/user/mlist?p=${i }">${i }</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>

							<c:if test='${map.nextPage > 0 }'>
								<li><a href="/gs25/user/mlist?p=${map.nextPage }">▶</a></li>
							</c:if>
							<c:if test='${map.nexttoPage > 0 }'>
								<li><a href="/gs25/user/mlist?p=${map.nexttoPage }">▶▶</a></li>
							</c:if>

						</ul>
					</div>
		        </div>
		        <div id="tab2" class="tab_content">
		        	<h4>
						BRANCH 회원수 :  <span>${map2.totalCount }</span>
					</h4>
					
					<table class="tbl-ex">
						<tr>
							<th>번호</th>
							<th>이름</th>
							<th>아이디</th>
							<th>비밀번호</th>
							<th>생년월일</th>
							<th>성별</th>
							<th>주소</th>
							<th>연락처</th>
							<th>포인트</th>
							<th>직책</th>
							<th>매장번호</th>
					
						</tr>
						<c:set var="firstIndex"	value="${map2.totalCount - (map2.currentPage - 1)*map2.sizeList }" />
						<c:forEach var='vo' items='${map2.list}' varStatus='status'>
							<tr>
							<td>${countList - s.index }</td>
							<td>${vo.name }</td>
							<td>${vo.email }</td>
							<td>${vo.password }</td>
							<td>${vo.birth }</td>
							<td>${vo.gender }</td>
							<td>${vo.address }</td>
							<td>${vo.phone }</td>
							<td>${vo.point }</td>
							<td>${vo.position }</td>
							<c:choose>
								<c:when test='${vo.store_no != 0 }'>
									<td>${vo.store_no }</td>
								</c:when>
								<c:otherwise>
									<td>X</td>
								</c:otherwise>
							</c:choose>
						
							</tr>
						</c:forEach>
					</table>
					
					<div class="pager">
						<ul>
							<c:if test="${map.prevtoPage >= 0 }">
								<li><a href="/gs25/user/mlist?p=${map.prevtoPage }">◀◀</a></li>
							</c:if>

							<c:if test="${map.prevPage >= 0 }">
								<li><a href="/gs25/user/mlist?p=${map.prevPage }">◀</a></li>
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
										<li><a href="/gs25/user/mlist?p=${i }">${i }</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>

							<c:if test='${map.nextPage > 0 }'>
								<li><a href="/gs25/user/mlist?p=${map.nextPage }">▶</a></li>
							</c:if>
							<c:if test='${map.nexttoPage > 0 }'>
								<li><a href="/gs25/user/mlist?p=${map.nexttoPage }">▶▶</a></li>
							</c:if>

						</ul>
					</div>
		        </div>
		    </div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</div>
</body>

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

<script>
$(function () {
    $(".tab_content").hide();
    $(".tab_content:first").show();
    $("ul.tabs li").click(function () {
        $("ul.tabs li").removeClass("active").css("color", "#333");
        $(this).addClass("active").css("color", "darkred");
        $(".tab_content").hide()
        var activeTab = $(this).attr("rel");
        $("#" + activeTab).fadeIn()
    });
});
</script>
</html>