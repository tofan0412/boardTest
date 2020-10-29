package boardTest.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import boardTest.board.model.BoardVo;
import boardTest.board.service.BoardService;
import boardTest.board.service.BoardServiceI;
import boardTest.boardfile.model.BoardfileVo;
import boardTest.member.model.MemberVo;
import boardTest.util.FileUploadUtil;

@WebServlet("/boardregist")
@MultipartConfig
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
		String kind_no = request.getParameter("kind_no");
		String board_title = request.getParameter("board_title");
		String board_cont = request.getParameter("board_cont");
		
		logger.debug("받아온 Board 파라미터들!! : user_id : {} board_title : {} , kind_no : {}, board_cont : {}",
						user_id, board_title, kind_no, board_cont);
		
		BoardVo boardVo = new BoardVo();
		
		boardVo.setUser_id(user_id);
		boardVo.setBoard_title(board_title);
		boardVo.setKind_no(kind_no);
		boardVo.setBoard_cont(board_cont);
		
		int result = service.boardRegist(boardVo);
		// selectKey 옵션을 사용했기 때문에, boardVo의 board_no에 시퀀스 값이 저장되어 온다..
		
		// 파일 업로드 처리하기
		Part imgPart1 = request.getPart("img1");
		Part imgPart2 = request.getPart("img2");
		Part imgPart3 = request.getPart("img3");
		Part imgPart4 = request.getPart("img4");
		Part imgPart5 = request.getPart("img5");
		
		logger.debug("Content-Disposition : {}",imgPart1.getHeader("Content-Disposition"));
		logger.debug("Content-Disposition : {}",imgPart2.getHeader("Content-Disposition"));
		logger.debug("Content-Disposition : {}",imgPart3.getHeader("Content-Disposition"));
		logger.debug("Content-Disposition : {}",imgPart4.getHeader("Content-Disposition"));
		logger.debug("Content-Disposition : {}",imgPart5.getHeader("Content-Disposition"));
		
		// Part 객체를 담는 리스트
		List<Part> filelist = new ArrayList<>();
		
		// FileVo를 담는 리스트
		List<BoardfileVo> realFilelist = new ArrayList<>();
		
		filelist.add(imgPart1);
		filelist.add(imgPart2);
		filelist.add(imgPart3);
		filelist.add(imgPart4);
		filelist.add(imgPart5);
		
		// 사용자가 업로드한 파일의 실제 이름 가져오기..
		for(int i = 0 ; i < filelist.size() ; i++) {
			String realfilename = FileUploadUtil.
					getFilename(filelist.get(i).getHeader("Content-Disposition"));
			logger.debug("실제 파일 이름은? {} ", realfilename);
			
			// 사용자가 해당 파일을 업로드 한 경우( 즉, 파일 이름이 none이 아닌 다른 경우)
			if (!realfilename.equals("")) {
				logger.debug("경로를 설정할 파일 : {} ", realfilename);
				
				String file_path = "d:\\upload\\"+ realfilename;

				BoardfileVo boardfileVo = new BoardfileVo();
				
				boardfileVo.setBoard_no(Integer.toString(result));
				boardfileVo.setRealfilename(realfilename);
				boardfileVo.setFile_path(file_path);
				
				// DB에 담을 객체 선발 ..
				realFilelist.add(boardfileVo);
				// 경로에 업로드 
				filelist.get(i).write("d:\\upload\\"+ realfilename);
				filelist.get(i).delete();
			}
		}
		
		int tot_cnt = 0;
		
		// 사용자가 등록한 파일들에 대해서, DB상에 객체를 생성한다.
		for(int i = 0 ; i < realFilelist.size() ; i++) {
			service.boardfileRegist(realFilelist.get(i));
			tot_cnt++;
		}
		// 이 때 realFilelist의 크기와 tot_cnt의 크기가 서로 같아야 한다. 
		logger.debug("tot_cnt의 크기 : {} , 업로드한 파일의 수 : {}", tot_cnt, realFilelist.size());
		
		boolean uploadresult = false;
		if (tot_cnt == realFilelist.size()) {
			uploadresult = true;
		}
		
		if(result > 0 && uploadresult) {
			request.setAttribute("kind_no", kind_no);
			request.getRequestDispatcher("alert/boardRegistSuccess.jsp").forward(request, response);
		
		// 게시글 작성에 실패한 경우..
		}else {
			request.setAttribute("kind_no", kind_no);
			doGet(request, response);
		}
	}
}
