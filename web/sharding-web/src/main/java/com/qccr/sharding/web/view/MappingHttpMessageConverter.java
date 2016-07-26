package com.qccr.dinner.web.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

//import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

/**
 * 
 * @author 孟伟 ~ 2015年7月20日 上午10:30:26
 */
public class MappingHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    public final static Charset UTF8 = Charset.forName("UTF-8");

    private Charset charset = UTF8;

    /**
     * json转换去掉引用类型
     */
    private SerializerFeature[] serializerFeature = { SerializerFeature.DisableCircularReferenceDetect };

    private String callback = "callback";
    //    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();

    public MappingHttpMessageConverter() {
        super(new MediaType("application", "json", UTF8),
            new MediaType("application", "*+json", UTF8));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    public Charset getCharset() {
        return this.charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public SerializerFeature[] getFeatures() {
        return serializerFeature;
    }

    public void setFeatures(SerializerFeature... features) {
        this.serializerFeature = features;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz,
                                  HttpInputMessage inputMessage) throws IOException,
                                                                 HttpMessageNotReadableException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        InputStream in = inputMessage.getBody();

        byte[] buf = new byte[1024];
        for (;;) {
            int len = in.read(buf);
            if (len == -1) {
                break;
            }

            if (len > 0) {
                baos.write(buf, 0, len);
            }
        }

        byte[] bytes = baos.toByteArray();
        if (charset == UTF8) {
            return JSON.parseObject(bytes, clazz);
        } else {
            return JSON.parseObject(bytes, 0, bytes.length, charset.newDecoder(), clazz);
        }
    }

    /**
     * 针对@ResponseBody 标注的方法，返回json或者jsonp的数据格式。是否为jsonp需要从请求中判断是否 callback 的参数
     *
     * @param obj
     * @param outputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(Object obj,
                                 HttpOutputMessage outputMessage) throws IOException,
                                                                  HttpMessageNotWritableException {

        String jsonpCallback = null;
        RequestAttributes reqAttrs = RequestContextHolder.currentRequestAttributes();
        if (reqAttrs instanceof ServletRequestAttributes) {
            jsonpCallback = ((ServletRequestAttributes) reqAttrs).getRequest()
                .getParameter(callback);
        }

        String securityStr = null;

        if (serializerFeature != null) {

            securityStr = JSON.toJSONString(obj, serializerFeature);
        } else {
            securityStr = JSON.toJSONString(obj);
        }

//        if (StringUtils.isNotBlank(securityStr)) {
//            securityStr = securityStr.replaceAll("\t", " ");
//        }
        if (jsonpCallback != null) {
            jsonpCallback = jsonpCallback.replaceAll("[^\\w]", "");
            StringBuilder sb = new StringBuilder(jsonpCallback).append("(").append(securityStr)
                .append(")");
            securityStr = sb.toString();
        }

        OutputStream out = outputMessage.getBody();
        byte[] bytes = securityStr.getBytes(charset);

        out.write(bytes);
    }
}
