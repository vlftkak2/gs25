package kr.ac.sungkyul.gs25.vo;

public class ProductVo {
	
	private Long no;
	private String name;
	private int price;
	private String reg_date;
	private String expiry_date;
	private String maker;
	private Long kind_no;
	private String kind;
	private int dispercent;
	private String category;
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
	
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public int getDispercent() {
		return dispercent;
	}
	public void setDispercent(int dispercent) {
		this.dispercent = dispercent;
	}

	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}
	public Long getKind_no() {
		return kind_no;
	}
	public void setKind_no(Long kind_no) {
		this.kind_no = kind_no;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	@Override
	public String toString() {
		return "ProductVo [no=" + no + ", name=" + name + ", price=" + price + ", reg_date=" + reg_date
				+ ", expiry_date=" + expiry_date + ", maker=" + maker + ", kind_no=" + kind_no + ", kind=" + kind
				+ ", dispercent=" + dispercent + ", category=" + category + ", imageurl=" + imageurl + "]";
	}
	
}
