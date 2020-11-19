package boardTest.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import boardTest.board.dao.BoardDaoI;
import boardTest.board.model.BoardVo;
import boardTest.boardfile.model.BoardfileVo;

@Service("boardService")
public class BoardService implements BoardServiceI{
	
	@Resource(name="boardDao")
	public BoardDaoI dao;
	
	private BoardService() {
	}
	
	@Override
	public List<BoardVo> boardList(String kind_no) {
		return dao.boardList(kind_no);
	}
	
	@Override
	public List<BoardVo> boardListPage(Map<String, String> map) {
		return dao.boardListPage(map);
	}

	@Override
	public int boardRegist(BoardVo boardVo) {
		int result = dao.boardRegist(boardVo);
		// 이 때 result는 개수를 불러온다.
		// 주의해야 할 점은, xml 쿼리에서 selectKey를 적용하였기 때문에 들어간 파라미터의
		// board_no에 시퀀스 값이 저장된다는 것이다.
		return Integer.parseInt(boardVo.getBoard_no());
	}
	
	// 게시글에 대한 답글 입력하는 메서드..
	@Override
	public int boardReplyRegist(BoardVo boardVo) {
		int result = dao.boardReplyRegist(boardVo);
		// 이 때 result는 처리한 작업의 수를 불러온다.
		// 주의해야 할 점은, xml 쿼리에서 selectKey를 적용하였기 때문에 들어간 파라미터의
		// board_no에 시퀀스 값이 저장된다는 것이다.
		return Integer.parseInt(boardVo.getBoard_no());
	}

	@Override
	public BoardVo readBoard(String board_no) {
		return dao.readBoard(board_no);
	}

	@Override
	public int modBoard(Map<String, String> map) {
		return dao.modBoard(map);
	}

	@Override
	public int delBoard(String board_no) {
		return dao.delBoard(board_no);
	}

	@Override
	public int boardfileRegist(BoardfileVo fileVo) {
		return dao.boardfileRegist(fileVo);
	}

	@Override
	public List<BoardfileVo> filelistRead(String board_no) {
		return dao.filelistRead(board_no);
	}

	@Override
	public int delFilelist(String board_no) {
		// 애초에 해당 글의 첨부파일 목록이 없을 수도 있다..
		return dao.delFilelist(board_no);
	}

	@Override
	public int delFileOne(String file_no) {
		return dao.delFileOne(file_no);
	}

	@Override
	public BoardfileVo readFileOne(String file_no) {
		return dao.readFileOne(file_no);
	}
}
