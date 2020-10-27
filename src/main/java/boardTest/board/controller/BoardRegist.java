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

@WebServlet("/boardregist")
public class BoardRegist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(BoardRegist.class);
    BoardServiceI service;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("alert/boardRegistFailure.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		service = BoardService.getService();
		
		HttpSession session = request.getSession();
		MemberVo memberVo = (MemberVo)session.getAttribute("s_member");

		String user_id = memberVo.getUser_id();
		String board_title = request.getParameter("board_title");
		String kind_no = request.getParameter("kind_no");
		String board_cont = request.getParameter("board_title");
		
		logger.debug("받아온 Board 파라미터들!! : user_id : {} board_title : {} , kind_no : {}, board_cont : {}",
						user_id, board_title, kind_no, board_cont);
		
		BoardVo boardVo = new BoardVo();
		
		boardVo.setUser_id(user_id);
		boardVo.setBoard_title(board_title);
		boardVo.setKind_no(kind_no);
		boardVo.setBoard_cont(board_cont);
		
		int result = service.boardRegist(boardVo);
		
		if(result > 0) {
			request.setAttribute("kind_no", kind_no);
			request.getRequestDispatcher("alert/boardRegistSuccess.jsp").forward(request, response);
		// 게시글 작성에 실패한 경우..
		}else {
			request.setAttribute("kind_no", kind_no);
			doGet(request, response);
		}
	}

}
