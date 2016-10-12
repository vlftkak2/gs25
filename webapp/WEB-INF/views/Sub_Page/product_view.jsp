<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/gs25/assets/css/sweetalert.css">
<link href="/gs25/assets/css/product.css" rel="stylesheet"
	type="text/css"><script type="text/javascript"
	src="/gs25/assets/js/jquery/jquery-1.9.0.js"></script>
	<script src="/gs25/assets/js/sweetalert.min.js"></script>
	<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/subheader.jsp" />
	<div class="container_main">
		<div id="title_head">
			<div id="title1">
				<h3>상품 상세 페이지</h3>
			</div>
		<div id ="title1_p">
			<p>매월 새롭게 진행되는 다양한 행사상품과 함께 고객님께 즐거움을 제공해드립니다.^^</p>
			<div class="go_see">
				<a href="/gs25/product/list">전체 상품 보기</a>
			</div>
		</div>
		</div>
	</div>

	<div id="product_back">
		<div class="container_main">
			<div class="inner">
			<div class="product_view">
				<div class="product_img">
					<span><img id="pro_img" src="${prodvo.imageurl }">
					</span>

					<ul class="tag_list_02">
						<li class='ico_tag_07'>1+1</li>
					</ul>
					
				</div>
				<div class="productView_content">
					<dl>
						<dt>
							<strong class="tit_product_view">${prodvo.name }</strong>
						</dt>
						<dd class="productView_content_dd_01">
							<ul class="productView_content_ul">
								<li><strong>제조사</strong> : <span>${prodvo.maker }</span></li>
								<li><strong>유통기한</strong> : <span>${prodvo.expiry_date }</span></li>
								<c:choose>
									<c:when test='${prodvo.remainderdate<1 }'>
										<c:choose>
										<c:when test='${prodvo.remaindercountdate<=0}'>
										<li><span id="countdate"><strong>할인이 종료되었습니다.</strong></span></li>
										</c:when>
										<c:otherwise>
								<li><span id="countdate"><strong>할인 마무리가 되기까지 남은일자</strong> : ${prodvo.remaindercountdate}일</span></li>
										</c:otherwise>
										</c:choose>
									</c:when>
										<c:otherwise>
									<li><span id="countdate"><strong>할인적용까지 남은 일자</strong> : ${prodvo.remainderdate }일</span></li>	
										</c:otherwise>
								</c:choose>
								
							<c:if test="${not empty authUser}">
							<c:choose>
								<c:when test='${empty checkVo.user_no}'>
									<button id="btn1">찜하기</button>
									<button id="btn2" class="hide">찜해제</button>
									<a href="/gs25/cart/list"><button id="btn3">찜목록바로가기</button></a>
								 </c:when>
								 <c:otherwise>
									<button id="btn2">찜해제</button>
									<button id="btn1" class="hide">찜하기</button>
									<a href="/gs25/cart/list"><button id="btn3">찜목록바로가기</button></a>
								</c:otherwise>
							</c:choose>
							</c:if>								
							</ul>

							
							<span id="txt1">고객님 위 제품은 어떠세요?아래 후기를 통해 참고하실 수 있습니다.^^</span>
						</dd>
						<dd class="productView_content_dd_02">
						
						<c:choose>
								<c:when test='${prodvo.remaindercountdate>7}'>
								<span class="product_wonprice"> 
								<strong>${prodvo.price }</strong><span>원</span>
								</span>							
								</c:when>
								<c:when test='${prodvo.remaindercountdate==7 && (prodvo.price-vo.countprice)>=(prodvo.price*0.5) }'>
								<span class="product_price"> <strong>${prodvo.price }</strong><span>원</span></span>
								<span class="product_countprice"><strong>할인 가격 : ${prodvo.price-(prodvo.countprice)}</strong><span>원</span></span>
								</c:when>
								
								<c:when test='${prodvo.remaindercountdate==6 && (prodvo.price-prodvo.countprice*2)>=(prodvo.price*0.5) }'>
								<span class="product_price"> <strong>${prodvo.price }</strong><span>원</span></span>
								<span class="product_countprice"><strong>할인 가격 : ${prodvo.price-(prodvo.countprice*2)}</strong><span>원</span></span>
								</c:when>
								
								<c:when test='${prodvo.remaindercountdate==5 && (prodvo.price-prodvo.countprice*3)>=(prodvo.price*0.5) }'>
								<span class="product_price"> <strong>${prodvo.price }</strong><span>원</span></span>
								<span class="product_countprice"><strong>할인 가격 : ${prodvo.price-(prodvo.countprice*3)}</strong><span>원</span></span>
								</c:when>
								
								<c:when test='${prodvo.remaindercountdate==4 && (prodvo.price-prodvo.countprice*4)>=(prodvo.price*0.5) }'>
								<span class="product_price"> <strong>${prodvo.price }</strong><span>원</span></span>
								<span class="product_countprice"><strong>할인 가격 : ${prodvo.price-(prodvo.countprice*4)}</strong><span>원</span></span>
								</c:when>
								
								<c:when test='${prodvo.remaindercountdate==3 && (prodvo.price-prodvo.countprice*5)>=(prodvo.price*0.5) }'>
								<span class="product_price"> <strong>${prodvo.price }</strong><span>원</span></span>
								<span class="product_countprice"><strong>할인 가격 : ${prodvo.price-(prodvo.countprice*5)}</strong><span>원</span></span>
								</c:when>
								
								<c:when test='${prodvo.remaindercountdate==2 && ((prodvo.price-(prodvo.countprice*6))>=(prodvo.price*0.5)) }'>
								<span class="product_price"> <strong>${prodvo.price }</strong><span>원</span></span>
								<span class="product_countprice"><strong>할인 가격 : ${prodvo.price-(prodvo.countprice*6)}</strong><span>원</span></span>
								</c:when>
								
								<c:when test='${prodvo.remaindercountdate==1 && (prodvo.price-prodvo.countprice*7)>=(prodvo.price*0.5) }'>
								<span class="product_price"> <strong>${prodvo.price }</strong><span>원</span></span>
								<span class="product_countprice"><strong>할인 가격 : ${prodvo.price-(prodvo.countprice*7)}</strong><span>원</span></span>
								</c:when>
								
								<c:when test='${prodvo.remaindercountdate<=0 }'>
								<span class="product_wonprice"> 
								<strong>${prodvo.price }</strong><span>원</span>
								</span>	
								</c:when>
								
								<c:otherwise>
								<span class="product_price"> <strong>${prodvo.price }</strong><span>원</span></span>
								<span class="product_countprice"><strong>할인 가격 : ${prodvo.halfprice}</strong><span>원</span></span>
								</c:otherwise>
							</c:choose>
						</dd>
					</dl>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- //cont_top -->
	<div class="container_main">
		<div id="Nsearch">
			<h5 id="tit">네이버 블로그 후기</h5>
			<table class="tbl_wtype1">
				<tr>
					<td id="table_head">
						<img src="https://ssl.pstatic.net/sstatic/search/2015/h_logo.png" width="99" height="18" alt="NAVER"/>
					</td>
				</tr>
				<c:choose>
				<c:when test='${empty nvo }'>
					<tr>
						<td id="nbloger">
							<img id="NBear" src="/gs25/assets/images/product/naverIcon.png"/>
							<p id="sorryT"><strong>죄송합니다.. 네이버 검색 결과가 없습니다...</strong></p>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
				<c:set var='countList' value='${fn:length(nvo)}'/>
               	<c:forEach var='nvo' items='${nvo }' varStatus='status'>
				<tr>
					<td id="nbloger">
						<img id="NBear" src="/gs25/assets/images/product/naverIcon.png"/>
						<a href="${nvo.bloggerlink }"><p id="blogerName">${nvo.bloggername }</p></a>
						<div id="ballonIn">
						<ul>
							<li><a href="${nvo.link }"><strong>${nvo.title }</strong></a></li>
							<li><span>${nvo.description }</span></li>
						</ul>
						</div>
					</td>
				</tr>
				</c:forEach>
				</c:otherwise>
				</c:choose>
			</table>
		</div>
	</div>

	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>

<script>
$(function() {
	$("#btn1").on("click", function(){
		console.log('click');
		var product_no = ${prodvo.no };
		$.ajax({
			url: "/gs25/cart/write",
			type: "POST",
			data: {"product_no":product_no},
			dataType: "text",
			success : function(result){
				console.log('리절트!');
				
				 if(result == "1"){
					sweetAlert("찜하기 완료!");
					$("#btn1").addClass("hide");
					$("#btn2").removeClass("hide");
					
				} 
				
			},
			"error": function(jsXHR, status, e){
				console.error("error:"+status+":"+e);
			}
		});
	});
	
	$("#btn2").on("click", function(){
		var product_no = ${prodvo.no };
		$.ajax({
			url: "/gs25/cart/relieve",
			type: "POST",
			data: {"product_no":product_no},
			dataType: "text",
			success: function(result){
				console.log('dkqjd');
				if(result == "1"){
					//$(this).siblings("button").removeClass("hide");
					sweetAlert("찜해제!");
					$("#btn2").addClass("hide");
					$("#btn1").removeClass("hide");
				}
					
					
			},
			"error": function(jsXHR, status, e){
				console.error("error:"+status+":"+e);
			}
	
		});
	
	});
});
</script>





</html>