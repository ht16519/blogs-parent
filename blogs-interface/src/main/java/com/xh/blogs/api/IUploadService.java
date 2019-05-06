package com.xh.blogs.api;

import com.xh.blogs.exception.BusinessException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * 图片上传Service
 * @author DWQin
 *
 */
public interface IUploadService {

	String real_upload_path = "K:/blogs_2019/accessory/";

	String UPLOAD_PATH = "file/";
	
	int WIDTH = 400;
	
	int HEIGHT = 400;
	
	
	
	/**
	 * 图片上传
	 * @param file（上传的文件）
	 * @param uploadPath（图片保存文件夹）
	 * @param realUploadPath（图片保存完整路径）
	 * @return
	 */
    String uploadImage(MultipartFile file, String userName) throws Exception;
    
    
	/**
	 * 图片上传并处理
	 * @param file（图片文件）
	 * @param uploadPath（图片保存文件夹）
	 * @param realUploadPath（图片保存完整路径）
	 * @return
	 * @throws IOException 
	 */
	String uploadThum4Image(MultipartFile file, String uploadPath, String userName) throws BusinessException;
	
	/**
	 * @Name uploadThum4Image
	 * @Description 图片上传并处理
	 * @Date 2019年2月10日
	 * @Author wen
	 * @param file
	 * @param uploadPath
	 * @param realUploadPath
	 * @param width
	 * @param height
	 * @return
	 * @throws IOException 
	 */
	String uploadThum4Image(MultipartFile file, String uploadPath, String userName, int width, int height) throws BusinessException;

	
}