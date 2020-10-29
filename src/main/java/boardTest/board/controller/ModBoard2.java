package boardTest.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import boardTest.board.service.BoardService;
import boardTest.board.service.BoardServiceI;
import boardTest.boardfile.model.BoardfileVo;
import boardTest.util.FileUploadUtil;

@WebServlet("/modboard2")
@MultipartConfig
public class ModBoard2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ModBoard2.class);
	private BoardServiceI service;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		///////////////////////// 파일 업로드 부분 /////////////////////////////////////////
		
		// 파일 업로드 처리하기
		Part imgPart1 = request.getPart("img1");
		Part imgPart2 = request.getPart("img2");
		Part imgPart3 = request.getPart("img3");
		Part imgPart4 = request.getPart("img4");
		Part imgPart5 = request.getPart("img5");

		// Part 객체를 담는 리스트
		List<Part> filelist = new ArrayList<>();

		// FileVo를 담는 리스트
		List<BoardfileVo> realFilelist = new ArrayList<>();
		filelist.add(imgPart1);
		filelist.add(imgPart2);
		filelist.add(imgPart3);
		filelist.add(imgPart4);
		filelist.add(imgPart5);
		
		// 한 게시글에서 첨부할 수 있는 최대 개수는 5개이다. 따라서 이를 검사해야 한다. 
		List<BoardfileVo> existfilelist = service.filelistRead(board_no);
		
		int upCnt = 0;
		for(int test = 0 ; test  < filelist.size() ; test++) {
			String realfilename = FileUploadUtil.getFilename(filelist.get(test).getHeader("Content-Disposition"));
			if (!realfilename.equals("")) {
				upCnt++;
			}
		}
		
		if(upCnt + existfilelist.size() > 5) {
			request.setAttribute("board_no", board_no);
			request.getRequestDispatcher("/alert/cannotUpload.jsp").forward(request, response);
			return; // 서블릿 끝내기
		}
		
		
		// 사용자가 업로드한 파일의 실제 이름 가져오기..
		for (int i = 0; i < filelist.size(); i++) {
			String realfilename = FileUploadUtil.getFilename(filelist.get(i).getHeader("Content-Disposition"));
			logger.debug("실제 파일 이름은? {} ", realfilename);
			
			// 사용자가 해당 파일을 업로드 한 경우( 즉, 파일 이름이 none이 아닌 다른 경우)
			if (!realfilename.equals("")) {
				logger.debug("경로를 설정할 파일 : {} ", realfilename);

				String file_path = "d:\\upload\\" + realfilename;

				BoardfileVo boardfileVo = new BoardfileVo();

				boardfileVo.setBoard_no(board_no);
				boardfileVo.setRealfilename(realfilename);
				boardfileVo.setFile_path(file_path);

				// DB에 담을 객체 선발 ..
				realFilelist.add(boardfileVo);
				// 경로에 업로드
				filelist.get(i).write("d:\\upload\\" + realfilename);
				filelist.get(i).delete();
			}
		}

		int tot_cnt = 0;

		// 사용자가 등록한 파일들에 대해서, DB상에 객체를 생성한다.
		for (int i = 0; i < realFilelist.size(); i++) {
			service.boardfileRegist(realFilelist.get(i));
			tot_cnt++;
		}
		// 이 때 realFilelist의 크기와 tot_cnt의 크기가 서로 같아야 한다.
		logger.debug("tot_cnt의 크기 : {} , 업로드한 파일의 수 : {}", tot_cnt, realFilelist.size());

		boolean uploadresult = false;
		if (tot_cnt == realFilelist.size()) {
			uploadresult = true;
		}

		request.setAttribute("board_no", board_no);
		if (result > 0 && uploadresult) {
			request.getRequestDispatcher("/alert/boardModSuccess.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/alert/boardModFailure.jsp").forward(request, response);
		}
	}

}