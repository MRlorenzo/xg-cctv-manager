package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.util.FileUpload;
import com.xg.cctv.exception.RRException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.xg.cctv.mybatis.po.SysOss;
import com.xg.cctv.service.SysOssService;
import com.xg.cctv.common.util.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;


import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lorenzo
 * @since 2020-02-04
 */
@RestController
@RequestMapping("/sysOss")
public class SysOssController {
    @Autowired
    public SysOssService iSysOssService;

    @Value("${upload.path}")
    private String basePath;

    /**
     * 分页查询数据
     *
     * @param page  分页信息
     * @param sysOss 查询条件
     * @return
     */
    @GetMapping("/page")
    public R getSysOssList(Page<SysOss> page,SysOss sysOss){
        return R.ok().put("data" , iSysOssService.selectPage(page, sysOss));
    }

    /**
     * 本地上传文件
     */
    @RequestMapping("/upload")
    /*@RequiresPermissions("sys:oss:all")*/
    public R localUpload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("UploadFilesNotEmpty");
        }

        //上传文件
        String url = FileUpload.upload(basePath , file.getBytes(), file.getOriginalFilename());
        if(StringUtils.isEmpty(url)){
            return R.error();
        }

        //保存文件信息
        SysOss ossEntity = new SysOss();
        ossEntity.setUrl(url);
        ossEntity.setCreateDate(new Date());
        iSysOssService.save(ossEntity);

        return R.ok().put("url", url);
    }

    /**
     * 根据id删除对象
     * @param id  实体ID
     * @return R
     */
    @PostMapping("/delete/{id}")
    public R sysOssDelete(@PathVariable String id){
        boolean rs = iSysOssService.removeById(id);
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
        boolean rs = iSysOssService.removeByIds(ids);
        if (rs){
            return R.ok().put("data" , ids.size());
        }
        return R.error();
    }
}
