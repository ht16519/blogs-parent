package com.xh.blogs.controller.base;

import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.HttpConst;
import com.xh.blogs.domain.po.User;
import com.xh.blogs.domain.vo.AccountProfile;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.error.CommomError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.utils.ShiroUtil;
import com.xh.blogs.utils.StringEscapeEditor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @Name BaseController
* @Description
* @Author wen
* @Date 2019/4/23
*/
@Slf4j
public class BaseController {

	protected static final String DATA_KEY = "data";

	protected static final String SUCCESSED_MESSAGE = "操作成功";

	@Autowired
	protected HttpSession session;

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));

		/**
		 * 防止XSS攻击
		 */
		binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
	}

	/**
	* @Name putProfile
	* @Description 设置用户基础信息到shiro的session中
	* @Author wen
	* @Date 2019/4/24
	* @param model
	* @return void
	*/
	protected void putProfile(ModelMap model) {
		try {
			this.putProfile(ShiroUtil.getUser());
		} catch (Exception e) {
			this.getModelMap(EmError.USER_AUTHENTICATION_FAILED, model);
		}
	}

	protected void putProfile(User user) {
		AccountProfile profile = new AccountProfile();
		BeanUtils.copyProperties(user, profile);
		ShiroUtil.sessionSetValue(CommonConst.SYSTEM_PROFILE, profile);
	}

	/**
	* @Name getProfile
	* @Description 从shiro的session中获取用户基础信息
	* @Author wen
	* @Date 2019/4/24
	* @return com.xh.blogs.domain.vo.AccountProfile
	*/
	protected AccountProfile getProfile() throws BusinessException {
		AccountProfile profile = (AccountProfile) ShiroUtil.sessionGetValue(CommonConst.SYSTEM_PROFILE);
		if(profile == null){
			throw new BusinessException(EmError.USER_NOT_LOGGED_IN);
		}
		return profile;
	}

	protected ModelMap getModelMap(CommomError commomError, ModelMap model){
		model.put(HttpConst.HTTP_RESPONSE_CODE_KEY, commomError.getErrCode());
		model.put(HttpConst.HTTP_RESPONSE_MSG_KEY, commomError.getErrMsg());
		return model;
	}

	protected ModelMap getModelMap(ModelMap model){
		return this.getModelMap(SUCCESSED_MESSAGE, model);
	}

	protected ModelMap getModelMap(String msg, ModelMap model){
		return this.getModelMap(null, msg, model);
	}

	protected ModelMap getModelMap(Object data, String msg, ModelMap model){
		model.put(HttpConst.HTTP_RESPONSE_CODE_KEY, 0);
		model.put(HttpConst.HTTP_RESPONSE_MSG_KEY, msg);
		if(data != null){
			model.put(DATA_KEY, data);
		}
		return model;
	}

}
