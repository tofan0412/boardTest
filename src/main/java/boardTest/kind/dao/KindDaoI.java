package boardTest.kind.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import boardTest.kind.model.KindVo;

public interface KindDaoI {
	
	// 존재하는 메뉴를 모두 불러온다..
	public List<KindVo> roadMenuList(SqlSession sqlSession);
	
}
