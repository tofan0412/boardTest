package boardTest.member.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import boardTest.DB.MyBatisUtil;
import boardTest.member.dao.MemberDao;
import boardTest.member.dao.MemberDaoI;
import boardTest.member.model.MemberVo;

public class MemberService implements MemberServiceI{
	private static MemberServiceI service;
	private MemberDaoI dao;
	
	private MemberService() {
		dao = MemberDao.getDao();
	}
	
	public static MemberServiceI getService() {
		if (service == null) service = new MemberService();
		return service;
	}
	
	@Override
	public MemberVo login(Map<String, String> map) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		MemberVo result = null; 
		result = dao.login(sqlSession, map);
		sqlSession.close();
		return result;
	}

}
