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
<link href="/gs25/assets/css/login.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/gs25/js/jquery/jquery-1.9.0.js"></script>

</head>
<body>

	<jsp:include page="/WEB-INF/views/include/header.jsp" />
	<div class="container">
		<h3 id="tlt1">GS편의점</h3>
		<div id="contents">
			<div id="user">
				<div id="log-border">
					<p id="title">
						<em>GS편의점</em> <em style="color: blue">로그인</em>
					</p>
					<p id="intro">
						기존 GS편의점 사이트에 가입하신<br> 아이디와 비밀번호로 로그인 하실 수 있습니다.
					</p>
					<form class="login-form" name="loginform" method="post"
						action="/gs25/user/login">
						<ul>
							<li><input id="email" name="email" class="form-control"
								type="text" value="" placeholder="이메일"></li>
							<li><input name="password" type="password"
								class="form-control" value="" placeholder="비밀번호"></li>
						</ul>
						<button class="btn btn-primary btn-block" type="submit">로그인</button>
					</form>
				</div>
			</div>
			<div id="findMember">
				<div id="joincan">
					<p>회원가입을 하시면 GS리테일의 다양한 혜택을 이용하실 수 있습니다.</p>
					<a href="/gs25/user/joinform"><input type="submit"
						class="btn btn_arr wt" id="canbtn1" value="회원가입" /></a>
				</div>
				<div id="findcan">
					<p>
						아이디 및 비밀번호를 잊으셨나요?<br>아이디 찾기와 비밀번호 찾기를 이용해 주세요.
					</p>
					<a href="/gs25/user/findInfo"><input type="button"
						class="btn btn_arr wt" id="canbtn2" value="아이디/비밀번호 찾기" /></a>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />

</body>
</html>