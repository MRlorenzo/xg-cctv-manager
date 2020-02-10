package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.StatusCode;
import com.xg.cctv.common.dto.SysUserVo;
import com.xg.cctv.common.util.ShiroUtils;
import com.xg.cctv.exception.RRException;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
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
@RestController
@Validated
@RequestMapping("/sysUser")
public class SysUserController {
    @Autowired
    public SysUserService iSysUserService;

    @GetMapping("/find/{username}")
    public R find(@PathVariable("username") String username){
        return R.ok().put("data" , iSysUserService.selectByUsername(username));
    }

    @GetMapping("/like/{username}")
    public R like(@PathVariable("username") String username){
        return R.ok().put("data" , iSysUserService.selectListByLikeName(username));
    }

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
    // @RequiresPermissions("sysuser:info")
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
    /*@GetMapping("/page")
    public R getSysUserList(Page<SysUserVo> page, SysUser sysUser){
        return R.ok().put("data" , iSysUserService.selectPage(page, sysUser));
    }*/

    @GetMapping("/page")
    public R getSysUserList(Page<SysUserVo> page , Map<String , Object> params){
        return R.ok().put("data" , iSysUserService.selectVoPage(page , params ));
    }

    /**
     * 保存和修改公用的
     * @param user 传递的实体
     * @return R
     */
    @PostMapping("/save")
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
    public R deleteBatchIds(@RequestBody Map<String,List<String>> requestMap){
        List<String> ids = requestMap.get("ids");
        boolean rs = iSysUserService.removeByIds(ids);
        if (rs){
            return R.ok().put("data" , ids.size());
        }
        return R.error();
    }
}
