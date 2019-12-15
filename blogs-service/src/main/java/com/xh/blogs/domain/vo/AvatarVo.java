package com.xh.blogs.domain.vo;

import com.xh.blogs.utils.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @Name AvatarVo
 * @Description
 * @Author wen
 * @Date 2019-05-05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvatarVo {

    @NotNull(message = "X坐标不能为空")
    @Range(min = 0, max = 220)
    private Float x;

    @NotNull(message = "Y坐标不能为空")
    @Range(min = 0, max = 3000)
    private Float y;

    @NotNull(message = "图片宽度不能为空")
    @Range(min = 100, max = 300)
    private Float width;

    @NotNull(message = "图片高度不能为空")
    @Range(min = 100, max = 300)
    private Float height;

    public int getIX(){
        return CommonUtil.null2Int(x, 1);
    }
    public int getIY(){
        return CommonUtil.null2Int(y, 1);
    }
    public int getIWidth(){
        return CommonUtil.null2Int(width, 100);
    }
    public int getIHeight(){
        return CommonUtil.null2Int(height, 100);
    }

}
