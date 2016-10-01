<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>gs25_subheader</title>
</head>
<body>

	<div class="header">
		<!-- topwrap -->
		<div class="topwrap">
			<!-- brandwrap -->
			<div class="brdwrap">
				<!-- utility -->
					<div class="uty">
					<ul>
					<c:choose>
					<c:when test='${empty sessionScope.authUser }'>
					<li><a href="/gs25/user/Subloginform">로그인</a></li>
					<li><a href="/gs25/user/Subjoinform">회원가입</a></li>
					</c:when>
					<c:otherwise>
						<li>${authUser.name}님안녕하세요^^;</li>
						<li><a href="/gs25/user/Submodifyform">회원정보수정</a></li>
						<li><a href="/gs25/user/Sublogout">로그아웃</a></li>
					</c:otherwise>
				</c:choose>
						</ul>
					</div>
				<!-- //utility -->
			</div>
			<!-- //brandwrap -->
			<!-- gnbwrap -->
			<div class="gnbwrap">
				<div class="gnbw">
					<h1 class="logo">
						<a href="/gs25/sub/main">GS25</a>
					</h1>
					<div class="gnb" id="gnb_menu">
						<ul>
							<li><h2>
									<a href="javascript:void(0);" class="on">방명록</a>
								</h2></li>
							<li><h2>
									<a href="javascript:void(0);">이벤트</a>
								</h2></li>
							<li><h2>
									<a href="/gs25/submap/sublist">매장검색</a>
								</h2></li>
							<li><h2>
									<a href="/gs25/product/list">상품</a>
								</h2>
						</ul>
					</div>
				</div>

				<div class="gnb_bg" style="display: none;"></div>
			</div>
			<!-- //gnbwrap -->
		</div>
		<!-- //topwrap -->
	</div>

</body>
</html>