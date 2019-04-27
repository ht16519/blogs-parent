package com.xh.blogs.service.impl;

import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.dao.mapper.UserMapper;
import com.xh.blogs.domain.po.User;
import com.xh.blogs.domain.vo.UserVo;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.service.IUserService;
import com.xh.blogs.utils.BeanValidator;
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
public class UserServiceImpl implements IUserService {

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
        //1.参数校验
        BeanValidator.check(userVo);
        //2.校验用户信息
        this.checkInputUserInfo(userVo);
        //3.设置基本属性
        //TODO 密码加密
        return userMapper.insertSelective(this.getUser(userVo));
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
        user.setAvatar(CommonConst.AVATAR);
        user.setCreateTime(new Date());
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
