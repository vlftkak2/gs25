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
<script type="text/javascript"
	src="/gs25/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp" />
	<div class="container">
		<div id="title1">
			<h3>GS25 편의점 회원정보 수정</h3>
		</div>
		<div id="title1_p">
			<p>
				고객과 함께 내일을 꿈꾸며,<br>새로운 삶의 가치를 창조하는 GS25 편의점에서 소중한 회원님의 회원정보를
				철저하게 관리하고 있습니다.
			</p>
		</div>
		<div id="block">
			<h4 id="tlt2_p0">수정 사항</h4>
			<div id="signup">
				<form id="join-form" name="joinForm" method="post"
					action="/gs25/user/modify">
					<div id="brdwrap2">
						<h5 id="tit">기본사항</h5>
						<div class="tblwrap">
							<table class="tbl_wtype1">
								<tbody>
									<tr>
										<th scope="row">이름</th>
										<td><input id="name" name="name" type="text"
											value="${userVo.name }"></td>
										<th scope="row" id="tit2">성별</th>
										<td><c:choose>
												<c:when test='${"FEMALE" == userVo.gender }'>
													<label>여</label>
													<input type="radio" name="gender" value="FEMALE"
														checked="checked">
													<label>남</label>
													<input type="radio" name="gender" value="MALE">
												</c:when>
												<c:otherwise>
													<label>여</label>
													<input type="radio" name="gender" value="FEMALE">
													<label>남</label>
													<input type="radio" name="gender" value="MALE"
														checked="checked">
												</c:otherwise>
											</c:choose></td>
									</tr>
									<tr>
										<th scope="row" class="">생년월일</th>
										<td><input id="birth" name="birth" type="text"
											value="${userVo.birth }" placeholder="Ex.19901212"></td>
										<th scope="row" id="tit2">휴대폰</th>
										<td><input id="phone" name="phone" type="text"
											value="${userVo.phone }" placeholder="'-'제외하고 숫자만 입력">
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div id="brdwrap2">
						<h5 id="tit">필수입력</h5>
						<div class="tblwrap">
							<table class="tbl_wtype1">
								<tbody>
									<tr>
										<th scope="row"><label for="web_pwd1">비밀번호 </label></th>
										<td><input id="password" name="password" type="password"
											value=""></td>
									</tr>
									<tr>
										<th scope="row"><label for="web_pwd2">비밀번호 재확인</label></th>
										<td><input id="repassword" name="repassword"
											type="password" value=""> <font name="passCheck"
											id="passCheck"></font></td>
									</tr>
									<tr>
										<th scope="row"><label for="web_pwd2">주소</label></th>
										<td><input id="address" name="address" type="text"
											value="${userVo.address }"></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<input class="btn btn-primary btn-register" type="submit"
						value="수정하기">
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
<script>
	$(function() {
		$('#password').keyup(function() {
			$('font[name=passCheck]').text('');
		}); //#password.keyup
		$('#repassword').keyup(function() {
			if ($('#password').val() != $('#repassword').val()) {
				$('font[name=passCheck]').text('');
				$('font[name=passCheck]').html("암호틀림");
			} else {
				$('font[name=passCheck]').text('');
				$('font[name=passCheck]').html("암호맞음");
			}
		});
	});
</script>
</body>
</html>