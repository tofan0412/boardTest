package boardTest.kind.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import boardTest.kind.model.KindVo;
import boardTest.kind.service.KindService;
import boardTest.kind.service.KindServiceI;

@WebServlet("/managekind")
public class ManageKindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ManageKindServlet.class);
    KindServiceI service;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service = KindService.getService();
		
		List<KindVo> menulist = service.roadMenuList();
		request.setAttribute("menulist", menulist);
		
		request.getRequestDispatcher("/modKind.jsp").forward(request, response);
	}
}
