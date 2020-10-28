<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	window.onunload = function () { 
	    window.opener.location.reload("https:www.naver.com"); 
	}
</script>

<style type="text/css">
table{
	border-collapse : collapse;
}
tr{
	padding : 5px;
	margin  : 15px;
}

</style>
</head>
<body>
	<% request.setCharacterEncoding("utf-8");%>
	<form method="POST" action="/modreply">
		<table border="1">
		<tr>
		<td>
		<label><strong>작성자 </strong></label>${param.user_id }<br>
		<hr>
		<label><strong>내용 </strong></label>
		<input name="reply_no" type="text" value="${param.reply_no}" hidden=hidden>
		<input name="reply_cont" type="text" value="${param.reply_cont}">
		<input name="board_no" type="text" value="${param.board_no}" hidden="hidden">
		<br>
		<br>
		</td>
		</tr>
		</table>
		<br>
		<input type="submit" value="수정" style="float: center;">
	</form>
</body>
</html>