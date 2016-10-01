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
<body>
<jsp:include page="/WEB-INF/views/include/subheader.jsp" />
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
			<form id="id-form" name="idForm" method="post" action="/gs25/user/SubidFind">
				<table class="tbl_wtype1 smaller">
					<tbody>
						<tr>
							<th scope="row">이름</th>
							<td><input id="name" name="name" type="text" value=""></td>
						</tr>
						<tr>
							<th scope="row">성별</th>
							<td>
								<label>남</label> <input type="radio" id="gemder" name="gender" value="MALE" checked="checked">
								<label>여</label> <input type="radio" id="gemder" name="gender" value="FEMALE">
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
			<form id="password-form" name="passForm" method="post">
				<table class="tbl_wtype1 smaller">
					<tbody>
						<tr>
							<th scope="row">아이디</th>
							<td><input id="email1" name="email" type="text" value=""></td>
						</tr>
						<tr>
							<th scope="row">이름</th>
							<td>
								<input id="name1" name="name" type="text" value="">
							</td>
						</tr>
						<tr>
							<th scope="row" class="">생년월일</th>
							<td><input id="birth1" name="birth" type="text" value="" placeholder="Ex.19901212"></td>
						</tr>
						<tr>
							<th scope="row">휴대폰</th>
							<td>
								<input id="phone1" name="phone" type="text" value="" placeholder="'-'제외하고 숫자만 입력">
							</td>
						</tr>
					</tbody>
				</table>
					<input class="btn btn-find" type="button" id="btn_pass" value="비밀번호찾기">
			</form>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
<script>
$(function() {
	//입력 사항   일치 여부)
	var tmp = '${result }';
	
	if(tmp != '') {	//받은 값이 없으므로 공백으로 받아짐
		alert('입력하신 내용과 일치하는 계정이 없습니다. 다시 입력하여 시도하시거나 회원가입해주세요.');
	}
	
	//비밀번호 입력시 일치 여부
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
	//입력하지 않았을 경우(아이디 찾기 폼)
	/* $("#id-form").submit(function(){ */
	$("#btn_Login").on("click", function(){ 	
		//이름 체크
		if($("#name").val() == ""){
		alert("이름은 필수 입력 항목입니다.");
		$("#name").focus();
		return false;
		}
		//생년월일
		var regBirth = /^(19|20)\d{2}([0][1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$/;	//정규식
		if($("#birth").val() == ""){
			alert("생년월일은 필수 입력 항목입니다.");
			$("#birth").focus();
			return false;
		} else {
			if(!regBirth.test($("#birth").val())) {  
			    alert("생년월일 입력 형식이 잘못되었습니다.");
			    $("#birth").focus();
			    return false;  
			}
		}
		//휴대폰
		var regPhone = /^((01[0|1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$/;
		if($("#phone").val() == ""){
			alert("휴대폰 번호는 필수 입력 항목입니다.");
			$("#phone").focus();
			return false;
		} else { 	//휴대폰 유효성 검사
			if(!regPhone.test($("#phone").val())) {  
			    alert("휴대폰 번호 입력된 내용이 잘못된 형식입니다.");
			    $("#phone").focus();
			    return false;  
			}
		}
		
	return true;
	
	});
	
	//입력하지 않았을 경우(비밀번호 찾기 폼)
	/* $("#password-form").submit(function(){ */
	$("#btn_pass").on("click", function(){ 	
		//이메일 체크
		var regEmail = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
		if($("#email1").val() == ""){
			alert("아이디를 입력하시지 않으셨습니다.");
			$("#email1").focus();
			return false;
		} else { 	//휴대폰 유효성 검사
			if(!regEmail.test($("#email1").val())) {  
				alert("입력된 아이디 형식이 유효하지 않습니다");
			    $("#email1").focus();
			    return false;  
			}
		}
		//이름 체크
		if($("#name1").val() == ""){
		alert("이름은 필수 입력 항목입니다.");
		$("#name1").focus();
		return false;
		}
		//생년월일
		var regBirth = /^(19|20)\d{2}([0][1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$/;	//정규식
		if($("#birth1").val() == ""){
			alert("생년월일은 필수 입력 항목입니다.");
			$("#birth1").focus();
			return false;
		} else {
			if(!regBirth.test($("#birth1").val())) {  
			    alert("생년월일 입력 형식이 잘못되었습니다.");
			    $("#birth1").focus();
			    return false;  
			}
		}
		//휴대폰
		var regPhone = /^((01[0|1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$/;
		if($("#phone1").val() == ""){
			alert("휴대폰 번호는 필수 입력 항목입니다.");
			$("#phone1").focus();
			return false;
		} else { 	//휴대폰 유효성 검사
			if(!regPhone.test($("#phone1").val())) {  
			    alert("휴대폰 번호 입력된 내용이 잘못된 형식입니다.");
			    $("#phone1").focus();
			    return false;  
			}
		}
		var email = $("#email1").val();
		var name = $("#name1").val();
		var birth= $("#birth1").val();
		var phone= $("#phone1").val();
		
		//Script 객체
		var userVo ={
			"email": email,
			"name": name,
			"birth": birth,
			"phone": phone
		};
		
		$.ajax({
			url: "checkPass",
			type: "POST",
			data: JSON.stringify(userVo),
			dataType: "json",
			contentType: "application/json",
			success: function(result){
				console.log(result);
				if(result == false){
					console.log(result);
					alert("입력된 아이디 형식이 유효하지 않습니다");
					return false;
				}
				
				 if(result == true){
					 location.href='Subpassresult';
				} 
			},
			
			error: function(jsXHR, status, e){
				console.error("error:"+status+":"+e);
			}
		});
		
	return true;
	});
});
</script>
</html>