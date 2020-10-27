package boardTest.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

@WebServlet("/modboard2")
public class ModBoard2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ModBoard2.class);
    private BoardServiceI service;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		service = BoardService.getService();
		String board_no = request.getParameter("board_no");
		String board_title = request.getParameter("board_title");
		String board_cont = request.getParameter("board_cont");
		
		logger.debug("번호 : {} 제목 : {} 내용 : {}", board_no, board_title, board_cont);
		Map<String, String> map = new HashMap<>();
		map.put("board_no", board_no);
		map.put("board_title", board_title);
		map.put("board_cont", board_cont);
		
		int result = service.modBoard(map);
		request.setAttribute("board_no", board_no);
		if (result > 0) {
			request.getRequestDispatcher("/alert/boardModSuccess.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/alert/boardModFailure.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}