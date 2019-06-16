package com.xh.blogs.domain.vo;

import com.xh.blogs.error.CommomError;
import lombok.Data;

/**
 * @Name ExceptionResult
 * @Description 全局异常返回结果
 * @Author wen
 * @Date 2019-03-30
 */
@Data
public class ExceptionResult {

    private int code;

    private String msg;

    private long timestamp;

    public ExceptionResult(CommomError commomError) {
        this.code = commomError.getErrCode();
        this.msg = commomError.getErrMsg();
        this.timestamp = System.currentTimeMillis();
    }

}
