package cn.gzsxt.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.gzsxt.common.pojo.PictureResult;
import cn.gzsxt.manager.service.FileUploadService;

@Controller
public class FileUploadController {
	@Autowired
	private FileUploadService service;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public PictureResult upload(MultipartFile uploadFile) {
		PictureResult result = service.upload(uploadFile);
		return result;
	}
		
	
	
}
