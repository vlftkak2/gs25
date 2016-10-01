package kr.ac.sungkyul.gs25.vo;

public class AttachFileEvVO {

	private Long fNO; // PK
	private Long no; // eventboard FK
	private String path; // 전체 경로
	private String orgName; // 원 파일명
	private String saveName; // 저장 파일명
	private Long fileSize; // 파일크기
	private String imageurl; // 이미지 경로
	
	
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
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	@Override
	public String toString() {
		return "AttachFileEvVO [fNO=" + fNO + ", no=" + no + ", path=" + path + ", orgName=" + orgName + ", saveName="
				+ saveName + ", fileSize=" + fileSize + ", imageurl=" + imageurl + "]";
	}
	
	
	
}
