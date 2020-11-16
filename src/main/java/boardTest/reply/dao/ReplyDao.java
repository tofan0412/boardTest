package boardTest.reply.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import boardTest.reply.model.ReplyVo;

@Repository("replyDao")
public class ReplyDao implements ReplyDaoI{
	
	@Resource(name="SqlSessionTemplate")
	private SqlSessionTemplate sqlSession;

	@Override
	public List<ReplyVo> readReplyAll(String board_no) {
		return sqlSession.selectList("reply.readReplyAll", board_no);
	}

	@Override
	public int modReply(ReplyVo replyVo) {
		return sqlSession.update("reply.modReply", replyVo);
	}

	@Override
	public int delReply(String reply_no) {
		return sqlSession.delete("reply.delReply", reply_no);
	}

	@Override
	public int replyRegist(ReplyVo replyVo) {
		return sqlSession.insert("reply.replyRegist", replyVo);
	}
}
