<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
</script>

<style type="text/css">
label {
	font-size: 1.3em;
}
</style>

<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">게시판 수정</h2>
		<div class="table-responsive">
				<c:forEach items="${menulist }" var="kind">
					<form action="/board/modKind" method="POST">
						<label>게시판 이름</label>
						<input name="kind_no" type="text" value="${kind.kind_no }" hidden="hidden"> 
						<input name="kind_name" type="text" value="${kind.kind_name }"> 
						<select name="kind_valid">
							<c:if test="${kind.kind_valid == '1' }">
								<option value="1" selected="selected">사용</option>
								<option value="0">미사용</option>
							</c:if>
							<c:if test="${kind.kind_valid == '0' }">
								<option value="1">사용</option>
								<option value="0" selected="selected">미사용</option>
							</c:if>
						</select> 
						<input type="submit" value="수정">
						<br>
					</form>
				</c:forEach>
		</div>
		<h2 class="sub-header">게시판 등록</h2>
		<div class="table-responsive">
			<form action="/board/kindRegist" method="POST">
				<input name="kind_name" type="text" value="${kind.kind_name }">
				<select name="kind_valid">
					<option value="1">사용</option>
					<option value="0">미사용</option>
				</select> <input type="submit" value="신규 게시판 등록">
			</form>
		</div>
	</div>
</div>
