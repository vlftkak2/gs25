package kr.ac.sungkyul.gs25.vo;

public class ProductVo {
	
	private Long no;
	private String name;
	private int price;
	private String regdate;
	private String expirydate;
	private String maker;
	private Long kindno;
	private String kind;
	private int dispercent;
	private String producturl;
	
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
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getExpirydate() {
		return expirydate;
	}
	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public Long getKindno() {
		return kindno;
	}
	public void setKindno(Long kindno) {
		this.kindno = kindno;
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
	
	
	public String getProducturl() {
		return producturl;
	}
	public void setProducturl(String producturl) {
		this.producturl = producturl;
	}
	
	@Override
	public String toString() {
		return "ProductVo [no=" + no + ", name=" + name + ", price=" + price + ", regdate=" + regdate + ", expirydate="
				+ expirydate + ", maker=" + maker + ", kindno=" + kindno + ", kind=" + kind + ", dispercent="
				+ dispercent + ", producturl=" + producturl + "]";
	}
	
	
	
	
	

}
