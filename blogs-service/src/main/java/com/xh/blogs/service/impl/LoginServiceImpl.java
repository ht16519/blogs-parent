package com.xh.blogs.service.impl;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import com.xh.blogs.api.ILoginService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.KeyConst;
import com.xh.blogs.dao.cache.RedisCacheUtil;
import com.xh.blogs.dao.mapper.UserMapper;
import com.xh.blogs.domain.po.User;
import com.xh.blogs.domain.vo.OAuthUser;
import com.xh.blogs.domain.vo.OAuthUserVo;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.enums.OAuthEnum;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private Oauth oauth;
    @Autowired
    private RedisCacheUtil redisCacheUtil;
    @Autowired
    private UserMapper userMapper;

    @Override
    public String getQQAuthorizeUrl(HttpServletRequest request) throws BusinessException {
        try {
            return oauth.getAuthorizeURL(request);
        } catch (QQConnectException e) {
            throw new BusinessException(EmError.GET_QQ_CONNECT_ERROR);
        }
    }

    @Override
    public OAuthUser qqLoginCallBack(HttpServletRequest request) throws BusinessException {
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
            OAuthUser authUser = new OAuthUser();
            //本地数据库没有该用户信息
            if (user == null) {
                this.setQQUserBaseInfoToLocalUser(accessTokenStr, qqOpenId, authUser);
            } else {
                authUser.setUser(user);
            }
            return authUser;
        } catch (QQConnectException e) {
            throw new BusinessException(EmError.GET_QQ_CONNECT_ERROR);
        }

    }

    /**
     * @param accessTokenStr
     * @param qqOpenId
     * @param authUser
     * @return com.xh.blogs.domain.po.User
     * @Name setQQUserBaseInfoToLocalUser
     * @Description 设置QQ用户基本信息到本地User实体
     * @Author wen
     * @Date 2019/7/10
     */
    private void setQQUserBaseInfoToLocalUser(String accessTokenStr, String qqOpenId, OAuthUser authUser) throws BusinessException, QQConnectException {
        //1.获取QQ用户基本信息
        UserInfo qzoneUserInfo = new UserInfo(accessTokenStr, qqOpenId);
        if (qzoneUserInfo == null) {
            throw new BusinessException(EmError.GET_QQ_ACCESS_TOKEN_FAIL);
        }
        UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
        OAuthUserVo authUserVo = new OAuthUserVo();
        authUserVo.setNickName(userInfoBean.getNickname())
                .setAvatar(userInfoBean.getAvatar().getAvatarURL100())
                .setSex(userInfoBean.getGender().equals(CommonConst.BLOGS_SEX_MAN) ? 1 : 0)
                .setType(OAuthEnum.QQ.getCode()).setOpenId(qqOpenId);
        //2.生成32位随机key，将信息存入redis
        String authToken = CommonUtil.getRAS32();
        authUser.setToken(authToken);
        redisCacheUtil.set(authToken, authUserVo);
    }

}
