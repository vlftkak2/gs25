<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/gs25/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<link href="/gs25/assets/css/board.css" rel="stylesheet" type="text/css">

<body>

<div id="search"> 
<form id="search_form" action="/gs25/product/list" method="get">
<input type="text" id="kwd" name="kwd" value="${map.keyword }"> 
<input type="submit" value="찾기">
</form>
</div>

<div class="product_list" style="margin-left:40px; margin-top:50px;">

<ul>

<c:forEach var='vo' items='${map.list}' varStatus='status'>

<li style="float:left; top: 50px; left: 192px; z-index: 3; border: 1px solid rgb(215, 215, 215); height: 250px; width: 350px;">
<ul class="tag_list_01">
<li class="ico_tag_03"></li>
</ul>
<div class="pic_product">
<img src="${vo.producturl }" alt="형민)맛있는도시락" width="80px" height="130px" >
<div class="infowrap">
<div class="name">${vo.name }</div>
<div class="price">${vo.price } </div> 
</div>
</div>
</li>

</c:forEach>


</ul>

</div>


<!-- begin:paging -->
	<div class="pager">
		<ul>
		
			<c:if test="${map.prevtoPage >= 0 }">
			<li><a href="/gs25/product/list?p=${map.prevtoPage }">◀◀</a></li>
			</c:if>
		
			<c:if test="${map.prevPage >= 0 }">
			<li><a href="/gs25/product/list?p=${map.prevPage }">◀</a></li>
			</c:if>
			
			
			<c:forEach begin='${map.firstPage }' end='${map.lastPage }' step='1' var='i'>
				<c:choose>
					<c:when test='${map.currentPage == i }'>
						<li class="selected">${i }</li>
					</c:when>

					<c:when test='${i > map.pageCount }'>
						<li>${i }</li>
					</c:when>

					<c:otherwise>
						<li><a href="/gs25/product/list?p=${i }">${i }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

				<c:if test='${map.nextPage > 0 }'>
				<li><a href="/gs25/product/list?p=${map.nextPage }">▶</a></li>
				</c:if>
				<c:if test='${map.nexttoPage > 0 }'>
				<li><a href="/gs25/product/list?p=${map.nexttoPage }">▶▶</a></li>
				</c:if>
				</ul>
			</div>






</body>
</html>