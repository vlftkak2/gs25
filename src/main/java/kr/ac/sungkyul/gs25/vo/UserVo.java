package kr.ac.sungkyul.gs25.vo;

public class UserVo {
	private Long no;
	private String name;
	private String email;
	private String password;
	private String birth;
	private String gender;
	private String address;
	private String phone;
	private Integer point;
	private String position;
	private Long store_no;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Long getStore_no() {
		return store_no;
	}
	public void setStore_no(Long store_no) {
		this.store_no = store_no;
	}
	@Override
	public String toString() {
		return "UserVo [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password + ", birth="
				+ birth + ", gender=" + gender + ", address=" + address + ", phone=" + phone + ", point=" + point
				+ ", position=" + position + ", store_no=" + store_no + "]";
	}
}