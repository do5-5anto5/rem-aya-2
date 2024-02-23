package br.com.rem_aya_2.data_vo_v1;

import java.io.Serializable;

public class UploadFileResponseVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String filename;
	private String downloadURI;
	private String fileType;
	private long fileSize;
	
	public UploadFileResponseVO() {}

	public UploadFileResponseVO(String filename, String downloadURI, String fileType, long fileSize) {
		this.filename = filename;
		this.downloadURI = downloadURI;
		this.fileType = fileType;
		this.fileSize = fileSize;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getDownloadURI() {
		return downloadURI;
	}

	public void setDownloadURI(String downloadURI) {
		this.downloadURI = downloadURI;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

}
