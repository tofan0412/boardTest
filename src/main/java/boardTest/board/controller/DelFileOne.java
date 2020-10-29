package boardTest.board.controller;

import java.io.File;
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
import boardTest.boardfile.service.FileService;
import boardTest.boardfile.service.FileServiceI;

@WebServlet("/delfileone")
public class DelFileOne extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DelFileOne.class);   
	BoardServiceI service;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service = BoardService.getService();
		
		String file_no = request.getParameter("file_no");
		String board_no = request.getParameter("board_no");
		String board_title = request.getParameter("board_title");
		String board_cont = request.getParameter("board_cont");
		
		boolean file_del = false;
		boolean file_db_del = false;
		
		// 실제 경로에 있는 해당 파일 삭제하기..
		BoardfileVo fileVo = service.readFileOne(file_no);
		String realfilename = fileVo.getRealfilename();
		String file_path = fileVo.getFile_path();
		logger.debug("삭제할 파일의 정보 : 1. 경로 : {}, 2. 파일이름 : {}", file_path, realfilename);
        File file = new File(file_path);
		file.delete();
		file_del = true;
		
		// 해당 파일 DB에서 삭제하기..
		int result = service.delFileOne(file_no);
		if (result > 0) {
			file_db_del = true;
		}
		if (file_del & file_db_del) {
			BoardVo boardVo = new BoardVo();
			boardVo.setBoard_no(board_no);
			boardVo.setBoard_title(board_title);
			boardVo.setBoard_cont(board_cont);
			
			// 다시 해당 게시글에 대한 파일리스트를 불러와야 한다.
			List<BoardfileVo> filelist = service.filelistRead(board_no);
			
			request.setAttribute("filelist", filelist);
			request.setAttribute("BoardVo", boardVo);
			request.getRequestDispatcher("/main_boardMod.jsp").forward(request, response);
		
		// 파일의 물리적 실패, DB상에서의 실패 둘 중 하나라도 실패한 경우
		}else {
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
