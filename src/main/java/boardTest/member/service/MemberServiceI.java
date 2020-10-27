package boardTest.member.service;

import java.util.Map;

import boardTest.member.model.MemberVo;

public interface MemberServiceI {
	
	// 로그인 메서드
	public MemberVo login(Map<String, String> map);
}
