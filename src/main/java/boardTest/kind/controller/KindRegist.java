package boardTest.kind.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boardTest.kind.model.KindVo;
import boardTest.kind.service.KindService;
import boardTest.kind.service.KindServiceI;

@WebServlet("/kindregist")
public class KindRegist extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private KindServiceI service;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service = KindService.getService();
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String kind_name = request.getParameter("kind_name");
		String kind_valid = request.getParameter("kind_valid");
		KindVo kindVo = new KindVo();
		kindVo.setKind_name(kind_name);
		kindVo.setKind_valid(kind_valid);
		
		int result = service.kindRegist(kindVo);
		
		// 신규 게시판을 등록한 경우, session 객체에 이를 다시 갱신해야 한다. 
		if (result > 0) {
			HttpSession session = request.getSession();
			List<KindVo> menulist = service.roadMenuList();
			session.setAttribute("menulist", menulist);
			
			request.getRequestDispatcher("/alert/kindRegistSuccess.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/alert/kindRegistFailure.jsp").forward(request, response);
		}
	}

}
