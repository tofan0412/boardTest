package boardTest.boardfile.dao;

import org.apache.ibatis.session.SqlSession;

import boardTest.boardfile.model.BoardfileVo;

public interface FileDaoI {
	// file_no 이용해서 하나의 파일 불러오기
	public BoardfileVo readFile(String file_no);
	
}
