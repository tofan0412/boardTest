package boardTest.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@WebServlet("/boardlist")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(BoardListServlet.class);
    private BoardServiceI service;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String kind_no = request.getParameter("kind_no");
		String kind_name = request.getParameter("kind_name");
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		service = BoardService.getService();
		
		// 페이지 번호와, 페이지 크기를 통해 특정 페이지만을 불러온다. 
		String page_req = request.getParameter("page");
		String pageSize_req = request.getParameter("pageSize");
		
		// 사용자가 별도로 요청한 page가 있으면 해당 값으로, 없으면 기본적으로 1페이지를 불러온다.
		String page = page_req == null?Integer.toString(1):page_req;
		// 사용자가 요청한 pageSize가 있으면 해당 값으로, 없으면 기본적으로 7개의 값을 불러온다.
		String pageSize = pageSize_req == null?Integer.toString(7):pageSize_req;
		
		Map<String, String> map = new HashMap<>();
		map.put("kind_no", kind_no);
		map.put("page", page);
		map.put("pageSize", pageSize);
		
		List<BoardVo> boardlist = service.boardListPage(map);
		
		for(BoardVo single : boardlist) {
			logger.debug(single.getBoard_title());
		}
		
		// 게시글의 수를 받고, 필요한 페이지의 개수를 반환해야 한다. 
		List<BoardVo> totcnt = service.boardList(kind_no);
		
		// LEVEL에 맞게 TITLE앞에 공백 붙여주기
		for (int i = 0 ; i < boardlist.size() ; i++) {
			String board_title = boardlist.get(i).getBoard_title();
			int level = Integer.parseInt(boardlist.get(i).getBoard_level()) - 1;
			String str = ">";
			if (level == 0) continue;
			else {
				for (int j = 0 ; j < level ; j++) {
					str += str;
				}
				boardlist.get(i).setBoard_title(str+board_title);
			}
		}
		
		int tot_cnt = totcnt.size();
		
		int page_cnt = (int)Math.ceil(((double)tot_cnt / Integer.parseInt(pageSize)));
		int page_int = Integer.parseInt(page);
		int pageSize_int = Integer.parseInt(pageSize);
		
		request.setAttribute("kind_no", kind_no);
		request.setAttribute("page_cnt", page_cnt);
		request.setAttribute("page", page_int);
		request.setAttribute("page-1", page_int-1);
		request.setAttribute("page+1", page_int+1);
		request.setAttribute("pageSize", pageSize_int);
		request.setAttribute("boardlist", boardlist);
		request.setAttribute("kind_name", kind_name);
		
		request.getRequestDispatcher("/boardlist.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
