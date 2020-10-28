package boardTest.board.service;

import java.util.List;
import java.util.Map;

import boardTest.board.model.BoardVo;
import boardTest.boardfile.model.BoardfileVo;

public interface BoardServiceI {
	// 특정 종류의 게시글을 모두 불러온다.
	public List<BoardVo> boardList(String kind_no);
	
	// 작성한 게시글 등록하기
	public int boardRegist(BoardVo boardVo);
	
	// 하나의 게시글 불러오기
	public BoardVo readBoard(String board_no);
	
	// 게시글 제목, 내용 수정하기
	public int modBoard(Map<String, String> map);
	
	// 게시글 삭제하기(속성값 변경)
	public int delBoard(String board_no);
	
	// 첨부파일 정보 저장하기
	public int boardfileRegist(BoardfileVo fileVo);
	
	// 첨부파일 정보 불러오기
	public List<BoardfileVo> filelistRead(String board_no);
	
	// 게시글에 대한 답글 작성하기
	public int boardReplyRegist(BoardVo boardVo);
	
}
