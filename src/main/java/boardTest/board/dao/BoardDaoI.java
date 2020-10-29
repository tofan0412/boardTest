package boardTest.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import boardTest.board.model.BoardVo;
import boardTest.boardfile.model.BoardfileVo;

public interface BoardDaoI {
	// 특정 종류의 게시글을 모두 불러온다.
	public List<BoardVo> boardList(SqlSession sqlSession, String kind_no);
	
	// 페이징을 이용하여 글 목록 불러오기
	public List<BoardVo> boardListPage(SqlSession sqlSession , Map<String, String> map);
	
	// 작성한 게시글 등록하기
	public int boardRegist(SqlSession sqlSession , BoardVo boardVo);
	
	// 하나의 게시글 불러오기
	public BoardVo readBoard(SqlSession sqlSession  , String board_no);
	
	// 게시글 제목, 내용 수정하기
	public int modBoard(SqlSession sqlSession, Map<String, String> map);
	
	// 게시글 삭제하기(속성값 변경)
	public int delBoard(SqlSession sqlSession , String board_no);
	
	// 첨부파일 정보 저장하기
	public int boardfileRegist(SqlSession sqlSession , BoardfileVo fileVo);
	
	// 첨부파일 정보 불러오기
	public List<BoardfileVo> filelistRead(SqlSession sqlSession, String board_no);
	
	// 게시글 번호 이용해서 해당글의 첨부파일 모두 지우기
	public int delFilelist(SqlSession sqlSession , String board_no);
	
	// 파일 번호 이용해서 하나의 첨부파일 객체 얻기
	public BoardfileVo readFileOne(SqlSession sqlSession , String file_no);
	
	// 파일 번호 이용해서 하나의 첨부파일 지우기
	public int delFileOne(SqlSession sqlSession , String file_no);
	
	// 게시글에 대한 답글 작성하기
	public int boardReplyRegist(SqlSession sqlSession , BoardVo boardVo);
	
}
