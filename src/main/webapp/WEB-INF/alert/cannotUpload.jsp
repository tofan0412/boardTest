<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	alert("한 게시물 당 파일은 최대 5개까지 올릴 수 있습니다.");
	var board_no = $("#board_no").val();
	$(location).attr('href', '/readboard?board_no='+board_no);
})
</script>
</head>
<body>
	<input type="text" id="board_no" value="${board_no }" readonly hidden="hidden">
</body>
</html>