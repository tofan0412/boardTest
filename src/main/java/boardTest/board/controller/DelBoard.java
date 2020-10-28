package boardTest.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import boardTest.board.service.BoardService;
import boardTest.board.service.BoardServiceI;
import boardTest.member.model.MemberVo;

@WebServlet("/delboard")
public class DelBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DelBoard.class);
    BoardServiceI service;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service = BoardService.getService();
		
		String board_no = request.getParameter("board_no");
		String user_id = request.getParameter("user_id");
		
		HttpSession session = request.getSession();
		MemberVo memberVo = (MemberVo)session.getAttribute("s_member");
		
		int result = service.delBoard(board_no);
		
		String kind_no = request.getParameter("kind_no");
		request.setAttribute("board_no", board_no);
		request.setAttribute("kind_no", kind_no);
		
		if (result > 0) {
			request.getRequestDispatcher("/alert/boardDelSuccess.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/alert/boardDelFailure.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
