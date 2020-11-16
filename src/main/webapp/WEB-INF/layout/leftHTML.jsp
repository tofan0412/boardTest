<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="nav nav-sidebar">
	<li class="active"><a href="/board/mainView">Main <span class="sr-only">(current)</span></a></li>
	<li class="active"><a href="/board/manageKindView">게시판 관리</a></li>
	<c:forEach items="${menulist }" var="kind">
		<c:choose>
			<c:when test="${kind.kind_valid == '1' }">
				<li class="active"><a
					href="/board/boardlist?kind_no=${kind.kind_no }&kind_name=${kind.kind_name}">${kind.kind_name }</a></li>
			</c:when>
		</c:choose>
	</c:forEach>
</ul>
$$$$$$$