package boardTest.reply.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import boardTest.reply.model.ReplyVo;

public class ReplyDao implements ReplyDaoI{
	private static ReplyDaoI dao;
	
	private ReplyDao() {
		
	}
	
	public static ReplyDaoI getDao() {
		if (dao == null) dao = new ReplyDao();
		return dao;
	}

	@Override
	public List<ReplyVo> readReplyAll(SqlSession sqlSession, String board_no) {
		return sqlSession.selectList("reply.readReplyAll", board_no);
	}

	@Override
	public int modReply(SqlSession sqlSession, ReplyVo replyVo) {
		return sqlSession.update("reply.modReply", replyVo);
	}

	@Override
	public int delReply(SqlSession sqlSession, String reply_no) {
		return sqlSession.delete("reply.delReply", reply_no);
	}

	@Override
	public int replyRegist(SqlSession sqlSession, ReplyVo replyVo) {
		return sqlSession.insert("reply.replyRegist", replyVo);
	}
	
}
