package boardTest.reply.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import boardTest.reply.service.ReplyService;
import boardTest.reply.service.ReplyServiceI;

@WebServlet("/delreply")
public class DelReply extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DelReply.class);
    ReplyServiceI service;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service = ReplyService.getService();
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String reply_no = request.getParameter("reply_no");
		
		int result = service.delReply(reply_no);
		
		String board_no = request.getParameter("board_no");
		
		request.setAttribute("board_no", board_no);
		if(result > 0) {
			request.getRequestDispatcher("/alert/delReplySuccess.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/alert/delReplyFailure.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
