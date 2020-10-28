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
$(function(){
	$(".lineBtn").on('click',function(){
		var board_no = $(this).data("board_no");
		$(location).attr('href', '/readboard?board_no='+board_no);
	})
})

</script>
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
						<h2 class="sub-header">게시판</h2>
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>글번호</th>
									<th>제목</th>
									<th>작성일</th>
									<th>작성자</th>
								</tr>
								<tbody id="boardlist">
								<c:forEach items="${boardlist}" var="board">
									<tr>
										<td>${board.board_no}</td>
										<c:choose>
											<c:when test="${board.board_delete == '0' }">
												<td>(-- 작성자에 의해 삭제된 게시글입니다 --)</td>
											</c:when>
											<c:when test="${board.board_delete == '1'}">
												<td class="lineBtn" data-board_no="${board.board_no}"><a>${board.board_title}</a></td>
											</c:when>
										</c:choose>
										<td>${board.board_date}</td>
										<td>${board.user_id}</td>
									</tr>
								</c:forEach>
								<c:if test="${boardlist.size() == 0 }">
									<td><h3 style="float : right;">작성된 글이 없습니다. </h3></td>
								</c:if>
								</tbody>
							</table>
						</div>

						<a href="${pageContext.request.contextPath}/main_boardregist.jsp" class="btn btn-default pull-right">게시글 작성</a>
						<div class="text-center">
							<ul class="pagination">
							<!-- 필요한 페이지의 갯수에 맞춰 자동으로 생성.. -->
								<c:forEach var="i" begin="1" end="${pages}">
									<c:choose>
										<c:when test="${i == page}">
											<li class="active"><span>${i }</span></li>
										</c:when>
										<c:otherwise>
											<li><a href="${pageContext.request.contextPath }/memberList?page=${i}&pageSize=${pageSize}">${i}</a></li>	
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
