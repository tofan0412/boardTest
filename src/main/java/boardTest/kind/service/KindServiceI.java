package boardTest.kind.service;

import java.util.List;
import java.util.Map;

import boardTest.kind.model.KindVo;

public interface KindServiceI {

	// 존재하는 메뉴를 모두 불러온다..
	public List<KindVo> roadMenuList();
	
	// 메뉴의 상태를 수정한다.
	public int changeStatus(Map<String, String> map);
	
	// 신규 게시판을 생성한다.
	public int kindRegist(KindVo kindVo);
	
	// 테스트 코드용. 하나의 게시판을 가져온다. 
	public KindVo readKindOne(String kind_no);
}
