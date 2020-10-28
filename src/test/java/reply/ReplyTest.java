package reply;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import boardTest.reply.dao.ReplyDao;
import boardTest.reply.dao.ReplyDaoI;
import boardTest.reply.model.ReplyVo;
import boardTest.reply.service.ReplyService;
import boardTest.reply.service.ReplyServiceI;

public class ReplyTest {
	ReplyServiceI service;
	ReplyDaoI dao;
	
	@Before
	public void setup() {
		service = ReplyService.getService();
		dao = ReplyDao.getDao();
	}
	
	@Test
	public void readReplyAlltest() {
		/***Given***/
		String board_no = "18";

		/***When***/
		List<ReplyVo> result = service.readReplyAll(board_no);
		
		/***Then***/
		assertEquals(1, result.size());
		
	}
	
	@Test
	public void modReplyTest() {
		/***Given***/
		String reply_no = "1";
		String reply_cont = "수정하였습니다.";
		ReplyVo replyVo = new ReplyVo();
		replyVo.setReply_no(reply_no);
		replyVo.setReply_cont(reply_cont);
		
		/***When***/
		int result = service.modReply(replyVo);
		
		/***Then***/
		assertEquals(1, result);
	}
	
	@Test
	public void delReplyTest() {
		/***Given***/
		String reply_no = "2";

		/***When***/
		int result = service.delReply(reply_no);
		/***Then***/
		assertEquals(1, result);
	}
	
	@Test
	public void replyRegistTest() {
		/***Given***/
		ReplyVo replyVo = new ReplyVo();
		replyVo.setUser_id("sally");
		replyVo.setBoard_no("25");
		replyVo.setReply_cont("테스트 코드용 댓글 입력..");

		/***When***/
		int result = service.replyRegist(replyVo);
		/***Then***/
		assertEquals(1, result);
		
	}

}
