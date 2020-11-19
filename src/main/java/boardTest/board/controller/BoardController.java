package boardTest.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import boardTest.board.model.BoardVo;
import boardTest.board.service.BoardServiceI;
import boardTest.boardfile.model.BoardfileVo;
import boardTest.boardfile.service.FileServiceI;
import boardTest.kind.model.KindVo;
import boardTest.kind.service.KindServiceI;
import boardTest.member.model.MemberVo;
import boardTest.member.service.MemberServiceI;
import boardTest.reply.model.ReplyVo;
import boardTest.reply.service.ReplyServiceI;
import boardTest.util.PageVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	// Access to Service Obj.
	@Resource(name = "boardService")
	private BoardServiceI boardService;

	@Resource(name = "fileService")
	private FileServiceI fileService;

	@Resource(name = "kindService")
	private KindServiceI kindService;

	@Resource(name = "memberService")
	private MemberServiceI memberService;

	@Resource(name = "replyService")
	private ReplyServiceI replyService;

	@Resource(name = "KindVo")
	private KindVo kindVo;

	@RequestMapping("/loginView")
	public String loginView() {
		return "application/loginView";
	}

	@RequestMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		Map<String, String> userInfo = new HashMap<String, String>();
		userInfo.put("user_id", userId);
		userInfo.put("user_pass", password);

		MemberVo memberVo = memberService.login(userInfo);

		if (memberVo == null) {
			return loginView();
		}

		if (memberVo.getUser_id().equals(userId)) {
			session.setAttribute("s_member", memberVo);
			return "tiles.mainView";
		} else
			return loginView();
	}

	@RequestMapping("/mainView")
	public String mainView() {
		return "tiles.mainView";
	}

	@RequestMapping("/menuList")
	public String menuList(Model model) {
		List<KindVo> result = kindService.roadMenuList();
		model.addAttribute("menulist", result);
		return "layout/leftHTML";
	}

	@RequestMapping("/manageKindView")
	public String manageKind(Model model) {
		List<KindVo> menulist = kindService.roadMenuList();
		model.addAttribute("menulist", menulist);

		return "tiles.manageKindView";
	}

	@RequestMapping("/modKind")
	public String modKind(String kind_no, String kind_name, String kind_valid, Model model) {
		logger.debug("kind_no : {}, kind_name : {}, kind_valid : {}", kind_no, kind_name, kind_valid);

		Map<String, String> menuInfo = new HashMap<>();

		menuInfo.put("kind_no", kind_no);
		menuInfo.put("kind_name", kind_name);
		menuInfo.put("kind_valid", kind_valid);

		int result = kindService.changeStatus(menuInfo);

		List<KindVo> menuList = kindService.roadMenuList();
		model.addAttribute("menulist", menuList);

		if (result > 0)
			return "tiles.manageKindView";
		else
			return "tiles.mainView";
	}

	@RequestMapping("/kindRegist")
	public String kindregist(String kind_name, String kind_valid, Model model) {
		kindVo.setKind_name(kind_name);
		kindVo.setKind_valid(kind_valid);

		int result = kindService.kindRegist(kindVo);

		List<KindVo> menuList = kindService.roadMenuList();
		model.addAttribute("menulist", menuList);
		if (result > 0)
			return "tiles.manageKindView";
		else
			return "tiles.mainView";
	}

	@RequestMapping("/boardlist")
	public String boardList(String kind_no, String kind_name, Model model, PageVo pageVo) {
		logger.debug("pageVo : {}", pageVo);

		// 사용자가 별도로 요청한 page가 있으면 해당 값으로, 없으면 기본적으로 1페이지를 불러온다.
		String page = pageVo.getPage() == 0 ? Integer.toString(1) : Integer.toString(pageVo.getPage());
		// 사용자가 요청한 pageSize가 있으면 해당 값으로, 없으면 기본적으로 7개의 값을 불러온다.
		String pageSize = pageVo.getPageSize() == 0 ? Integer.toString(7) : Integer.toString(pageVo.getPageSize());

		logger.debug("수정 후 페이지 정보 : {},{}", page, pageSize);

		Map<String, String> pageInfo = new HashMap<String, String>();
		pageInfo.put("kind_no", kind_no);
		pageInfo.put("page", page);
		pageInfo.put("pageSize", pageSize);

		List<BoardVo> boardlist = boardService.boardListPage(pageInfo);

		logger.debug("불러온 페이지의 글 개수 : {}", boardlist.size());

		// 필요한 페이지 수의 계산을 위해 전체 게시글의 수 불러오기 ..
		List<BoardVo> totCnt = boardService.boardList(kind_no);

		// LEVEL에 맞게 TITLE앞에 공백 붙여주기
		for (int i = 0; i < boardlist.size(); i++) {
			String board_title = boardlist.get(i).getBoard_title();
			int level = Integer.parseInt(boardlist.get(i).getBoard_level()) - 1;
			String str = ">";
			if (level == 0)
				continue;
			else {
				for (int j = 0; j < level; j++) {
					str += str;
				}
				boardlist.get(i).setBoard_title(str + board_title);
			}
		}

		int tot_cnt = totCnt.size();

		int page_cnt = (int) Math.ceil(((double) tot_cnt / Integer.parseInt(pageSize)));
		int page_int = Integer.parseInt(page);
		int pageSize_int = Integer.parseInt(pageSize);

		model.addAttribute("kind_no", kind_no);
		model.addAttribute("page_cnt", page_cnt);
		model.addAttribute("page", page_int);
		model.addAttribute("page-1", page_int - 1);
		model.addAttribute("page+1", page_int + 1);
		model.addAttribute("pageSize", pageSize_int);
		model.addAttribute("boardlist", boardlist);
		model.addAttribute("kind_name", kind_name);

		return "tiles.boardlist";
	}

	@RequestMapping("boardRegistView")
	public String boardRegist(Model model) {
		List<KindVo> menulist = kindService.roadMenuList();
		model.addAttribute("menulist", menulist);
		return "tiles.boardRegistView";
	}

	@RequestMapping("/boardRegist")
	public String boardRegist(BoardVo boardVo, HttpSession session, @RequestParam("img1") MultipartFile file1,
			@RequestParam("img2") MultipartFile file2, @RequestParam("img3") MultipartFile file3,
			@RequestParam("img4") MultipartFile file4, @RequestParam("img5") MultipartFile file5, Model model) {
		MemberVo mv = (MemberVo) session.getAttribute("s_member");
		String user_id = mv.getUser_id();
		logger.debug("user_id : {}, kind_no : {}, board_title : {}, board_cont : {}", user_id, boardVo.getKind_no(),
				boardVo.getBoard_title(), boardVo.getBoard_cont());

		boardVo.setUser_id(user_id);

		// selectKey 옵션을 사용했기 때문에 board_no를 반환한다.
		int result = boardService.boardRegist(boardVo);

		List<MultipartFile> filelist = new ArrayList<MultipartFile>();
		filelist.add(file1);
		filelist.add(file2);
		filelist.add(file3);
		filelist.add(file4);
		filelist.add(file5);

		int fileNum = 0;
		for (int i = 0; i < filelist.size(); i++) {
			if (filelist.get(i).getSize() > 0) {
				BoardfileVo fileVo = new BoardfileVo();
				fileVo.setBoard_no(Integer.toString(result));
				fileVo.setRealfilename(filelist.get(i).getOriginalFilename());
				fileVo.setFile_path("d:\\upload\\" + filelist.get(i).getOriginalFilename());

				boardService.boardfileRegist(fileVo);
				fileNum++;
				File uploadFile = new File("d:\\upload\\" + filelist.get(i).getOriginalFilename());
				try {
					filelist.get(i).transferTo(uploadFile);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		logger.debug("업로드한 파일 수 : {}, 리스트의 크기 : {}", fileNum, filelist.size());

		return readBoard(boardVo.getBoard_no(), model);
	}

	@RequestMapping("/readBoard")
	public String readBoard(String board_no, Model model) {
		BoardVo boardVo = boardService.readBoard(board_no);
		List<ReplyVo> replyList = replyService.readReplyAll(board_no);
		List<BoardfileVo> fileList = boardService.filelistRead(board_no);

		model.addAttribute("BoardVo", boardVo);
		model.addAttribute("replylist", replyList);
		model.addAttribute("filelist", fileList);
		return "tiles.readBoard";
	}

	@RequestMapping("/profileImgView")
	public String/* View */ profileImgView(@RequestParam("file_no") String file_no, Model model)
			throws ServletException, IOException {
		// 응답으로 생성하려고 하는 것 : Img 파일을 읽어서 output stream 객체에 쓰는 것
		BoardfileVo fileVo = boardService.readFileOne(file_no);

		model.addAttribute("filepath", fileVo.getFile_path());

//		return new ProfileImgView();
		return "profileImgView";
	}

	@RequestMapping("profileDownload")
	public String profileDownload(@RequestParam("file_no") String file_no, Model model)
			throws ServletException, IOException {
		BoardfileVo fileVo = boardService.readFileOne(file_no);
		model.addAttribute("realFilename", fileVo.getRealfilename());
		model.addAttribute("filepath", fileVo.getFile_path());
		return "profileImgDownloadView";
	}

	@RequestMapping("delBoard")
	public String delBoard(String user_id, String board_no, String kind_no, Model model) {
		int result = boardService.delBoard(board_no);

		List<BoardfileVo> delFileList = boardService.filelistRead(board_no);

		// 1. 파일 삭제하기
		for (int i = 0; i < delFileList.size(); i++) {
			String realfilename = delFileList.get(i).getRealfilename();
			String file_path = delFileList.get(i).getFile_path();

			File file = new File(file_path);
			file.delete();
		}

		boardService.delFilelist(board_no);

		PageVo pageVo = new PageVo();
		logger.debug("kind_no : {}", kind_no);
		KindVo kindvo = kindService.readKindOne(kind_no);

		if (result > 0)
			return boardList(kind_no, kindVo.getKind_name(), model, pageVo);
		else
			return "tiles.mainView";
	}

	@RequestMapping("/modBoardView")
	public String modboardView(String user_id, String board_no, Model model, HttpSession session) {

		MemberVo memberVo = (MemberVo) session.getAttribute("s_member");

		// 해당 글에 대한 권한이 없는 경우 .. (글의 작성자 id와 session에 저장된 아이디가 서로 다른 경우)
		if (!memberVo.getUser_id().equals(user_id)) {
			return readBoard(board_no, model);
		}

		BoardVo boardVo = boardService.readBoard(board_no);

		// 해당 게시글의 첨부 파일 목록 불러오기..
		List<BoardfileVo> filelist = boardService.filelistRead(board_no);

		model.addAttribute("filelist", filelist);
		model.addAttribute("BoardVo", boardVo);

		return "tiles.boardModView";
	}

	@RequestMapping("modBoard")
	public String modBoard(String board_no, String board_title, String board_cont,
			@RequestParam("img1") MultipartFile file1, @RequestParam("img2") MultipartFile file2,
			@RequestParam("img3") MultipartFile file3, @RequestParam("img4") MultipartFile file4,
			@RequestParam("img5") MultipartFile file5, Model model) {

		logger.debug("번호 : {} 제목 : {} 내용 : {}", board_no, board_title, board_cont);
		Map<String, String> map = new HashMap<>();
		map.put("board_no", board_no);
		map.put("board_title", board_title);
		map.put("board_cont", board_cont);

		int result = boardService.modBoard(map);

		///////////////////////// 파일 업로드 부분 /////////////////////////////////////////

		// 파일 업로드 처리하기

		// Part 객체를 담는 리스트
		List<MultipartFile> filelist = new ArrayList<>();
		filelist.add(file1);
		filelist.add(file2);
		filelist.add(file3);
		filelist.add(file4);
		filelist.add(file5);

		List<BoardfileVo> realFilelist = new ArrayList<>();

		// 한 게시글에서 첨부할 수 있는 최대 개수는 5개이다. 따라서 이를 검사해야 한다.
		List<BoardfileVo> existfilelist = boardService.filelistRead(board_no);

		int upCnt = 0;
		for (int test = 0; test < filelist.size(); test++) {
			if (filelist.get(test).getSize() > 0) {
				upCnt++;
			}
		}

		if (upCnt + existfilelist.size() > 5) {
			logger.debug("최대 첨부 파일 개수 초과 !!!!");
			return readBoard(board_no, model);
		}

		// 사용자가 업로드한 파일의 실제 이름 가져오기..
		for (int i = 0; i < filelist.size(); i++) {
			logger.debug("실제 파일 이름은? {} ", filelist.get(i).getOriginalFilename());

			// 사용자가 해당 파일을 업로드 한 경우( 즉, 파일 이름이 none이 아닌 다른 경우)
			if (filelist.get(i).getSize() > 0) {
				String file_path = "d:\\upload\\" + filelist.get(i).getOriginalFilename();

				BoardfileVo boardfileVo = new BoardfileVo();

				boardfileVo.setBoard_no(board_no);
				boardfileVo.setRealfilename(filelist.get(i).getOriginalFilename());
				boardfileVo.setFile_path(file_path);

				// DB에 담을 객체 선발 ..
				realFilelist.add(boardfileVo);

				File uploadFile = new File("d:\\upload\\" + filelist.get(i).getOriginalFilename());
				try {
					filelist.get(i).transferTo(uploadFile);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}

		int tot_cnt = 0;

		// 사용자가 등록한 파일들에 대해서, DB상에 객체를 생성한다.
		for (int i = 0; i < realFilelist.size(); i++) {
			boardService.boardfileRegist(realFilelist.get(i));
			tot_cnt++;
		}
		// 이 때 realFilelist의 크기와 tot_cnt의 크기가 서로 같아야 한다.
		logger.debug("tot_cnt의 크기 : {} , 업로드한 파일의 수 : {}", tot_cnt, realFilelist.size());

		boolean uploadresult = false;
		if (tot_cnt == realFilelist.size()) {
			uploadresult = true;
		}

		model.addAttribute("board_no", board_no);
		if (result > 0 && uploadresult)
			return readBoard(board_no, model);
		else
			return "tiles.mainView";
	}

	@RequestMapping("delFileOne")
	public String delFileOne(String file_no, String board_no, String board_title, String board_cont, Model model,
			HttpSession session) {
		boolean file_del = false;
		boolean file_db_del = false;

		// 실제 경로에 있는 해당 파일 삭제하기..
		BoardfileVo fileVo = boardService.readFileOne(file_no);
		String realfilename = fileVo.getRealfilename();
		String file_path = fileVo.getFile_path();
		logger.debug("삭제할 파일의 정보 : 1. 경로 : {}, 2. 파일이름 : {}", file_path, realfilename);
		File file = new File(file_path);
		file.delete();
		file_del = true;

		// 해당 파일 DB에서 삭제하기..
		int result = boardService.delFileOne(file_no);
		if (result > 0) {
			file_db_del = true;
		}
		if (file_del & file_db_del) {
			BoardVo boardVo = new BoardVo();
			boardVo.setBoard_no(board_no);
			boardVo.setBoard_title(board_title);
			boardVo.setBoard_cont(board_cont);

			// 다시 해당 게시글에 대한 파일리스트를 불러와야 한다.
			List<BoardfileVo> filelist = boardService.filelistRead(board_no);

			model.addAttribute("filelist", filelist);
			model.addAttribute("BoardVo", boardVo);

			MemberVo memberVo = (MemberVo) session.getAttribute("s_member");

			return modboardView(memberVo.getUser_id(), board_no, model, session);

		} else
			return "tiles.mainView";
	}

	@RequestMapping("replyregist")
	public String replyregist(String board_no, String reply_cont, HttpSession session, Model model) {
		MemberVo memberVo = (MemberVo) session.getAttribute("s_member");
		String user_id = memberVo.getUser_id();

		ReplyVo replyVo = new ReplyVo();
		replyVo.setUser_id(user_id);
		replyVo.setBoard_no(board_no);
		replyVo.setReply_cont(reply_cont);

		int result = replyService.replyRegist(replyVo);

		if (result > 0) {
			return readBoard(board_no, model);
		} else {
			return "tiles.mainView";
		}
	}

	@RequestMapping("delreply")
	public String delreply(String reply_no, String board_no, Model model) {

		// 댓글 삭제
		int result = replyService.delReply(reply_no);

		if (result > 0)
			return readBoard(board_no, model);
		else
			return readBoard(board_no, model);
	}

	@RequestMapping("boardReplyRegistView")
	public String boardReplyRegistView(String board_no, String kind_no, Model model) {
		model.addAttribute("board_no", board_no);
		model.addAttribute("kind_no", kind_no);

		return "tiles.boardReplyRegistView";
	}

	@RequestMapping("boardReply")
	public String boardReply(BoardVo boardVo, HttpSession session, @RequestParam("img1") MultipartFile file1,
			@RequestParam("img2") MultipartFile file2, @RequestParam("img3") MultipartFile file3,
			@RequestParam("img4") MultipartFile file4, @RequestParam("img5") MultipartFile file5, Model model) {

		MemberVo memberVo = (MemberVo) session.getAttribute("s_member");
		String user_id = memberVo.getUser_id();

		BoardVo insertbv = new BoardVo();

		insertbv.setUser_id(user_id);
		insertbv.setBoard_title(boardVo.getBoard_title());
		insertbv.setKind_no(boardVo.getKind_no());
		insertbv.setBoard_cont(boardVo.getBoard_cont());
		insertbv.setBoard_parent_no(boardVo.getBoard_no());

		// 이때 result는 입력한 답글의 게시글 번호이다.
		int result = boardService.boardReplyRegist(insertbv);

		// 파일 업로드 처리하기
		List<MultipartFile> filelist = new ArrayList<MultipartFile>();
		filelist.add(file1);
		filelist.add(file2);
		filelist.add(file3);
		filelist.add(file4);
		filelist.add(file5);

		int fileNum = 0;
		for (int i = 0; i < filelist.size(); i++) {
			if (filelist.get(i).getSize() > 0) {
				BoardfileVo fileVo = new BoardfileVo();
				fileVo.setBoard_no(Integer.toString(result));
				fileVo.setRealfilename(filelist.get(i).getOriginalFilename());
				fileVo.setFile_path("d:\\upload\\" + filelist.get(i).getOriginalFilename());

				boardService.boardfileRegist(fileVo);
				fileNum++;
				File uploadFile = new File("d:\\upload\\" + filelist.get(i).getOriginalFilename());
				try {
					filelist.get(i).transferTo(uploadFile);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		logger.debug("업로드한 파일 수 : {}, 리스트의 크기 : {}", fileNum, filelist.size());

		return readBoard(Integer.toString(result), model);
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/board/loginView";
	}
}
