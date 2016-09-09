<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>main_index</title>
<link href="/gs25/assets/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/gs25/assets/js/jquery/jquery-1.9.0.js"></script>

</head>
<body>

<div id="container">
<jsp:include page="/WEB-INF/views/include/header.jsp" />

<div id="content">

<div class="container_index">
		<img src="/gs25/assets/images/gs25_03.gif"/>
		<div class="market_list">
			<ul class="market_list_area">
				<li><a href="#" class="on">서울</a></li>
				<li><a href="#">경기</a></li>
				<li><a href="#">인천</a></li>
				<li><a href="#">강원</a></li>
				<li><a href="#">대전/충청</a></li>
				<li><a href="#">광주/전라</a></li>
				<li><a href="#">대구/울산/부산</a></li>
				<li><a href="#">경상</a></li>
				<li><a href="#">제주</a></li>
			</ul>

			<ul class="market_list_point">
				<li><a href="#" class="on">지점1</a></li>
				<script language="JavaScript">
					for (i = 0; i < 27; i++) {
						document.write("<li><a href='#'>지점"+i+"</a></li>")
					}

				</script>
			</ul>


		</div>
	</div>	


</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />

</div>
</body>
</html>