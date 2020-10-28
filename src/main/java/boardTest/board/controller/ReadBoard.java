package boardTest.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import boardTest.board.model.BoardVo;
import boardTest.board.service.BoardService;
import boardTest.board.service.BoardServiceI;
import boardTest.boardfile.model.BoardfileVo;
import boardTest.reply.model.ReplyVo;
import boardTest.reply.service.ReplyService;
import boardTest.reply.service.ReplyServiceI;

@WebServlet("/readboard")
public class ReadBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ReadBoard.class);
    private BoardServiceI service;
    private ReplyServiceI service_r;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service = BoardService.getService();
		
		String board_no = request.getParameter("board_no");
		
		BoardVo boardVo = service.readBoard(board_no);
		logger.debug("title : {} cont : {} board_no : {}", 
				boardVo.getBoard_title(), boardVo.getBoard_cont(), boardVo.getBoard_no());
		
		// 해당 글에 달린 댓글을 로드한다. 
		service_r = ReplyService.getService();
		List<ReplyVo> replylist = service_r.readReplyAll(board_no);

		// 첨부파일 리스트를 로드한다. 
		List<BoardfileVo> filelist = service.filelistRead(board_no);

		request.setAttribute("filelist", filelist);		// 해당 글에 대한 파일 리스트
		request.setAttribute("BoardVo", boardVo);		// 해당 글 정보
		request.setAttribute("replylist", replylist);	// 해당 글에 대한 댓글 리스트
		
		request.getRequestDispatcher("/readBoard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
