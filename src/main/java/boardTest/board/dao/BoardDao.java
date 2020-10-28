package boardTest.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import boardTest.board.model.BoardVo;
import boardTest.boardfile.model.BoardfileVo;

public class BoardDao implements BoardDaoI {
	private static BoardDaoI dao;
	
	private BoardDao() {
		
	}
	
	public static BoardDaoI getDao() {
		if (dao == null) dao = new BoardDao();
		return dao;
	}
	
	@Override
	public List<BoardVo> boardList(SqlSession sqlSession, String kind_no) {
		return sqlSession.selectList("board.boardlist", kind_no);
	}

	@Override
	public int boardRegist(SqlSession sqlSession, BoardVo boardVo) {
		return sqlSession.insert("board.boardRegist", boardVo);   
	}

	@Override
	public BoardVo readBoard(SqlSession sqlSession, String board_no) {
		return sqlSession.selectOne("board.readBoard", board_no);
	}

	@Override
	public int modBoard(SqlSession sqlSession, Map<String, String> map) {
		return sqlSession.update("board.modBoard", map);
	}

	@Override
	public int delBoard(SqlSession sqlSession, String board_no) {
		return sqlSession.update("board.delBoard", board_no);
	}

	@Override
	public int boardfileRegist(SqlSession sqlSession, BoardfileVo fileVo) {
		return sqlSession.insert("board.boardfileRegist", fileVo);
	}

	@Override
	public List<BoardfileVo> filelistRead(SqlSession sqlSession, String board_no) {
		return sqlSession.selectList("board.filelistRead", board_no);
	}

	@Override
	public int boardReplyRegist(SqlSession sqlSession, BoardVo boardVo) {
		return sqlSession.insert("board.boardReplyRegist", boardVo);
	}

}
