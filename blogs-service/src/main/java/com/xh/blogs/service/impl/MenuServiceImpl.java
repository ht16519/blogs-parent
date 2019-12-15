package com.xh.blogs.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xh.blogs.service.IMenuService;
import com.xh.blogs.consts.ConfigConst;
import com.xh.blogs.consts.KeyConst;
import com.xh.blogs.dao.mapper.MenuMapper;
import com.xh.blogs.domain.entity.EMenuNode;
import com.xh.blogs.domain.entity.ERoleMenu;
import com.xh.blogs.domain.po.Menu;
import com.xh.blogs.domain.po.Role;
import com.xh.blogs.domain.po.User;
import com.xh.blogs.domain.vo.MenuVo;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.utils.BeanValidator;
import com.xh.blogs.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Name MenuServiceImpl
 * @Description
 * @Author wen
 * @Date 2019-05-04
 */
@Service
@Slf4j
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
    public List<ERoleMenu> getAllMenus() {
        return menuMapper.selectAllMenus();
    }

    @Override
    public Map<Integer, ERoleMenu> getRoleMenuTreeCache() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        if(StringUtils.isEmpty(ops.get(KeyConst.ADMIN_ROLE_MENU_TREE_CACHE_KEY))){
            return this.createRoleMenuTreeCache();
        }else {
            return JsonUtil.parseMap(ops.get(KeyConst.ADMIN_ROLE_MENU_TREE_CACHE_KEY), Integer.class, ERoleMenu.class);
        }
    }

    @Override
    public Map<Integer, ERoleMenu> createRoleMenuTreeCache() {
        log.info("============ START初始化角色菜单关系树 ===========");
        Map<Integer, Set<ERoleMenu>> roleMenuCache = this.createRoleMenuCache();
        Map<Integer, ERoleMenu> roleMenuMap = new HashMap<>();
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        for (Integer key : roleMenuCache.keySet()) {
            Set<ERoleMenu> roleMenus = roleMenuCache.get(key);
            ERoleMenu roleMenu = this.buildRoleMenuTree(roleMenus);
            roleMenuMap.put(key, roleMenu);
        }
        //角色菜单树放入缓存
        ops.set(KeyConst.ADMIN_ROLE_MENU_TREE_CACHE_KEY, JsonUtil.serialize(roleMenuMap));
        log.info("============ END初始化角色菜单关系树成功 ===========");
        return roleMenuMap;
    }

    @Override
    public ERoleMenu buildRoleMenuTree(Set<ERoleMenu> roleMenus) {
        ERoleMenu result = new ERoleMenu();
        Map<Integer, ERoleMenu> map = new HashMap<>();
        for (ERoleMenu roleMenu : roleMenus) {
            map.put(roleMenu.getId(), roleMenu);
        }
        for (ERoleMenu roleMenu : roleMenus) {
            if(roleMenu.getParentId() == 0){
                result = roleMenu;
            }else {
                ERoleMenu roleMenu4Map = map.get(roleMenu.getParentId());
                if(roleMenu4Map.getChilds() == null){
                    roleMenu4Map.setChilds(new ArrayList());
                }
                roleMenu4Map.getChilds().add(roleMenu);
            }
        }
        return result;
    }

    @Override
    public Map<Integer, Set<ERoleMenu>> createRoleMenuCache() {
        log.info("============ START初始化角色菜单缓存 ===========");
        Map<Integer, Set<ERoleMenu>> menuListMap = this.getRoleMenuRoleSetMap();
        log.info("============ END角色菜单缓存初始化成功 ===========");
        return menuListMap;
    }

    @Override
    public Map<Integer, Set<ERoleMenu>> updateRoleMenuCache() {
        log.info("============ START更新角色菜单缓存 ===========");
        Map<Integer, Set<ERoleMenu>> menuListMap = this.getRoleMenuRoleSetMap();
        log.info("============ END更新角色菜单缓存成功 ===========");
        return menuListMap;
    }

    /**
    * @Name getRoleMenuRoleSetMap
    * @Description 获取并设置角色菜单缓存
    * @Author wen
    * @Date 2019/5/26
    * @param
    * @return java.util.Map<java.lang.Integer,java.util.Set<com.xh.blogs.domain.entity.ERoleMenu>>
    */
    private Map<Integer, Set<ERoleMenu>> getRoleMenuRoleSetMap() {
        Map<Integer, Set<ERoleMenu>> menuListMap = new HashMap<>();
        Set<ERoleMenu> menus = this.getAllMenu();
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
        //角色菜单
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

    @Override
    public Menu getById(int id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Menu> getByRoleId(int id) {
        return menuMapper.selectByRoleId(id);
    }

    @Override
    public List<EMenuNode> getNodeByRoleId(int roleId) {
        return menuMapper.selectNodeByRoleId(roleId);
    }

    @Override
    public List<EMenuNode> getNode() {
        return menuMapper.selectNode();
    }

    @Override
    @Transactional
    public int save(MenuVo menuVo, int userId) throws BusinessException {
        //1.参数校验
        BeanValidator.check(menuVo);
        int res;
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVo, menu);
        if(menuVo.getId() == null){
            //新增操作
            //判断菜单等级
            if(menu.getParentId() == null){
                menu.setLevel(2);
            } else {
                Menu dbMenu = menuMapper.selectByPrimaryKey(menu.getParentId());
                if(dbMenu == null){
                    throw new BusinessException(EmError.FAIL);
                }
                menu.setLevel(dbMenu.getLevel() + 1);
            }
            menu.setCreateBy(userId);
            menu.setCreateTime(new Date());
            res = menuMapper.insertSelective(menu);
        } else {
            //修改操作
            menu.setUpdateBy(userId);
            menu.setUpdateTime(new Date());
            res = menuMapper.updateByPrimaryKeySelective(menu);
        }
        if(res <= 0){
            throw new BusinessException(EmError.FAIL);
        }
        return res;
    }

}