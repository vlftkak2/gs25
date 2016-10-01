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
<link href="/gs25/assets/css/eventboard.css" rel="stylesheet" type="text/css">

</head>
<body>
<div id="container">
<jsp:include page="/WEB-INF/views/include/subheader.jsp" />
	<div id="content">
		<div id="event_main">
			<div id="event_sub">
				<div id="brand_main">
				<h1>이벤트</h1>
				</div>
	
				<div id="brdwrap">
				<div id="tblwrap">
					<div id="event_type1">
						<div id="event_sect">
							<h3 id="title"><strong>${vo.title }</strong></h3>
							<ul id="info">
							<li>이벤트 기간  : 
							<span>${vo.startdate }</span> ~ <span>${vo.enddate }</span>
							</li>
							</ul>
						</div>
						<div id="event_content">
							<img src="${vo.imageurl }">
						</div>
					</div>
				</div>
				
				<div id="btnwrap">
					<a href="/gs25/event/eventlist" id="btn">목록</a>
				</div>
				</div>	
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</div>
</body>
</html>