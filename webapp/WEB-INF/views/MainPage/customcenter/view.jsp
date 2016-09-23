<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	pageContext.setAttribute("newLine", "\n");
%>
<title>gs25_view</title>
<link href="/gs25/assets/css/customboard.css" rel="stylesheet" type="text/css">

</head>
<body>

	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />

		<div id="content">
			<div id="customBoard_main">
				<div id="customBoard_sub">
					<div id="wrap">
						<h1>고객센터</h1>
					</div>

					<div id="board" class="board-form">
						<table class="tbl-ex">
							<tr>
								<th colspan="2">글보기</th>
							</tr>
							<tr>
								<td class="label">제목</td>
								<td>${vo.title }</td>
							</tr>
							<tr>
								<td class="label">내용</td>
								<td>
									<div class="view-content">${fn:replace(vo.content, newLine, "<br>") }
									</div>
								</td>
							</tr>
							<tr>
								<td class="label">첨부파일</td>
							<td id="attachFile" data-fno="${attachFileVO.fNO }">${attachFileVO.orgName }</td>
							</tr>
						</table>
						<img alt="이미지 없음" src="download?fNO=${attachFileVO.fNO }">
				<div id="cma_image" style="width:95%; max-width:100%; border:1px solid #c0c0c0;">
				</div>
						<div class="bottom">
							<a href="/gs25/custom/list">글목록</a>
							<c:if test='${not empty authUser }'>
								<a href="/gs25/custom/replyform?no=${vo.no }">답글달기</a>
								<c:if test='${authUser.no == vo.userNo }'>
									<a href="/gs25/custom/modifyform?no=${vo.no}">글수정</a>
								</c:if>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>


		<jsp:include page="/WEB-INF/views/include/footer.jsp" />

	</div>

 <script>
/*--------첨부파일 다운로드--- -----------*/
$("#attachFile").on("click", function(event){
	var fNO = $(this).data("fno");
	var url = "download?fNO=" + fNO; 
	window.open(url);
});
</script>
</body>
</html>