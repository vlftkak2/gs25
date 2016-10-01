<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	pageContext.setAttribute( "newLine", "\n" );
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/gs25/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
</head>
<body>
<div id="container">
		<jsp:include page="/WEB-INF/views/include/subheader.jsp" />
		<div id="content">
			<div id="guestbook">
				<form action="/gs25/guestbook/write?store_no=4" method="post">
				
				<h4 id="br">
					전체 글수 : <span>${map.totalCount }</span>
				</h4>
				
					<table class="tbl-ex">
						<tr>
							<th id=auth_email colspan=2>아이디: ${authUser.email }</th>
						</tr>
						
						<tr>
							<td colspan=2>
							<textarea name="content" id="content" placeholder="내용을 입력하세요."></textarea>
							</td>
						</tr>
						
						<tr>
							<td colspan=2 align=right><input type="submit" VALUE=" 확인 "
							class="btn btn-primary btn-block"></td>
						</tr>
					</table>
				</form>
				
				
				
				<ul>
					<c:set var='countList' value='${map.totalCount - (map.currentPage - 1)*map.sizeList }'/>
					<c:forEach var='vo' items='${map.list }' varStatus='s'>
						<li>
							<table class="tbl-ex">
								<tr>
									<td>[${countList - s.index }]</td>
									
									<td>
									<c:choose>
									<c:when test='${not empty authUser && authUser.no == vo.user_no }'>
										<a href="/gs25/Sub_Page/delete?no=${vo.no }" class="del">삭제</a>
									</c:when>
									<c:otherwise>
										&nbsp;
									</c:otherwise>
									</c:choose>
									</td>
								</tr>
								
								
								<tr>
									<td id="size">등록일: ${vo.reg_date}</td>
							
								<td>작성자: ${vo.email }</td>
								</tr>
								
								<tr>
									<td colspan=2>
									${fn:replace(vo.content, newLine, "<br>") }	
									</td>
								</tr>
											
							</table>
							<br>
							
						</li>
					</c:forEach>
				</ul>
				
				
					<!-- begin:paging -->
				<div class="pager">
							<ul>

								<c:if test="${map.prevtoPage >= 0 }">
									<li><a href="/gs25/guestbook/list?p=${map.prevtoPage }">◀◀</a></li>
								</c:if>

								<c:if test="${map.prevPage >= 0 }">
									<li><a href="/gs25/guestbook/list?p=${map.prevPage }">◀</a></li>
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
											<li><a href="/gs25/guestbook/list?p=${i }">${i }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:if test='${map.nextPage > 0 }'>
									<li><a href="/gs25/guestbook/list?p=${map.nextPage }">▶</a></li>
								</c:if>
								<c:if test='${map.nexttoPage > 0 }'>
									<li><a href="/gs25/guestbook/list?p=${map.nexttoPage }">▶▶</a></li>
								</c:if>

							</ul>
						</div>
				

		
			</div>
		</div>
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>