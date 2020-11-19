package file;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import boardTest.boardfile.dao.FileDaoI;
import boardTest.boardfile.model.BoardfileVo;
import boardTest.boardfile.service.FileServiceI;
import test.config.ModelTestConfig;

public class fileTest extends ModelTestConfig{
	@Resource(name="fileService")
	FileServiceI service;
	@Resource(name="fileDao")
	FileDaoI dao;
	
	@Before
	public void setup() {
	}
	
	
	@Test
	public void readFiletest() {
		/***Given***/
		String file_no = "41";

		/***When***/
		BoardfileVo result = service.readFile(file_no);
		/***Then***/
		assertEquals("cony.png", result.getRealfilename());
		
		
	}

}
