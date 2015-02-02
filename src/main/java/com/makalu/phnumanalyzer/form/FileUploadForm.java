package com.makalu.phnumanalyzer.form;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadForm {
	MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
