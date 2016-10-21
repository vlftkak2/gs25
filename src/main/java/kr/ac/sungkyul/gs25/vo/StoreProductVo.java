package kr.ac.sungkyul.gs25.vo;

public class StoreProductVo {
	
	private Long no;
	private String storename;
	private String name;
	private int price;
	private String maker;
	private String reg_date;
	private String expiry_date;
	private int newdate;
	private String imageurl;
	private int remainderdate;
	private int countprice;
	private int halfprice;
	private int count;
	private Long store_no;
	private Long product_no;
	private int mount;
	private int remaindercountdate;
	private String orgname;

	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
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
	public int getNewdate() {
		return newdate;
	}
	public void setNewdate(int newdate) {
		this.newdate = newdate;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public int getRemainderdate() {
		return remainderdate;
	}
	public void setRemainderdate(int remainderdate) {
		this.remainderdate = remainderdate;
	}
	public int getCountprice() {
		return countprice;
	}
	public void setCountprice(int countprice) {
		this.countprice = countprice;
	}
	public int getHalfprice() {
		return halfprice;
	}
	public void setHalfprice(int halfprice) {
		this.halfprice = halfprice;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Long getStore_no() {
		return store_no;
	}
	public void setStore_no(Long store_no) {
		this.store_no = store_no;
	}
	public Long getProduct_no() {
		return product_no;
	}
	public void setProduct_no(Long product_no) {
		this.product_no = product_no;
	}
	public int getMount() {
		return mount;
	}
	public void setMount(int mount) {
		this.mount = mount;
	}
	
	public int getRemaindercountdate() {
		return remaindercountdate;
	}
	public void setRemaindercountdate(int remaindercountdate) {
		this.remaindercountdate = remaindercountdate;
	}
	
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	@Override
	public String toString() {
		return "StoreProductVo [no=" + no + ", storename=" + storename + ", name=" + name + ", price=" + price
				+ ", maker=" + maker + ", reg_date=" + reg_date + ", expiry_date=" + expiry_date + ", newdate="
				+ newdate + ", imageurl=" + imageurl + ", remainderdate=" + remainderdate + ", countprice=" + countprice
				+ ", halfprice=" + halfprice + ", count=" + count + ", store_no=" + store_no + ", product_no="
				+ product_no + ", mount=" + mount + ", remaindercountdate=" + remaindercountdate + ", orgname="
				+ orgname + "]";
	}


	
	
	
	

	
	
	
}
