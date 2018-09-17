package com.gaox.encrypt.example.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

public class HttpUtils {

    private static final String CHARACTER_ENCODING="UTF-8";
    private static final String METHOD_POST="POST";
    private static final String CONTENT_TYPE="Content-Type";

    /**
     * 打印数据
     * @param response http响应
     * @param data 待打印数据
     * @throws IOException
     */
    public static void responseWrite(HttpServletResponse response,byte[] data) throws IOException {
        if(null!=data){
            response.setContentLength(data.length);
            DataOutputStream outputStream = new DataOutputStream(response.getOutputStream());
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();
        }else {
            System.out.println("无数据可写");
        }
    }

    /**
     * 从请求中读取字节流
     * @param request http请求
     * @return byte[] 请求中的数据
     * @throws IOException
     */
    public static byte[] requestRead(HttpServletRequest request) throws IOException {
        int contentLength = request.getContentLength();
        byte[] data = null;
        if(contentLength>0){
            data = new byte[contentLength];
            InputStream inputStream = request.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            dataInputStream.readFully(data);
            dataInputStream.close();
        }
        return data;
    }

    /**
     * 以POST方式向指定地址发送数据包请求，并取得返回的数据包
     * @param url 请求地址
     * @param requestData 请求数据
     * @return byte[] 数据包
     */
    public static byte[] postRequest(String url,byte[] requestData){
        Properties requestProperties = new Properties();
        requestProperties.setProperty(CONTENT_TYPE,"application/octet-stream;charset="+CHARACTER_ENCODING);
        return postRequest(url,requestData,requestProperties);
    }

    /**
     * 以POST方式向指定地址发送数据包请求，并取得返回的数据包
     * @param url 请求地址
     * @param requestData 请求数据
     * @param requestProperties 请求包体
     * @return byte[] 数据包
     */
    public static byte[] postRequest(String url, byte[] requestData, Properties requestProperties) {
        byte[] responseData = null;
        HttpURLConnection conn = null;
        try {
            URL u = new URL(url);
            conn = (HttpURLConnection) u.openConnection();
            if(null!=requestProperties&&(requestProperties.size()>0)){
                for (Map.Entry<Object,Object> entry:requestProperties.entrySet()
                     ) {
                    String key = String.valueOf(entry.getKey());
                    String value = String.valueOf(entry.getValue());
                    conn.setRequestProperty(key,value);
                }
            }
            conn.setRequestMethod(METHOD_POST);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            DataOutputStream outputStream = new DataOutputStream(conn.getOutputStream());
            if(null!=requestData){
                outputStream.write(requestData);
            }
            outputStream.flush();
            outputStream.close();
            DataInputStream inputStream = new DataInputStream(conn.getInputStream());
            int contentLength = conn.getContentLength();
            if(contentLength>0){
                requestData = new byte[contentLength];
                inputStream.readFully(requestData);
            }
            inputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null!=conn){
                conn.disconnect();
            }
        }
        return requestData;
    }
}
