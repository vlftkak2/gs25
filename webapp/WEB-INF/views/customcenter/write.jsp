<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>gs25_write</title>
<link href="/gs25/assets/css/customboard.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/gs25/assets/js/jquery/jquery-1.9.0.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>
function getThumbnailPrivew(html, $target) {
    if (html.files && html.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $target.css('display', '');
            //$target.css('background-image', 'url(\"' + e.target.result + '\")'); // 배경으로 지정시
            $target.html('<img src="' + e.target.result + '" border="0" alt="" />');
        }
        reader.readAsDataURL(html.files[0]);
    }
}
</script>


</head>
<body>

	<div class="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />

		<div id="content">
			<div id="customBoard_main">
			<div id="customBoard_sub">
				<div id="wrap">
					<h1>고객센터</h1>
				</div>


				<div id="board">
					<form class="board-form" method="post" autocomplete="off"
						action="/gs25/custom/write?userno=${authUser.no }"
						enctype="multipart/form-data">
						<table class="tbl-ex">
							<tr>
								<th colspan="2">글쓰기</th>
							</tr>
							<tr>
								<td class="label">제목</td>
								<td><input type="text" name="title" value=""></td>
							</tr>
							<tr>
								<td class="label">내용</td>
								<td><textarea id="content" name="content"></textarea></td>
							</tr>
							<tr>
								<td class="label">첨부파일</td>
								<td><input type="file" name="file" id="file accept="image/*" capture="camera" onchange="getThumbnailPrivew(this,$('#cma_image'))"></td>
							</tr>
						</table>
						 <div id="cma_image" style="width:95%; max-width:100%; border:1px solid #c0c0c0;display:none;"></div>
							
						<div class="bottom">
							<a href="/gs25/custom/list">취소</a> <input type="submit"
								value="등록">
						</div>
					</form>
				</div>
			</div>

		</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />

	</div>

</body>
</html>