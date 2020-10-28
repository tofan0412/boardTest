package boardTest.util;

import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardTest.boardfile.model.BoardfileVo;
import boardTest.boardfile.service.FileService;
import boardTest.boardfile.service.FileServiceI;

@WebServlet("/filedownload")
public class ProfileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private FileServiceI service;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service = FileService.getService();
		
		String file_no = request.getParameter("file_no");
		// PK인 file 번호 이용해서 해당 파일 객체 불러오기
		BoardfileVo fileVo = service.readFile(file_no);

		// response의 content-type 설정
		response.setHeader("Content-Disposition", "attachment; filename=\"\""+ "Down_" + fileVo.getRealfilename() + "\"");
		response.setContentType("application/octet-stream");
		
		// 경로 확인 후 파일 입출력을 통해 응답 생성
		// 파일 읽기
		// 응답 생성
		FileInputStream fis = new FileInputStream(fileVo.getFile_path());
		ServletOutputStream sos = response.getOutputStream();
		
		byte[] buffer = new byte[512];
		
		while( fis.read(buffer) != -1) {	// 읽을 수 있는게 존재하는 동안..
			sos.write(buffer);
		}
		fis.close();
		sos.flush();	// 버퍼에 남아있는걸 전송
		sos.close();
	}
}