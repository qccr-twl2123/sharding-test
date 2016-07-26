package com.qccr.backend.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 文件上传工具类
 * @author 王小虎
 * @date 2015年6月18日 下午4:36:13
 */
public class FileUploadUtil {

    /** LOGGER for ModularServiceFacade **/
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadUtil.class);
    
	/**
	 * 图片资源
	 */
	public static final String FILE_TYPE_IMAGE = "twlImages";
	/**
	 * 静态文件资源
	 */
	public static final String FILE_TYPE_STATIC = "twlStatic";

	public static final String[] IMAGE_EXTNAMES = { "PNG", "BMP", "JPG",
			"JPEG", "GIF" };
	public static final String[] STATIC_EXTNAMES = { "css", "js", "html" };
	private static final String UPLOAD_URL = "http://static.qichechaoren.com/autoUpfile.php";

	/**
	 * @Description: 上传图片
	 * @author 王小虎
	 * @param request
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public static String uploadImage(final File imageFile, String fileName,
			String uploadUrl) throws Exception {
		return upload(imageFile, FILE_TYPE_IMAGE, fileName, uploadUrl);
	}

	/**
	 * @Description: 上次静态资源
	 * @author 王小虎
	 * @param request
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public static String uploadStatic(final File staticFile) throws Exception {
		return upload(staticFile, FILE_TYPE_STATIC);
	}

	private static String upload(final File file, String type) throws Exception {
		return upload(file, type, null, null);
	}

	private static String upload(final File file, String type, String fileName,
			String uploadUrl) throws Exception {
		String extName = getFileExtName(fileName);
		if (!isValidate(extName, type)) {
			throw new RuntimeException("不支持的文件类型，仅支持："
					+ getLegalExtFileName(type));
		}
		if (fileName == null) {
			fileName = "TWL_" + System.currentTimeMillis()
					+ ((int) (Math.random() * 1000 * 1000)) + extName;
		}
		try {
			String result = postFile(file, fileName, type, uploadUrl);
			if (result.indexOf("'status':'0'") == -1) {
				throw new RuntimeException(result);
			}
			int i=result.indexOf("'name':");
			fileName=result.substring(i).split(":")[1];
			fileName=fileName.substring(1, fileName.length()-1);
		} finally {
			file.delete();
		}
		return fileName;
	}

	private static String postFile(File file, String fileName, String fileType,
			String uploadUrl) {
	    if (StringUtil.isNullOrEmpty(uploadUrl)) {
	        uploadUrl = UPLOAD_URL;
	    }
	    InputStream in = null;
	    try {
            in = new FileInputStream(file.getPath());
        } catch (FileNotFoundException e) {
            LOGGER.error("文件找不到啦！",e);
        }
		HttpPost httpPost = new HttpPost(uploadUrl);
		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder
				.create();
		multipartEntityBuilder.setMode(HttpMultipartMode.STRICT);
		multipartEntityBuilder.setCharset(Consts.UTF_8);
		multipartEntityBuilder.addBinaryBody("file", in,
				ContentType.DEFAULT_BINARY, fileName);
		multipartEntityBuilder.addTextBody("name", fileType);
		multipartEntityBuilder.addTextBody("project", "shop");
		HttpEntity httpEntity = multipartEntityBuilder.build();
		httpPost.setEntity(httpEntity);
		return HttpUtil.excute(httpPost);
	}

	/**
	 * @Description: 获取文件扩展名
	 * @author 王小虎
	 * @param fileItem
	 *            文件对象
	 * @return 扩展名
	 * @throws
	 */
	private static String getFileExtName(String name) {
		String extName = null;
		if (!StringUtil.isNullOrEmpty(name) && name.lastIndexOf(".") >= 0) {
			extName = name.substring(name.lastIndexOf("."));
		}
		return extName;
	}

	/**
	 * @Description: 验证文件扩展名是否是支持类型
	 * @author 王小虎
	 * @param extName
	 *            文件扩展名称
	 * @return 是否支持
	 * @throws
	 */
	private static boolean isValidate(String extName, String type) {
		if (extName == null) {
			return false;
		}
		for (String valExtName : FILE_TYPE_IMAGE.equals(type) ? IMAGE_EXTNAMES
				: STATIC_EXTNAMES) {
			if (("." + valExtName).equalsIgnoreCase(extName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @Description: 获取支持文件扩展名字符
	 * @author 王小虎
	 * @return 扩展名称字符
	 * @throws
	 */
	public static String getLegalExtFileName(String type) {
		StringBuilder extNameBuilder = new StringBuilder();
		for (String valExtName : FILE_TYPE_IMAGE.equals(type) ? IMAGE_EXTNAMES
				: STATIC_EXTNAMES) {
			extNameBuilder.append(valExtName).append(",");
		}
		if (!"".equals(extNameBuilder.toString())) {
			extNameBuilder.deleteCharAt(extNameBuilder.length() - 1);
		}
		return extNameBuilder.toString();
	}

}
