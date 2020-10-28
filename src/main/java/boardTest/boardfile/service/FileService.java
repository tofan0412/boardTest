package boardTest.boardfile.service;

import org.apache.ibatis.session.SqlSession;

import boardTest.DB.MyBatisUtil;
import boardTest.boardfile.dao.FileDao;
import boardTest.boardfile.dao.FileDaoI;
import boardTest.boardfile.model.BoardfileVo;

public class FileService implements FileServiceI{
	private static FileServiceI service;
	private FileDaoI dao;
	
	private FileService() {
		dao = FileDao.getDao();
	}
	
	public static FileServiceI getService() {
		if (service == null) service = new FileService();
		return service;
	}

	@Override
	public BoardfileVo readFile(String file_no) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		BoardfileVo fileVo = dao.readFile(sqlSession, file_no);
		sqlSession.close();
		return fileVo;
	}
}
