package member;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import boardTest.member.dao.MemberDaoI;
import boardTest.member.model.MemberVo;
import boardTest.member.service.MemberServiceI;
import test.config.ModelTestConfig;

public class MemberTest extends ModelTestConfig{
	@Resource(name="memberService")
	MemberServiceI service;
	@Resource(name="memberDao")
	MemberDaoI dao;
	
	@Before
	public void setup() {
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
