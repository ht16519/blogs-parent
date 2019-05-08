package com.xh.blogs.api;

import com.xh.blogs.domain.vo.AvatarVo;
import com.xh.blogs.domain.vo.UMEditorResult;
import com.xh.blogs.exception.BusinessException;
import org.springframework.web.multipart.MultipartFile;


/**
 * 附件上传Service
 * @author wen
 *
 */
public interface IUploadService {

	/**
	* @Name uploadThum4Image
	* @Description 上传图片并返回数据库保存路径
	* @Author wen
	* @Date 2019/5/7
	* @param file
	* @param avatarVo
	* @param userName
	* @return java.lang.String
	*/
	String uploadThum4Image(MultipartFile file, AvatarVo avatarVo, String userName) throws BusinessException;

	/**
	* @Name ueditorImageUpload
	* @Description UMEditor图片上传
	* @Author wen
	* @Date 2019/5/7
	* @param file
	* @param userName
	* @return com.xh.blogs.domain.vo.UMEditorResult
	*/
	UMEditorResult ueditorImageUpload(MultipartFile file, String userName) throws BusinessException;
}