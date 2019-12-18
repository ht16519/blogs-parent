package com.xh.blogs.service.impl;

import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.KeyConst;
import com.xh.blogs.consts.NotifyConst;
import com.xh.blogs.consts.SystemConst;
import com.xh.blogs.dao.mapper.NotifyMapper;
import com.xh.blogs.dao.mapper.UserMapper;
import com.xh.blogs.domain.po.User;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.exception.LoginException;
import com.xh.blogs.service.IOAuth2Service;
import com.xh.blogs.utils.CommonUtil;
import com.xh.blogs.utils.JsonUtil;
import com.xh.blogs.utils.NotifyUtil;
import com.xh.blogs.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Name LoginServiceImpl
 * @Description
 * @Author wen
 * @Date 2019-07-10
 */
@Service
@Slf4j
public class OAuth2ServiceImpl implements IOAuth2Service {

    @Autowired
    private AuthRequest authQqRequest;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NotifyMapper notifyMapper;

    @Override
    public String getQQAuthorizeUrl(HttpServletRequest request) throws LoginException {
        return authQqRequest.authorize();
    }

    @Override
    @Transactional
    public User getOAuthUserByQQAPI(AuthCallback authCallback) throws BusinessException {
        //1.QQ授权
        AuthResponse<AuthUser> response = authQqRequest.login(authCallback);
        log.info("【QQ授权】授权返回信息：{}", JsonUtil.serialize(response));
        if (response == null || !response.ok()) {
            throw new BusinessException(EmError.GET_QQ_ACCESS_TOKEN_FAIL);
        }
        AuthUser data = response.getData();
        if (data == null) {
            throw new BusinessException(EmError.GET_QQ_ACCESS_TOKEN_FAIL);
        }
        //2.通过返回的openid查看本地数据是否有对应用户openID
        Map<String, String> openIdMap = new HashMap<>();
        openIdMap.put(KeyConst.QQ_OPEN_ID_KEY, data.getToken().getOpenId());
        User user = userMapper.selectUserInfoByOpenId(openIdMap);
        //3.本地数据库没有该用户信息，保存用户信息到本地数据库
        if (user == null) {
            return this.saveQQUserBaseInfoToLocalDB(data);
        }
        return user;
    }

    /**
     * @param data
     * @return User
     * @Name saveQQUserBaseInfoToLocalDB
     * @Description 设置QQ用户基本信息到本地User实体
     * @Author wen
     * @Date 2019/7/10
     */
    private User saveQQUserBaseInfoToLocalDB(AuthUser data) throws BusinessException {
        //1.保存QQ用户基本信息
        User user = new User();
        user.setNickName(this.checkAndHandleNickName(data.getNickname()));  //昵称查重并保存
        user.setAvatar(data.getAvatar());
        user.setSex(data.getGender().getCode());
        user.setQqOpenId(data.getToken().getOpenId());
        user.setUserName(StringUtil.getUUID16());      //生成16位的账号
        user.setPassword(CommonConst.EMPTY_STRING);
        user.setCreateTime(new Date());
        if (userMapper.insertSelective(user) > 0) {
            //3.发送认证成功站内信
            NotifyUtil.sendNotify(notifyMapper, NotifyConst.EVENT_REGISTERED_SUCCESSFULLY2, SystemConst.SYSTEM_ID, user.getId());
            return user;
        }
        return null;
    }

    /**
     * @param nickname
     * @return
     * @Name checkAndHandleNickName
     * @Description 昵称查重并处理
     * @Author wen
     * @Date 2019/7/19
     */
    private String checkAndHandleNickName(String nickname) {
        User user = new User();
        user.setNickName(nickname);
        //昵称重复
        if (userMapper.select(user).size() > 0) {
            //可根据需求生成昵称
            return nickname + CommonUtil.getSalt();
        }
        return nickname;
    }


}
