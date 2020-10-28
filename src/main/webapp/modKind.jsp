<%@page import="java.util.List"%>
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
<link rel="icon" href="../../favicon.ico">
<title>Jsp</title>
<%@ include file="/layout/commonlib.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">



</script>
<style type="text/css">
label{
	font-size : 1.3em;
}
</style>
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
						<h2 class="sub-header">게시판 수정</h2>
						<div class="table-responsive">
							<c:forEach items="${menulist }" var="kind">
								
								<form action="/modkind" method="POST">
								<label>게시판 이름</label>
								<input name="kind_no" type="text" value="${kind.kind_no }" hidden>
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
								</form>
								
							</c:forEach>
						</div>
						<h2 class="sub-header">게시판 등록</h2>
						<div class="table-responsive">
							<form action="/kindregist" method="POST">
								<input name="kind_name" type="text" value="${kind.kind_name }">
								<select name="kind_valid">
									<option value="1">사용</option>
									<option value="0">미사용</option>	
								</select>
								<input type="submit" value="신규 게시판 등록">
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
