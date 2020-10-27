<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="icon" href="../../favicon.ico">
<title>Jsp</title>
<%@ include file="/layout/commonlib.jsp"%>
<style type="text/css">
label{
	font-size : 1.3em;
}
#cont{
	font-size : 15px;
}
</style>
<script type="text/javascript">
$(function(){
	$('.modBtn').on('click',function(){
		var board_no = $(this).data("board_no");
		var user_id = $("#user_id").data("user_id");
// 		alert(user_id);
		$(location).attr('href', '/modboard?user_id='+user_id+"&board_no="+board_no);
	})

	$('.delBtn').on('click',function(){
		var board_no = $(this).data("board_no");
		var user_id = $("#user_id").data("user_id");
// 		alert(user_id);
		$(location).attr('href', '/delboard?user_id='+user_id+"&board_no="+board_no);
	})
})
</script>
</head>
<body>
	<%@ include file="/layout/header.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
				<%@ include file="/layout/left.jsp"%>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<div id="header">
							<label>제목<h2>${BoardVo.board_title }</h2></label><br>
							<label>작성자<h4 id="user_id" data-user_id="${BoardVo.user_id}">${BoardVo.user_id}</h4></label>
							<div style="float: right;">
							<label>작성일<h4>${BoardVo.board_date}</h4></label>
							</div>
						</div>
						<hr>
						<div id="cont">	
							${BoardVo.board_cont}
							<br>
							<br>
							<c:if test="${BoardVo.user_id == s_member.user_id}">
							<input class="modBtn" data-board_no="${BoardVo.board_no }" 
							type="button" value="수정" style="float: right;">
							<input class="delBtn" data-board_no="${BoardVo.board_no }" 
							type="button" value="삭제" style="float: right;">		
						</c:if>
						</div>
						<br>
						<hr>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
