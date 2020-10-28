<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<title>답글 작성</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="/js/summernote/summernote-lite.js"></script>
<script src="/js/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="/css/summernote/summernote-lite.css">
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script type="text/javascript">
$(document).ready(function() {
	//여기 아래 부분
	$('#summernote').summernote({
		  height: 500,                 	// 에디터 높이
		  minHeight: 300,              	// 최소 높이
		  maxHeight: 600,              	// 최대 높이
		  focus: true,                 	// 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",			   	// 한글 설정
		  placeholder: '글을 작성해주세요. 최대 2048자까지 입력 가능합니다.'
		  //placeholder 설정
	});
});
</script>
</head>
<body>
	<form method="post" action="/boardreply" enctype="multipart/form-data">
		답글 제목 : <input type="text" name="board_title">
		<input type="text" name="board_no" value="${param.board_no }" readonly hidden="hidden">
		<input type="text" name="kind_no" value="${param.kind_no }" readonly hidden="hidden">
		<br><br>
		<textarea id="summernote" name="board_cont"></textarea>
		<br>
		<h3>파일첨부</h3>
		<input type="file" name="img1" /><br>
		<input type="file" name="img2" /><br>
		<input type="file" name="img3" /><br>
		<input type="file" name="img4" /><br>
		<input type="file" name="img5" /><br>
		<input type="submit" value="작성" style="float: right;">
	</form>
</body>
</html>
