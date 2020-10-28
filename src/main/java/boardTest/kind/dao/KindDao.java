package boardTest.kind.dao;

import java.util.List;
import java.util.Map;

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

	@Override
	public int changeStatus(SqlSession sqlSession, Map<String, String> map) {
		return sqlSession.update("kind.changeStatus", map);
	}

	@Override
	public int kindRegist(SqlSession sqlSession, KindVo kindVo) {
		return sqlSession.insert("kind.kindRegist", kindVo);
	}

	@Override
	public KindVo readKindOne(SqlSession sqlSession, String kind_no) {
		return sqlSession.selectOne("kind.readKindOne", kind_no);
	}

}
