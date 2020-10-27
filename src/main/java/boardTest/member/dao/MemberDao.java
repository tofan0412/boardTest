package boardTest.member.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import boardTest.member.model.MemberVo;

public class MemberDao implements MemberDaoI {
	private static MemberDaoI dao;
	
	private MemberDao() {
		
	}
	
	public static MemberDaoI getDao() {
		if (dao == null) dao = new MemberDao();
		return dao;
	}
	
	@Override
	public MemberVo login(SqlSession sqlSession, Map<String, String> map) {
		return sqlSession.selectOne("member.login", map);
	}

}
