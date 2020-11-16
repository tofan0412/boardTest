package boardTest.member.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import boardTest.member.dao.MemberDaoI;
import boardTest.member.model.MemberVo;

@Service("memberService")
public class MemberService implements MemberServiceI{
	@Resource(name="memberDao")
	private MemberDaoI dao;
	
	@Override
	public MemberVo login(Map<String, String> map) {
		return dao.login(map);
	}

}
