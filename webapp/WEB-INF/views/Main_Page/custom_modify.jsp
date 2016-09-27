<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>gs25_modify</title>
<link href="/gs25/assets/css/customboard.css" rel="stylesheet"
	type="text/css">

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
					<div id="board">
						<form class="board-form" method="post"
							action="/gs25/custom/modify?no=${vo.no }">
							<table class="tbl-ex">
								<tr>
									<th colspan="2">글수정</th>
								</tr>
								<tr>
									<td class="label">제목</td>
									<td><input type="text" name="title" value="${vo.title }"></td>
								</tr>
								<tr>
									<td class="label">내용</td>
									<td><textarea id="content" name="content">${vo.content }</textarea></td>
								</tr>
							</table>
							<div class="bottom">
								<a href="javascript:history.go(-1);">취소</a> <input type="submit"
									value="수정">
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />

	</div>
</body>
</html>