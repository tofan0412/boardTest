package boardTest.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import boardTest.DB.MyBatisUtil;
import boardTest.board.dao.BoardDao;
import boardTest.board.dao.BoardDaoI;
import boardTest.board.model.BoardVo;

public class BoardService implements BoardServiceI{
	private static BoardServiceI service;
	private BoardDaoI dao;
	
	private BoardService() {
		dao = BoardDao.getDao();
	}
	
	public static BoardServiceI getService() {
		if (service == null) service = new BoardService();
		return service;
	}
	
	@Override
	public List<BoardVo> boardList(String kind_no) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<BoardVo> boardlist = dao.boardList(sqlSession, kind_no);
		sqlSession.close();
		return boardlist;
	}

	@Override
	public int boardRegist(BoardVo boardVo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = dao.boardRegist(sqlSession, boardVo);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	@Override
	public BoardVo readBoard(String board_no) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		BoardVo result = dao.readBoard(sqlSession, board_no);
		sqlSession.close();
		return result;
	}

	@Override
	public int modBoard(Map<String, String> map) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = dao.modBoard(sqlSession , map);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	@Override
	public int delBoard(String board_no) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = dao.delBoard(sqlSession , board_no);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

}
