package boardTest.board.controller;

import java.io.IOException;
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

@WebServlet("/readboard")
public class ReadBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ReadBoard.class);
    private BoardServiceI service;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service = BoardService.getService();
		
		String board_no = request.getParameter("board_no");
		
		BoardVo boardVo = service.readBoard(board_no);
		logger.debug("title : {} cont : {} board_no : {}", 
				boardVo.getBoard_title(), boardVo.getBoard_cont(), boardVo.getBoard_no());
		
		request.setAttribute("BoardVo", boardVo);
		
		request.getRequestDispatcher("/readBoard.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
