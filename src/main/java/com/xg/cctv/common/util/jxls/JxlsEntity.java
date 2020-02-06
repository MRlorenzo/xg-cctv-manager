package com.xg.cctv.common.util.jxls;

import java.io.Serializable;
import java.util.Map;

/**
 * 导出excel缓存模板
 * @author IT1
 *
 */
public class JxlsEntity implements Serializable{
	
	private String templatePath;
	
	private Map<String, Object> model;
	
	private String filename;
	
	public JxlsEntity(String templatePath, Map<String, Object> model, String filename) {
		this.templatePath = templatePath;
		this.model = model;
		this.filename = filename;
	}

	public String getTemplatePath() {
		return templatePath;
	}
	
	public Map<String, Object> getModel() {
		return model;
	}
	
	public String getFilename() {
		return filename;
	}

}
