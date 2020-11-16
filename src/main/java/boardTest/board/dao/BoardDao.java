
package boardTest.board.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import boardTest.board.model.BoardVo;
import boardTest.boardfile.model.BoardfileVo;

@Repository("boardDao")
public class BoardDao implements BoardDaoI {

	@Resource(name="SqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<BoardVo> boardList(String kind_no) {
		return sqlSession.selectList("board.boardlist", kind_no);
	}
	
	@Override
	public List<BoardVo> boardListPage(Map<String, String> map) {
		return sqlSession.selectList("board.boardListPage", map);
	}

	@Override
	public int boardRegist(BoardVo boardVo) {
		return sqlSession.insert("board.boardRegist", boardVo);   
	}

	@Override
	public BoardVo readBoard(String board_no) {
		return sqlSession.selectOne("board.readBoard", board_no);
	}

	@Override
	public int modBoard(Map<String, String> map) {
		return sqlSession.update("board.modBoard", map);
	}

	@Override
	public int delBoard(String board_no) {
		return sqlSession.update("board.delBoard", board_no);
	}

	@Override
	public int boardfileRegist(BoardfileVo fileVo) {
		return sqlSession.insert("board.boardfileRegist", fileVo);
	}

	@Override
	public List<BoardfileVo> filelistRead(String board_no) {
		return sqlSession.selectList("board.filelistRead", board_no);
	}

	@Override
	public int boardReplyRegist(BoardVo boardVo) {
		return sqlSession.insert("board.boardReplyRegist", boardVo);
	}

	@Override
	public int delFilelist(String board_no) {
		return sqlSession.delete("board.delFilelist", board_no);
	}

	@Override
	public int delFileOne(String file_no) {
		return sqlSession.delete("board.delFileOne", file_no);
	}

	@Override
	public BoardfileVo readFileOne(String file_no) {
		return sqlSession.selectOne("board.readFileOne", file_no);
	}

}
