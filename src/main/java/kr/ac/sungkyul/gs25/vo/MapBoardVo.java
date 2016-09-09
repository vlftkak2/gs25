package kr.ac.sungkyul.gs25.vo;

public class MapBoardVo {
	
	private Long no;
	private String name;
	private String address;
	private Long regionno;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getRegionno() {
		return regionno;
	}
	
	public void setRegionno(Long regionno) {
		this.regionno = regionno;
	}
	@Override
	public String toString() {
		return "MapBoardVo [no=" + no + ", name=" + name + ", address=" + address + ", regionno=" + regionno + "]";
	}
	
	
	
	
	
	
	

}
