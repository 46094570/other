package com.gaox.encrypt.example.symmetric.http;

import com.gaox.encrypt.example.symmetric.http.utils.AESCoder;
import com.gaox.encrypt.example.symmetric.http.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DataServlet extends HttpServlet {

    private static final long serialVersionUID = 6355291462377121703L;

    private static String key;

    private static final String KEY_PARAM = "key";

    private static final String HEAD_MD = "messageDigest";
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
            //从请求头中获取摘要信息
            String messageDigest = req.getHeader(HEAD_MD);
            System.out.println("doPost requestRead messageDigest:\r\n"+messageDigest);
            byte[] input = HttpUtils.requestRead(req);
            System.out.println("doPost requestRead:\r\n"+new String(input));
            byte[] data = AESCoder.decrypt(input, key);
            System.out.println("doPost decrypt:\r\n"+new String(data));
            byte[] output = "".getBytes();
            if (AESCoder.validate(data, messageDigest)) {
                output = "OK".getBytes();
            }
            //加密回复
            HttpUtils.responseWrite(resp, AESCoder.encrypt(output, key));
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
