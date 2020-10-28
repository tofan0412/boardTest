<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${s_member == null }">
		<jsp:include page="/alert.jsp"></jsp:include>
	</c:if>

	<ul class="nav nav-sidebar">
		<li class="active"><a href="${pageContext.request.contextPath}/main.jsp">Main <span class="sr-only">(current)</span></a></li>
		<li class="active"><a href="/managekind">게시판 관리</a></li>
		<c:forEach items="${menulist }" var="kind">
			<c:choose>
			<c:when test="${kind.kind_valid == '1' }">
				<li class="active"><a href="/boardlist?kind_no=${kind.kind_no }">${kind.kind_name }</a></li>
			</c:when>
			</c:choose>
		</c:forEach>
	</ul>
</body>
</html>