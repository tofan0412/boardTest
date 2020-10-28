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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="icon" href="../../favicon.ico">
<title>Jsp</title>
<%@ include file="/layout/commonlib.jsp"%>
<style type="text/css">
.board {
	font-size: 1.3em;
}

#cont {
	font-size: 15px;
}

#board_hr {
	height: 10px;
	border: 0 none;
	color: #000;
	background-color: orange;
}
textarea{
	resize : none;
}
</style>
<script type="text/javascript">
$(function(){
	$('.modBtn').on('click',function(){
		var board_no = $(this).data("board_no");
		var user_id = $("#user_id").data("user_id");
		$(location).attr('href', '/modboard?user_id='+user_id+"&board_no="+board_no);
	})

	$('.delBtn').on('click',function(){
		var board_no = $(this).data("board_no");
		var user_id = $("#user_id").data("uzser_id");
		var kind_no = $('#kind_no').val();
		
		$(location).attr('href', '/delboard?user_id='+user_id+"&board_no="+board_no+"&kind_no="+kind_no);
	})
	
	$('.modBtn_reply').on('click',function(){
		var reply_no = $(this).data("reply_no");
		var user_id = $(this).data("user_id");
		var reply_cont = $(this).data("reply_cont");
		var board_no = $('#board_no').val();
		
		window.open("/modReply.jsp?reply_no="+reply_no+"&user_id="+user_id+"&reply_cont="+reply_cont+"&board_no="+board_no,
				"popup_window","left=700, top=400, width=250, height=170, resizable=no, scrollbars=no");
	})

	$('.delBtn_reply').on('click',function(){
		var reply_no = $(this).data("reply_no");
		var board_no = $('#board_no').val();
		
		$(location).attr('href', '/delreply?reply_no='+reply_no+"&board_no="+board_no);
	
	})

	$('.profileDownBtn').on('click', function(){
		var file_no = $(this).data('file_no'); 
		$(location).attr('href', '/filedownload?file_no='+file_no);
	})

	// 답글 작성 함수
	$('.replyBoatdBtn').on('click', function(){
		var board_no = $(this).data('board_no'); 
		var kind_no = $(this).data('kind_no'); 
		$(location).attr('href', '/main_boardReplyRegist.jsp?board_no='+board_no+"&kind_no="+kind_no);
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
							<input id="board_no" type="text" readonly value="${BoardVo.board_no }" hidden="hidden">
							<input id="kind_no" type="text" readonly value="${BoardVo.kind_no }" hidden="hidden">
							<label class="board"><h1>${BoardVo.board_title }</h1></label><br>
							<br> <label class="board">작성자
								<h4 id="user_id" data-user_id="${BoardVo.user_id}">${BoardVo.user_id}</h4>
							</label>
							<div style="float: right;">
								<label class="board">작성일
									<h4>${BoardVo.board_date}</h4>
								</label>
							</div>
						</div>
						<hr>
						<div id="cont">
							${BoardVo.board_cont} <br> <br>
							<hr>
							<h4><li>첨부파일 목록</li></h4>
							<c:forEach items="${filelist }" var="FileVo">
								<c:if test="${filelist.size() == 0 }">
									<h4>첨부 파일이 없습니다.</h4>
								</c:if>
								<label>${FileVo.realfilename }</label><br>
								<img src="${pageContext.request.contextPath}/
								profileImg?file_no=${FileVo.file_no}" style="height : 100px; width : 100px;"/><br>
								<button type="button" class="btn btn-default profileDownBtn" data-file_no="${FileVo.file_no}">
									다운로드
								</button>
								<br>
							</c:forEach>
							
							<c:if test="${BoardVo.user_id == s_member.user_id}">
								<input class="modBtn" data-board_no="${BoardVo.board_no }"
									type="button" value="수정" style="float: right;">
								<input class="delBtn" data-board_no="${BoardVo.board_no }"
									type="button" value="삭제" style="float: right;">
							</c:if>
							<c:if test="${BoardVo.user_id != s_member.user_id}">
								<input class="replyBoatdBtn" 
								data-board_no="${BoardVo.board_no }"
								data-kind_no="${BoardVo.kind_no }"
									type="button" value="답글 쓰기" style="float: right;">
							</c:if>
						</div>
						<br>
						<hr id="board_hr">
						<div id="reply">
							<h3>댓글 (${replylist.size()})</h3>
							<br>
							<c:forEach items="${replylist }" var="reply">
								
								<label>${reply.user_id }</label>
								<div style="float: right;">
								<label>${reply.reply_date }</label>
								</div>
								<br><br>
								${reply.reply_cont }
								
								<c:if test="${reply.user_id == s_member.user_id}">
									<input class="modBtn_reply" 
									data-reply_no="${reply.reply_no }"
									data-user_id="${reply.user_id }"
									data-reply_cont="${reply.reply_cont }"
									type="button" value="수정" style="float: right;">
									
									<input class="delBtn_reply" 
									data-reply_no="${reply.reply_no }" 
									data-user_id="${reply.user_id }"
									data-reply_cont="${reply.reply_cont }"
									type="button" value="삭제" style="float: right;">
								</c:if>
								<br>
								<hr>
							</c:forEach>
							<div id="replyRegist">
								<form action="replyregist" method="POST">
									<input name="board_no" type="text" value="${BoardVo.board_no }" hidden="hidden">
									<textarea name="reply_cont" rows="5" cols="120" placeholder="욕설과 모욕적인 표현은 피해 주세요 :)"></textarea>
									<input type="submit" value="댓글 작성" style="float: right;">
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
