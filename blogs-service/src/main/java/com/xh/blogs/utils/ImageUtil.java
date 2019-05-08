package com.xh.blogs.utils;

import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.domain.dos.DArticleAccessory;
import com.xh.blogs.domain.vo.AvatarVo;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Name AvatarUtil
 * @Description
 * @Author wen
 * @Date 2019-05-07
 */
public class ImageUtil {

    /**
     * 比例精度
     */
    private static final int PRECISION = 1000 * 1000;

    /**
     * 默认图片边长
     */
    public static final int DEFAULT_IMAGE_SIDE = 300;

    /** 文章图片最大宽度*/
    public static final int MAX_ARTICLE_IMAGE_WIDTH = 700;

    /**
    * @Name handle
    * @Description 处理上传的图片
    * @Author wen
    * @Date 2019/5/7
    * @param file
    * @return com.xh.blogs.domain.vo.ArticleAccessoryVo 
    */
    public static DArticleAccessory handle(MultipartFile file) throws IOException, BusinessException {
        //1.判断是否图片
        BufferedImage image = getImage(file.getInputStream());
        DArticleAccessory accessory = new DArticleAccessory();
        int width = image.getWidth();
        int height = image.getHeight();
        if(width < MAX_ARTICLE_IMAGE_WIDTH){
            accessory.setWidth(width);
            accessory.setHeight(height);
        }else {
            int proportion = MAX_ARTICLE_IMAGE_WIDTH * PRECISION / width;
            accessory.setWidth(MAX_ARTICLE_IMAGE_WIDTH);
            accessory.setHeight(height * proportion / PRECISION);
        }
        return accessory;
    }

    /**
    * @Name handle
    * @Description 处理AvatarVo
    * @Author wen
    * @Date 2019/5/7
    * @param avatarVo
    * @param file
    * @return void
    */
    public static void handle(AvatarVo avatarVo, MultipartFile file) throws IOException, BusinessException {
        //1.判断是否图片
        BufferedImage image = getImage(file.getInputStream());
        int width = image.getWidth();
        if (width >= DEFAULT_IMAGE_SIDE) {
            int proportion = width * PRECISION / DEFAULT_IMAGE_SIDE;
            avatarVo.setX(avatarVo.getX() * proportion / PRECISION);
            avatarVo.setY(avatarVo.getY() * proportion / PRECISION);
            avatarVo.setWidth(avatarVo.getWidth() * proportion / PRECISION);
            avatarVo.setHeight(avatarVo.getHeight() * proportion / PRECISION);
        }
    }

    /**
     * @param input
     * @return boolean
     * @Name checkIsImage
     * @Description 判断是否图片
     * @Author wen
     * @Date 2019/5/7
     */
    public static BufferedImage getImage(InputStream input) throws BusinessException {
        try {
            BufferedImage image = ImageIO.read(input);
            if (image == null) {
                throw new BusinessException(EmError.FILE_IS_NOT_PICTURE);
            }
            return image;
        } catch (IOException ex) {
            throw new BusinessException(EmError.FILE_IS_NOT_PICTURE);
        }
    }

    /**
     * @param originalName
     * @return
     * @Name getExt
     * @Description 获取文件后缀名
     * @Date 2019年2月10日
     * @Author wen
     */
    public static String getExt(String originalName) {
        return originalName.substring(originalName.lastIndexOf(CommonConst.SEPARATOR_POINT), originalName.length());
    }
}
