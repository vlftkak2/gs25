package kr.ac.sungkyul.gs25.vo;

public class StoreVo {
	
	private Long no;
	private String name;
	private String address;
	private Long map_no;
	private String region_name;	//지역이름
	
	
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
	public Long getMap_no() {
		return map_no;
	}
	public void setMap_no(Long map_no) {
		this.map_no = map_no;
	}
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	
	@Override
	public String toString() {
		return "StoreVo [no=" + no + ", name=" + name + ", address=" + address + ", map_no=" + map_no + ", region_name="
				+ region_name + "]";
	}

}
