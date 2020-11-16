package boardTest.boardfile.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import boardTest.boardfile.model.BoardfileVo;

@Repository("fileDao")
public class FileDao implements FileDaoI{
	
	@Resource(name="SqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public BoardfileVo readFile(String file_no) {
		return sqlSession.selectOne("file.readFile", file_no);
	}
}
