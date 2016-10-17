<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>product_insert</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>	
<link href="/gs25/assets/css/product.css" rel="stylesheet" type="text/css">
<script src="/gs25/assets/js/sweetalert.min.js"></script> 
<link rel="stylesheet" type="text/css" href="/gs25/assets/css/sweetalert.css">
</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/views/include/subheader.jsp" />

		<div id="content">
			<div class="container_product">
				<div class="container_productsub">
					<div id="wrap">
						<h1>상품등록</h1>
					</div>

					<div id="board">
						<form class="board-form" method="post" id="board-form"
							action="/gs25/product/insert?store_no=${store_no }" enctype="multipart/form-data">
							<table class="tbl-ex">
								<tr>
									<th colspan="6">글쓰기</th>
								</tr>
								<tr>
									<td class="label">매장이름</td>
									<td>
										<div class="select_box">
											<select id="selectbox" name="store_no">
												<option selected="selected">선택해 주세요</option>
												<option value="${store_no }">GS${StoreVo.storename }점</option>
											</select>
										</div>
									</td>
									
									
									<td class="label">상품</td>

									<td>
										<div class="select_box">
											<select id="selectbox2" name="product_no">
												<option selected="selected">선택해 주세요</option>
												<c:forEach var='vo' items='${Productlist }' varStatus='s'>
												<option value="${vo.no }">${vo.name }</option>
												</c:forEach>
											</select>
										</div>
									</td>
									<td class="label">수량</td>
									<td><input type="text" id="mount" name="mount" value=""></td>
								

								</tr>
								<tr>
									<td class="label">제조일</td>
									<td><input type="text" id="datepicker1" name="reg_date" value=""></td>
									<td class="label">유통기한</td>
									<td><input type="text" id="datepicker2" name="expiry_date" value=""></td>
									<td class="label"></td>
									<td>&nbsp</td>
								</tr>
							</table>
							<div id="cma_image"
								style="width: 95%; max-width: 100%; border: 1px solid #c0c0c0; display: none;"></div>

							<div class="bottom">
								<a href="/gs25/product/list">취소</a> <input type="submit"
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

<script>
  $(function() {
	  
	  $("#board-form").submit(
	          function() {
	        	  
	        	  //매장 이름 체크
	        	  if ($("#selectbox option:selected").val()=='선택해 주세요') {
		                sweetAlert("매장이름은 필수 입력 항목입니다.",
		                      "Something went wrong", "error");
		                $("#selectbox").focus();
		                return false;
		             }
	        	  
	        	  //상품 체크
	        	    if ($("#selectbox2 option:selected").val()=='선택해 주세요') {
		                sweetAlert("상품은 필수 입력 항목입니다.",
		                      "Something went wrong", "error");
		                $("#selectbox2").focus();
		                return false;
		             }
	        	  
	             //수량 체크
	             if ($("#mount").val() == "") {
	                sweetAlert("상품이름은 필수 입력 항목입니다. ", "Something went wrong",
	                      "error");
	                $("#mount").focus();
	                return false;
	             }
	             
	             //제조일 체크
	             if ($("#datepicker1").val() == "") {
	                sweetAlert("제조일은 필수 입력 항목입니다. ", "Something went wrong",
	                      "error");
	                $("#datepicker1").focus();
	                return false;
	             }
	             
	             //유통기한 체크
	             if ($("#datepicker2").val() == "") {
	                sweetAlert("유통기한은 필수 입력 항목입니다. ", "Something went wrong",
	                      "error");
	                $("#datepicker2").focus();
	                return false;
	             }
	            
	          });
	  
	 $.datepicker.setDefaults({
      dateFormat: 'yymmdd',
      showButtonPanel: true,
      prevText: '이전 달',
      nextText: '다음 달',
      monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
      monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
      dayNames: ['일','월','화','수','목','금','토'],
      dayNamesShort: ['일','월','화','수','목','금','토'],
      dayNamesMin: ['일','월','화','수','목','금','토'],
      showMonthAfterYear: true,
      changeMonth: true,
      changeYear: true,
      yearSuffix: '년'
    });
  });
  $(function() {
	    $("#datepicker1, #datepicker2").datepicker();
	  });
</script>
</html>