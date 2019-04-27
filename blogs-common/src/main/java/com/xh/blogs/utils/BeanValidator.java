package com.xh.blogs.utils;

import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @Name BeanValidator
 * @Description
 * @Author wen
 * @Date 2019-04-27
 */
public class BeanValidator {

    /**
     * 验证某个bean的参数
     */
    public static <T> void check(T object) throws BusinessException {
        //获得验证器
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        //执行验证
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);
        //如果有验证信息，则取出来包装成异常返回
        if (CollectionUtils.isEmpty(constraintViolations)) {
            return;
        }
        throw new BusinessException(EmError.PARAMETER_VERIFICATION_ERROR, convertErrorMsg(constraintViolations));
    }

    /**
     * 转换异常信息
     *
     * @param set
     * @param <T>
     * @return
     */
    private static <T> String convertErrorMsg(Set<ConstraintViolation<T>> set) {
        StringBuffer sb = new StringBuffer();
        for (ConstraintViolation<T> cv : set) {
            sb.append(cv.getMessage()).append(CommonConst.SEPARATOR_SEMICOLON);
        }
        return sb.toString();
    }
}
