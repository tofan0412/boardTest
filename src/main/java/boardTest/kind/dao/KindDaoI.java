package boardTest.kind.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import boardTest.kind.model.KindVo;

public interface KindDaoI {
	
	// 존재하는 메뉴를 모두 불러온다..
	public List<KindVo> roadMenuList(SqlSession sqlSession);
	
	// 메뉴의 상태를 수정한다.
	public int changeStatus(SqlSession sqlSession, Map<String, String> map);
	
	// 신규 게시판을 생성한다.
	public int kindRegist(SqlSession sqlSession, KindVo kindVo);
	
	// 테스트 코드용. 하나의 게시판을 가져온다. 
	public KindVo readKindOne(SqlSession sqlSession, String kind_no);
	
}
