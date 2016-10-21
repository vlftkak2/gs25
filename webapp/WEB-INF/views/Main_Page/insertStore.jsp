<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>product_insert</title>
<link href="/gs25/assets/css/product.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp" />
	<div class="container">
		<div id="content">
			<div class="container_product">
				<div class="container_productsub">
					<div id="wrap">
						<h1>새로운 지점 등록</h1>
					</div>
					
					<div id="board">
						<form class="board-form" method="post" action="/gs25/map/insert">
							<table class="tbl-ex">
								<tr>
									<th colspan="4">지점등록</th>
								</tr>
								<tr>
									<td class="label">지역</td>
									<td><div class="select_box">
										<select id="selectbox" name="region_no">
												<option selected="selected">선택해 주세요</option>
											<c:forEach var='region_vo' items='${regionvo}' varStatus='status'>
												<option value="${region_vo.no}">${region_vo.name}</option>
											</c:forEach>
											
										</select>
									</div></td>
									<td class="label">지점명</td>
									<td><input type="text" name="name" value=""></td>
								</tr>
								<tr>
									<td class="label">x좌표</td>
									<td><input type="text" name="localx" value=""></td>
									<td class="label">y좌표</td>
									<td><input type="text" name="localy" value=""></td>
								</tr>
								<tr>
									<td class="label" colspan="1">상세주소</td>
									<td colspan="3"><input type="text" style="width:683px;" name="address" value=""></td>
								</tr>
							</table>
							<div id="cma_image"
								style="width: 95%; max-width: 100%; border: 1px solid #c0c0c0; display: none;"></div>

							<div class="bottom">
								<a href="/gs25/map/mlist">취소</a> <input type="submit" value="등록">
							</div>
						</form>
					</div>
				</div>

			</div>
		</div>
	</div>
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>