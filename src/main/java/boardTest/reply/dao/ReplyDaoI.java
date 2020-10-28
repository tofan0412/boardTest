package boardTest.reply.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import boardTest.reply.model.ReplyVo;

public interface ReplyDaoI {
	
	// 게시판 번호에 해당하는 모든 댓글을 불러온다. 
	public List<ReplyVo> readReplyAll(SqlSession sqlSession, String board_no);
	
	// 댓글을 수정한다.
	public int modReply(SqlSession sqlSession, ReplyVo replyVo);
	
	// 댓글을 삭제한다. 
	public int delReply(SqlSession sqlSession, String reply_no);
	
	// 댓글을 입력한다.
	public int replyRegist(SqlSession sqlSession, ReplyVo replyVo);
}
