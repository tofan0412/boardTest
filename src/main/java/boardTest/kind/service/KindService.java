package boardTest.kind.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import boardTest.DB.MyBatisUtil;
import boardTest.kind.dao.KindDao;
import boardTest.kind.dao.KindDaoI;
import boardTest.kind.model.KindVo;

public class KindService implements KindServiceI{
	private static KindServiceI service;
	private KindDaoI dao;
	
	private KindService() {
		dao = KindDao.getDao();
	}
	
	public static KindServiceI getService() {
		if (service == null) {
			service = new KindService();
		}
		return service;
	}
	
	
	@Override
	public List<KindVo> roadMenuList() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<KindVo> menulist = dao.roadMenuList(sqlSession);
		sqlSession.close();
		return menulist;
	}

}
