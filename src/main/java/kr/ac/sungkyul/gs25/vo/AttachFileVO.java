package kr.ac.sungkyul.gs25.vo;

public class AttachFileVO {
	
	private Long fNO;
	private Long no;
	private String path;
	private String orgName;
	private String saveName;
	private long fileSize;
	private String imageurl;
	private int groupno;
	private int orderno;


	public Long getfNO() {
		return fNO;
	}

	public void setfNO(Long fNO) {
		this.fNO = fNO;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
	

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	
	


	public int getGroupno() {
		return groupno;
	}

	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}

	
	
	public int getOrderno() {
		return orderno;
	}

	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}

	@Override
	public String toString() {
		return "AttachFileVO [fNO=" + fNO + ", no=" + no + ", path=" + path + ", orgName=" + orgName + ", saveName="
				+ saveName + ", fileSize=" + fileSize + ", imageurl=" + imageurl + ", groupno=" + groupno + ", orderno="
				+ orderno + "]";
	}

	


}
