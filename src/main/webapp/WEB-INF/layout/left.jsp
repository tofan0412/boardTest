<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
$(function(){
	var user_id = "${s_member.user_id}";
	console.log(user_id);
	
	if (user_id == ""){
		alert("로그인이 필요합니다.");
		$(location).attr('href', '/board/loginView');
	}

	menuList();	

	function menuList(){
		$.ajax({
			url : "/board/menuList",
			method : "GET",
			success : function(res){
				$('#menuCont').empty();
				var html = res.split("$$$$$$$");
				$('#menuCont').html(html[0]);
			}
		})
	}
})
</script>
<div id="menuCont"></div>
