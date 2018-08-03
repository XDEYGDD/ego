package cn.gzsxt.manager.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.gzsxt.common.pojo.PictureResult;
import cn.gzsxt.manager.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {
	
	PictureResult result = new PictureResult();
	
	@Value("${FTP_HOST}")
	private String FTP_HOST;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_URL}")
	private String FTP_BASE_URL;
	@Value("${PIR_BASE_URL}")
	private String PIR_BASE_URL;

	@Override
	public PictureResult upload(MultipartFile file) {
		FTPClient client = new FTPClient();
		try {
			client.connect(FTP_HOST);
			client.login(FTP_USERNAME, FTP_PASSWORD);
				client.setFileType(FTP.BINARY_FILE_TYPE);
				client.changeWorkingDirectory(FTP_BASE_URL);
				
				
				String originalFileName = file.getOriginalFilename();
				String fileType = originalFileName.substring(originalFileName.lastIndexOf("."));
				
				String fileName = System.currentTimeMillis()+""+new Random().nextInt(100);
				
				client.storeFile(fileName+fileType, file.getInputStream());
				client.logout();
				result.setError(0);
				result.setUrl(PIR_BASE_URL+fileName+fileType);
				
				
		} catch (IOException e) {
			e.printStackTrace();
			result.setError(1);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}

}
