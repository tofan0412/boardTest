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
	alert("게시글을 삭제하였습니다.");
	var kind_no = $('#kind_no').val();
	$(location).attr('href', '/boardlist?kind_no='+kind_no);
})
</script>
</head>
<body>
	<input id="kind_no" type="text" value="${kind_no }">
</body>
</html>