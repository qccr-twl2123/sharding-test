/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.dinner.controller.dinner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 * @since $Revision:1.0.0, $Date: 2016年4月5日 下午1:39:14 $
 */
public class BatchSend {
    //suggest
	private static final String  LOGIN_URL = "http://dc.kfqccr.com/";
    public static final String 	BOOKING_URL = "http://dc.kfqccr.com/meal/set";
    private static final String USERNAME ="C端营销中心";
    private static final String PASSWORD = "1234";
   
    
    private static URL realUrl = null;
    
    public static List<String> userids = new ArrayList<String>();
    static{
        userids.add("twl2123");
        userids.add("twl2328");
        userids.add("twl2179");
        //userids.add("twl1090");
        userids.add("twl655");
      
    }
    public static void main(String[] args) {
    	try{
    		String cookie = autoLoginReturnSession(LOGIN_URL, USERNAME, PASSWORD);
    		cookie = cookie.substring(0, cookie.indexOf(";", 1));
    		System.out.println(cookie);
            for(String s : userids){
              try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
              String result = sendPost(BOOKING_URL,"suggest=" + s, cookie);
              System.out.println(result);
            }
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }

    /**
     * 向指定 URL 发送POST方法的请   求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param, String cookie) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
        	realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("cookie", "JSESSIONID=F068CACD72F4C6E270D5862C8069F8B5"); 
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("host", "dc.kfqccr.com");
            conn.setRequestProperty("origin", "http://dc.kfqccr.com");
            conn.setRequestProperty("referer", "http://dc.kfqccr.com/meal/to");
            conn.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
//            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    public static String autoLoginReturnSession(String loginUrl, String userName, String password) throws IOException {
        
        //访问URL，并把信息存入sb中  
        //如果服务端登录成功后，服务端的代码调用下面的代码   
        //response.sendRedirect("welcome.jsp");  
        //则会不成功，原因(Step2，没有上传jsessionid值，导致没session)如下  
        //Step1[login.jsp登录成功]->转到->  
        //Step2[welcome.jsp不能得到session，判断没有登录成功]->转到->Step3[login.jsp要求用户登录]  
          
    	realUrl = new URL(loginUrl);  
        HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();  
        connection.setDoInput(true);  
        connection.setDoOutput(true);//允许连接提交信息       
        connection.setRequestMethod("POST");//网页默认“GET”提交方式  
  
        StringBuffer sb = new StringBuffer();  
        sb.append("username="+userName);  
        sb.append("&password="+password);  
        connection.setRequestProperty("Content-Length",  String.valueOf(sb.toString().length()));     
          
        OutputStream os = connection.getOutputStream();  
        os.write(sb.toString().getBytes());  
        os.close();  
  
        //取Cookie  
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));  
        String responseCookie = connection.getHeaderField("Set-Cookie");//取到所用的Cookie  
          
        return responseCookie;
}
}

