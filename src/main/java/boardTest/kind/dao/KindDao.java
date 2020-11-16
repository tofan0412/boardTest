package boardTest.kind.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import boardTest.kind.model.KindVo;

@Repository("kindDao")
public class KindDao implements KindDaoI{
	
	@Resource(name="SqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	
	@Override
	public List<KindVo> roadMenuList() {
		return sqlSession.selectList("kind.roadMenuList");
	}

	@Override
	public int changeStatus(Map<String, String> map) {
		return sqlSession.update("kind.changeStatus", map);
	}

	@Override
	public int kindRegist(KindVo kindVo) {
		return sqlSession.insert("kind.kindRegist", kindVo);
	}

	@Override
	public KindVo readKindOne(String kind_no) {
		return sqlSession.selectOne("kind.readKindOne", kind_no);
	}

}
