package com.xh.blogs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @Name UMEditorResult
 * @Description
 * @Author wen
 * @Date 2019-05-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class UMEditorResult implements Serializable{

    public static HashMap<String, String> errorInfo = new HashMap<>();

    static {
        try {
            errorInfo.put("SUCCESS", "SUCCESS"); //默认成功
            errorInfo.put("NOFILE", "请选择要上传的图片"); //无图片
            errorInfo.put("TYPE", URLEncoder.encode("不允许的文件格式", "UTF-8"));
            errorInfo.put("SIZE", URLEncoder.encode("文件大小超出限制，最大支持1Mb", "UTF-8"));
            errorInfo.put("REQUEST", URLEncoder.encode("上传请求异常", "UTF-8"));
            errorInfo.put("UNKNOWN", URLEncoder.encode("未知错误", "UTF-8"));
        } catch (Exception e) {
            log.error("UMEditor 图片上传异常:", e);
        }
    }

    private String name;

    private String originalName;

    private long size;

    private String state;

    private String type;

    private String url;

}
