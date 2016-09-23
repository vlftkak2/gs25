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
		<div id="title1">
			<h3>GS25 편의점 회원정보 찾기</h3>
		</div>
		<div id ="title1_p">
			<p>회원가입 시 입력하셨던 고객님의 소중한 개인정보 인증을 통해 알려드리고 있습니다.</p>
			<p><em id="emphasis">GS25</em>편의점 회원이실 경우,  각 해당란에 모두 기입해주십시오.</p>
		</div>
		<div id="contents">
			<div class="both left">
			<div class="popwrap">
			<h4 class="pop_tlt1">아이디 찾기</h4>
			</div>
			<form id="id-form" name="idForm" method="post" action="/gs25/user/idFind">
				<table class="tbl_wtype1 smaller">
					<tbody>
						<tr>
							<th scope="row">이름</th>
							<td><input id="name" name="name" type="text" value=""></td>
						</tr>
						<tr>
							<th scope="row">성별</th>
							<td>
								<label>남</label> <input type="radio" name="gender" value="MALE" checked="checked">
								<label>여</label> <input type="radio" name="gender" value="FEMALE">
							</td>
						</tr>
						<tr>
							<th scope="row" class="">생년월일</th>
							<td><input id="birth" name="birth" type="text" value="" placeholder="Ex.19901212"></td>
						</tr>
						<tr>
							<th scope="row">휴대폰</th>
							<td>
								<input id="phone" name="phone" type="text" value="" placeholder="'-'제외하고 숫자만 입력">
							</td>
						</tr>
					</tbody>
				</table>
					<input class="btn btn-find" type="submit" value="아이디찾기">
			</form>
			</div>
			<div class="both right">
			<div class="popwrap">
			<h4 class="pop_tlt1">비밀번호 찾기</h4>
			</div>
			<form id="password-form" name="passForm" method="post" action="/gs25/email/send">
				<table class="tbl_wtype1 smaller">
					<tbody>
						<tr>
							<th scope="row">아이디</th>
							<td><input id="email" name="email" type="text" value=""></td>
						</tr>
						<tr>
							<th scope="row">이름</th>
							<td>
								<input id="name" name="name" type="text" value="">
							</td>
						</tr>
						<tr>
							<th scope="row" class="">생년월일</th>
							<td><input id="birth" name="birth" type="text" value="" placeholder="Ex.19901212"></td>
						</tr>
						<tr>
							<th scope="row">휴대폰</th>
							<td>
								<input id="phone" name="phone" type="text" value="" placeholder="'-'제외하고 숫자만 입력">
							</td>
						</tr>
					</tbody>
				</table>
					<input class="btn btn-find" type="submit" value="비밀번호찾기">
			</form>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>