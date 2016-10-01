window.addEventListener('load', slideShow, false);

function slideShow() {
  
  /* GLOBALS */
	
  var globals = {
    slideDelay: 4000, // 지정된 슬라이드가 화면에 표시되는 시간(밀리초)
    fadeDelay: 35, // 첫 슬라이드와 다음 슬라이드 간 불투명도 변경 시간 간격(밀리초)
    wrapperID: "slideShowImages", 
    buttonID: "slideShowButton",
    buttonStartText: "start", // 슬라이드 쇼 전환 단추에서 사용할 텍스트
    buttonStopText: "stop",
    wrapperObject: null, 
    buttonObject: null, 
    slideImages: [],
    slideShowID: null, 
    slideShowRunning: true, 
    slideIndex: 0 
  }
  
  /* 메인 */
  
  initializeGlobals(); // globals를 초기화하여 <div> 래퍼 요소, <img> 및 전환 단추에 대한 액세스를 제공
  
  if ( insufficientSlideShowMarkup() ) {
    return; // slideShow.js에 필요한 태그가 있는 경우 false를 반환하고 그렇지 않으면 true를 반환
  }
 
  if (globals.slideImages.length == 1) {
    return; 
  }
  
 
  initializeSlideShowMarkup(); // 지정된 슬라이드 쇼 태그를 준비
  
  // 이벤트 수신기 toggleSlideShow를 추가, 슬라이드 쇼가 해제되거나 설정함
  globals.wrapperObject.addEventListener('click', toggleSlideShow, false);
  // 슬라이드 쇼 전환 단추가 태그에 표시되고 동일한 이벤트 수신기 함수(toggleSlideShow)를 이 단추에 연결
  if (globals.buttonObject) {
    globals.buttonObject.addEventListener('click', toggleSlideShow, false); 
  } 
  
  startSlideShow(); // startSlideShow를 호출하여 슬라이드 쇼를 본격적으로 시작
  
  /* Fuctions */
  
  function initializeGlobals() {   
	  // 해당 값에 대한 참조를 가져옴
    globals.wrapperObject = (document.getElementById(globals.wrapperID) ? document.getElementById(globals.wrapperID) : null);
    globals.buttonObject = (document.getElementById(globals.buttonID) ? document.getElementById(globals.buttonID) : null);   
    
    if (globals.wrapperObject) {
    	// querySelectorAll을 사용하여 슬라이드 <img> 개체로 배열을 채움
      globals.slideImages = (globals.wrapperObject.querySelectorAll('img') ? globals.wrapperObject.querySelectorAll('img') : []);
    }
  } 
  
 
  function insufficientSlideShowMarkup() {
    if (!globals.wrapperObject) { // 필요한 모든 태그가 있는 경우 false를 반환하고 그렇지 않으면 true를 반환
      if (globals.buttonObject) {
        globals.buttonObject.style.display = "none"; //  필수 태그 중 일부가 없는 경우 이 코드는 true를 반환하기 전에 연결된 슬라이드 쇼 태그를 정리
      }
      return true;
    }

    if (!globals.slideImages.length) { 
      if (globals.wrapperObject) {
        globals.wrapperObject.style.display = "none"; 
      }
    
      if (globals.buttonObject) {
        globals.buttonObject.style.display = "none";
      }
    
      return true;
    }
    
    return false; 
  } 
  
  
  function initializeSlideShowMarkup() {  
    var slideWidthMax = maxSlideWidth(); 
    var slideHeightMax = maxSlideHeight(); 
    
    globals.wrapperObject.style.position = "relative";
    globals.wrapperObject.style.overflow = "hidden"; 
    globals.wrapperObject.style.width = slideWidthMax + "px";
    globals.wrapperObject.style.height = slideHeightMax + "px";
    
    var slideCount = globals.slideImages.length;
    for (var i = 0; i < slideCount; i++) { 
      globals.slideImages[i].style.opacity = 0;
      // <div id="slideShowImages"> 컨테이너의 가운데 오도록 이미지의 absolute 위치를 조정
      globals.slideImages[i].style.position = "absolute";
      globals.slideImages[i].style.top = (slideHeightMax - globals.slideImages[i].getBoundingClientRect().height) / 2 + "px";   
      globals.slideImages[i].style.left = (slideWidthMax - globals.slideImages[i].getBoundingClientRect().width) / 2 + "px";               
    }
    
    globals.slideImages[0].style.opacity = 1;
        
    if (globals.buttonObject) {
    	// 전환 단추의 "슬라이드 쇼 중지" 텍스트
      globals.buttonObject.textContent = globals.buttonStopText;
    }
  }
  
 
    
  function maxSlideWidth() { // 가장 넓은 이미지의 너비와 가장 큰 이미지의 높이를 반환
    var maxWidth = 0;
    var maxSlideIndex = 0;
    var slideCount = globals.slideImages.length;
    
    for (var i = 0; i < slideCount; i++) {
      if (globals.slideImages[i].width > maxWidth) {
        maxWidth = globals.slideImages[i].width; 
        maxSlideIndex = i; 
      }
    }

    return globals.slideImages[maxSlideIndex].getBoundingClientRect().width;
  }
  
  // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    
  function maxSlideHeight() {
    var maxHeight = 0;
    var maxSlideIndex = 0;    
    var slideCount = globals.slideImages.length;
    
    for (var i = 0; i < slideCount; i++) {
      if (globals.slideImages[i].height > maxHeight) {
        maxHeight = globals.slideImages[i].height; 
        maxSlideIndex = i; 
      }
    }
    
    return globals.slideImages[maxSlideIndex].getBoundingClientRect().height;  
  } 

  

  function startSlideShow() { 
	  // 반복 호출을 중지할 때까지 gloabls.slideDelay밀리초마다 transitionSlides 함수를 호출
	  globals.slideShowID = setInterval(transitionSlides, globals.slideDelay);                
  } 

  
  
  function haltSlideShow() {
	  // 밀리초마다 이전에 요청된 transitionSlides 호출을 지워서 슬라이드 쇼를 중지
    clearInterval(globals.slideShowID);   
  } 

 
  /*  toggleSlideShow는 globals.slideShowRunning의 현재 상태에 따라 startSlideShow 및 haltSlideShow를 호출하여 슬라이드 쇼를 켜거나 끕니다. */
  function toggleSlideShow() {
    if (globals.slideShowRunning) {
      haltSlideShow();
      if (globals.buttonObject) { 
        globals.buttonObject.textContent = globals.buttonStartText; 
      }
    }
    else {
      startSlideShow();
      if (globals.buttonObject) { 
        globals.buttonObject.textContent = globals.buttonStopText; 
      }            
    }
    globals.slideShowRunning = !(globals.slideShowRunning);
  } 
  
 

  function transitionSlides() {
    var currentSlide = globals.slideImages[globals.slideIndex]; //  슬라이드 이미지 개체의 배열
    
    ++(globals.slideIndex); // 다음 슬라이드로 이동
    if (globals.slideIndex >= globals.slideImages.length) { // 다음 슬라이드가 없는 경우 처음부터 시작
      globals.slideIndex = 0;
    }
    
    var nextSlide = globals.slideImages[globals.slideIndex]; // 다음 슬라이드 지정
    
    
    // 현재 슬라이드를 페이드 아웃하고 다음 슬라이드를 페이드 인하는 데 사용되는 로컬 변수
    var currentSlideOpacity = 1; 
    var nextSlideOpacity = 0;
    var opacityLevelIncrement = 1 / globals.fadeDelay;
    var fadeActiveSlidesID = setInterval(fadeActiveSlides, globals.fadeDelay);
    
    function fadeActiveSlides() {
      currentSlideOpacity -= opacityLevelIncrement;
      nextSlideOpacity += opacityLevelIncrement;
     
      
      if (currentSlideOpacity >= 0 && nextSlideOpacity <= 1) {
        currentSlide.style.opacity = currentSlideOpacity;
        nextSlide.style.opacity = nextSlideOpacity; 
      }
      else {
        currentSlide.style.opacity = 0;
        nextSlide.style.opacity = 1; 
        clearInterval(fadeActiveSlidesID);
      }        
    } 
  } 
  
} 