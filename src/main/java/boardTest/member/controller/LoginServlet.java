package boardTest.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boardTest.kind.model.KindVo;
import boardTest.kind.service.KindService;
import boardTest.kind.service.KindServiceI;
import boardTest.member.model.MemberVo;
import boardTest.member.service.MemberService;
import boardTest.member.service.MemberServiceI;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    MemberServiceI service;
    KindServiceI service_k;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/alert.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service = MemberService.getService();
		service_k = KindService.getService();
		
		String user_id = request.getParameter("user_id");
		String user_pass = request.getParameter("user_pass");
		
		Map<String, String> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("user_pass", user_pass);
		
		MemberVo result = service.login(map);
		
		// 로그인 실패
		if (result == null) {
			doGet(request, response);
		}
		// 로그인 성공
		else {
			// 존재하는 메뉴를 불러오자..
			List<KindVo> menulist = service_k.roadMenuList();
			// 아이디와 메뉴를 세션 객체에 저장한다. 
			HttpSession session = request.getSession();
			session.setAttribute("s_member", result);
			session.setAttribute("menulist", menulist);
			
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
	}

}
