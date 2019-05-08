package com.xh.blogs.service.impl;

import com.xh.blogs.api.IUploadService;
import com.xh.blogs.consts.ConfigConst;
import com.xh.blogs.domain.dos.DArticleAccessory;
import com.xh.blogs.domain.dos.DPath;
import com.xh.blogs.domain.vo.AvatarVo;
import com.xh.blogs.domain.vo.UMEditorResult;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.utils.BeanValidator;
import com.xh.blogs.utils.DateUtil;
import com.xh.blogs.utils.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Name UploadServiceImpl
 * @Description 附件上传服务
 * @Date 2019年2月10日
 * @Author wen
 */
@Service
@Slf4j
public class UploadServiceImpl implements IUploadService {

	@Value("${fileUpload.rootSavePath}")
	private String rootSavePath;
	@Value("${fileUpload.rootHttpPath}")
	private String rootHttpPath;

	@Override
	public String uploadThum4Image(MultipartFile file, AvatarVo avatarVo, String userName) throws BusinessException {
		//1.参数校验
		BeanValidator.check(avatarVo);
		try {
		    //2.图片处理
			ImageUtil.handle(avatarVo, file);
			//3.获取路径
			DPath path = this.getPath(file, userName, ConfigConst.AVATAR_PREFIX);
			//4.文件保存操作
			Thumbnails.of(file.getInputStream()) // 文件流
					.sourceRegion(avatarVo.getIX(), avatarVo.getIY(), avatarVo.getIWidth(), avatarVo.getIHeight()) //剪切
					.size(ImageUtil.DEFAULT_IMAGE_SIDE, ImageUtil.DEFAULT_IMAGE_SIDE) //宽高
					.keepAspectRatio(false)
					.toFile(path.getSavePath());
			return path.getDbPath();
		} catch (IOException e) {
			log.error("文件写入失败：", e);
			throw new BusinessException(EmError.FILE_WRITE_ERROR);
		}
	}

	@Override
	public UMEditorResult ueditorImageUpload(MultipartFile file, String userName) {
		UMEditorResult data = new UMEditorResult();
		if(!(file == null || file.isEmpty())){
            long size = file.getSize();
            if(size <= ConfigConst.MAX_FILE_SIZE){
                try {
                    //1.图片处理
                    DArticleAccessory accessory = ImageUtil.handle(file);
                    //文件原名
                    String originalName = file.getOriginalFilename();
                    //文件后缀
                    String ext = ImageUtil.getExt(originalName);
                    //公有的路径
                    String commonPath = ConfigConst.ARTICLE_IMAGE_PREFIX + DateUtil.getDateStr() + ext;
                    //保存物理路径
                    String res = this.getSaveFolder(userName) + commonPath;
                    //数据库保存路径
                    String dbPath = rootHttpPath + userName + commonPath;
                    //2.文件保存操作
                    Thumbnails.of(file.getInputStream()) // 文件流
                            .size(accessory.getWidth(), accessory.getHeight()) //宽高
                            .toFile(res);
                    //3.设置返回值
                    data.setName(originalName);
                    data.setOriginalName(originalName);
                    data.setType(ext);
                    data.setUrl(dbPath);
                    data.setSize(size);
                    data.setState(UMEditorResult.errorInfo.get("SUCCESS"));
                } catch (IOException e) {
                    data.setState(UMEditorResult.errorInfo.get("REQUEST"));
                } catch (BusinessException e) {
                    data.setState(UMEditorResult.errorInfo.get("TYPE"));
                }
            } else {
                data.setState(UMEditorResult.errorInfo.get("SIZE"));
            }
		} else {
			data.setState(UMEditorResult.errorInfo.get("NOFILE"));
        }
		return data;
	}

	/**
	* @Name getPath
	* @Description 获取保存路径
	* @Author wen
	* @Date 2019/5/7
	* @param file
	* @param userName
	* @param prefix
	* @return com.xh.blogs.domain.dos.DPath
	*/
	private DPath getPath(MultipartFile file, String userName, String prefix){
		//文件原名
		String originalName = file.getOriginalFilename();
		//文件后缀
		String ext = ImageUtil.getExt(originalName);
		//公有的路径
		String commonPath = prefix + DateUtil.getDateStr() + ext;
		//保存物理路径
		String res = this.getSaveFolder(userName) + commonPath;
		//数据库保存路径
		String dbPath = rootHttpPath + userName + commonPath;
		return new DPath(res, dbPath);
	}

	/**
     * @Name getSaveFolder
     * @Description 获取保存附件的文件目录
     * @Date 2019年2月16日
     * @Author wen
     * @param userName
     * @return
     */
    private String getSaveFolder(String userName) {
        String path = rootSavePath + userName;
        File file = new File(path);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        return path;
    }

    //	@Override
//	public String uploadThum4Image(MultipartFile file, String userName) throws BusinessException {
//		if(file == null || file.isEmpty()){
//			throw new BusinessException(EmError.FILE_IS_NOT_EXIST);
//		}
//		try {
//			//1.图片处理
//			DArticleAccessory accessory = ImageUtil.handle(file);
//			//2.获取路径
//			DPath path = this.getPath(file, userName, ConfigConst.ARTICLE_IMAGE_PREFIX);
//			//3.文件保存操作
//			Thumbnails.of(file.getInputStream()) // 文件流
//					.size(accessory.getWidth(), accessory.getHeight()) //宽高
//					.toFile(path.getSavePath());
//			return path.getDbPath();
//		} catch (IOException e) {
//			throw new BusinessException(EmError.FILE_WRITE_ERROR);
//		}
//	}

}