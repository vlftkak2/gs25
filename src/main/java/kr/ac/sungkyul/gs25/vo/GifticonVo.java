package kr.ac.sungkyul.gs25.vo;

public class GifticonVo {
	
	private Long no;
	private Long user_no;
	private Long storeproduct_no;
	private String receive_date;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getUser_no() {
		return user_no;
	}
	public void setUser_no(Long user_no) {
		this.user_no = user_no;
	}
	public Long getStoreproduct_no() {
		return storeproduct_no;
	}
	public void setStoreproduct_no(Long storeproduct_no) {
		this.storeproduct_no = storeproduct_no;
	}
	public String getReceive_date() {
		return receive_date;
	}
	public void setReceive_date(String receive_date) {
		this.receive_date = receive_date;
	}
	
	@Override
	public String toString() {
		return "GifticonVo [no=" + no + ", user_no=" + user_no + ", storeproduct_no=" + storeproduct_no
				+ ", receive_date=" + receive_date + "]";
	}
	
	

}
