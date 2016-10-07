<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/gs25/assets/js/jquery/jquery-1.9.0.js"></script>
<title>Insert title here</title>
</head>
<body>
<form id="mainForm">
    		<select id="dateYear" onChange="setDay()">
    		<option value="dateYear"></option>
    		</select>년&nbsp;
   			 <select id="dateMonth" onChange="setDay()">
   			 <option value="dateMonth"></option>
   			 </select>월&nbsp;
   			 <select id="dateDay">
   			  <option value="dateDay"></option>
   			 </select>일&nbsp;
   			
   			 
   			 <input type="submit" value="등록">
</form>
<input type="text" size="20" id="test">
</body>


<script language="javascript">
var monthtext=['01','02','03','04','05','06','07','08','09','10','11','12'];
var val = "";
window.onload = function() {
    var frm = document.getElementById('mainForm');
    var nowDate        = new Date();
    var nowYear        = nowDate.getFullYear();
    var nowMonth    = eval(nowDate.getMonth()) +1;
    var nowDay        = eval(nowDate.getDate());
    
    /***************
     * 년 세팅
     ***************/
    var startYear    = 2016;
    for( var i=0; i<20; i++ ) {
        frm['dateYear'].options[i] = new Option(startYear+i, startYear+i);
    }
    /***************
     * 월 세팅
     **************/
    for (var i=0; i<12; i++) {
        frm['dateMonth'].options[i] = new Option(monthtext[i], monthtext[i]);
        frm['dateMonth'].options[nowMonth]=new Option(monthtext[nowMonth],monthtext[nowMonth],true,true);
    }
    
    
    /***************************************
     * 먼저 현재 년과 월을 셋팅
     * 윤년계산과 월의 마지막 일자를 구하기 위해
     ***************************************/
    frm['dateYear'].value        = nowYear;
    frm['dateMonth'].value    = nowMonth;
    setDay();
    /********************************************
     * 일(day)의 select를 생성하고 현재 일자로 초기화
     ********************************************/
    frm['dateDay'].value        = nowDay;
}
/******************
 * 일(day) 셋팅
 ******************/
function setDay() {
    var frm = document.getElementById('mainForm');
    
    var year            = frm['dateYear'].value;
    var month            = frm['dateMonth'].value;
    var day                = frm['dateDay'].value;    
    var dateDay        = frm['dateDay'];
    
    var arrayMonth    = [31,28,31,30,31,30,31,31,30,31,30,31];
    /*******************************************
     * 윤달 체크 부분
     * 윤달이면 2월 마지막 일자를 29일로 변경
     *******************************************/
    if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
        arrayMonth[1] = 29;
    }
    /**************************************
     * 기존 일(day) select를 모두 삭제한다.
     **************************************/
    for( var i=dateDay.length; i>0; i--) {
        dateDay.remove(dateDay.selectedIndex);
    }
        
    /*********************************
     * 일(day) select 옵션 생성
     *********************************/
    for (var i=1; i<=arrayMonth[month-1]; i++) {
    	if(i<10){
    	val = "0" + new String(i);
        dateDay.options[i-1] = new Option(val, val);
    	}else{
    		dateDay.options[i-1] = new Option(i, i);
    	}
    }
    /*********************************************
     * 기존에 선택된 일값 유지
     * 기존 일값보다 현재 최대일값이 작을 경우
     * 현재 선택 최대일값으로 세팅
     ********************************************/
    if( day != null || day != '' ) {
        if( day > arrayMonth[month-1] ) {
            dateDay.options.selectedIndex = arrayMonth[month-1]-1;
        } else {
            dateDay.options.selectedIndex = day-1;
        }
    }
}


$("#mainForm").submit(function(){
	console.log(dateYear);
	console.log(dateMonth);
	console.log(dateDay);
});


</script>

</html>