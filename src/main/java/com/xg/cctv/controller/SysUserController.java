package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.StatusCode;
import com.xg.cctv.common.dto.SysUserVo;
import com.xg.cctv.common.util.ShiroUtils;
import com.xg.cctv.exception.RRException;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.xg.cctv.mybatis.po.SysUser;
import com.xg.cctv.service.SysUserService;
import com.xg.cctv.common.util.R;

import org.springframework.beans.factory.annotation.Autowired;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lorenzo
 * @since 2020-01-24
 */
// @Api  注解可以用来标记 Controller 的功能
@Api(value = "SysUserController", description = "用户相关")
@RestController
@Validated
@RequestMapping("/sysUser")
public class SysUserController {
    @Autowired
    public SysUserService iSysUserService;

    @GetMapping("/find/{username}")
    @RequiresPermissions("sysuser:search")
    @ApiImplicitParam(name = "username", value = "用户名", required = false )
    @ApiOperation(value="精确查找用户信息", notes="精确查找用户信息接口" , httpMethod = "GET" , response = R.class)
    public R find(@PathVariable("username") String username){
        return R.ok().put("data" , iSysUserService.selectByUsername(username));
    }

    @GetMapping("/like/{username}")
    @RequiresPermissions("sysuser:search")
    @ApiImplicitParam(name = "username", value = "用户名", required = false )
    @ApiOperation(value="模糊匹配用户信息，根据用户名", notes="模糊匹配用户信息接口" , httpMethod = "GET" , response = R.class)
    public R like(@PathVariable("username") String username){
        return R.ok().put("data" , iSysUserService.selectListByLikeName(username));
    }

    // @ApiOperation 注解用来标记一个方法的作用
    @ApiOperation(value="用户登录", notes="用户登录接口" , httpMethod = "POST" , response = R.class)
    @ApiImplicitParam(name = "params" , paramType = "body")
    @PostMapping("/login")
    public R login(@RequestBody Map<String , String> params) throws AuthenticationException {

        Subject subject = SecurityUtils.getSubject();
        String username = params.getOrDefault("username", "");
        String password = params.getOrDefault("password", "");
        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken(username, password);

        try {
            subject.login(usernamePasswordToken);
        } catch (IncorrectCredentialsException e){
            throw new RRException("账号或者密码错误", StatusCode.USER_LOGIN_ERROR);
        } catch (AuthenticationException e){
            throw new RRException(e.getMessage() ,  StatusCode.USER_LOGIN_ERROR);
        }

        return R.ok().put("msg","登录成功").put("data", subject.getSession().getId());
    }

    @GetMapping("/info")
    @ApiOperation(value="获取用户信息", notes="用户信息接口" , httpMethod = "GET" , response = R.class)
    @RequiresPermissions("sysuser:info")
    public R info(){
        return R.ok().put("data" , ShiroUtils.getUserEntity());
    }

    @GetMapping("/logout")
    public R logout(){

        Subject subject = SecurityUtils.getSubject();

        if(subject.getPrincipals() != null ) {
            subject.logout();
        }

        return R.ok();
    }

    /**
     * 分页查询数据
     *
     * @param page  分页信息
     * @return
     */
    @GetMapping("/page")
    @RequiresPermissions("sysuser:list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", required = false),
            @ApiImplicitParam(name = "size", value = "每页显示条数，默认 10", required = false )
    })
    @ApiOperation(value="获取用户信息分页", notes="用户信息分页接口" , httpMethod = "GET" , response = R.class)
    public R getSysUserAllInfoPage(Page<SysUser> page ,@RequestParam Map<String , Object> params){
        return R.ok().put("data" , iSysUserService.selectAllInfoPage(page , params ));
    }

    /**
     * 保存和修改公用的
     * @param user 传递的实体
     * @return R
     */
    @PostMapping("/save")
    @RequiresPermissions("sysuser:save")
    @ApiOperation(value="保存用户", notes="保存用户接口" , httpMethod = "POST" , response = R.class)
    public R sysUserSave(@RequestBody @Valid SysUser user){
        if (user == null || !StringUtils.isNotEmpty(user.getPassword())){
            return R.error();
        }

        SimpleHash pawd = new Sha256Hash(user.getPassword());
        pawd.setIterations(1);
        user.setPassword(pawd.toHex());
        boolean rs = iSysUserService.saveOrUpdate(user);
        if (rs){
            return R.ok();
        }
        return R.error();
    }

    /**
     * 根据id删除对象
     * @param id  实体ID
     * @return R
     */
    @PostMapping("/delete/{id}")
    @RequiresPermissions("sysuser:delete")
    @ApiImplicitParam(name = "id", value = "用户id", required = true )
    @ApiOperation(value="根据id删除对象", notes="根据id删除对象接口" , httpMethod = "POST" , response = R.class)
    public R sysUserDelete(@PathVariable String id){
        boolean rs = iSysUserService.removeById(id);
        if (rs) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 批量删除对象
     * @param requestMap 实体集合ID
     * @return R
     */
    @PostMapping("/batchDelete")
    @RequiresPermissions("sysuser:delete")
    @ApiImplicitParam(name = "ids", value = "用户ids", required = true )
    @ApiOperation(value="批量删除对象", notes="批量删除对象接口" , httpMethod = "POST" , response = R.class)
    public R deleteBatchIds(@RequestBody Map<String,List<String>> requestMap){
        List<String> ids = requestMap.get("ids");
        boolean rs = iSysUserService.removeByIds(ids);
        if (rs){
            return R.ok().put("data" , ids.size());
        }
        return R.error();
    }
}
