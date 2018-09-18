package com.gaox.encrypt.example.asymmetric.http;



import com.gaox.encrypt.example.asymmetric.http.utils.HttpUtils;
import com.gaox.encrypt.example.asymmetric.http.utils.RSACoder;
import com.gaox.encrypt.example.symmetric.http.utils.AESCoder;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DataServlet extends HttpServlet {

    private static final long serialVersionUID = 6355291462377121703L;

    private static String key;

    private static final String KEY_PARAM = "key";

    private static final String AESkey = "Kys6WVHZYkQlLDUDsGaKmY7m7ytEANVSB0gmG4CaYBE=";
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
            byte[] data = RSACoder.decryptByPrivateKey(input, key);
            System.out.println("doPost decrypt:\r\n"+new String(data));
            String k = new String(data);
            System.out.println("k:\t"+k);

            StringBuilder builder = new StringBuilder();
            builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
            builder.append("<dataGroup>\r\n");
            builder.append("\t<dataItem>\r\n");
            builder.append("\t\t<id>");
            builder.append("10201");
            builder.append("</id>\r\n");
            builder.append("\t\t<price>");
            builder.append("35.0");
            builder.append("</price>\r\n");
            builder.append("\t\t<time>");
            builder.append("2019-10-30");
            builder.append("</time>\r\n");
            builder.append("\t</dataItem>\r\n");
            builder.append("\t<dataItem>\r\n");
            builder.append("\t\t<id>");
            builder.append("10301");
            builder.append("</id>\r\n");
            builder.append("\t\t<price>");
            builder.append("55.0");
            builder.append("</price>\r\n");
            builder.append("\t\t<time>");
            builder.append("2019-10-31");
            builder.append("</time>\r\n");
            builder.append("\t<dataItem>\r\n");
            builder.append("</dataGroup>\r\n");
            byte[] output = builder.toString().getBytes();
            System.out.println("output:"+ Base64.encodeBase64String(output));
            System.out.println("output length:"+ output.length);
            //加密回复
            HttpUtils.responseWrite(resp, RSACoder.encryptByPrivateKey(output, key));
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
