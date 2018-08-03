package cn.gzsxt.manager.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class TestFtp {

	public static void main(String[] args) {	
		FTPClient client = new FTPClient();
		try {
			client.connect("192.168.182.3", 21);
			client.login("ftpuser", "ftpuser");
			File file = new File("D:\\Vue\\nova3.jpg");
			if (file.exists()) {
				FileInputStream fileInputStream = new FileInputStream(file);
				client.setFileType(FTP.BINARY_FILE_TYPE);
				client.changeWorkingDirectory("/home/ftpuser/images");
				client.storeFile("nova3.jpg", fileInputStream);
				fileInputStream.close();
				client.logout();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
