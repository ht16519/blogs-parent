package com.xh.blogs.service.impl;

import com.xh.blogs.api.IRoleService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.dao.mapper.MenuMapper;
import com.xh.blogs.dao.mapper.RoleMapper;
import com.xh.blogs.dao.mapper.RoleMenuMapper;
import com.xh.blogs.domain.po.Role;
import com.xh.blogs.domain.po.RoleMenu;
import com.xh.blogs.domain.vo.RoleVo;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.utils.BeanValidator;
import com.xh.blogs.utils.CommonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Name RoleServiceImpl
 * @Description
 * @Author wen
 * @Date 2019-05-18
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<Role> getAll() {
        return roleMapper.selectAll();
    }

    @Override
    public Role getById(int id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int update(RoleVo roleVo) throws BusinessException {
        //1.校验参数
        BeanValidator.check(roleVo);
        Role dbRole = roleMapper.selectByPrimaryKey(roleVo.getId());
        if (dbRole == null) {
            throw new BusinessException(EmError.ROLE_INFO_IS_NOT_EXIST);
        }
        //2.修改角色信息
        Role role = new Role();
        BeanUtils.copyProperties(roleVo, role);
        roleMapper.updateByPrimaryKeySelective(role);
        //3.删除原有角色菜单权限
        roleMenuMapper.batchDeleteByRoleId(dbRole.getId());
        //4.组建提交的角色菜单权限
        this.buildRoleMenu(roleVo, dbRole);
        return dbRole.getId();
    }

    @Override
    @Transactional
    public int add(RoleVo roleVo) throws BusinessException {
        //1.校验参数
        BeanValidator.check(roleVo);
        //验证角色是否存在
        Role role = new Role();
        role.setName(roleVo.getName());
        Role dbRole = roleMapper.selectOne(role);
        if(dbRole != null){
            throw new BusinessException(EmError.ROLE_INFO_IS_EXIST);
        }
        //2.不存在，新增
        BeanUtils.copyProperties(roleVo, role);
        roleMapper.insertSelective(role);
        //4.组建提交的角色菜单权限
        this.buildRoleMenu(roleVo, role);
        return role.getId();
    }

    @Override
    @Transactional
    public int delete(int id) {
        //1.删除角色信息
        int res = roleMapper.deleteByPrimaryKey(id);
        if(res > 0){
            //2.删除角色菜单权限
            roleMenuMapper.batchDeleteByRoleId(id);
        }
        return res;
    }

    /**
     * @Name buildRoleMenu
     * @Description 组建提交的角色菜单权限
     * @Author wen
     * @Date 2019/5/21
     * @param roleVo
     * @param dbRole
     * @return void
     */
    private void buildRoleMenu(RoleVo roleVo, Role dbRole) {
        if (CollectionUtils.isNotEmpty(roleVo.getMenus())) {
            List<Integer> idList = menuMapper.checkIds(roleVo.getMenus());
            if (idList.size() > 0) {
                List<RoleMenu> roleMenuList = new ArrayList<>();
                for (int id : idList) {
                    RoleMenu roleMenu = new RoleMenu();
                    roleMenu.setMenuId(id);
                    roleMenu.setRoleId(dbRole.getId());
                    roleMenuList.add(roleMenu);
                }
                roleMenuMapper.insertList(roleMenuList);
            }
        }
    }
}
