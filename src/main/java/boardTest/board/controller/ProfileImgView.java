package boardTest.board.controller;

import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public class ProfileImgView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		FileInputStream fis = new FileInputStream((String)model.get("filepath"));
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
