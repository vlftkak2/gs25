package kr.ac.sungkyul.gs25.vo;

public class AttachFileVO {
	
	private int fNO;
	private int no;
	private String path;
	private String orgName;
	private String saveName;
	private long fileSize;

	public int getfNO() {
		return fNO;
	}

	public void setfNO(int fNO) {
		this.fNO = fNO;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
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

	@Override
	public String toString() {
		return "AttachFileVO [fNO=" + fNO + ", no=" + no + ", path=" + path + ", orgName=" + orgName + ", saveName="
				+ saveName + ", fileSize=" + fileSize + "]";
	}

}
