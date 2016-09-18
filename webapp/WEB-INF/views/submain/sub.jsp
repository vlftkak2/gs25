<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/gs25/assets/css/sub.css" rel="stylesheet" type="text/css">
</head>
<body>

	<jsp:include page="/WEB-INF/views/include/subheader.jsp" />
	<div class="container">
		<div id="contents">
			<div class="img">
				<img src="/gs25/assets/imgages/gs25Main.png" width="600px">
			</div>
			<div class="login">
				<div id="login1">
					<p id="title">
						<em>GS편의점</em> <em style="color: blue">로그인</em>
					</p>
					<p id="intro">
						기존 GS편의점 사이트에 가입하신<br> 아이디와 비밀번호로 로그인 하실 수 있습니다.
					</p>
				</div>
				<div id="login2">
					<form class="login-form" name="loginform" method="post"
						action="/convenience/user/login">
						<ul>
							<li><input id="email" name="email" class="form-control"
								type="text" value="" placeholder="이메일"></li>
							<li><input name="password" type="password"
								class="form-control" value="" placeholder="비밀번호"></li>
						</ul>
						<input type="button" id="login-btn" type="submit" value="로그인">
						<a href="/convenience/user/findInfo">아이디/비밀번호찾기</a>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div id="product-list">
		<div class="container">
			<div id="side-menu">
				<ul>
					<li><a href="#">메뉴1</a></li>
					<li><a href="#">메뉴2</a></li>
					<li><a href="#">메뉴3</a></li>
				</ul>
			</div>
			<div id="product">
				<ul>
					<li><a href="#">상품1</a></li>
					<li><a href="#">상품2</a></li>
					<li><a href="#">상품3</a></li>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>