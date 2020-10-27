package boardTest.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import boardTest.board.model.BoardVo;

public interface BoardDaoI {
	// 특정 종류의 게시글을 모두 불러온다.
	public List<BoardVo> boardList(SqlSession sqlSession, String kind_no);
	
	// 작성한 게시글 등록하기
	public int boardRegist(SqlSession sqlSession , BoardVo boardVo);
	
	// 하나의 게시글 불러오기
	public BoardVo readBoard(SqlSession sqlSession  , String board_no);
	
	// 게시글 제목, 내용 수정하기
	public int modBoard(SqlSession sqlSession, Map<String, String> map);
	
	// 게시글 삭제하기(속성값 변경)
	public int delBoard(SqlSession sqlSession , String board_no);
}
