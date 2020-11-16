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
	alert("댓글을 수정하였습니다.");
	window.close();
	var f= document.forms.popupForm;
	document.domain = "127.0.0.1"; 
	opener.name = "parentPage";
	f.target = opener.name;
	f.submit();
	
})
</script>
</head>
<body>

</body>
</html>