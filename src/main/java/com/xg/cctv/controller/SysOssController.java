package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.util.FileUpload;
import com.xg.cctv.common.util.jxls.JxlsEntity;
import com.xg.cctv.common.util.jxls.JxlsMap;
import com.xg.cctv.common.util.jxls.JxlsUtils;
import com.xg.cctv.excel.impl.DailyLogSimpleExcelService;
import com.xg.cctv.exception.RRException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.xg.cctv.mybatis.po.SysOss;
import com.xg.cctv.service.SysOssService;
import com.xg.cctv.common.util.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
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

    @Value("${local.fileserver.dir}")
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
     * 导出excel
     * @param uuid
     * @param response
     * @return
     */
    @RequestMapping("/download/excel/{uuid}")
    public R downloadExcel(@PathVariable("uuid") String uuid,HttpServletResponse response){
        JxlsEntity jxls = JxlsMap.get(uuid);
        if (jxls == null) {
            return R.error("文件不存在");
        }
        // filename = 文件名
        String filename = jxls.getFilename();
        // 模板相对路径
        String templePath = jxls.getTemplatePath();
        // 数据
        Map<String , Object> model = jxls.getModel();
        try {
            OutputStream os = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=" + filename);
            response.setContentType("application/msexcel");
            JxlsUtils.exportExcel(templePath , os , model);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/test")
    public R test(){

        return R.ok().put("key" , new DailyLogSimpleExcelService().exportExcel(new ArrayList<>()));
    }
}
