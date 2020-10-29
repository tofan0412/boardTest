package boardTest.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
import boardTest.boardfile.model.BoardfileVo;
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
		
		// 해당 게시글에 대한 첨부파일도 지워야 한다.
		
		List<BoardfileVo> delfilelist = service.filelistRead(board_no);

		// 1. 파일 삭제하기
		for (int i = 0; i < delfilelist.size(); i++) {
			String realfilename = delfilelist.get(i).getRealfilename();
			String file_path = delfilelist.get(i).getFile_path();

			logger.debug("삭제할 파일의 정보 : 1. 경로 : {}, 2. 파일이름 : {}", file_path, realfilename);
			File file = new File(file_path);
			file.delete();
		}
		
		// 하단의 메서드는 DB상에서만 지우는 것이다. 
		service.delFilelist(board_no);
		
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
