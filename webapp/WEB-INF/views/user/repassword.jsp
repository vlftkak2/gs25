<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 비밀번호 재설정</title>

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
			<c:when test='${userno == 0}'>
				<div id ="title1_p">
					<p><em id="emphasis">GS25편의점 회원 인증이 필요합니다.</em></p>
					<p>홈페이지에 방문하셔서 위 서비스를 다시 이용하시기 바랍니다.</p>
				</div>
				<div id="repass">
					<div id="sorryman">
					</div>
				</div>				
			</c:when>
			<c:otherwise>
			<div id ="title1_p">
					<p><em id="emphasis">회원임을 인증되었습니다.</em></p>
					<p>비밀번호를 새로 설정하여 서비스를 이용하시기 바랍니다.</p>
				</div>
				<div id="repass">
					<div id="id-form" >
						<%-- <input type = "hidden" name = "no" value="${userno }"> --%>
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
						<input class="btn btn-find marRight" id = "btn_complete" type="button" value="수정완료">
					</div>
				</div>
			</c:otherwise>
		</c:choose>
				<div id="move">
			<h3 class="lineM ceLeft"><a href="/gs25/main">GS25 편의점 홈페이지 이동</a></h3>
		</div>
		</div>
		</div>
	</div>
</body>
<script>
$(function(){
	
	$('#password').keydown(function() {
		$('font[name=passCheck]').html('');
		$('#repassword').val('');
	});
	
	$('#repassword').keyup(function() {
		if ($('#password').val() != $('#repassword').val()) {
			$('font[name=passCheck]').text('');
			$('font[name=passCheck]').html("암호틀림");
		} else {
			$('font[name=passCheck]').text('');
			$('font[name=passCheck]').html("암호맞음");
		}
	});
	
	$("#btn_complete").on("click", function(){ 
		//패스워드
		if($("#password").val() == ""){
			alert("비밀번호는 필수 입력 항목입니다.");
			$("#password").focus();
			return false;
			}
		
		//재 패스워드
		if($("#repassword").val() == ""){
			alert("비밀번호 재입력은 필수 입력 항목입니다.");
			$("#repassword").focus();
			return false;
			}
		//패스워드 일치 여부
		if($("#password").val() != $("#repassword").val()){
			alert("비밀번호가 일치하지 않습니다.");
			$("#password").focus();
			return false;
		}
		
		var no = ${userno };
		console.log(no);
		var password = $("#password").val();	
		console.log(password);
		$.ajax({	
			url: "/gs25/user/setPass",
			type: "POST",
			data: {"no":no, "password":password},
			dataType: "text",
			success: function(result){	//비동기식으로 진행되어 결과와 상관 없이 submit되므로 계속 refres됨(따로 동기식으로 변경해야함)
				console.log(result);
					
				 if(result == "1"){
					location.href='/gs25/user/repasswordSuccess';
				} else {
					console.log(result);
					alert("죄송합니다. 비밀번호 재설정을 실패했습니다. \n 다시 시도해주세요.")
					//window.close();
				}
			},
			
			error: function(jsXHR, status, e){
				console.error("error:"+status+":"+e);
			}
		});
	});
});
</script>
</html>