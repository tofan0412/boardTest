<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="/js/summernote/summernote-lite.js"></script>
<script src="/js/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="/css/summernote/summernote-lite.css">
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">

<script type="text/javascript">
	$(document).ready(function() {
		//여기 아래 부분
		$('#summernote').summernote({
			height : 500, // 에디터 높이
			minHeight : 300, // 최소 높이
			maxHeight : 600, // 최대 높이
			focus : true, // 에디터 로딩후 포커스를 맞출지 여부
			lang : "ko-KR", // 한글 설정
			placeholder : '글을 작성해주세요. 최대 2048자까지 입력 가능합니다.'
		//placeholder 설정
		});
	});
</script>

<div id="listCont"
	class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<h2 class="sub-header">게시글 작성</h2>
	<form method="POST" action="/board/boardRegist" enctype="multipart/form-data">
		제목 : <input type="text" name="board_title"><br> <br>
		게시판선택 <select name="kind_no">
			<c:forEach items="${menulist }" var="kind">
				<c:choose>
					<c:when test="${kind.kind_valid == '1' }">
						<option value="${kind.kind_no }">${kind.kind_name }</option>
					</c:when>
				</c:choose>
			</c:forEach>
		</select> <br> <br>
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
</div>