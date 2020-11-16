<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">${kind_name }</h2>
		<div class="table-responsive">
			<select id="setPageNum" style="float: right;">
				<option value="5">5개씩 보기</option>
				<option value="7">7개씩 보기</option>
				<option value="10">10개씩 보기</option>
			</select>
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
						<td><h3 style="float: right;">작성된 글이 없습니다.</h3></td>
					</c:if>
				</tbody>
			</table>
		</div>
		<a href="/board/boardRegist" class="btn btn-default pull-right">게시글
			작성</a>
		<div class="text-center">
			<ul class="pagination">
				<!-- 필요한 페이지의 갯수에 맞춰 자동으로 생성.. -->
				<c:if test="${page_cnt != 0 }">
					<c:if test="${page != '1' }">
						<li><a
							href="/board/boardlist?kind_no=${kind_no }&kind_name=${kind_name }&page=1&pageSize=${pageSize}">&lt;&lt;</a></li>
						<li><a
							href="/board//boardlist?kind_no=${kind_no }&kind_name=${kind_name }&page=${page-1}&pageSize=${pageSize}">&lt;</a></li>
					</c:if>
				</c:if>
				<c:forEach var="i" begin="1" end="${page_cnt}">
					<c:choose>
						<c:when test="${i == page}">
							<li class="active"><span>${i }</span></li>
						</c:when>
						<c:otherwise>
							<li><a
								href="/board/boardlist?kind_no=${kind_no }&kind_name=${kind_name }&page=${i}&pageSize=${pageSize}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${page_cnt != 0 }">
					<c:if test="${page != page_cnt}">
						<li><a
							href="/board//boardlist?kind_no=${kind_no }&kind_name=${kind_name }&page=${page+1}&pageSize=${pageSize}">&gt;</a></li>
						<li><a
							href="/board//boardlist?kind_no=${kind_no }&kind_name=${kind_name }&page=${page_cnt}&pageSize=${pageSize}">&gt;&gt;</a></li>
					</c:if>
				</c:if>
			</ul>
		</div>
	</div>
</div>
$$$$$$$