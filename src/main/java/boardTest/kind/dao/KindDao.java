package boardTest.kind.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import boardTest.kind.model.KindVo;

public class KindDao implements KindDaoI{
	private static KindDaoI dao;
	
	private KindDao() {
		
	}
	
	public static KindDaoI getDao() {
		if (dao == null) dao = new KindDao();
		return dao;
	}
	
	@Override
	public List<KindVo> roadMenuList(SqlSession sqlSession) {
		return sqlSession.selectList("kind.roadMenuList");
	}

}
