package com.gaox.encrypt.example.digitalSignature.http;




import com.gaox.encrypt.example.digitalSignature.http.utils.MessageUtils;
import com.gaox.encrypt.example.digitalSignature.http.utils.AESHttpCoder;
import com.gaox.encrypt.example.digitalSignature.http.utils.HttpUtils;
import com.gaox.encrypt.example.digitalSignature.http.utils.RSAHttpSignature;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class DataServlet extends HttpServlet {

    private static final long serialVersionUID = 3585796677899494932L;

    private static String key;

    private static final String KEY_PARAM = "key";

    private static final String CHARACTER_SET="utf-8";

    @Override
    public void init() throws ServletException {
        key = getInitParameter(KEY_PARAM);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            byte[] input = HttpUtils.requestRead(req);
            byte[] data = RSAHttpSignature.decryptByPrivateKey(input, key);
            System.out.println("recieveData:\r\n"+ Arrays.toString(data));
            System.out.println("recieveData length:\r\n"+ data.length);
            byte[] msg = Arrays.copyOfRange(data, 0,data.length - AESHttpCoder.KEY_SIZE/4);
            byte[] aes = Arrays.copyOfRange(data, data.length - AESHttpCoder.KEY_SIZE/4,data.length);
            String aesKey = new String(aes,CHARACTER_SET);
            System.out.println("aesKey:\r\n"+aesKey);
            byte[] decrypt = AESHttpCoder.decrypt(msg, aesKey);
            String clientMsg = new String(decrypt, CHARACTER_SET);
            System.out.println("clientMsg:\r\n"+clientMsg);

            //回应给客户端
            String serverMsg = MessageUtils.generateServerMessage();
            byte[] output = serverMsg.getBytes(CHARACTER_SET);
            System.out.println("output:"+ Base64.encodeBase64String(output));
            System.out.println("output length:"+ output.length);
            //AES加密数据后，再执行签名
            output = AESHttpCoder.encrypt(output,aesKey);
            String sign = RSAHttpSignature.sign(output, key);
            /**
             * 重新组织待输出的数据，将该数据的签名信息封装在数据包顶部
             * RSA数字签名得到的签名固定值长度固定，与初始化密钥长度相同。
             * 如果初始化密钥长度为1024位，使用SHA1withRSA
             * 则得到签名长度为1024位二进制，256位十六进制字符串
             */
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(sign.getBytes(CHARACTER_SET));
            outputStream.write(output);
            output = outputStream.toByteArray();
            outputStream.flush();
            outputStream.close();
            //加密回复
            HttpUtils.responseWrite(resp, RSAHttpSignature.encryptByPrivateKey(output, key));
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
