<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
<link rel="stylesheet"
    href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<meta charset="utf-8">
<!-- <link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"> -->
<script src="https://code.jquery.com/jquery-3.4.1.js"
    integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
    crossorigin="anonymous"></script>
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style>
fieldset.scheduler-border {
	width: 40%;
	border: 1px groove #ddd !important;
	padding: 0 1.4em 1.4em 1.4em !important;
	margin: 0 0 1.5em 0 !important;
	-webkit-box-shadow: 0px 0px 0px 0px #000;
	box-shadow: 0px 0px 0px 0px #000;
}

legend.scheduler-border {
	font-size: 1.2em !important;
	font-weight: bold !important;
	text-align: left !important;
	width: auto;
	padding: 0 10px;
	border-bottom: none;
}

.container {
	width: 750px;
}

.form-control {
	display: inline-block;
	width: 50%;
}

.popover {
	width: 300px;
}
#datespan{
	color:red;	
}
#span{
	color:blue;	
}
.ml-auto .dropdown-menu {
    left: auto !important;
    right: 0px;
}
#test_btn1 {
    margin-right: 0px;
}
#test_btn2 {
    margin-left: -2px;
}
#test_btn2, #test_btn1 {
    border: 0px black;
    background-color: rgba(0, 0, 0, 0);
    color: white;
    padding: 5px;
}
#test_btn3, #test_btn4 {
    background-color: rgba(0, 0, 0, 0);
    color: black;
    padding: 5px;
}
.the-legend {
    border-style: none;
    border-width: 0;
    font-size: 14px;
    line-height: 20px;
    margin-bottom: 0;
    width: auto;
    padding: 0 10px;
    border: 1px solid #e0e0e0;
}
.the-fieldset {
    border: 1px solid #e0e0e0;
    padding: 10px;
}
legend.scheduler-border {
    width: inherit; /* Or auto */
    padding: 0 10px; /* To give a bit of padding on the left and right */
    border-bottom: none;
}
body>div>div>div>ul ul { /*서브메뉴 선택시 서브메뉴화면  */
    display: none;
    background: white;
    box-shadow: 1px 1px 5px black;
}
#modal {
    position: relative;
    width: 100%;
    height: 100%;
    z-index: 1;
}
#modal h2 {
    margin: 0;
     
     
}
#modal button {
    display: inline-block;
    width: 100px;
    margin-left: calc(100% - 100px - 10px);
}
#modal .modal_content {
    width: 300px;
    margin: 100px auto;
    padding: 20px 10px;
    background: #fff;
    border: 2px solid #666;
}
#modal .modal_layer {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    z-index: -1;
}
#dropdown-box {
    dispaly: none;
}
body>nav>div>ul>div>ul {
    background-color: black;
}
.card-text {
    display: inline-block;
    width: 360px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: normal;
    line-height: 1.2;
    height: 150px;
    text-align: left;
    word-wrap: break-word;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
}
body>nav>div>ul>div>ul {
    background-color: black;
}
</style>
<script type="text/javascript">
	$(function() {
		$('#login').popover({
			placement : 'bottom',
			html : true,
			content : $('#myForm').html()
		}).on('click', function() {
			$(function() {
				$('.popover-content').css('background-color', '#465355');
			});
			$(function() {
				$('.popover-title').css('background-color', '#465355');
			});
			// had to put it within the on click action so it grabs the correct info on submit
			$('.btn-primary').click(function() {
				$.post('/echo/html/', {
					email : $('#email').val(),
					name : $('#password').val()
				}, function(r) {
					$('#pops').popover('hide')
				})
			})
		})
	})
	
	$(function(){
		$("#span").text("오늘 당신이 가장 많이 검색한 키워드는 ${key } 입니다.");
	})
	
	$(function(){
		$("#datespan").text("지금 즉시 주문하면 ${date } 일 후 배송완료 예정!");
	})
	
</script>
<script type="text/javascript">
    function write() {
        location.href = "write";
    }
</script>

<!-- Bootstrap core CSS -->
<link href="../../dist/css/bootstrap.min.css" rel="stylesheet">
​
<!-- Custom styles for this template -->
<link href="jumbotron.css" rel="stylesheet">
​
<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="../../assets/js/ie-emulation-modes-warning.js"></script>
​
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
    <!-- 메인내용 -->
    <nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"></a>
			</div>
			<c:if test="${islogin ne 'isLogin'}">
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
						<li><a data-toggle="modal" href="#myModal"><img width="20px" src="./img/사람.png">Sign
								Up</a></li>
						<li id=login><a href="#"><img width="20px" src="./img/사람.png">Login</a></li>
					</ul>
				</div>
			</c:if>
			<c:if test="${islogin eq 'isLogin'}">
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="memInfo"><img width="20px" src="./img/사람.png">회원정보수정</a></li>
						<li id=logout><a href="Logout"><img width="20px" src="./img/사람.png">Logout</a></li>
					</ul>
				</div>
			</c:if>
		</div>
	</nav>


	<a href="/"> <img src="./img/ssafy.png"></a>
	<ul class="nav navbar-nav navbar-right">
		<li class="active"><a href="#" style="margin-right: 30px">공지
				사항 </a></li>
		<li><a href="productInfo" style="margin-right: 30px">상품
				정보</a></li>
		<li><a href="#" style="margin-right: 30px">베스트 섭취 정보</a></li>
		<li><a href="#" style="margin-right: 30px">예상 섭취 정보</a></li>
		<li><a href="#" class="glyphicon glyphicon-search"
			style="margin-right: 100px"></a></li>
	</ul>

	<!-- Main jumbotron for a primary marketing message or call to action -->


	<div class="jumbotron"
		style="background-color: #A9A9A9; margin-bottom: 0px">
		<div class="container" style="text-align: center">
			<h1 style="color: white">WHAT WE PROVIDE</h1>
			<hr>
			<p style="color: white">건강한 삶을 위한 먹거리 프로젝트</p>
		</div>
	</div>
    
    <table class="table">
        <thead class="bg-warning" style='background-color: #FFFF55'>
            <th scope="col">#</th>
            <th scope="col">제목</th>
            <th scope="col">조회수</th>
            <th scope="col">게시자</th>
        <thead>
        <tbody>
            <c:forEach var="name" items="${board}">
                <tr></tr>
                <tr style="cursor: pointer" onClick="location.href='${name.index}'">
                    <th scope="row">${name.index }</th>
                    <td>${name.contents }</td>
                    <td>${name.count }</td>
                    <td>${name.name }</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <div id="myForm" class="hide">
		<form action="/echo/html/" id="popForm" method="get">
			<div>
				<label for="email" style="color: white;">ID</label> <br />
				<input type="text" name="email" id="email"
					class="form-control input-md" style="background-color: #677475;">
			</div>
			<div>
				<label for="password" style="color: white;">비밀번호</label> <br /> <input
					type="password" name="name" id="name" class="form-control input-md"
					style="background-color: #677475;">
				<div style="margin-top: 20px">
					<button type="submit" class="btn btn-gray btn-sm">로 그 인</button>
					<button type="button" class="btn btn-gray btn-sm">비밀번호 찾기</button>
				</div>
			</div>
		</form>
	</div>
	<div id="result"></div>
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">


			<div class="modal-content">
				<form name="registerform" method="post"
					enctype="multipart/form-data" action="./register">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">×</button>
						<h3 class="modal-title">Sign Up</h3>
					</div>
					<div class="modal-body">
						<div class="cntainer">
							<div class="well">
								<div class="container">
									<div class="form-group">
										<label for="email">ID*:</label> <br /> <input type="email"
											class="form-control" id="email" placeholder="Enter email"
											name="email">
									</div>
									<div class="form-group">
										<label for="pwd">Password*:</label><br /> <input
											type="password" class="form-control" id="pwd"
											placeholder="Enter password" name="pwd">
									</div>
									<div class="form-group">
										<label for="userPwCheck">비밀번호 확인*</label><br /> <input
											type="password" id="userPwCheck" class="form-control"
											maxlength="20" autocomplete="off">
									</div>
									<div class="form-group">
										<label for="username">이름*</label><br /> <input type="text"
											id="userId" class="form-control" maxlength="20" value="">
									</div>
									<div class="form-group">
										<label for="useradress">주소*</label><br /> <input type="text"
											id="userId" class="form-control" maxlength="20" value="">
									</div>
									<div class="form-group">
										<label for="email01">이메일*</label>
									</div>
									<div class="form-group">
										<input type="text" id="email01" class="form-inline"
											name="email01" size="20" maxlength="20" value=""
											autocomplete="off"><span>@</span> <input id="email02"
											name="email02" class="form-inline" list="domains"
											placeholder="도메인입력/선택">
										<datalist id="domains">
											<option value="naver.com">
											<option value="daum.net">
											<option value="gmail.com">
											<option value="yahoo.co.kr">
										</datalist>
									</div>
									<div class="fieldlabel">
										<label for="mPhone1">전화번호</label>
									</div>
									<div class="formfield">
										<select id="mPhone1" name="mPhone1">
											<option value="010" selected>010</option>
											<option value="011">011</option>
											<option value="016">017</option>
											<option value="018">018</option>
											<option value="019">019</option>
										</select>- <input id="mPhone2" name="mPhone2" type="number" value=""
											size="4" maxlength="4" autocomplete="off">- <input
											id="mPhone3" name="mPhone3" type="number" value="" size="4"
											maxlength="4" autocomplete="off">
									</div>
									<br />
									<fieldset class="scheduler-border" id="option">
										<legend class="scheduler-border">알레르기*</legend>
										<div class="formfield">
											<input type="checkbox" name="hobby" value="대두" alt="취미">
											대두 <input type="checkbox" name="hobby" value="땅콩" alt="취미">
											땅콩 <input type="checkbox" name="hobby" value="우유" alt="취미">
											우유 <input type="checkbox" name="hobby" value="우유" alt="취미">
											게 <br> <input type="checkbox" name="hobby" value="새우"
												alt="취미"> 새우 <input type="checkbox" name="hobby"
												value="참치" alt="취미"> 참치 <input type="checkbox"
												name="hobby" value="연어" alt="취미"> 연어 <input
												type="checkbox" name="hobby" value="쑥" alt="취미"> 쑥 <br>
											<input type="checkbox" name="hobby" value="소고기" alt="취미">
											소고기 <input type="checkbox" name="hobby" value="닭고기" alt="취미">
											닭고기 <input type="checkbox" name="hobby" value="돼지고기" alt="취미">
											돼지고기 <br> <input type="checkbox" name="hobby"
												value="복숭아" alt="취미"> 복숭아 <input type="checkbox"
												name="hobby" value="민들레" alt="취미"> 민들레 <input
												type="checkbox" name="hobby" value="계란흰자" alt="취미">
											계란흰자
										</div>
									</fieldset>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<a href="/" style="align: left; margin-right: 450px"><input
							type="button" class="btn btn-primary btn-sm" value="등록"></a>
						<button algin="right" type="button" class="btn btn-default"
							data-dismiss="modal">Close</button>
					</div>
				</form>
			</div>

		</div>
	</div>
	
	<div class="footer" style="background-color: #F8F8FF">
		<div class="container">
			<div class="footer-inner">
				<div class="row">
					<div class="col-lg-10">
						<div class="footer-content">
							<h2 class="title">Find Us</h2>
							<hr>
							<div class="separator-2"></div>
							<ul class="list-icons" style="list-style: none;">
								<li style="opacity: 0.6"><img src="./img/위치.png"
									width="20px"> (SSAFY) 서울시 강남구 테헤란로 멀티스퀘어</li>
								<li style="opacity: 0.6"><img src="./img/전화.png"
									width="20px"> 1544-9001</li>
								<li style="opacity: 0.6"><a href="#"><img
										src="./img/이메일.png" width="20px"> admin@ssafy.com</a></li>
							</ul>
							<br />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="subfooter" style="background-color: #F5F5F5">
		<div class="container">
			<div class="subfooter-inner">
				<div class="row">
					<div class="col-md-12">
						<br />
						<p class="text-center" style="opacity: 0.6">Copyright by
							SSAFY. All rights reserved.</p>

					</div>
				</div>
			</div>
		</div>
	</div>
    <!-- 푸터 내용 -->
    <%-- <jsp:include page="footer.jsp" /> --%>
</body>
</html>