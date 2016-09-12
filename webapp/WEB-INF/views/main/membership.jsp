<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/gs25/assets/css/membership.css" rel="stylesheet"
	type="text/css">
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />

		<div id="content">
			<div id="member_main">
			<div id="member_sub">
				<div id="top_wrap">
					<div id="wrap">
						<h1>할인카드</h1>
						<h4>GS25 매장에서 할인 및 멤버십 혜택을 누리세요.</h4>
					</div>
				
				<div id="card_main">
					<img src="/gs25/assets/images/membership/card.png" width="1000px">
				</div>
					</div>

				<div id="LG_wrap">
					<div id="LG">
						<img src="/gs25/assets/images/membership/card1.jpg" width="250px" height="150px" >
					</div>
					<div id="LG_content">
						<p id="highlight">
							LG U+멤버십카드 15%할인 
						<p>
						<p>
							GS25에서 상품 구매 후 LG유플러스 카드를 제시하시면 15%를 할인 받으실 수 있습니다. <br> (단,
							주류/담배/서비스/안전상비의약품 등 일부 품목은 15%할인 대상에서 제외 됩니다.) <br> ※ 휴게소 등
							일부 GS25에서는 15%할인 적용이 불가 합니다.
						</p>
					</div>
				</div>

				<div id="Opoint_wrap">
					<div id="Opoint">
						<img src="/gs25/assets/images/membership/card2.jpg" width="250px" height="150px">
					</div>
					<div id="Opoint_content">
						<p id="highlight">
							OH POINT 카드
						</p>
						<p>
							세븐일레븐 바이더웨이 매장 포인트 15% 차감할인 <br> 멤버십 카드 외 신용카드 뒷면에 OH POINT
							마크가 있는 제휴카드도 할인 적용 가능
						</p>
					</div>
				</div>

				<div id="MVisa_wrap">
					<div id="MVisa">
						<img src="/gs25/assets/images/membership/card3.jpg" width="250px" height="150px">
					</div>
					<div id="MVisa_content">
						<p id="highlight">
							현대 카드
						</p>
						<p>
							세븐일레븐 바이더웨이 매장 M포인트 20% 차감할인 <br> GS수퍼마켓에서 현대M카드로 3만원 이상 결제
							시 2천 현대M포인트 사용 가능합니다.
						</p>
					</div>
				</div>

				<div id="Happy_wrap">
					<div id="Happy">
						<img src="/gs25/assets/images/membership/card4.jpg" width="250px" height="150px">
					</div>
					<div id="Happy_content">
						<p id="highlight">
						해피포인트 카드
						</p>
						<p>
							GS25에서 상품 구매 후 해피포인트 카드를 제시하시면 15%를 할인 받으실 수 있습니다. <br> (단,
							주류/담배/서비스/안전상비의약품 등 일부 품목은 15%할인 대상에서 제외 됩니다.) <br> 티머니카드로
							해피포인트 할인 받으시면 할인금액의 10%를 해피포인트로 돌려 드립니다.
						</p>
					</div>

				</div>
				
				</div>
				
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
		
	</div>
</body>
</html>