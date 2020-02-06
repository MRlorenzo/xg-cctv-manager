package com.xg.cctv.common.util.jxls;

import com.xg.cctv.common.util.SpringContextUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public final class JxlsMap implements Serializable{

	private final static String LAST_FILE_NAME_XLS = ".xls";

	private final static String LAST_FILE_NAME_XLSX = ".xlsx";

	private static RedisTemplate redisTemplate;

	static {
		redisTemplate = SpringContextUtils.getBean("redisTemplate" , RedisTemplate.class);
	}

	private JxlsMap() {
	}

	/**
	 * 临时存放需要导出的数据
	 * @param value
	 * @return
	 */
	private static String put(JxlsEntity value) {
		String key = getKey();


		ValueOperations valueOperations = redisTemplate.opsForValue();

		while (valueOperations.get(key)!=null){
			key = getKey();
		}

		valueOperations.set(key, value, 10, TimeUnit.MINUTES);
		return key;
	}

	private static String getKey() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 获取需要打印的数据
	 * @param key
	 * @return
	 */
	public static JxlsEntity get(String key) {
		ValueOperations valueOperations = redisTemplate.opsForValue();
		JxlsEntity obj = (JxlsEntity) valueOperations.get(key);
		redisTemplate.delete(key);
		return obj;
	}

	/**
	 * 临时存放需要打印的数据
	 * @param templePath 模板路径
	 * @param model	需要导出的数据
	 * @return
	 */
	public static String xls(String templePath , Map<String , Object> model ){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		String fileName = dateFormat.format(new Date()) + LAST_FILE_NAME_XLS;

		return put(templePath , model , fileName);
	}

	/**
	 * 临时存放需要打印的数据
	 * @param templePath 模板路径
	 * @param model	需要导出的数据
	 * @return
	 */
	public static String xlsx(String templePath , Map<String , Object> model ){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		String fileName = dateFormat.format(new Date()) + LAST_FILE_NAME_XLSX;

		return put(templePath , model , fileName);
	}

	public static String put(String templePath , Map<String , Object> model , String filename){
		return put(new JxlsEntity(templePath , model , filename));
	}
}
