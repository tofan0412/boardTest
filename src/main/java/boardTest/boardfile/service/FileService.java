package boardTest.boardfile.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import boardTest.boardfile.dao.FileDaoI;
import boardTest.boardfile.model.BoardfileVo;

@Service("fileService")
public class FileService implements FileServiceI{
	
	@Resource(name="fileDao")
	private FileDaoI dao;

	@Override
	public BoardfileVo readFile(String file_no) {
		return dao.readFile(file_no);
	}
}
