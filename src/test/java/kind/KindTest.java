package kind;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import boardTest.kind.dao.KindDao;
import boardTest.kind.dao.KindDaoI;
import boardTest.kind.model.KindVo;
import boardTest.kind.service.KindService;
import boardTest.kind.service.KindServiceI;
import junit.extensions.TestSetup;

public class KindTest {
	KindServiceI service;
	KindDaoI dao;
	
	@Before
	public void Setup() {
		service = KindService.getService();
		dao = KindDao.getDao();
	}
	
	@Test
	public void loadMenuListServiceTest() {
		/***Given***/

		/***When***/
		List<KindVo> menu = service.roadMenuList();
		/***Then***/
		assertEquals(4, menu.size());
	}
	
	@Test
	public void changeStatusTest() {
		/***Given***/
		String kind_no = "1";
		String kind_name = "자유게시판_수정";
		String kind_valid = "0";

		Map<String, String> map = new HashMap<>();
		map.put("kind_no", kind_no);
		map.put("kind_name", kind_name);
		map.put("kind_valid", kind_valid);
		
		/***When***/
		int result = service.changeStatus(map);
		
		/***Then***/
		assertEquals(1, result);
	}
	
	@Test
	public void kindRegistTest() {
		/***Given***/
		String kind_name = "테스트 코드";
		String kind_valid = "1";

		KindVo kindVo = new KindVo();
		kindVo.setKind_name(kind_name);
		kindVo.setKind_valid(kind_valid);
		/***When***/
		int result = service.kindRegist(kindVo);
		/***Then***/
		assertEquals(1, result);
	}
}
