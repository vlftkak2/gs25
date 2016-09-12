<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/gs25/assets/css/companyintro.css" rel="stylesheet"
	type="text/css">
<title>회사 소개</title>
</head>
<body>

	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />

		<div id="content">
			<div id="intro_main">
			<div id="intro_sub">
				<div id="brand_main">
					<h1>회사소개</h1>
				</div>

				<div id="first_wrap">

					<div id="about_wrap">
						<div id=about_top>
							<p id=highlight>ABOUT</p>
							<p>
								매일매일 신선함으로 가득 차는 GS25는 재치 있는 상품으로 하루를 충전하고, 365일 24시간 언제 어디 <br>
								서나 편리하게 원하는 것을 넘어 필요한 것까지 찾아주는 서비스로 편의점 이상의 가치를 드립니다.
							</p>
						</div>

						<div id=about_bottom>
							<div id=groce_wrap>
								<img src="/gs25/assets/images/introduce/groceries.png"
									width=100px />
							</div>
							<div id=fresh_wrap>
								<p id=highlight>Fresh & Refresh, GS25</p>
								<p>
									신선함으로 일상을 충전하고 활력을 얻는 공간, 고객의 하루가 시작되고 머무르고 마무리되는 </p>
									<p>Fresh한 경험을 통해 일상을 Refresh!!</p>
								
							</div>
						</div>
						
					</div>

				<div id="month_average">
							<img src="/gs25/assets/images/introduce/8000.png" />
							</div>

				</div>

				<div id="fresh_photo">
					<img src="/gs25/assets/images/introduce/fresh.png" width="1000px" />
				</div>


				<div id="daily_wrap">
					<p id="highlight">Daily Life Platform</p>
					<p>
						<strong>고객의 하루가 시작되고 잠깐의 휴식이 되어 머무르고, 하루의 마무리를 같이하는 편의점</strong> <br>
						이전까지의 편의점은 단순히 제품과 서비스를 채워두고 제공하는 편의점으로 고객들이 필요로 하는 사품과 서비스, 편안한
						휴식 제공에 대해 부족함이 있었습니다. <br> GS2는 정형화된 틀을 깨는 Fresh한 생각으로 매장을
						방문한 고객들이 1분 1초도 Refresh 할 수 있도록 편안한 휴식처를 제공합니다.
					</p>
				</div>

				<div id="refresh_photo">
					<img src="/gs25/assets/images/introduce/refresh.png" width="1000px" />
				</div>

			</div>
			</div>

		</div>
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>

</body>
</html>