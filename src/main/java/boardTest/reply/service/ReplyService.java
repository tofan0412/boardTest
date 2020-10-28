package boardTest.reply.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import boardTest.DB.MyBatisUtil;
import boardTest.reply.dao.ReplyDao;
import boardTest.reply.dao.ReplyDaoI;
import boardTest.reply.model.ReplyVo;

public class ReplyService implements ReplyServiceI{
	private static ReplyServiceI service;
	private ReplyDaoI dao;

	private ReplyService() {
		dao = ReplyDao.getDao();
	}
	
	public static ReplyServiceI getService() {
		if (service == null) service = new ReplyService();
		return service;
	}
	
	@Override
	public List<ReplyVo> readReplyAll(String board_no) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<ReplyVo> result = dao.readReplyAll(sqlSession, board_no);
		sqlSession.close();
		return result;
	}

	@Override
	public int modReply(ReplyVo replyVo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = dao.modReply(sqlSession, replyVo);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	@Override
	public int delReply(String reply_no) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = dao.delReply(sqlSession, reply_no);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	@Override
	public int replyRegist(ReplyVo replyVo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = dao.replyRegist(sqlSession, replyVo);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	
	
}
