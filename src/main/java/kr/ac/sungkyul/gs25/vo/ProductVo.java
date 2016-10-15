package kr.ac.sungkyul.gs25.vo;

public class ProductVo {
	
	private Long no;
	private String name;
	private int price;
	private String maker;
	private Long kind_no;
	private String imageurl;
	
	
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public Long getKind_no() {
		return kind_no;
	}
	public void setKind_no(Long kind_no) {
		this.kind_no = kind_no;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	
	@Override
	public String toString() {
		return "ProductVo [no=" + no + ", name=" + name + ", price=" + price + ", maker=" + maker + ", kind_no="
				+ kind_no + ", imageurl=" + imageurl + "]";
	}
	


	
	
	


	
}
