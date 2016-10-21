<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/gs25/assets/css/menubar.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/gs25/assets/js/Mainmenubar.js"></script>
<style>
#STATICMENU {
	margin: 0 170px;
	padding: 0pt;
	position: absolute;
	right: 0px;
	top: 0px;
}
</style>

</head>
<body>

<div id="menubar">
			<div id="STATICMENU">
				<div class="myarea_wrap">
					<div class="mymenu">
						<div class="couwrap_off">
						<c:choose>
						<c:when test='${empty authUser}'>
							<p>
								서비스 이용을<br> 위해 로그인<br> 해주세요 <br>
							</p>
							<input type="button" class="btn_log" value="로그인"
								onclick="location.href='/gs25/user/loginform';">
								</c:when>
								<c:otherwise>
								<p>
								환영합니다<br>${authUser.name }님 <br> ^^
								</p>
								<input type="button" class="btn_log" value="로그아웃"
								onclick="location.href='/gs25/user/logout';">
								</c:otherwise>
								</c:choose>
						</div>
						<ul class="my_lst">
						  	<li><a href="/gs25/product/Mainlist" class="my_m2">상품</a></li>
							<li><a href="/gs25/custom/list" class="my_m3" >고객센터</a></li>
						</ul>
						<div class="menuwrap">
							<c:choose>
								<c:when test='${authUser.no ==100}'>
							
									<p class="menu_tit">관리자 메뉴</p>
									<ul class="my_lst">
					<li>
						<a href="/gs25/map/mlist" class="my_m0">매장관리</a>
					</li>
					<li>
						<a href="/gs25/user/mlist" class="my_m1">회원관리</a>
					</li>
					<li>
						<a href="/gs25/product/Mainlist" class="my_m2">상품관리</a>
					</li>
					<li>
						<a href="/gs25/custom/customboardManager" class="my_m3">고객센터</a>
					</li>
				</ul>
								</c:when>
								<c:otherwise>
									<p class="menu_tit">반가워요</p>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<a href="#" class="top">TOP</a>
				</div>
			</div>
		</div>

</body>

</html>