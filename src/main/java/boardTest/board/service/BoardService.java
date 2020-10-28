package boardTest.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import boardTest.DB.MyBatisUtil;
import boardTest.board.dao.BoardDao;
import boardTest.board.dao.BoardDaoI;
import boardTest.board.model.BoardVo;
import boardTest.boardfile.model.BoardfileVo;

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
		// 이 때 result는 개수를 불러온다.
		if (result > 0) {
			sqlSession.commit();
		}
		sqlSession.close();
		// 주의해야 할 점은, xml 쿼리에서 selectKey를 적용하였기 때문에 들어간 파라미터의
		// board_no에 시퀀스 값이 저장된다는 것이다.
		return Integer.parseInt(boardVo.getBoard_no());
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
		if (result > 0) {
			sqlSession.commit();
		}
		sqlSession.close();
		return result;
	}

	@Override
	public int delBoard(String board_no) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = dao.delBoard(sqlSession , board_no);
		if (result > 0) {
			sqlSession.commit();
		}
		sqlSession.close();
		return result;
	}

	@Override
	public int boardfileRegist(BoardfileVo fileVo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int result = dao.boardfileRegist(sqlSession, fileVo);
		if (result > 0) {
			sqlSession.commit();
		}
		sqlSession.close();
		return 0;
	}

	@Override
	public List<BoardfileVo> filelistRead(String board_no) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<BoardfileVo> filelist = dao.filelistRead(sqlSession, board_no);
		sqlSession.close();
		return filelist;
	}

}
