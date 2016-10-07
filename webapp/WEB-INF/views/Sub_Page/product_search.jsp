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
<script type="text/javascript"
	src="/gs25/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<link href="/gs25/assets/css/product.css" rel="stylesheet"
	type="text/css">

<body>

	<div id="container">

		<jsp:include page="/WEB-INF/views/include/subheader.jsp" />


		<div id="content">

			<div class="container_product">
				<div class="container_productsub">

					<div id="store_header">
						<div id="store">
							<h1 id="sub_h1">상품검색</h1>
						</div>
						<div id="txt_header">
							<p>GS25 할인 상품을 안내해드립니다.</p>
							<p>할인상품을 검색을 통해 찾아보세요</p>
						</div>

					</div>

					<div id="search">
						<form id="search_form" action="/gs25/product/list" method="get">
							<input type="text" id="kwd" name="kwd" value="${map.keyword }">
							<input type="submit" value="찾기">
						</form>
					</div>

					<c:if test='${authUser.no==1 }'>
						<div class="insert">
							<a href="/gs25/product/insert" id="new-book">상품등록</a>
						</div>
					</c:if>

					<div id="productboard">
						<div id="productlist">
							<ul>
								<c:forEach var='vo' items='${map.list}' varStatus='status'>
									<li id="product_li">
										<ul class="tag_list_01">
											<li class="ico_tag_03">
												<!--${vo.category}-->
											</li>
										</ul> 
										<div class="pic_product">
										<c:if test="${authUser.no==1 }">
										<div id="del">
										<a href="/gs25/product/delete?no=${vo.no }" class="del">삭제</a>
										</div>
										</c:if>
										
											<a href="/gs25/product/view?no=${vo.no }&&name=${vo.name}">
											<img id="product" src="${vo.imageurl }" alt="형민)맛있는도시락">
											</a>
											<div class="infowrap">
												
												<span class='title'>
								<em class='mt'>${vo.maker})${vo.name }</em><em>${vo.price }원</em>
								</span>
												
												
											</div>
										</div>
									</li>
								</c:forEach>
							</ul>

							<c:if test="${empty map.list}">
								<div id="right">
									<div id="risk">
										<img src="/gs25/assets/images/customcenter/risk.png">
									</div>
									<p class="list-right">
										검색된 결과를 찾을 수 없습니다. <br>
									</p>
								</div>
							</c:if>


							<c:if test='${not empty map.list }'>
								<!-- begin:paging -->
								<div class="pager">
									<ul>
										<c:if test="${map.prevtoPage >= 0 }">
											<li><a href="/gs25/product/list?p=${map.prevtoPage }">◀◀</a></li>
										</c:if>

										<c:if test="${map.prevPage >= 0 }">
											<li><a href="/gs25/product/list?p=${map.prevPage }">◀</a></li>
										</c:if>

										<c:forEach begin='${map.firstPage }' end='${map.lastPage }'
											step='1' var='i'>
											<c:choose>
												<c:when test='${map.currentPage == i }'>
													<li class="selected">${i }</li>
												</c:when>

												<c:when test='${i > map.pageCount }'>
													<li>${i }</li>
												</c:when>

												<c:otherwise>
													<li><a href="/gs25/product/list?p=${i }">${i }</a></li>
												</c:otherwise>
											</c:choose>
										</c:forEach>

										<c:if test='${map.nextPage > 0 }'>
											<li><a href="/gs25/product/list?p=${map.nextPage }">▶</a></li>
										</c:if>
										<c:if test='${map.nexttoPage > 0 }'>
											<li><a href="/gs25/product/list?p=${map.nexttoPage }">▶▶</a></li>
										</c:if>
									</ul>
								</div>
							</c:if>
						</div>
					</div>

				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>