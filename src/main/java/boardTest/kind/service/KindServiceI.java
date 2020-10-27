package boardTest.kind.service;

import java.util.List;

import boardTest.kind.model.KindVo;

public interface KindServiceI {

	// 존재하는 메뉴를 모두 불러온다..
	public List<KindVo> roadMenuList();
	
}
