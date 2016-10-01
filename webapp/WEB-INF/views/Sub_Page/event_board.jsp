<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/gs25/assets/css/eventboard.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/gs25/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript" src="/gs25/assets/js/slideShow.js"></script>
<title>Slide Show</title>
<style>
    #slideShowImages { /* css  */
      border: 1px #f7f7f7 solid;
    }   
  
    #slideShowImages img { /* css */
      border: 0.8em white solid;
      padding: 3px;
    }   
  </style>
</head>
<body>
<div id="container">
	<jsp:include page="/WEB-INF/views/include/subheader.jsp" />
	<div id="content">
	
	<div id="event_main">
	<div id="event_sub">
		<div id="brand_main">
			<h1>이벤트</h1>
		</div>
		
		<div id="event_slide">
			<div id="slideShowImages"> <!--  slideShowImages는 원하는 슬라이드 쇼 이미지를 가리키는 <img> 요소를 포함 -->
		    <c:forEach var='vo' items='${map.list }' varStatus='s'>
		    
		    <img src="${vo.imageurl }" alt="${s.index }" />
		    </c:forEach>
			</div>  
			<img src="/gs25/assets/images/eventboard/stop2.png" id="slideShowButton"></img> <!-- Optional button element. -->
			
		</div>
	
	<div id="Board_main">
		<div id="event_board">
			<form id="search_form" action="/gs25/event/eventlist" method="get">
				<input type="text" id="kwd" name="kwd" value="${map.keyword }">
				<input type="submit" value="찾기">
			</form>
			<table class="tbl-ex">
			<tr>
				<th>번호</th>
				<th>이미지</th>
				<th>이벤트 내용</th>
				<th>조회수</th>
				<th>&nbsp;</th>		
			</tr>
			<c:set var="countList" value="${map.totalCount - (map.currentPage - 1)*map.sizeList }" />
			<c:forEach var='vo' items='${map.list }' varStatus='s'>
			<tr>
				<td>${countList - s.index }</td>
				<td><a href="/gs25/event/view?no=${vo.no}"><img src="${vo.imageurl }" width="250px" height="100px"></a></td>
				<td><a href="/gs25/event/view?no=${vo.no}">${vo.title }</a>
				<p> 이벤트 기간 : ${vo.startdate} ~ ${vo.enddate }</p>
				</td>
				<td>${vo.count }</td>
				<td><c:choose>
						<c:when test='${authUser.no == 1}'>
						<a href="/gs25/event/event_delete?no=${vo.no}" class="del">삭제</a>
						</c:when>
						<c:otherwise>
	            		&nbsp;
	              		</c:otherwise>
					</c:choose></td>
			</tr>
			</c:forEach>
			</table>
			
				<c:if test="${empty map.list}">
							<div id="event_search">
								<div id="event_search_risk">
									<img src="/gs25/assets/images/customcenter/risk.png">
								</div>
								<p class="event_search_list-right">
									검색된 결과를 찾을 수 없습니다. <br>
								</p>
							</div>
						</c:if>
		
		<c:if test='${not empty map.list }'>
			<!-- begin:paging -->
						<div class="pager">
							<ul>

								<c:if test="${map.prevtoPage >= 0 }">
									<li><a href="/gs25/event/eventlist?p=${map.prevtoPage }">◀◀</a></li>
								</c:if>

								<c:if test="${map.prevPage >= 0 }">
									<li><a href="/gs25/event/eventlist?p=${map.prevPage }">◀</a></li>
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
											<li><a href="/gs25/event/eventlist?p=${i }">${i }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:if test='${map.nextPage > 0 }'>
									<li><a href="/gs25/event/eventlist?p=${map.nextPage }">▶</a></li>
								</c:if>
								<c:if test='${map.nexttoPage > 0 }'>
									<li><a href="/gs25/event/eventlist?p=${map.nexttoPage }">▶▶</a></li>
								</c:if>

							</ul>
						</div>
		</c:if>
	
		
		<!-- end:paging -->
			<c:choose>
			<c:when test='${empty authUser }'>

   				&nbsp;      
   			</c:when>
			<c:otherwise>
			<div class="bottom">
				<c:choose>
				<c:when test='${authUser.no==1 }'>
					<a href="/gs25/event/event_write?userno=${authUser.no}" id="new-book">글쓰기</a>
				</c:when>
				<c:otherwise>
					&nbsp;
				</c:otherwise>
				</c:choose>
			</div>
			</c:otherwise>
			</c:choose>
	
		</div>
	</div>

	</div>
	</div>
	</div>
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</div>
</body>
<script>
 $(function() {
	   
	 $('#slideShowButton').on("click", function(){
	      var src = ($(this).attr("src") === "/gs25/assets/images/eventboard/stop2.png") 
	         ? "/gs25/assets/images/eventboard/start.png"
	         : "/gs25/assets/images/eventboard/stop2.png";
	      $(this).attr("src", src);
	   });
}); 
</script>
</html>