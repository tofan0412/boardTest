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
	alert("잘못 된 접근입니다. 다시 시도해 주십시오..");
	$(location).attr('href', '/login.jsp');
})
</script>
</head>
<body>

</body>
</html>