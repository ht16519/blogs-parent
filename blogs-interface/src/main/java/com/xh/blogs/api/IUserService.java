package com.xh.blogs.api;


import com.xh.blogs.domain.po.User;
import com.xh.blogs.domain.vo.*;
import com.xh.blogs.exception.BusinessException;

/**
 * @Name IUserService
 * @Description
 * @Author wen
 * @Date 2019-04-22
 */
public interface IUserService {

    /**
    * @Name getById获取用户信息
    * @Description 通过用户id
    * @Author wen
    * @Date 2019/4/22
    * @param id
    * @return com.xh.blogs.po.User
    */
    User getById(Integer id);

    /**
    * @Name getByUserName
    * @Description 根据用户名获取用户信息
    * @Author wen
    * @Date 2019/4/22
    * @param userName
    * @return com.xh.blogs.po.User 
    */
    User getByUserName(String userName);

    /**
    * @Name getUserInfoByName
    * @Description 根据用户名获取用户信息（角色加权限）
    * @Author wen
    * @Date 2019/4/23
    * @param userName
    * @return com.xh.blogs.domain.po.User
    */
    User getUserInfoByName(String userName);

    /**
    * @Name register
    * @Description 用户注册
    * @Author wen
    * @Date 2019/4/23
    * @param userVo
    * @return int
    */
    int register(UserVo userVo) throws BusinessException;

    /**
    * @Name updateById
    * @Description 修改用户信息
    * @Author wen
    * @Date 2019/5/4
    * @param userBasicVo
    * @return int
    */
    User updateBasicById(UserBasicVo userBasicVo) throws BusinessException;

    /**
    * @Name updatePasswordById
    * @Description 修改用户迷你
    * @Author wen
    * @Date 2019/5/4
    * @param passwordVo
    * @return int
    */
    int updatePasswordById(UserPasswordVo passwordVo) throws BusinessException;

    /**
    * @Name updateAvatarById
    * @Description 更新用户头像路径
    * @Author wen
    * @Date 2019/5/7
    * @param id
    * @param path
    * @return com.xh.blogs.domain.po.User
    */
    User updateAvatarById(int id, String path) throws BusinessException;

    /**
    * @Name initSystemAccount
    * @Description 初始化系统账户
    * @Author wen
    * @Date 2019/5/12
    * @param 
    * @return void 
    */
    @Deprecated
    void initSystemAccount();

    /**
    * @Name activeEmailById
    * @Description 修改邮箱活跃
    * @Author wen
    * @Date 2019/5/13
    * @param emailVo
     * @param id
     * @return User
    */
    User activeEmailById(EmailVo emailVo, int id);

    /**
    * @Name validationEmail
    * @Description 验证邮箱信息
    * @Author wen
    * @Date 2019/5/13
    * @param email
    * @param profile
    * @return void
    */
    void validationEmail(String email, AccountProfile profile) throws BusinessException;

    /**
    * @Name doOauthBind
    * @Description 绑定操作
    * @Author wen
    * @Date 2019/7/19
    * @param userVo
    * @return user
    */
    User doOauthBind(UserVo userVo, String openId);

    /**
    * @Name checkIsAccess
    * @Description 校验用户是否可以访问
    * @Author wen
    * @Date 2019/7/19
    * @param userName
    * @return void
    */
    void checkIsAccess(String userName);
}
