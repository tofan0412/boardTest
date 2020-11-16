package boardTest.member.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import boardTest.member.model.MemberVo;

@Repository("memberDao")
public class MemberDao implements MemberDaoI {
	
	@Resource(name="SqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public MemberVo login(Map<String, String> map) {
		return sqlSession.selectOne("member.login", map);
	}

}
