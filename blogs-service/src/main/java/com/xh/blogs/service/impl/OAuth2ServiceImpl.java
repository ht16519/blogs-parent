package com.xh.blogs.service.impl;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import com.xh.blogs.service.IOAuth2Service;
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
import com.xh.blogs.utils.CommonUtil;
import com.xh.blogs.utils.MD5Util;
import com.xh.blogs.utils.NotifyUtil;
import com.xh.blogs.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
    private Oauth oauth;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NotifyMapper notifyMapper;

    @Override
    public String getQQAuthorizeUrl(HttpServletRequest request) throws LoginException {
        try {
            return oauth.getAuthorizeURL(request);
        } catch (QQConnectException e) {
            throw new LoginException(EmError.GET_QQ_CONNECT_ERROR);
        }
    }

    @Override
    @Transactional
    public User getOAuthUserByQQAPI(HttpServletRequest request) throws BusinessException {
        try {
            //1.获取accessTkoen
            AccessToken accessToken = oauth.getAccessTokenByRequest(request);
            if (accessToken == null) {
                throw new BusinessException(EmError.GET_QQ_ACCESS_TOKEN_FAIL);
            }
            String accessTokenStr = accessToken.getAccessToken();
            //2.获取openID
            OpenID openID = new OpenID(accessTokenStr);
            if (openID == null) {
                throw new BusinessException(EmError.GET_QQ_CONNECT_ERROR);
            }
            String qqOpenId = openID.getUserOpenID();
            if (StringUtils.isEmpty(qqOpenId)) {
                throw new BusinessException(EmError.GET_QQ_ACCESS_TOKEN_FAIL);
            }
            //3.查看本地数据是否有对应用户openID
            Map<String, String> openIdMap = new HashMap<>();
            openIdMap.put(KeyConst.QQ_OPEN_ID_KEY, qqOpenId);
            User user = userMapper.selectUserInfoByOpenId(openIdMap);
            //本地数据库没有该用户信息
            if (user == null) {
                return this.saveQQUserBaseInfoToLocalDB(accessTokenStr, qqOpenId);
            }
            return user;
        } catch (QQConnectException e) {
            throw new BusinessException(EmError.GET_QQ_CONNECT_ERROR);
        }
    }

    /**
     * @param accessTokenStr
     * @param qqOpenId
     * @return User
     * @Name saveQQUserBaseInfoToLocalDB
     * @Description 设置QQ用户基本信息到本地User实体
     * @Author wen
     * @Date 2019/7/10
     */
    private User saveQQUserBaseInfoToLocalDB(String accessTokenStr, String qqOpenId) throws BusinessException, QQConnectException {
        //1.获取QQ用户基本信息
        UserInfo qzoneUserInfo = new UserInfo(accessTokenStr, qqOpenId);
        if (qzoneUserInfo == null) {
            throw new BusinessException(EmError.GET_QQ_ACCESS_TOKEN_FAIL);
        }
        UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
        //2-1.昵称查重并处理
        String nickname = userInfoBean.getNickname();
        this.checkAndHandleNickName(nickname);
        //2-2.存入本地数据库
        User user = new User();
        user.setNickName(nickname);
        user.setAvatar(userInfoBean.getAvatar().getAvatarURL100());
        user.setSex(userInfoBean.getGender().equals(CommonConst.BLOGS_SEX_MAN) ? 1 : 0);
        user.setQqOpenId(qqOpenId);
        String uuid16 = StringUtil.getUUID16();
        user.setUserName(uuid16);      //生成16位的账号
        user.setPassword(CommonConst.EMPTY_STRING);
        user.setCreateTime(new Date());
        if(userMapper.insertSelective(user) > 0 ){
            //3.发送认证成功站内信
            NotifyUtil.sendNotify(notifyMapper, NotifyConst.EVENT_REGISTERED_SUCCESSFULLY2, SystemConst.SYSTEM_ID, user.getId());
            return user;
        }
        return null;
    }

    /**
    * @Name checkAndHandleNickName
    * @Description 昵称查重并处理
    * @Author wen
    * @Date 2019/7/19
    * @param nickname
    * @return
    */
    private void checkAndHandleNickName(String nickname) {
        User user = new User();
        user.setNickName(nickname);
        //昵称重复
        List<User> list = userMapper.select(user);
        if(list.size() > 0){
            //TODO 可根据需求生成昵称
            nickname += CommonUtil.getSalt();
        }
    }


}
