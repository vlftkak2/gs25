<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/gs25/assets/css/cart.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/gs25/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
<div id="container">
<jsp:include page="/WEB-INF/views/include/subheader.jsp" />
   <div id="content">
   <div id="cart_list">
   <div id="cart_wrap">
      <div id="cart_header">
         <div id="cart">
            <h1 id="sub_c1">찜목록</h1>
         </div>
         <div id="txt_header">
            <p>찜한 상품을 안내해드립니다.</p>
         </div>
      </div>
   
      
      <div id="cartboard">
         <div id="cartlist">
            <ul>
            <c:forEach var='vo' items='${map.list}' varStatus='status'>
               <li id="cart_li">
	               <div id="cart_product">

						<div id="del">
							<a href="/gs25/cart/delete?no=${vo.product_no }&store_no=${store_no}" class="del">찜해제</a>
						</div>

						
						<a href="/gs25/product/view?no=${vo.product_no }&name=${vo.name}&store_no=${store_no}">
						<img id="product" src="${vo.imageurl }" alt="형민)맛있는도시락">
						</a>
		                
		                <div class="infowrap">
		                     <span class='title'>
		                     <em class='mt'>${vo.maker})${vo.name }</em>
		                     
		                   <c:choose>
								<c:when test='${vo.remainderdate>7}'>
								<em>${vo.price }원</em>
								</c:when>
								
								<c:when test='${vo.remainderdate==7 && (vo.price-vo.countprice)>=(vo.price*0.5) }'>
								<em id="price">${vo.price }원</em>
								<em id="countprice">${vo.price-(vo.countprice) }원</em>
								</c:when>
								
								<c:when test='${vo.remainderdate==6 && (vo.price-vo.countprice*2)>=(vo.price*0.5) }'>
								<em id="price">${vo.price }원</em>
								<em id="countprice">${vo.price-(vo.countprice*2) }원</em>
								</c:when>
								
								<c:when test='${vo.remainderdate==5 && (vo.price-vo.countprice*3)>=(vo.price*0.5) }'>
								<em id="price">${vo.price }원</em>
								<em id="countprice">${vo.price-(vo.countprice*3) }원</em>
								</c:when>
								
								<c:when test='${vo.remainderdate==4 && (vo.price-vo.countprice*4)>=(vo.price*0.5) }'>
								<em id="price">${vo.price }원</em>
								<em id="countprice">${vo.price-(vo.countprice*4) }원</em>
								</c:when>
								
								<c:when test='${vo.remainderdate==3 && (vo.price-vo.countprice*5)>=(vo.price*0.5) }'>
								<em id="price">${vo.price }원</em>
								<em id="countprice">${vo.price-(vo.countprice*5) }원</em>
								</c:when>
								
								<c:when test='${vo.remainderdate==2 && (vo.price-vo.countprice*6)>=(vo.price*0.5) }'>
								<em id="price">${vo.price }원</em>
								<em id="countprice">${vo.price-(vo.countprice*6) }원</em>
								</c:when>
								
								<c:when test='${vo.remainderdate==1 && (vo.price-vo.countprice*7)>=(vo.price*0.5) }'>
								<em id="price">${vo.price }원</em>
								<em id="countprice">${vo.price-(vo.countprice*7) }원</em>
								</c:when>
								
								<c:when test='${vo.remainderdate<=0 }'>
								<em>${vo.price }원</em>
								</c:when>
								
								<c:otherwise>
								<em id="price">${vo.price }원</em>
								 <em id="countprice">${vo.halfprice }원</em>
								</c:otherwise>
							</c:choose>
		                     </span>
	                  	</div>
	               </div>
               </li>
            </c:forEach>
            </ul>
         
            <c:if test="${empty map.list}">
               <div id="right">
                  <div id="risk">
                     <img src="/gs25/assets/images/cart/risk.png">
                  </div>
                  <p class="empty-list">찜한 목록이 없습니다. <br> </p>
               </div>
            </c:if>
         
         
           <c:if test='${not empty map.list }'>
          <!-- begin:paging -->
			<div class="pager">
				<ul>
					<c:if test="${map.prevtoPage >= 0 }">
					<li><a href="/gs25/cart/list?p=${map.prevtoPage }">◀◀</a></li>
					</c:if>
					<c:if test="${map.prevPage >= 0 }">
						<li><a href="/gs25/cart/list?p=${map.prevPage }">◀</a></li>
					</c:if>

				<c:forEach begin='${map.firstPage }' end='${map.lastPage }' step='1' var='i'>
				<c:choose>
					<c:when test='${map.currentPage == i }'>
					<li class="selected">${i }</li>
					</c:when>
					<c:when test='${i > map.pageCount }'>
					<li>${i }</li>
					</c:when>
					<c:otherwise>
					<li><a href="/gs25/cart/list?p=${i }">${i }</a></li>
					</c:otherwise>
				</c:choose>
				</c:forEach>

							<c:if test='${map.nextPage > 0 }'>
								<li><a href="/gs25/cart/list?p=${map.nextPage }">▶</a></li>
							</c:if>
							<c:if test='${map.nexttoPage > 0 }'>
								<li><a href="/gs25/cart/list?p=${map.nexttoPage }">▶▶</a></li>
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