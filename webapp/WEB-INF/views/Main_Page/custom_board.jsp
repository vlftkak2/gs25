<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CustomBoardList</title>
<link href="/gs25/assets/css/customboard.css" rel="stylesheet"
	type="text/css">
</head>
<body>

	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<div id="content">

			<div id="customBoard_main">

				<div id="custom_wrap">
					<div id="main_center">
						<img src="/gs25/assets/images/customcenter/customer_center.jpg">
					</div>

					<div id="store">
						<img src="/gs25/assets/images/customcenter/store.png"
							width="550px">
					</div>
					<div id="tel">
						<img src="/gs25/assets/images/customcenter/tel.png" height="300px">
					</div>
				</div>

				<div id="custom_board">
					<form id="search_form" action="/gs25/custom/list"
						method="get">
						<input type="text" id="kwd" name="kwd" value="${map.keyword }">
						<input type="submit" value="찾기">
					</form>
					<h4>
						전체 글수 : <span>${map.totalCount }</span> ${map.list3 }
					</h4>
					<table class="tbl-ex">
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>글내용</th>
							<th>글쓴이</th>
							<th>조회수</th>
							<th>작성일</th>
							<th>&nbsp;</th>
						
							
						</tr>

						<c:set var="firstIndex"
							value="${map.totalCount - (map.currentPage - 1)*map.sizeList }" />
						<c:forEach var='vo' items='${map.list}' varStatus='status'>
							<tr>
								<c:choose>
									<c:when test='${vo.depth == 1 }'>
										<td><img src="/gs25/assets/images/customcenter/que.PNG"></td>
									</c:when>
									<c:otherwise>
										<td><img src="/gs25/assets/images/customcenter/an.PNG"></td>
									</c:otherwise>
								</c:choose>


								<td style="text-align:left; padding-left:${vo.depth*10}px">

									<c:if test='${vo.depth > 1 }'>
										<img src="/gs25/assets/images/customcenter/re2.png">
									</c:if> <a href="/gs25/custom/viewform?no=${vo.no}&&groupNo=${vo.groupNo}">${vo.title }</a>									
								</td>

								<td>${vo.name }</td>
								<td>${vo.count }</td>
								<td>${vo.date }</td>
								<td><c:choose>
										<c:when
											test='${(not empty authUser && authUser.no == vo.userNo) || (authUser.no==1)  }'>
											<a
												href="/gs25/custom/delete?groupNo=${vo.groupNo}&&groupOrderNo=${vo.groupOrderNo }"
												class="del">삭제</a>
										</c:when>
										<c:otherwise>
            						&nbsp;
              					</c:otherwise>
									</c:choose></td>

							</tr>
						</c:forEach>

					</table>

					<!-- begin:paging -->
					<div class="pager">


						<ul>

							<c:if test="${map.prevtoPage >= 0 }">
								<li><a href="/gs25/custom/list?p=${map.prevtoPage }">◀◀</a></li>
							</c:if>

							<c:if test="${map.prevPage >= 0 }">
								<li><a href="/gs25/custom/list?p=${map.prevPage }">◀</a></li>
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
										<li><a href="/gs25/custom/list?p=${i }">${i }</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>

							<c:if test='${map.nextPage > 0 }'>
								<li><a href="/gs25/custom/list?p=${map.nextPage }">▶</a></li>
							</c:if>
							<c:if test='${map.nexttoPage > 0 }'>
								<li><a href="/gs25/custom/list?p=${map.nexttoPage }">▶▶</a></li>
							</c:if>

						</ul>
					</div>

					<!-- end:paging -->
					<c:choose>
						<c:when test='${empty authUser }'>

   						&nbsp;      
   						</c:when>
						<c:otherwise>
							<div class="bottom">

								<a href="/gs25/custom/write?userno=${authUser.no}" id="new-book">글쓰기</a>
							</div>

						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>

		<jsp:include page="/WEB-INF/views/include/footer.jsp" />

	</div>

</body>
</html>