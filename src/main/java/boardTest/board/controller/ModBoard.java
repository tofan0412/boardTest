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

import boardTest.board.model.BoardVo;
import boardTest.board.service.BoardService;
import boardTest.board.service.BoardServiceI;
import boardTest.member.model.MemberVo;

@WebServlet("/modboard")
public class ModBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ModBoard.class);
    private BoardServiceI service;
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service = BoardService.getService();
		String board_no = request.getParameter("board_no");
		String user_id = request.getParameter("user_id");
		
		HttpSession session = request.getSession();
		MemberVo memberVo = (MemberVo)session.getAttribute("s_member");
		
		// 해당 글에 대한 권한이 없는 경우 .. (글의 작성자 id와 session에 저장된 아이디가 서로 다른 경우)
		if(!memberVo.getUser_id().equals(user_id)) {
			request.getRequestDispatcher("/alert/authorizationAlert.jsp");
		}
		
		BoardVo boardVo = service.readBoard(board_no);
		
		request.setAttribute("BoardVo", boardVo);
		request.getRequestDispatcher("/main_boardMod.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}