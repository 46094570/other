package com.gaox.encrypt.example.digitalSignature.http;



import com.gaox.encrypt.example.asymmetric.http.utils.HttpUtils;
import com.gaox.encrypt.example.asymmetric.http.utils.MessageUtils;
import com.gaox.encrypt.example.asymmetric.http.utils.RSACoder;
import com.gaox.encrypt.example.digitalSignature.http.utils.RSAHttpSignature;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class DataServlet extends HttpServlet {

    private static final long serialVersionUID = 3585796677899494932L;

    private static String key;

    private static final String KEY_PARAM = "key";

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
            System.out.println("doPost decrypt:\r\n"+new String(data));
            String clientMsg = new String(data);
            System.out.println("clientMsg:\t"+clientMsg);
            String serverMsg = MessageUtils.generateMessage();
            byte[] output = serverMsg.getBytes();
            System.out.println("output:"+ Base64.encodeBase64String(output));
            System.out.println("output length:"+ output.length);
            String sign = RSAHttpSignature.sign(output, key);
            /**
             * 重新组织待输出的数据，将该数据的签名信息封装在数据包顶部
             * RSA数字签名得到的签名固定值长度固定，与初始化密钥长度相同。
             * 如果初始化密钥长度为1024位，使用SHA1withRSA
             * 则得到签名长度为1024位二进制，256位十六进制字符串
             */
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(sign.getBytes());
            outputStream.write(output);
            output = outputStream.toByteArray();
            outputStream.flush();
            outputStream.close();
            //加密回复
            HttpUtils.responseWrite(resp, RSACoder.encryptByPrivateKey(output, key));
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
