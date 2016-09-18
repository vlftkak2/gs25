<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/gs25/assets/css/login.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp" />
	<div class="container">
		<div id="content">
			<div class="tblwrap">
				<form id="id-form" name="idForm" method="post"
					action="/gs25/user/idFind">
					<fieldset>
						<legend>아이디 찾기</legend>
						<table class="tbl_wtype1 smaller">
							<tbody>
								<tr>
									<th scope="row">이름</th>
									<td><input id="name" name="name" type="text" value=""></td>
								</tr>
								<tr>
									<th scope="row" id="tit2">성별</th>
									<td><label>여</label> <input type="radio" name="gender"
										value="FEMALE" checked="checked"> <label>남</label> <input
										type="radio" name="gender" value="MALE"></td>
								</tr>
								<tr>
									<th scope="row" class="">생년월일</th>
									<td><input id="birth" name="birth" type="text" value=""
										placeholder="Ex.19901212"></td>
								</tr>
								<tr>
									<th scope="row" id="tit2">휴대폰</th>
									<td><input id="phone" name="phone" type="text" value=""
										placeholder="'-'제외하고 숫자만 입력"></td>
								</tr>
							</tbody>
						</table>
						<input type="submit" value="아이디찾기">
					</fieldset>
				</form>
				<form id="password-form" name="passForm" method="post"
					action="/gs25/user/passFind">
					<fieldset>
						<legend>비밀번호 찾기</legend>
						<div class="tblwrap">
							<table class="tbl_wtype1 smaller">
								<tbody>
									<tr>
										<th scope="row">아이디</th>
										<td><input id="email" name="email" type="text" value=""></td>
									</tr>
									<tr>
										<th scope="row" id="tit2">이름</th>
										<td><input id="name" name="name" type="text" value="">
										</td>
									</tr>
									<tr>
										<th scope="row" class="">생년월일</th>
										<td><input id="birth" name="birth" type="text" value=""
											placeholder="Ex.19901212"></td>
									</tr>
									<tr>
										<th scope="row" id="tit2">휴대폰</th>
										<td><input id="phone" name="phone" type="text" value=""
											placeholder="'-'제외하고 숫자만 입력"></td>
									</tr>
								</tbody>
							</table>
							<input type="submit" value="비밀번호찾기">
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>