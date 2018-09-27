package com.hs.datatrans.utils.qianpen.utils;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * HTTP工具类
 * @author yi
 * @version [版本号, 2017年12月15日]
 */
public class HttpTools
{
    public static String doFormPost(String url, Map<String, String> map)
        throws ParseException, IOException
    {
        String body = "";
        //创建httpclient对象  
        CloseableHttpClient client = HttpClients.createDefault();
        //创建post方式请求对象  
        HttpPost httpPost = new HttpPost(url);

        //装填参数  
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (map != null)
        {
            for (Entry<String, String> entry : map.entrySet())
            {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        //设置参数到请求对象中  
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
        //System.out.println("请求地址：" + url);
        //System.out.println("请求参数：" + nvps.toString());
        //设置header信息  
        //指定报文头【Content-type】、【User-Agent】  
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //执行请求操作，并拿到结果（同步阻塞）  
        CloseableHttpResponse response = client.execute(httpPost);
        //获取结果实体  
        HttpEntity entity = response.getEntity();
        if (entity != null)
        {
            //按指定编码转换结果实体为String类型  
            body = EntityUtils.toString(entity, "utf-8");
        }
        EntityUtils.consume(entity);
        //释放链接  
        response.close();
        return body;
    }
    
    /**
	 * 上传文件
	 * 
	 * @param serverUrl
	 *            服务器地址
	 * @param localFilePath
	 *            本地文件路径
	 * @param serverFieldName
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String doFilePost(String url, String filePath, Map<String, String> map)
			throws ParseException, IOException {
		String body = "";
		// 创建httpclient对象
		CloseableHttpClient client = HttpClients.createDefault();
		// 创建post方式请求对象
		HttpPost httpPost = new HttpPost(url);
		// 表单提交
		MultipartEntityBuilder mutiEntity = MultipartEntityBuilder.create();
		mutiEntity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		mutiEntity.setCharset(Consts.UTF_8);
		File files = new File(filePath);
		// 这里执行上传文件的操作
		FileBody fileBody = new FileBody(files, ContentType.create("multipart/form-data", Consts.UTF_8));
		// 设置参数到请求对象中
		mutiEntity.addPart("file", fileBody);
		// 设置其他上传参数
		setUploadParams(mutiEntity, map);
		// 提交到http
		HttpEntity reqEntity = mutiEntity.build();
		httpPost.setEntity(reqEntity);
		// 指定报文头【Content-type】、【User-Agent】
		// httpPost.setHeader("Content-type", "multipart/form-data");
		// httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows
		// NT; DigExt)");
		// 请求结果
		// 执行请求操作，并拿到结果（同步阻塞）
		CloseableHttpResponse response = client.execute(httpPost);

		HttpEntity resEntity = response.getEntity();
		if (resEntity != null) {
			// 按指定编码转换结果实体为String类型
			body = EntityUtils.toString(resEntity, "utf-8");
			//System.out.println(body);
		}
		EntityUtils.consume(resEntity);
		// 释放链接
		response.close();
		return body;
	}
	
	/**
	 * 设置上传文件时所附带的其他参数
	 * 
	 * @param multipartEntityBuilder
	 * @param params
	 * @throws UnsupportedEncodingException
	 */
	private static void setUploadParams(MultipartEntityBuilder multipartEntityBuilder, Map<String, String> params)
			throws UnsupportedEncodingException {
		if (params != null && params.size() > 0) {
			Set<String> keys = params.keySet();
			for (String key : keys) {
				multipartEntityBuilder.addPart(key,
						new StringBody(params.get(key), ContentType.create("multipart/form-data", Consts.UTF_8)));
			}
		}
	}
}
