package kr.ac.sungkyul.gs25.vo;

public class EventBoardVo {

	private Long no;
	private String title;
	private String startdate;
	private String enddate;
	private Integer count;
	private Long store_no;
	private Long UserNo;
	private String imageurl;
	
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Long getStore_no() {
		return store_no;
	}
	public void setStore_no(Long store_no) {
		this.store_no = store_no;
	}
	public Long getUserNo() {
		return UserNo;
	}
	public void setUserNo(Long userNo) {
		UserNo = userNo;
	}
	
	
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	@Override
	public String toString() {
		return "EventBoardVo [no=" + no + ", title=" + title + ", startdate=" + startdate + ", enddate=" + enddate
				+ ", count=" + count + ", store_no=" + store_no + ", UserNo=" + UserNo + ", imageurl=" + imageurl + "]";
	}
	
	
	
	
}
