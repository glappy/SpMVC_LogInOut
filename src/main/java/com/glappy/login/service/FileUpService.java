package com.glappy.login.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileUpService {
	@Autowired
	ServletContext context;

	public String upload(MultipartFile file) {
		String realPath = context.getRealPath("/files/");
		File dir = new File(realPath);

		if (!dir.exists())
			dir.mkdir();
		if (file.isEmpty() || file == null)
			return null;

		String realFile = file.getOriginalFilename();
		String saveFile = UUID.randomUUID().toString();
		File upFile = new File(realFile, saveFile);

		try {
			file.transferTo(upFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return saveFile;
	}

	public List<String> uploads(MultipartHttpServletRequest files) {
		List<MultipartFile> fileList = files.getFiles("files");
		List<String> fileNames = new ArrayList<String>();
		for (MultipartFile file : fileList) {
			fileNames.add(this.upload(file));
		}
		return fileNames;
	}
}