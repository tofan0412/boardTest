package reply;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import boardTest.reply.dao.ReplyDaoI;
import boardTest.reply.model.ReplyVo;
import boardTest.reply.service.ReplyServiceI;
import test.config.ModelTestConfig;

public class ReplyTest extends ModelTestConfig{
	@Resource(name="replyService")
	ReplyServiceI service;
	@Resource(name="replyDao")
	ReplyDaoI dao;
	@Resource(name="ReplyVo")
	ReplyVo replyVo;
	
	@Before
	public void setup() {
	}
	
	@Test
	public void readReplyAlltest() {
		/***Given***/
		String board_no = "41";

		/***When***/
		List<ReplyVo> result = service.readReplyAll(board_no);
		
		/***Then***/
		assertEquals(2, result.size());
		
	}
	
	@Test
	public void modReplyTest() {
		/***Given***/
		String reply_no = "8";
		String reply_cont = "테스트 코드상에서 수정하였습니다.";
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
		String reply_no = "5";

		/***When***/
		int result = service.delReply(reply_no);
		/***Then***/
		assertEquals(1, result);
	}
	
	@Test
	public void replyRegistTest() {
		/***Given***/
		replyVo.setUser_id("sally");
		replyVo.setBoard_no("25");
		replyVo.setReply_cont("테스트 코드용 댓글 입력..");

		/***When***/
		int result = service.replyRegist(replyVo);
		/***Then***/
		assertEquals(1, result);
	}
}
