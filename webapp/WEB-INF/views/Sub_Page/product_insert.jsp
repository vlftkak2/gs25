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
	<div class="container">
		<jsp:include page="/WEB-INF/views/include/subheader.jsp" />

		<div id="content">
			<div class="container_product">
				<div class="container_productsub">
					<div id="wrap">
						<h1>상품등록</h1>
					</div>

					<div id="board">
						<form class="board-form" method="post"
							action="/gs25/product/insert" enctype="multipart/form-data">
							<table class="tbl-ex">
								<tr>
									<th colspan="6">글쓰기</th>
								</tr>
								<tr>
									<td class="label">상품이름</td>
									<td><input type="text" name="name" value=""></td>
									<td class="label">가격</td>
									<td><input type="text" name="price" value=""></td>
									<td class="label">제조일</td>
									<td><input type="text" name="reg_date" value=""></td>

								</tr>
								<tr>
									<td class="label">유통기한</td>
									<td><input type="text" name="expiry_date" value=""></td>
									<td class="label">제조사</td>
									<td><input type="text" name="maker" value=""></td>
									<td class="label">첨부파일</td>
									<td><input type="file" name="file" id="file"></td>
								</tr>
								<tr>
									<td class="label">상품종류</td>

									<td>
										<div class="select_box">
											<select id="selectbox" name="kind_no">
												<option selected="selected">선택해 주세요</option>
												<option value="1">과일</option>
												<option value="2">도시락</option>
												<option value="3">김밥</option>
												<option value="4">햄버거</option>
												<option value="5">주류</option>
												<option value="6">라면</option>
												<option value="7">아이스크림</option>
												<option value="8">샐러드</option>

											</select>
										</div>
									</td>
									<td class="label">카테고리</td>

									<td>
										<div class="select_box">
											<!--  	<label for="selectbox">선택해 주세요</label>-->
											<select id="selectbox" name="category">
												<option selected="selected">선택해 주세요</option>
												<option value="인기">인기</option>
												<option value="단체">단체</option>
												<option value="신상">신상</option>
												<option value="없음">없음</option>
											</select>
										</div>
									</td>
									<td class="label"></td>
									<td>&nbsp</td>
								</tr>
							</table>
							<div id="cma_image"
								style="width: 95%; max-width: 100%; border: 1px solid #c0c0c0; display: none;"></div>

							<div class="bottom">
								<a href="/gs25/product/list">취소</a> <input type="submit"
									value="등록">
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