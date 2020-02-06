package com.xg.cctv.common.util.jxls;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.util.JxlsHelper;

import java.io.*;
import java.net.URL;
import java.util.Map;

public class JxlsUtils {

    public static void exportExcel(InputStream is, OutputStream os, Map<String, Object> model) throws IOException {
        Context context = PoiTransformer.createInitialContext();
        if (model != null) {
            for (Map.Entry<String, Object> entry: model.entrySet()) {
                context.putVar(entry.getKey(), entry.getValue());
            }
        }
        JxlsHelper jxlsHelper = JxlsHelper.getInstance();
        Transformer transformer = jxlsHelper.createTransformer(is, os);

        // 必须要这个，否者表格函数统计会错乱
        jxlsHelper
                .setUseFastFormulaProcessor(false)
                .processTemplate(context, transformer);
    }

    public static void exportExcel(String templatePath, OutputStream os, Map<String, Object> model) throws Exception {
        File template = getTemplate(templatePath);
        if (template != null) {
            exportExcel(new FileInputStream(template), os, model);
        } else {
            throw new Exception("Excel 模板未找到。");
        }
    }

    // 获取jxls模版文件
    public static File getTemplate(String path) {
        File template = new File(path);
        if (template.exists()) {
            return template;
        } else {
            URL url = JxlsUtils.class.getResource(path);
            if (url != null) {
                template = new File(url.getPath());
                if (template.exists()) {
                    return template;
                }
            }
        }
        return null;
    }


}
