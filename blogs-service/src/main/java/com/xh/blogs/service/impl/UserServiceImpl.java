package com.xh.blogs.service.impl;

import com.xh.blogs.api.IUserService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.ConfigConst;
import com.xh.blogs.consts.NotifyConst;
import com.xh.blogs.consts.SystemConst;
import com.xh.blogs.dao.mapper.NotifyMapper;
import com.xh.blogs.dao.mapper.UserMapper;
import com.xh.blogs.domain.po.User;
import com.xh.blogs.domain.vo.*;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.utils.BeanValidator;
import com.xh.blogs.utils.CommonUtil;
import com.xh.blogs.utils.MD5Util;
import com.xh.blogs.utils.NotifyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Name UserServiceImpl
 * @Description
 * @Author wen
 * @Date 2019-04-22
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private NotifyMapper notifyMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User getByUserName(String userName) {
        User user = new User();
        user.setUserName(userName);
        return userMapper.selectOne(user);
    }

    @Override
    public User getUserInfoByName(String userName) {
        return userMapper.selectUserInfoByName(userName);
    }

    @Override
    @Transactional
    public int register(UserVo userVo) throws BusinessException{
        //1.检查并获取user实体对象
        User user = this.checkAndGetUser(userVo);
        int res = userMapper.insertSelective(user);
        if(res > 0){
            //4.发送注册成功站内信
            NotifyUtil.sendNotify(notifyMapper, NotifyConst.EVENT_REGISTERED_SUCCESSFULLY, SystemConst.SYSTEM_ID, user.getId());
        }
        return res;
    }

    /**
    * @Name checkAndGetUser
    * @Description 检查并获取user实体对象
    * @Author wen
    * @Date 2019/7/19
    * @param userVo
    * @return com.xh.blogs.domain.po.User
    */
    private User checkAndGetUser(UserVo userVo) {
        //1.参数校验
        BeanValidator.check(userVo);
        //2.校验用户信息
        this.checkInputUserInfo(userVo);
        //3.新增用户
        return this.getUser(userVo);
    }

    @Override
    public User updateBasicById(UserBasicVo userBasicVo) throws BusinessException {
        BeanValidator.check(userBasicVo);
        User user = new User();
        BeanUtils.copyProperties(userBasicVo, user);
        user.setUpdateTime(new Date());
        int res = userMapper.updateByPrimaryKeySelective(user);
        if(res <= 0){
            throw new BusinessException(EmError.FAIL);
        }
        return userMapper.selectByPrimaryKey(user.getId());
    }

    @Override
    public int updatePasswordById(UserPasswordVo passwordVo) throws BusinessException {
        //1.参数校验
        BeanValidator.check(passwordVo);
        if(!passwordVo.getPassword().equals(passwordVo.getRePassword())){
            throw new BusinessException(EmError.PASSWORD_MISMATCH);
        }
        //2.校验用户是否存在
        User user = new User();
        user.setUserName(passwordVo.getUserName());
        User dbUser = userMapper.selectOne(user);
        if(dbUser == null){
            throw new BusinessException(EmError.USER_IS_NOT_EXIST);
        }
        //用户被冻结
        if(dbUser.getStatus().equals(CommonConst.INVALID_STATUS)){
            throw new BusinessException(EmError.USER_IS_UNAVAILABLE);
        }
        //校验用户输入是否正确
        user.setPassword(MD5Util.inputPass2DBPass(passwordVo.getOldPassword(), dbUser.getSalt()));
        User dbUser2 = userMapper.selectOne(user);
        if(dbUser2 == null){
            throw new BusinessException(EmError.USER_PASSWORD_ERROR);
        }
        //修改密码
        user.setUserName(null);
        user.setId(passwordVo.getId());
        user.setPassword(MD5Util.inputPass2DBPass(passwordVo.getPassword(), dbUser2.getSalt()));
        user.setUpdateTime(new Date());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User updateAvatarById(int id, String path) throws BusinessException {
        User user = new User();
        user.setId(id);
        user.setAvatar(path);
        user.setUpdateTime(new Date());
        if(userMapper.updateByPrimaryKeySelective(user) <= 0){
            throw new BusinessException(EmError.FAIL);
        }
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    @Deprecated
    public void initSystemAccount() {
        log.info("============ START初始化博客header分类 ===========");
        if(null == userMapper.selectByPrimaryKey(SystemConst.SYSTEM_ID)){
            log.info("============ END系统账户不存在，新建账户，初始化成功！============");
            User user = new User();
            user.setId(SystemConst.SYSTEM_ID);
            user.setUserName(SystemConst.SYSTEM_NAME);
            user.setNickName(SystemConst.SYSTEM_NAME);
            user.setCreateTime(new Date());
            user.setPassword(CommonUtil.getRAS32());
            user.setAvatar(SystemConst.SYSTEM_AVATAR_PATH);
            userMapper.insertSelective(user);
        }else {
            log.info("============ END系统账户已存在，初始化成功！============");
        }
    }

    @Override
    public User activeEmailById(EmailVo emailVo, int id) {
        //1.修改邮箱状态
        User user = new User();
        user.setId(id);
        user.setActiveEmail(CommonConst.EFFECTIVE_STATUS);
        user.setEmail(emailVo.getEmail());
        userMapper.updateByPrimaryKeySelective(user);
        //2.发送邮箱认证成功通知
        NotifyUtil.sendNotify(notifyMapper, NotifyConst.EVENT_EMAIL_ACTIVATION, SystemConst.SYSTEM_ID, user.getId());
        return userMapper.selectByPrimaryKey(user.getId());
    }

    @Override
    public void validationEmail(String email, AccountProfile profile) throws BusinessException {
        if (!CommonUtil.isEmail(email)) {
            throw new BusinessException(EmError.EMAIL_INCORRECT_FORMAT);
        }
        User user = new User();
        user.setEmail(email);
        User dbUser = userMapper.selectOne(user);
        if(dbUser != null){
            //邮箱不是自己的（被注册）
            if(!dbUser.getId().equals(profile.getId())){
                throw new BusinessException(EmError.USER_EMAIL_IS_EXIST);
            }else{
                //自己的邮箱，判断邮箱是否已激活
                if(dbUser.getActiveEmail().equals(CommonConst.EFFECTIVE_STATUS)){
                    throw new BusinessException(EmError.USER_EMAIL_IS_ACTIVE);
                }
            }
        }
    }

    @Override
    @Transactional
    public User doOauthBind(UserVo userVo, String openId) {
        //1.检查并获取user实体对象
        User user = this.checkAndGetUser(userVo);
        user.setQqOpenId(openId);
        //2.修改用户信息
        int res = userMapper.updateByQQOpenId(user);
        if(res > 0){
            //4.发送注册成功站内信
            NotifyUtil.sendNotify(notifyMapper, NotifyConst.EVENT_REGISTERED_SUCCESSFULLY, SystemConst.SYSTEM_ID, user.getId());
            User userInfo = userMapper.selectByUserName(user.getUserName());
            userInfo.setQqOpenId(openId);
            return userInfo;
        }
        return null;
    }

    @Override
    public void checkIsAccess(String userName) throws BusinessException{
        String password = userMapper.selectPasswordByUsername(userName);
        if(StringUtils.isEmpty(password)){
            throw new BusinessException(EmError.USER_UNAUTHORIZED);
        }
    }

    /**
    * @Name setUser
    * @Description 设置基本属性
    * @Author wen
    * @Date 2019/4/23
    * @param userVo
    * @return void
    */
    private User getUser(UserVo userVo) {
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        user.setAvatar(ConfigConst.AVATAR_PATH);
        user.setCreateTime(new Date());
        String salt = CommonUtil.getSalt();
        user.setSalt(salt);
        user.setPassword(MD5Util.inputPass2DBPass(userVo.getPassword(), salt));
        return user;
    }

    /**
    * @Name checkInputUserInfo
    * @Description 校验用户信息
    * @Author wen
    * @Date 2019/4/23
    * @return void
    */
    private void checkInputUserInfo(UserVo userVo) throws BusinessException {
        User user = new User();
        //用户名查重
        user.setUserName(userVo.getUserName());
        List<User> userList = userMapper.select(user);
        if(userList.size() > 0){
            throw new BusinessException(EmError.USER_NAME_IS_EXIST);
        }
        //用户昵称查重
        user = new User();
        user.setNickName(userVo.getNickName());
        userList = userMapper.select(user);
        if(userList.size() > 0){
            throw new BusinessException(EmError.USER_NICK_NAME_IS_EXIST);
        }
        //用户邮箱查重
        user = new User();
        user.setEmail(userVo.getEmail());
        userList = userMapper.select(user);
        if(userList.size() > 0){
            throw new BusinessException(EmError.USER_EMAIL_IS_EXIST);
        }
    }

}
