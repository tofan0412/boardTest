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
	alert("게시글 작성에 실패하였습니다..");
	$(location).attr('href', '/boardlist?kind_no='+${kind_no});
})
</script>
</head>
<body>

</body>
</html>