package kind;

import static org.junit.Assert.*;

import java.util.List;

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

}
