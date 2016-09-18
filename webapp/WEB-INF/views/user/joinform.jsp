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
			<h3>GS25 편의점 신규 회원가입</h3>
		</div>
		<div id ="title1_p">
			<p>고객과 함께 내일을 꿈꾸며, 새로운 삶의 가치를 창조하는 GS25 편의점 회원가입을 환영합니다.</p>
			<p>GS25편의점 회원에 가입하시면, <em id="emphasis">GS25</em> 홈페이지를 하나의 아이디와 비밀번호로 이용하실 수 있습니다.</p>
		</div>
		<div id="block">
			<h4 id="tlt2_p0">입력 사항</h4>
			<div id="signup">
				<form id="join-form" name="joinForm" method="post" action="/gs25/user/join">
					<div id="brdwrap2">
						<h5 id="tit">기본사항</h5>
						<div class="tblwrap">
							<table class="tbl_wtype1">
								<tbody>
									<tr>
										<th scope="row"><strong class="reqd" title="필수항목">*</strong>이름</th>
										<td><input id="name" name="name" type="text" value=""></td>
										<th scope="row" id="tit2"><strong class="reqd" title="필수항목">*</strong>성별</th>
										<td>
											<label>여</label> <input type="radio" name="gender" value="FEMALE" checked="checked">
											<label>남</label> <input type="radio" name="gender" value="MALE">
										</td>
									</tr>
									<tr>
										<th scope="row" class=""><strong class="reqd" title="필수항목">*</strong>생년월일</th>
										<td><input id="birth" name="birth" type="text" value="" placeholder="Ex.19901212"></td>
										<th scope="row" id="tit2"><strong class="reqd" title="필수항목">*</strong>휴대폰</th>
										<td>
											<input id="phone" name="phone" type="text" value="" placeholder="'-'제외하고 숫자만 입력">
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				<div id="brdwrap2">
					<h5 id="tit">필수입력</h5>
					<div class="tblwrap">
						<p class="reqd_box"><strong class="reqd">*</strong> 필수 입력사항</p>
					 	<table class="tbl_wtype1">
							<tbody>
								<tr>
									<th scope="row"><label for="intgrWebId">아이디 <strong class="reqd" title="필수항목">*</strong></label></th>
									<td>
										<input type="text" id="intgrWebId" name="intgrWebId" value="" />
										<input type="button" class="btn banner" value="ID 중복확인" onclick="customerInfoWebIdCheck('#intgrWebId', this);"/>
									</td>
								</tr>
								<tr>
									<th scope="row"><label for="web_pwd1">비밀번호 <strong class="reqd" title="필수항목">*</strong></label></th>
									<td>
										<input id="password" name="password" type="password" value="">
									</td>
								</tr>
								<tr>
									<th scope="row"><label for="web_pwd2">비밀번호 재확인<strong class="reqd" title="필수항목">*</strong></label></th>
									<td>
										<input id="repassword" name="repassword" type="password" value="">
										<font name="passCheck" id="passCheck"></font>
									</td>
								</tr>
								<tr>
									<th scope="row"><label for="web_pwd2">주소<strong class="reqd" title="필수항목">*</strong></label></th>
									<td>
										<input id="address" name="address" type="text" value="">
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					</div>
					<input class="btn btn-primary btn-register" type="submit" value="가입하기">
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
<script>
$(function(){
	 $('#password').keyup(function(){
		   $('font[name=passCheck]').text('');
		  }); //#password.keyup
		  $('#repassword').keyup(function(){
		   if($('#password').val()!=$('#repassword').val()){
		    $('font[name=passCheck]').text('');
		    $('font[name=passCheck]').html("암호틀림");
		   }else{
		    $('font[name=passCheck]').text('');
		    $('font[name=passCheck]').html("암호맞음");
		   }
		  }); 
});
</script>
</html>