package boardTest.reply.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import boardTest.reply.dao.ReplyDaoI;
import boardTest.reply.model.ReplyVo;

@Service("replyService")
public class ReplyService implements ReplyServiceI{
	
	@Resource(name="replyDao")
	private ReplyDaoI dao;
	
	@Override
	public List<ReplyVo> readReplyAll(String board_no) {
		return dao.readReplyAll(board_no);
	}

	@Override
	public int modReply(ReplyVo replyVo) {
		return dao.modReply(replyVo);
	}

	@Override
	public int delReply(String reply_no) {
		return dao.delReply(reply_no);
	}

	@Override
	public int replyRegist(ReplyVo replyVo) {
		return dao.replyRegist(replyVo);
	}

	
	
}
