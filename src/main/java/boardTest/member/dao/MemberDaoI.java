package boardTest.member.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import boardTest.member.model.MemberVo;

public interface MemberDaoI {
	// 로그인 메서드
	public MemberVo login(SqlSession sqlSession , Map<String, String> map);
}
