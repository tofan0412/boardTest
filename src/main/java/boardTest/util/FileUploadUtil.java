package boardTest.util;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUploadUtil {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);

	public static String getFilename(String contentDisposition) {
		String filename = "";
		String[] values = contentDisposition.split("; ");
		
		logger.debug("파일 이름 얻기 1 세미콜론 구분자로 나누기 : {}", values);
		
		for (String index : values) {
			String[] value = index.split("=");
			if (value[0].equals("filename")){
				return value[1].replaceAll("\"", "");
			}
		}
		// 파일이름을 찾을 수 없을 경우, String "none"를 반환한다.
		return filename;
	}
	
	//filename : sally.png => png
	public static String getExtension(String filename) {
//		String extension = "null";
//		
//		if (extension.equals("null")) {
//			extension = filename.valueOf(".jpg");
//		}
//		if (extension.equals("null")) {
//			extension = filename.valueOf(".jpeg");
//		}
//		if (extension.equals("null")) {
//			extension = filename.valueOf(".png");
//		}
//		return extension;
		
		if(filename == null || filename.indexOf(".") == -1) {
			return "";
		}else {
			return filename.split("\\.")[1];
		}
	}
	
	// 랜덤으로 입력한 수 길이 만큼의 문자열 만들기..
	public static String randomAlphaWord(int wordLength) {
		Random r = new Random();
		StringBuilder sb = new StringBuilder(wordLength);

		for(int i = 0; i < wordLength; i++) {
			char tmp = (char) ('a' + r.nextInt('z' - 'a'));
			sb.append(tmp);
		}
		return sb.toString();
	}
	
	
}
