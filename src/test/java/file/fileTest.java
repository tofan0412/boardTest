package file;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import boardTest.boardfile.dao.FileDao;
import boardTest.boardfile.dao.FileDaoI;
import boardTest.boardfile.model.BoardfileVo;
import boardTest.boardfile.service.FileService;
import boardTest.boardfile.service.FileServiceI;

public class fileTest {
	FileServiceI service;
	FileDaoI dao;
	
	@Before
	public void setup() {
		service = FileService.getService();
		dao = FileDao.getDao();
	}
	
	
	@Test
	public void readFiletest() {
		/***Given***/
		String file_no = "1";

		/***When***/
		BoardfileVo result = service.readFile(file_no);
		/***Then***/
		assertEquals("brown.png", result.getRealfilename());
		
		
	}

}
