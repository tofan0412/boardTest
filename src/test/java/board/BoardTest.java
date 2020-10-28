package board;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import boardTest.board.dao.BoardDao;
import boardTest.board.dao.BoardDaoI;
import boardTest.board.model.BoardVo;
import boardTest.board.service.BoardService;
import boardTest.board.service.BoardServiceI;
import boardTest.boardfile.model.BoardfileVo;

public class BoardTest {
	BoardServiceI service;
	BoardDaoI dao;
	
	@Before
	public void setup() {
		service = BoardService.getService();
		dao = BoardDao.getDao();
	}
	
	
	@Test
	public void boardlistTest() {
		/***Given***/
		String kind_no = "1";

		/***When***/
		List<BoardVo> boardlist = service.boardList(kind_no);
		/***Then***/
		assertEquals(1, boardlist.size());
		
	}
	
	@Test
	public void boardRegistTest() {
		/***Given***/
		String user_id = "brown";
		String board_title = "시퀀스 값 확인용";
		String kind_no = "1";
		String board_cont = "시퀀스 값 확인용";
		
		BoardVo boardVo = new BoardVo();
		
		boardVo.setUser_id(user_id);
		boardVo.setBoard_title(board_title);
		boardVo.setKind_no(kind_no);
		boardVo.setBoard_cont(board_cont);

		/***When***/
		int result = service.boardRegist(boardVo);
		
		/***Then***/
		// result는 현재 sequence 값을 가져온다.
		assertEquals(1, result);
	}
	
	@Test
	public void readBoardTest() {
		/***Given***/
		String board_no = "1";
		
		/***When***/
		BoardVo boardVo = service.readBoard(board_no);
		/***Then***/
		assertEquals(board_no, boardVo.getBoard_no());
	}
	
	@Test
	public void modBoardTest() {
		/***Given***/
		String board_no = "1";
		String board_title = "자유게시판글수정했음..!";
		String board_cont = "자유게시판글수정했음..!";
		Map<String, String> map = new HashMap<>();
		map.put("board_no", board_no);
		map.put("board_title", board_title);
		map.put("board_cont", board_cont);
		
		/***When***/
		int result = service.modBoard(map);
		/***Then***/
		BoardVo boardVo = service.readBoard(board_no);
		assertEquals(board_title, boardVo.getBoard_title());
	}
	
	@Test
	public void delBoardTest() {
		/***Given***/
		String board_no = "1";

		/***When***/
		int result = service.delBoard(board_no);
		
		/***Then***/
		BoardVo boardVo = service.readBoard(board_no);
		assertEquals("0", boardVo.getBoard_delete());
	}
	
	@Test
	public void boardfileRegistTest() {
		/***Given***/
		BoardfileVo boardfileVo = new BoardfileVo();
		
		boardfileVo.setBoard_no("32");
		boardfileVo.setRealfilename("brown.png");
		boardfileVo.setFile_path("d:\\upload\\brown.png");

		/***When***/
		int result = service.boardfileRegist(boardfileVo);
		
		/***Then***/
		assertEquals(1, result);
	}
	
	@Test
	public void filelistReadTest() {
		/***Given***/
		String board_no = "37";

		/***When***/
		List<BoardfileVo> filelist = service.filelistRead(board_no);
		/***Then***/
		assertEquals(5, filelist.size());
	}
	

}
