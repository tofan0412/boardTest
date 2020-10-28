package boardTest.reply.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import boardTest.reply.model.ReplyVo;
import boardTest.reply.service.ReplyService;
import boardTest.reply.service.ReplyServiceI;

@WebServlet("/modreply")
public class ModReply extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ModReply.class);
    ReplyServiceI service;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service = ReplyService.getService();
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String reply_no = request.getParameter("reply_no");
		String reply_cont = request.getParameter("reply_cont");
		
		logger.debug("no : {} cont : {} ",reply_no, reply_cont);
		
		ReplyVo replyVo = new ReplyVo();
		replyVo.setReply_no(reply_no);
		replyVo.setReply_cont(reply_cont);
		
		int result = service.modReply(replyVo);
		
		// 해당 게시글로 다시 리다이렉트한다.
		String board_no = request.getParameter("board_no");
		logger.debug("리다이렉트할 게시글 번호 : {} ",board_no);
		request.setAttribute("board_no", board_no);
		
		request.getRequestDispatcher("/alert/modReplySuccess.jsp").forward(request, response);
		
	}

}
