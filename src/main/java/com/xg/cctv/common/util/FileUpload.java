package com.xg.cctv.common.util;
import org.springframework.util.StringUtils;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * 文件上传，上传到本地
 *
 */
public final class FileUpload {

	private final static byte[] EMPTY_BYTE =  new byte[0];

	/**
	 * 本地路径
	 */
	public static String LOCAL_PATH = "D:\\TEMP";

	/**
	 * 上传
	 *
	 * @param data 上传的文件字节码
	 * @param filename 文件名
	 */
	public static String upload(byte[] data, String filename) {
		String path;
		FileImageOutputStream out = null;
		try {
			// 文件后缀
			String suffix = getFileSuffix(filename);

			// 日期目录
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String catalog = sdf.format(new Date());

			// uuid文件名
			String fname = UUID.randomUUID().toString().replaceAll("-", "");
			File catalogFile = new File(LOCAL_PATH + File.separator + catalog);
			if (!catalogFile.exists()) {
				catalogFile.mkdir();
			}
			path = File.separator + catalog + File.separator + fname + "." + suffix;
			File file = new File(LOCAL_PATH + path);
			file.createNewFile();
			out = new FileImageOutputStream(file);
			out.write(data);
		} catch (Exception e) {
			path = null;
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return path;
	}

	private static String getFileSuffix(String filename) {
		String suffix = null;
		try {
			String[] split = filename.split("\\.");
			suffix = split[split.length - 1];
		} catch (Exception e) {
			e.printStackTrace();
		}
		return suffix;
	}

	/**
	 * 根据系统短路径获取图片base64
	 * @param shortUrl
	 * @return
	 */
	public static String getBase64(Object shortUrl){
		byte[] data = getFileByteArray(shortUrl);
		if(data == null){
			return null;
		}
		try {
			return new String(Base64.getEncoder().encode(data), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * 获取文件byte数组
	 * @param shortUrl
	 * @return
	 */
	public static byte[] getFileByteArray(Object shortUrl){
		if(StringUtils.isEmpty(shortUrl)){
			return EMPTY_BYTE;
		}
		InputStream in = null;
		try {
			in = new FileInputStream(LOCAL_PATH + shortUrl);
			byte[] data = new byte[in.available()];
			in.read(data);
			return data;
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			if(in != null){
				try {
					in.close();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		return EMPTY_BYTE;
	}
}
