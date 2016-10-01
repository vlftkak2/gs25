package kr.ac.sungkyul.gs25.vo;

public class GuestbookVo {

	private Long no;
	private String content;
	private String reg_date;
	private Long user_no;
	private Long store_no;
	private String email; // 회원 아이디 가져오기 위함
	 
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	
	public Long getUser_no() {
		return user_no;
	}
	public void setUser_no(Long user_no) {
		this.user_no = user_no;
	}
	public Long getStore_no() {
		return store_no;
	}
	public void setStore_no(Long store_no) {
		this.store_no = store_no;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "GuestbookVo [no=" + no + ", content=" + content + ", reg_date=" + reg_date + ", user_no=" + user_no
				+ ", store_no=" + store_no + ", email=" + email + "]";
	}
	
	
	
}
