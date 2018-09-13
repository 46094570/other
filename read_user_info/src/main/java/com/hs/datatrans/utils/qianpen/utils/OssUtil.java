/*
 * 文 件 名:  FilesUtil.java
 * 版    权:  深圳市迪蒙网络科技有限公司
 * 描    述:  <描述>
 * 修 改 人:  huangjinbing
 * 修改时间:  2016年10月8日
 */
package com.hs.datatrans.utils.qianpen.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.hs.datatrans.entity.qianpen.vo.BaseDataResp;
import com.hs.datatrans.utils.dimeng.utils.DateUtils;
import com.hs.datatrans.utils.dimeng.utils.FileUtil;
import com.hs.datatrans.utils.dimeng.utils.FrameworkConfigurer;
import org.apache.log4j.Logger;
import org.apache.xmlgraphics.image.loader.ImageException;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 项目图片附件上传、删除
 * <功能详细描述>
 * 
 * @author  huangjinbing
 * @version  [版本号, 2016年10月8日]
 */
public class OssUtil
{
	//log日志  
    private static Logger logger = Logger.getLogger(FileUtil.class);
	//阿里云API的外网域名  
    public static final String ENDPOINT = "http://oss-cn-shenzhen.aliyuncs.com";  
    //阿里云API的密钥Access Key ID  
    public static final String ACCESS_KEY_ID = "LTAIuDrKeHY9kfof";  
    //阿里云API的密钥Access Key Secret  
    public static final String ACCESS_KEY_SECRET = "NAoGjAXWxysPLpx8kmRlhlKOPlGBaX";  
    //阿里云API的bucket名称  
    public static final String BACKET_NAME = "yjqapp";  
    //阿里云API的文件夹名称 
    //本地测试
    public static final String FOLDER="somnus/"; 
    //public static final String FOLDER="formal/";
  //在自己内部定义自己的一个 实例，只供内部调用  
    private static final OSSClient ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
      
    /** 
     * 获取阿里云OSS客户端对象 
     * @return ossClient 
     */  
    public static  OSSClient getOSSClient(){  
        return new OSSClient(ENDPOINT,ACCESS_KEY_ID, ACCESS_KEY_SECRET);  
    }  
  
    /** 
     * 创建存储空间 
     * @param ossClient      OSS连接 
     * @param bucketName 存储空间 
     * @return 
     */  
    public  static String createBucketName(OSSClient ossClient,String bucketName){  
        //存储空间  
        final String bucketNames=bucketName;  
        if(!ossClient.doesBucketExist(bucketName)){  
            //创建存储空间  
            Bucket bucket=ossClient.createBucket(bucketName);  
            logger.info("创建存储空间成功");  
            return bucket.getName();  
        }  
        return bucketNames;  
    }  
      
    /** 
     * 删除存储空间buckName 
     * @param ossClient  oss对象 
     * @param bucketName  存储空间 
     */  
    public static  void deleteBucket(OSSClient ossClient, String bucketName){    
        ossClient.deleteBucket(bucketName);     
        logger.info("删除" + bucketName + "Bucket成功");    
    }    
      
    /** 
     * 创建模拟文件夹 
     * @param ossClient oss连接 
     * @param bucketName 存储空间 
     * @param folder   模拟文件夹名如"qj_nanjing/" 
     * @return  文件夹名 
     */  
    public  static String createFolder(OSSClient ossClient,String bucketName,String folder){  
        //文件夹名   
        final String keySuffixWithSlash =folder;  
        //判断文件夹是否存在，不存在则创建  
        if(!ossClient.doesObjectExist(bucketName, keySuffixWithSlash)){  
            //创建文件夹  
            ossClient.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));  
            //logger.info("创建文件夹成功");  
            //得到文件夹名  
            OSSObject object = ossClient.getObject(bucketName, keySuffixWithSlash);  
            String fileDir=object.getKey();  
            return fileDir;  
        }  
        return keySuffixWithSlash;  
    }  
      
     /**   
        * 根据key删除OSS服务器上的文件   
        * @param ossClient  oss连接 
        * @param bucketName  存储空间  
        * @param folder  模拟文件夹名 如"qj_nanjing/" 
        * @param key Bucket下的文件的路径名+文件名 如："upload/cake.jpg" 
        */      
       public static void deleteFile(OSSClient ossClient, String bucketName, String folder, String key){      
            ossClient.deleteObject(bucketName, folder + key);     
            //logger.info("删除" + bucketName + "下的文件" + folder + key + "成功");    
       }
       
       
       /**
        * 获得图片路径
        *
        * @param fileUrl
        * @return
        */
       public static  String getImageSt(String fileUrl) {
         if (!StringUtils.isEmpty(fileUrl)) {
           String[] split = fileUrl.split("/");
           return OssUtil.getUrl(OssUtil.FOLDER + split[split.length - 1]);
         }
         return null;
       }
       
       /**
        * 获得url链接
        *
        * @param key
        * @return
        */
       public static  String getUrl(String key) {
         // 设置URL过期时间为10年  3600l* 1000*24*365*10
         Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
         // 生成URL
         URL url = ossClient.generatePresignedUrl(BACKET_NAME, key, expiration);
         if (url != null) {
           return url.toString();
         }
         return null;
       }   
       /**
        * 上传图片
        *  @param createId 创建人ID
        *  @param type 图片类型：1项目图片;2验证医疗证明;3户口本照片;4资金用途证明;5项目相关证明材料;6项目动态;7举报项目
        *  @param url
        */
       public static String uploadImg2Oss(MultipartFile file,String createId, String type) throws Exception{
         String originalFilename = file.getOriginalFilename();
         String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
         Random random = new Random();
         String fileName = random.nextInt(10000) + System.currentTimeMillis() + substring;
         long filesize = file.getSize();
         try {
           InputStream inputStream = file.getInputStream();
           OssUtil.uploadObject2OSS(inputStream, fileName,createId,type);
      	    return fileName;
         } catch (Exception e) {
       	  throw new ImageException("图片上传失败");
         }
       }
       /** 
        * 上传图片至OSS  
        * @param bucketName  存储空间 
        * @param createId 创建人ID
        * @param type 图片类型：1项目图片;2验证医疗证明;3户口本照片;4资金用途证明;5项目相关证明材料;6项目动态;7举报项目
        * @return String 返回的唯一MD5数字签名 
        * */  
   	public static  String uploadObject2OSS(InputStream instream, String fileName, String createId, String type) throws Exception{  
           String resultStr = null;  
           try {  
           	//创建上传Object的Metadata
               ObjectMetadata objectMetadata = new ObjectMetadata();
               objectMetadata.setContentLength(instream.available());
               objectMetadata.setCacheControl("no-cache");
               objectMetadata.setHeader("Pragma", "no-cache");
               objectMetadata.setContentType(getContentType(fileName.substring(fileName.lastIndexOf(".")))); 
               //指定该Object被下载时的内容编码格式  
               objectMetadata.setContentEncoding("utf-8"); 
               objectMetadata.setContentDisposition("inline;filename=" + fileName);
               String FOLDER = OssUtil.FOLDER;
               Date date = new Date();
   	           String file =FOLDER+new SimpleDateFormat("yyyy/MM/dd/").format(date);
   	           String folder = file+createId+"/";
               //上传文件   (上传文件流的形式)  
               PutObjectResult putResult = ossClient.putObject(BACKET_NAME, folder + fileName, instream, objectMetadata);   
               //解析结果  
               resultStr = putResult.getETag();
             } catch (IOException e) {
             logger.error(e.getMessage(), e);
             } 
           //"http://"+ OssUtil.BACKET_NAME +"."+ "oss-cn-shenzhen.aliyuncs.com"+"/"+folder+user.getUserId()+"/"+upPic
           //"http://"+ OssUtil.BACKET_NAME +"."+ "oss-cn-shenzhen.aliyuncs.com"+"/"+FOLDER+new SimpleDateFormat("yyyy/MM/dd/").format(new Date())+createId+ fileName
           return FOLDER+new SimpleDateFormat("yyyy/MM/dd/").format(new Date())+createId+"/"+ fileName;
       }
   	
    /**
     * 得到阿里云的文件读取流对象
     *
     * @param bucketName
     * @param key
     * @throws IOException
     */
    public static InputStream getObject(String bucketName, String key)
            throws IOException {

        // 初始化OSSClient
        OSSClient client = OssUtil.getOSSClient();

        // 获取Object，返回结果为OSSObject对象
        OSSObject object = client.getObject(bucketName, key);

        // 获取ObjectMeta
        ObjectMetadata meta = object.getObjectMetadata();

        // 获取Object的输入流
        InputStream objectContent = object.getObjectContent();

        // 处理Object

        // 关闭流
        // objectContent.close();

        return objectContent;
    }
    
    /** 
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType 
     * @param fileName 文件名 
     * @return 文件的contentType 
     */  
    public static  String getContentType(String fileName){  
        //文件的后缀名  
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));  
        if(".bmp".equalsIgnoreCase(fileExtension)) {  
            return "image/bmp";  
        }  
        if(".gif".equalsIgnoreCase(fileExtension)) {  
            return "image/gif";  
        } 
        if(".pdf".equalsIgnoreCase(fileExtension)) {  
            return "application/pdf";  
        } 
        if(".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension)  || ".png".equalsIgnoreCase(fileExtension) ) {  
            return "image/jpeg";  
        }  
        if(".html".equalsIgnoreCase(fileExtension)) {  
            return "text/html";  
        }  
        if(".txt".equalsIgnoreCase(fileExtension)) {  
            return "text/plain";  
        }  
        if(".vsd".equalsIgnoreCase(fileExtension)) {  
            return "application/vnd.visio";  
        }  
        if(".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {  
            return "application/vnd.ms-powerpoint";  
        }  
        if(".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {  
            return "application/msword";  
        }  
        if(".xml".equalsIgnoreCase(fileExtension)) {  
            return "text/xml";  
        }  
        //默认返回类型  
        return "image/jpeg";  
    }
    
    /**
     * 保存文件
     * <功能详细描述>
     * @param file 文件
     * @param createId 创建人ID
     * @param type 图片类型：1项目图片;2验证医疗证明;3户口本照片;4资金用途证明;5项目相关证明材料;6项目动态;7举报项目
     * @return
     */
    public static BaseDataResp saveUpFile(MultipartFile file, String createId, String type)
    {
        BaseDataResp result = null;
        MultipartFile[] files = {file};
        result = saveUpFiles(files, createId, type);
        return result;
    }
    
    /**
     * 保存多文件
     * <功能详细描述>
     * @param files 多文件
     * @param createId 创建人ID
     * @param type 图片类型：1项目图片;2验证医疗证明;3户口本照片;4资金用途证明;5项目相关证明材料;6项目动态;7举报项目;8其他图片(注：类型1-7的数据是保存在表t_project_attachment，类型8的数据是保存在表t_system_files)
     * @return
     */
    public static BaseDataResp saveUpFiles(MultipartFile[] files, String createId, String type)
    {
        BaseDataResp resp = new BaseDataResp();
        List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
        String[] allowFile = {"jpg", "png", "jpeg", "bmp", "doc", "xls", "xlsx", "docx", "txt", "pdf", "csv", "ppt",
            "pptx", "apk", "ipa", "avi", "mp4", "3gp", "AVI", "MP4", "3gp"};
        String fileName = "", oldName = "";
        try
        {
            for (MultipartFile multipartFile : files)
            {
                CommonsMultipartFile file = (CommonsMultipartFile)multipartFile;
                Map<String, String> resultMap = new HashMap<String, String>();
                String id = System.currentTimeMillis() + "";
                if (file != null)
                {
                    fileName = file.getFileItem().getName();
                    oldName = file.getFileItem().getName();
                    resultMap.put("oldName", fileName);
                    if (StringUtils.isEmpty(fileName))
                    {
                        resultMap.put("status", "errorType");
                        resultMap.put("message", "file name is null");
                        resultMap.put("url", "error");
                        resultMap.put("size", "0");
                        resultList.add(resultMap);
                        continue;
                    }
                    //检测文件是否合法
                    boolean pass = false;
                    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
                    for (int j = 0; j < allowFile.length; j++)
                    {
                        if (allowFile[j].equalsIgnoreCase(fileExt))
                        {
                            pass = true;
                        }
                    }
                    if (!pass)
                    {
                        resultMap.put("status", "errorType");
                        resultMap.put("message", "file type error");
                        resultMap.put("url", "error");
                        resultMap.put("size", "0");
                        resultList.add(resultMap);
                        continue;
                    }
                    fileName = id + fileName.substring(fileName.lastIndexOf("."));
                    String savePath = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
                    String url = saveFile(file.getInputStream(), savePath, fileName);
                    resultMap.put("status", "ok");
                    resultMap.put("message", "ok");
                    resultMap.put("url", url);
                    resultMap.put("size", file.getSize() + "");
                    resultMap.put("newFileName", fileName);
                    resultMap.put("fileExt", fileExt);//图片后缀
                    resultMap.put("oldName", oldName);//原始图片名称
                    resultList.add(resultMap);
                }
            }
            resp.setData(resultList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return resp;
    }
    
    public static String saveFile(InputStream ins, String type, String filename) {
    	StringBuffer bufStr = new StringBuffer();
		String savePath = bufStr.append("/fileStore/").append(getFileDirName(type)).append(filename).toString();
		String filepath = FrameworkConfigurer.getProperties("fileStore.home").toString().replace("/", System.getProperty("file.separator"))
				+ savePath.replace("/fileStore/", "").replace("/", System.getProperty("file.separator"));
		saveFile(ins, filepath);
		logger.info("上传文件至>>>>>>" + filepath);
		return savePath;
	}
    
    /**
     * 保存文件
     * <功能详细描述>
     * @param file 文件
     * @param createId 创建人ID
     * @param type 图片类型：1项目图片;2验证医疗证明;3户口本照片;4资金用途证明;5项目相关证明材料;6项目动态;7举报项目
     * @return
     */
    public static BaseDataResp saveFile(MultipartFile file, String createId,String type)
    {
        BaseDataResp result = null;
        MultipartFile[] files = {file};
        result = saveFiles(files, createId, type);
        return result;
    }
    /**
     * 保存多文件
     * <功能详细描述>
     * @param files 多文件
     * @param createId 创建人ID
     * @param type 图片类型：1项目图片;2验证医疗证明;3户口本照片;4资金用途证明;5项目相关证明材料;6项目动态;7举报项目;8其他图片(注：类型1-7的数据是保存在表t_project_attachment，类型8的数据是保存在表t_system_files)
     * @return
     */
    public static BaseDataResp saveFiles(MultipartFile[] files, String createId, String type)
    {
        BaseDataResp resp = new BaseDataResp();
        List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
        String[] allowFile = {"jpg", "png", "jpeg", "bmp", "doc", "xls", "xlsx", "docx", "txt", "pdf", "csv", "ppt",
            "pptx", "apk", "ipa", "avi", "mp4", "3gp", "AVI", "MP4", "3gp"};
        String fileName = "", oldName = "";
        try
        {
            for (MultipartFile multipartFile : files)
            {
                CommonsMultipartFile file = (CommonsMultipartFile)multipartFile;
                Map<String, String> resultMap = new HashMap<String, String>();
                String id = System.currentTimeMillis() + "";
                if (file != null)
                {
                    fileName = file.getFileItem().getName();
                    oldName = file.getFileItem().getName();
                    resultMap.put("oldName", fileName);
                    if (StringUtils.isEmpty(fileName))
                    {
                        resultMap.put("status", "errorType");
                        resultMap.put("message", "file name is null");
                        resultMap.put("url", "error");
                        resultMap.put("size", "0");
                        resultList.add(resultMap);
                        continue;
                    }
                    //检测文件是否合法
                    boolean pass = false;
                    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
                    for (int j = 0; j < allowFile.length; j++)
                    {
                        if (allowFile[j].equalsIgnoreCase(fileExt))
                        {
                            pass = true;
                        }
                    }
                    if (!pass)
                    {
                        resultMap.put("status", "errorType");
                        resultMap.put("message", "file type error");
                        resultMap.put("url", "error");
                        resultMap.put("size", "0");
                        resultList.add(resultMap);
                        continue;
                    }
                    fileName = id + fileName.substring(fileName.lastIndexOf("."));
//                    String savePath = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
                    String url = OssUtil.uploadObject2OSS(file.getInputStream(),fileName,createId,fileExt);
//                    FileUtil.uploadObject2OSS();
//                    String url = FileUtil.saveFile(file.getInputStream(), savePath, fileName);
                    resultMap.put("status", "ok");
                    resultMap.put("message", "ok");
                    resultMap.put("url", url);
                    resultMap.put("size", file.getSize() + "");
                    resultMap.put("newFileName", fileName);
                    resultMap.put("fileExt", fileExt);//图片后缀
                    resultMap.put("oldName", oldName);//原始图片名称
                    resultList.add(resultMap);
                }
            }
            resp.setData(resultList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return resp;
    }
    
    /**
     * 保存多文件
     * 修改人：wgc
     * <功能详细描述>
     * @param files 多文件
     * @param createId 创建人ID
     * @param type 图片类型：1项目图片;2验证医疗证明;3户口本照片;4资金用途证明;5项目相关证明材料;6项目动态;7举报项目;8其他图片(注：类型1-7的数据是保存在表t_project_attachment，类型8的数据是保存在表t_system_files)
     * @return
     */
    public static BaseDataResp saveFilesNew(MultipartFile[] files, String createId, String type, String serPath)
    {
        BaseDataResp resp = new BaseDataResp();
        List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
        String[] allowFile = {"jpg", "png", "jpeg", "bmp", "doc", "xls", "xlsx", "docx", "txt", "pdf", "csv", "ppt",
            "pptx", "apk", "ipa", "avi", "mp4", "3gp", "AVI", "MP4", "3gp"};
        String fileName = "", oldName = "";
        try
        {
            for (MultipartFile multipartFile : files)
            {
                CommonsMultipartFile file = (CommonsMultipartFile)multipartFile;
                Map<String, String> resultMap = new HashMap<String, String>();
                String id = System.currentTimeMillis() + "";
                if (file != null)
                {
                    fileName = file.getFileItem().getName();
                    oldName = file.getFileItem().getName();
                    resultMap.put("oldName", fileName);
                    if (StringUtils.isEmpty(fileName))
                    {
                        resultMap.put("status", "errorType");
                        resultMap.put("message", "file name is null");
                        resultMap.put("url", "error");
                        resultMap.put("size", "0");
                        resultList.add(resultMap);
                        continue;
                    }
                    //检测文件是否合法
                    boolean pass = false;
                    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
                    for (int j = 0; j < allowFile.length; j++)
                    {
                        if (allowFile[j].equalsIgnoreCase(fileExt))
                        {
                            pass = true;
                        }
                    }
                    if (!pass)
                    {
                        resultMap.put("status", "errorType");
                        resultMap.put("message", "file type error");
                        resultMap.put("url", "error");
                        resultMap.put("size", "0");
                        resultList.add(resultMap);
                        continue;
                    }
                    fileName = id + fileName.substring(fileName.lastIndexOf("."));
                    String savePath = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
                    String url = saveFileSet(file.getInputStream(), savePath, fileName, serPath);
                    resultMap.put("status", "ok");
                    resultMap.put("message", "ok");
                    resultMap.put("url", url);
                    resultMap.put("size", file.getSize() + "");
                    resultMap.put("newFileName", fileName);
                    resultMap.put("fileExt", fileExt);//图片后缀
                    resultMap.put("oldName", oldName);//原始图片名称
                    resultList.add(resultMap);
                }
            }
            resp.setData(resultList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return resp;
    }
    
    public static String saveFileSet(InputStream ins, String type, String filename, String serPath) {
        StringBuffer bufStr = new StringBuffer();
        String savePath = bufStr.append("/fileStore/").append(getFileDirName(type)).append(filename).toString();
//        String filepath = FrameworkConfigurer.getProperties("fileStore.home")
//                + savePath.toString().replace("/fileStore/", "").replace("/", System.getProperty("file.separator"));
        //骚春写的
//        String filepath = serPath.substring(0, serPath.lastIndexOf("\\")) + FrameworkConfigurer.getProperties("fileStore.home")
//            + savePath.toString().replace("/fileStore/", "").replace("/", System.getProperty("file.separator"));
        
        //帅哥写的
        String filepath = FrameworkConfigurer.getProperties("fileStore.home") + savePath.toString().replace("/fileStore/", "").replace("/", System.getProperty("file.separator"));
        saveFile(ins, filepath);
        logger.info("上传文件至>>>>>>" + filepath);
        logger.info("骚多满意了没。。。");
        return filepath.substring((filepath.indexOf("s")+1), filepath.length());
    }
    
    public static void saveFile(InputStream ins, String filepath) {
        BufferedInputStream in = null;
        BufferedOutputStream out = null;

        try {
            int e = filepath.lastIndexOf(System.getProperty("file.separator"));
            File folder = new File(filepath.substring(0, e));
            if (!folder.exists()) {
                folder.mkdirs();
            }

            in = new BufferedInputStream(ins);
            out = new BufferedOutputStream(new FileOutputStream(filepath));
            byte[] b = new byte[8096];

            int i;
            while ((i = in.read(b)) != -1) {
                out.write(b, 0, i);
            }

            out.flush();
        } catch (IOException arg19) {
            logger.error("FileUtil.saveFile  IOException: " + filepath + ": ", arg19);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException arg18) {
                logger.error("FileUtil.saveFile  IOException: " + filepath + ": ", arg18);
            }

            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException arg17) {
                logger.error("FileUtil.saveFile  IOException: " + filepath + ": ", arg17);
            }

        }

    }
    
    private static String getFileDirName(String typeName) {
        StringBuffer bufStr = new StringBuffer();
        Date nowTime = new Date(System.currentTimeMillis());
        bufStr.append(DateUtils.getYear(nowTime)).append("/");
        bufStr.append(DateUtils.getMonth(nowTime)).append("/");
        bufStr.append(DateUtils.getDay(nowTime)).append("/");
        bufStr.append(DateUtils.getHour(nowTime)).append("/");
        bufStr.append(typeName).append("/");
        return bufStr.toString();
    }
    
	/**
	 * @Description: 根据图片地址转换为base64编码字符串
	 * @Author: 
	 * @CreateTime: 
	 * @return
	 */
	public static String getImageStr(String imgFile) {
	    InputStream inputStream = null;
	    byte[] data = null;
	    try {
	        inputStream = new FileInputStream(imgFile);
	        data = new byte[inputStream.available()];
	        inputStream.read(data);
	        inputStream.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    // 加密
	    BASE64Encoder encoder = new BASE64Encoder();
	    return encoder.encode(data);
	}
    
    /**
     * 保存文件
     * 修改人:wgc
     * <功能详细描述>
     * @param file 文件
     * @param createId 创建人ID
     * @param type 图片类型：1项目图片;2验证医疗证明;3户口本照片;4资金用途证明;5项目相关证明材料;6项目动态;7举报项目
     * @return
     */
    public static BaseDataResp saveFileNew(MultipartFile file, String createId, String type, String serPath)
    {
        BaseDataResp result = null;
        MultipartFile[] files = {file};
        result = saveFilesNew(files, createId, type, serPath);
        return result;
    }
    
    /**
	 * 下载文件
	 * 
	 * @param client
	 * @param bucketName
	 * @param key
	 * @param filename
	 * @throws OSSException
	 * @throws ClientException
	 */
	public static File downloadFile(OSSClient ossClient, String bucketName, String key)
			throws OSSException, ClientException {
		OSSObject oSSObject = ossClient.getObject(new GetObjectRequest(bucketName, key));
		InputStream inputStream = oSSObject.getObjectContent();
		File file = new File(inputStream.toString());
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			return file;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return null;

	}
	
	/**
	   * 上传到OSS服务器  如果同名文件会覆盖服务器上的
	   *
	   * @param instream 文件流
	   * @param fileName 文件名称 包括后缀名
	   * @return 出错返回"" ,唯一MD5数字签名
	   */
public static String uploadFile2OSS(OSSClient ossClient, File file, String bucketName, String folder)
			throws Exception {
		String resultStr = null;
		try {
			// 以输入流的形式上传文件
			InputStream inputStream = new FileInputStream(file);
			// 文件名
			String fileName = file.getName();
			// 文件大小
			Long fileSize = file.length();
			// 创建上传Object的Metadata
			ObjectMetadata metadata = new ObjectMetadata();
			// 上传的文件的长度
			metadata.setContentLength(inputStream.available());
			// 指定该Object被下载时的网页的缓存行为
			metadata.setCacheControl("no-cache");
			// 指定该Object下设置Header
			metadata.setHeader("Pragma", "no-cache");
			// 指定该Object被下载时的内容编码格式
			metadata.setContentEncoding("utf-8");
			// 文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
			// 如果没有扩展名则填默认值application/octet-stream
			metadata.setContentType(getContentType(fileName));
			// 指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
			metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
			// 上传文件 (上传文件流的形式)
			PutObjectResult putResult = ossClient.putObject(bucketName, folder + fileName, inputStream, metadata);
			// 解析结果
			resultStr = putResult.getETag();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
		}
		return resultStr;
	}
}
