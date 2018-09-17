import com.gaox.encrypt.example.utils.AESCoder;
import com.gaox.encrypt.example.utils.HttpUtils;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.Properties;


public class DataServletTest {
    private static final String KEY = "MgOFgiheLOMyhD4NF/m0AIfPEn2yQRWyarr3rGxZlAA=";
    private static final String URL = "http://localhost:8080/dataserver";

    @Test
    public final void test() throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
        builder.append("<dataGroup>\r\n");
        builder.append("\t<dataItem>\r\n");
        builder.append("\t\t<id>\r\n");
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
        builder.append("\t\t<id>\r\n");
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
        byte[] data = builder.toString().getBytes();
        Properties properties = new Properties();
        properties.put("messageDigest", AESCoder.shaHex(data));
        byte[] input = HttpUtils.postRequest(URL, AESCoder.encrypt(data, KEY), properties);
        input = AESCoder.decrypt(input, KEY);
        assertEquals("OK", new String(input));

    }
}
