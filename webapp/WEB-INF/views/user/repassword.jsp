<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="/gs25/assets/js/jquery/jquery-1.9.0.js"></script>


</head>
<body id="gsBack">
	<div id="wrapper">
		<div id="box">
		<div id="sbox">
			<div id="title1">
				<h3>GS편의점 회원 비밀번호 재설정</h3>
			</div>
		<c:choose>
			<c:when test='${rannum eq param.ranNum and userid eq param.userid}'>
				<div id ="title1_p">
					<p><em id="emphasis">회원임을 인증되었습니다.</em></p>
					<p>비밀번호를 새로 설정하여 서비스를 이용하시기 바랍니다.</p>
				</div>
				<div id="repass">
					<form id="id-form" name="idForm" method="post" action="/gs25/user/setPass">
						<input type = "hidden" name = "email" value="${userid }">
					 	<table class="tbl_wtype2">
							<tbody>
								<tr>
									<th scope="row"><label for="web_pwd1">비밀번호</label></th>
									<td>
										<input id="password" name="password" type="password" value="">
									</td>
								</tr>
								<tr>
									<th scope="row"><label for="web_pwd2">비밀번호 재입력</label></th>
									<td>
										<input id="repassword" name="repassword" type="password" value="">
										<font name="passCheck" id="passCheck"></font>
									</td>
								</tr>
							</tbody>
						</table>
						<input class="btn btn-find marRight" type="submit" value="수정완료">
					</form>
				</div>
			</c:when>
			<c:otherwise>
				<div id ="title1_p">
					<p><em id="emphasis">GS25편의점 회원 인증이 필요합니다.</em></p>
					<p>홈페이지에 방문하셔서 위 서비스를 다시 이용하시기 바랍니다.</p>
				</div>
				<div id="repass">
					<div id="sorryman">
					</div>
				</div>
				<div id="move">
			<h3 class="lineM ceLeft"><a href="/gs25/main">GS25 편의점 홈페이지 이동</a></h3>
		</div>
			</c:otherwise>
		</c:choose>
		</div>
		</div>
	</div>
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
</body>
</html>