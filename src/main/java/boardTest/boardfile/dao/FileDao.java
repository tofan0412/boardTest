package boardTest.boardfile.dao;

import org.apache.ibatis.session.SqlSession;

import boardTest.boardfile.model.BoardfileVo;

public class FileDao implements FileDaoI{
	private static FileDaoI dao;
	
	private FileDao() {
		
	}
	
	public static FileDaoI getDao() {
		if (dao == null) dao = new FileDao();
		return dao;
	}

	@Override
	public BoardfileVo readFile(SqlSession sqlSession, String file_no) {
		return sqlSession.selectOne("file.readFile", file_no);
	}
	
	
	
	
}
