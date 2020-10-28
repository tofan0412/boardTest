package boardTest.kind.controller;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import boardTest.kind.model.KindVo;
import boardTest.kind.service.KindService;
import boardTest.kind.service.KindServiceI;

@WebServlet("/modkind")
public class modKind extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(modKind.class);
    KindServiceI service;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service = KindService.getService();
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String kind_no = request.getParameter("kind_no");
		String kind_name = request.getParameter("kind_name");
		String kind_valid = request.getParameter("kind_valid");
		
		Map<String, String> map = new HashMap<>();
		
		map.put("kind_no", kind_no);
		map.put("kind_name", kind_name);
		map.put("kind_valid", kind_valid);
		
		int result = service.changeStatus(map);
		
		// 세션을 수정해야 한다. 
		HttpSession session = request.getSession();
		List<KindVo> menulist = service.roadMenuList();
		session.setAttribute("menulist", menulist);

		if (result > 0 ) {
			request.getRequestDispatcher("/alert/changeStatusSuccess.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/alert/changeStatusFailure.jsp").forward(request, response);
		}
	}
}
