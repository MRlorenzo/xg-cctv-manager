package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.util.FileUpload;
import com.xg.cctv.common.util.jxls.JxlsEntity;
import com.xg.cctv.common.util.jxls.JxlsMap;
import com.xg.cctv.common.util.jxls.JxlsUtils;
import com.xg.cctv.excel.impl.DailyLogExcelService;
import com.xg.cctv.exception.RRException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "SysOssController", description = "Oss相关")
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", required = false),
            @ApiImplicitParam(name = "size", value = "每页显示条数，默认 10", required = false )
    })
    @ApiOperation(value="获取信息分页", notes="信息分页接口" , httpMethod = "GET" , response = R.class)
    public R getSysOssList(Page<SysOss> page,SysOss sysOss){
        return R.ok().put("data" , iSysOssService.selectPage(page, sysOss));
    }

    /**
     * 本地上传文件
     */
    @PostMapping("/upload")
    @ApiImplicitParam(name = "file", value = "文件", required = true)
    @ApiOperation(value="上传文件", notes="上传文件接口" , httpMethod = "POST" , response = R.class)
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
    @GetMapping("/download/excel/{uuid}")
    @ApiImplicitParam(paramType = "path", name = "uuid", value = "redis中的key,将会得到excel的模板路径，文件名称，待写入的数据等", required = true)
    @ApiOperation(value="下载excel文件", notes="下载excel文件接口" , httpMethod = "GET" , response = JxlsEntity.class)
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

    @GetMapping("/test")
    @ApiOperation(value="测试导出excel文件功能", notes="测试接口" , httpMethod = "GET" , response = R.class)
    public R test(){

        return R.ok().put("key" , new DailyLogExcelService().exportExcel(new ArrayList<>()));
    }
}
