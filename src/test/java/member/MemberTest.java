package member;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import boardTest.member.dao.MemberDao;
import boardTest.member.dao.MemberDaoI;
import boardTest.member.model.MemberVo;
import boardTest.member.service.MemberService;
import boardTest.member.service.MemberServiceI;

public class MemberTest {
	MemberServiceI service;
	MemberDaoI dao;
	
	@Before
	public void setup() {
		service = MemberService.getService();
		dao = MemberDao.getDao();
	}
	
	@Test
	public void loginServiceTest() {
		/***Given***/
		String user_id = "brown";
		String user_pass = "brownPass";
		Map<String, String> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("user_pass", user_pass);
		/***When***/
		MemberVo memberVo = service.login(map);
		
		/***Then***/
		assertEquals("brown", memberVo.getUser_id());
		assertEquals("brownPass", memberVo.getUser_pass());
		
	}
	
	

}
