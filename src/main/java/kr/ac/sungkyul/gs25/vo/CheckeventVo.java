package kr.ac.sungkyul.gs25.vo;

public class CheckeventVo {
	private Long user_no;
	private Integer years;
	private Integer months;
	private Integer dates;
	private Long store_no;
	
	public Long getUser_no() {
		return user_no;
	}
	public void setUser_no(Long user_no) {
		this.user_no = user_no;
	}
	public Integer getYears() {
		return years;
	}
	public void setYears(Integer years) {
		this.years = years;
	}
	public Integer getMonths() {
		return months;
	}
	public void setMonths(Integer months) {
		this.months = months;
	}
	public Integer getDates() {
		return dates;
	}
	public void setDates(Integer dates) {
		this.dates = dates;
	}
	
	public Long getStore_no() {
		return store_no;
	}
	public void setStore_no(Long store_no) {
		this.store_no = store_no;
	}
	
	@Override
	public String toString() {
		return "CheckeventVo [user_no=" + user_no + ", years=" + years + ", months=" + months + ", dates=" + dates
				+ ", store_no=" + store_no + "]";
	}
}
