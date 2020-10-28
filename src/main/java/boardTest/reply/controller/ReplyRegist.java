package boardTest.reply.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import boardTest.member.model.MemberVo;
import boardTest.reply.model.ReplyVo;
import boardTest.reply.service.ReplyService;
import boardTest.reply.service.ReplyServiceI;

@WebServlet("/replyregist")
public class ReplyRegist extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ReplyRegist.class);
    ReplyServiceI service;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		service = ReplyService.getService();
		
		String board_no = request.getParameter("board_no");
		String reply_cont = request.getParameter("reply_cont");
		
		HttpSession session = request.getSession();
		MemberVo memberVo = (MemberVo)session.getAttribute("s_member");
		String user_id = memberVo.getUser_id();
		
		ReplyVo replyVo = new ReplyVo();
		replyVo.setUser_id(user_id);
		replyVo.setBoard_no(board_no);
		replyVo.setReply_cont(reply_cont);
		
		int result = service.replyRegist(replyVo);
		
		request.setAttribute("board_no", board_no);
		if (result > 0) {
			request.getRequestDispatcher("/readboard").forward(request, response);
		}
		
	}

}
