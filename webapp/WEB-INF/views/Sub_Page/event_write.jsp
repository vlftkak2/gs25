<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>이벤트 글쓰기</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/gs25/assets/css/eventboard.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/gs25/assets/css/sweetalert.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="/gs25/assets/js/sweetalert.min.js"></script>
<script type="text/javascript" src="/gs25/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>
	function getThumbnailPrivew(html, $target) {
		if (html.files && html.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$target.css('display', '');
				//$target.css('background-image', 'url(\"' + e.target.result + '\")'); // 배경으로 지정시
				$target
						.html('<img src="' + e.target.result + '" border="0" alt="" />');
			}
			reader.readAsDataURL(html.files[0]);
		}
	}
</script>
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="event_main">
				<div id="event_sub">

					<div id="brand_main">
						<h1>이벤트</h1>
					</div>

					<div id="board">
						<form id="mainForm" method="post"
							action="/gs25/event/event_write?userno=${authUser.no }&store_no=${store_no}"
							enctype="multipart/form-data">
							<table class="tbl-ex">
								<tr>
									<th colspan="4">이벤트 글쓰기</th>
								</tr>

								<tr>
									<td class="label">제목</td>
									<td><input type="text" id="event_title" name="title" value=""></td>
									<td class="label"></td>
									<td>&nbsp</td>
								</tr>

								<tr>
									<td class="label">시작일</td>
									<td><input type="text" id="datepicker1" name="startdate"
										value=""></td>
									<td class="label">종료일</td>
									<td><input type="text" id="datepicker2" name="enddate"
										value=""></td>
								</tr>

								<tr>
									<td class="label">첨부파일</td>
									<td><input type="file" name="file" id="file"
										accept="image/*" capture="camera"
										onchange="getThumbnailPrivew(this,$('#cma_image'))"></td>
									<td class="label"></td>
									<td>&nbsp</td>
								</tr>
							</table>

							<div id="cma_image"
								style="width: 95%; max-width: 100%; border: 1px solid #c0c0c0; display: none;">
							</div>

							<div class="bottom">
								<a href="/gs25/event/eventlist?store_no=${store_no }">취소</a>
								<input type="submit" id="submit" class="register" value="등록">
							</div>
						</form>
					</div>
				</div>
			</div>

		</div>
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
<script>
	$(function() {
		$("#mainForm").submit(
				function() {
					//제목 체크
					if ($("#event_title").val() == "") {
						sweetAlert("제목은 필수 입력 항목입니다. ", "Something went wrong",
								"error");
						$("#event_title").focus();
						return false;
					}
					//시작일 체크
					if ($("#datepicker1").val() == "") {
						sweetAlert("시작일은 필수 입력 항목입니다.",
								"Something went wrong", "error");
						$("#datepicker1").focus();
						return false;
					}
					//종료일 체크
					if ($("#datepicker2").val() == "") {
						sweetAlert("종료일은 필수 입력 항목입니다.",
								"Something went wrong", "error");
						$("#datepicker2").focus();
						return false;
					}
				});
	});
</script>
</html>