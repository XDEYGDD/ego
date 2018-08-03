package cn.gzsxt.manager.service;

import org.springframework.web.multipart.MultipartFile;

import cn.gzsxt.common.pojo.PictureResult;

public interface FileUploadService {
	/**
	 * 文件上传
	 * @param file
	 * @return
	 */
	public PictureResult upload(MultipartFile file);
}
