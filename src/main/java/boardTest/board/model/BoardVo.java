package boardTest.board.model;

import java.sql.Date;

public class BoardVo {
	private String board_no; 
	private String board_title; 
	private String board_cont; 
	private Date board_date; 
	private String board_delete; 
	private String board_parentnum; 
	private String user_id; 
	private String kind_no;
		
	public String getBoard_no() {
		return board_no;
	}
	public void setBoard_no(String board_no) {
		this.board_no = board_no;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_cont() {
		return board_cont;
	}
	public void setBoard_cont(String board_cont) {
		this.board_cont = board_cont;
	}
	public Date getBoard_date() {
		return board_date;
	}
	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}
	public String getBoard_delete() {
		return board_delete;
	}
	public void setBoard_delete(String board_delete) {
		this.board_delete = board_delete;
	}
	public String getBoard_parentnum() {
		return board_parentnum;
	}
	public void setBoard_parentnum(String board_parentnum) {
		this.board_parentnum = board_parentnum;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getKind_no() {
		return kind_no;
	}
	public void setKind_no(String kind_no) {
		this.kind_no = kind_no;
	}
}
