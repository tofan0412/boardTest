package controller;


import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import boardTest.board.service.BoardServiceI;
import boardTest.boardfile.service.FileServiceI;
import boardTest.kind.model.KindVo;
import boardTest.kind.service.KindServiceI;
import boardTest.member.service.MemberServiceI;
import boardTest.reply.service.ReplyServiceI;
import test.config.ModelTestConfig;

public class ControllerTest extends ModelTestConfig {
	@Resource(name = "boardService")
	private BoardServiceI boardService;

	@Resource(name = "fileService")
	private FileServiceI fileService;

	@Resource(name = "kindService")
	private KindServiceI kindService;

	@Resource(name = "memberService")
	private MemberServiceI memberService;

	@Resource(name = "replyService")
	private ReplyServiceI replyService;

	@Resource(name = "KindVo")
	private KindVo kindVo;
	
	@Before
	public void setup() {
		super.setup();
	}
	
	@Test
	public void loginTest() {
		/***Given***/
		

		/***When***/

		/***Then***/
	}
	
	
	
	
	

}
