package com.xh.blogs.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xh.blogs.api.IMenuService;
import com.xh.blogs.consts.ConfigConst;
import com.xh.blogs.dao.mapper.MenuMapper;
import com.xh.blogs.domain.entity.ERoleMenu;
import com.xh.blogs.domain.po.Role;
import com.xh.blogs.domain.po.User;
import com.xh.blogs.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Name MenuServiceImpl
 * @Description
 * @Author wen
 * @Date 2019-05-04
 */
@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Set<ERoleMenu> getAllMenu() {
        return menuMapper.selectAllMenu();
    }

    @Override
    public Map<Integer, Set<ERoleMenu>> createRoleMenuCache() {
        Map<Integer, Set<ERoleMenu>> menuListMap = new HashMap<>();
        Set<ERoleMenu> menus = menuMapper.selectAllMenu();
        Set<ERoleMenu> menuList;
        for (ERoleMenu menu : menus) {
            int roleId = menu.getRoleId();
            if ((menuList = menuListMap.get(roleId)) == null) {
                menuList = new HashSet<>();
                menuListMap.put(roleId, menuList);
            }
            menuList.add(menu);
        }
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(ConfigConst.ADMIN_ROLE_MENU_CACHE_KEY, JsonUtil.serialize(menuListMap));
        return menuListMap;
    }

    @Override
    public Map<Integer, Set<ERoleMenu>> getRoleMenu() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String roleMeunObject = ops.get(ConfigConst.ADMIN_ROLE_MENU_CACHE_KEY);
        if (StringUtils.isEmpty(roleMeunObject)) {
            return this.createRoleMenuCache();
        } else {
            return JsonUtil.nativeRead(roleMeunObject, new TypeReference<Map<Integer, Set<ERoleMenu>>>(){});
        }
    }

    @Override
    public Set<ERoleMenu> getUserMenuCache(User user) {
        Map<Integer, Set<ERoleMenu>> roleMenuMap = this.getRoleMenu();
        Set<ERoleMenu> menus = new HashSet<>();
        if (roleMenuMap.size() > 0) {
            Set<Role> roles = user.getRoles();
            for (Role role : roles) {
                if (null != roleMenuMap.get(role.getId())) {
                    menus.addAll(roleMenuMap.get(role.getId()));
                }
            }
        }
        return menus;
    }

}