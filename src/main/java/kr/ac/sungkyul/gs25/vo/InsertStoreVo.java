package kr.ac.sungkyul.gs25.vo;

public class InsertStoreVo {
	
	private Long map_no;
	private String name;
	private double localx;
	private double localy;
	private String address;
	private Long region_no;
	
	public Long getRegion_no() {
		return region_no;
	}
	public void setRegion_no(Long region_no) {
		this.region_no = region_no;
	}
	public Long getMap_no() {
		return map_no;
	}
	public void setMap_no(Long map_no) {
		this.map_no = map_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLocalx() {
		return localx;
	}
	public void setLocalx(double localx) {
		this.localx = localx;
	}
	public double getLocaly() {
		return localy;
	}
	public void setLocaly(double localy) {
		this.localy = localy;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "InsertStoreVo [map_no=" + map_no + ", name=" + name + ", localx=" + localx + ", localy=" + localy
				+ ", address=" + address + ", region_no=" + region_no + "]";
	}

}
