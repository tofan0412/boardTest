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

@WebServlet("/boardreply")
public class BoardReply extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(BoardReply.class);
    BoardServiceI service;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		service = BoardService.getService();
		
		HttpSession session = request.getSession();
		MemberVo memberVo = (MemberVo)session.getAttribute("s_member");
		
		String user_id = memberVo.getUser_id();
		String board_title = request.getParameter("board_title");
		String kind_no = request.getParameter("kind_no");
		String board_cont = request.getParameter("board_title");
		String board_parent_no = request.getParameter("board_no");
		
		logger.debug("받아온 Board 파라미터들!! : user_id : {} board_title : {} , kind_no : {}, board_cont : {}, board_parent_no : {}",
				user_id, board_title, kind_no, board_cont, board_parent_no);
		
		BoardVo boardVo = new BoardVo();
		
		boardVo.setUser_id(user_id);
		boardVo.setBoard_title(board_title);
		boardVo.setKind_no(kind_no);
		boardVo.setBoard_cont(board_cont);
		boardVo.setBoard_parent_no(board_parent_no);
		
		// 이때 result는 입력한 답글의 게시글 번호이다.
		int result = service.boardReplyRegist(boardVo);
		
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
