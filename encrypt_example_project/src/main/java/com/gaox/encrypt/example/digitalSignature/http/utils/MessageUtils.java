package com.gaox.encrypt.example.digitalSignature.http.utils;

import java.text.SimpleDateFormat;

public class MessageUtils {
    private static   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public static String generateClientMessage(){
        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
        builder.append("<dataGroup>\r\n");
        builder.append("\t<dataItem>\r\n");
        builder.append("\t\t<id>");
        builder.append("客户端10201");
        builder.append("</id>\r\n");
        builder.append("\t\t<price>");
        builder.append("35.0");
        builder.append("</price>\r\n");
        builder.append("\t\t<time>");
        builder.append(sdf.format(System.currentTimeMillis()));
        builder.append("</time>\r\n");
        builder.append("\t</dataItem>\r\n");
        builder.append("\t<dataItem>\r\n");
        builder.append("\t\t<id>");
        builder.append("客户端10301");
        builder.append("</id>\r\n");
        builder.append("\t\t<price>");
        builder.append("55.0");
        builder.append("</price>\r\n");
        builder.append("\t\t<time>");
        builder.append(sdf.format(System.currentTimeMillis()));
        builder.append("</time>\r\n");
        builder.append("\t<dataItem>\r\n");
        builder.append("</dataGroup>\r\n");
        return builder.toString();
    }

    public static String generateServerMessage(){
        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
        builder.append("<dataGroup>\r\n");
        builder.append("\t<dataItem>\r\n");
        builder.append("\t\t<id>");
        builder.append("服务端20201");
        builder.append("</id>\r\n");
        builder.append("\t\t<price>");
        builder.append("35.0");
        builder.append("</price>\r\n");
        builder.append("\t\t<time>");
        builder.append(sdf.format(System.currentTimeMillis()));
        builder.append("</time>\r\n");
        builder.append("\t</dataItem>\r\n");
        builder.append("\t<dataItem>\r\n");
        builder.append("\t\t<id>");
        builder.append("服务端20301");
        builder.append("</id>\r\n");
        builder.append("\t\t<price>");
        builder.append("55.0");
        builder.append("</price>\r\n");
        builder.append("\t\t<time>");
        builder.append(sdf.format(System.currentTimeMillis()));
        builder.append("</time>\r\n");
        builder.append("\t<dataItem>\r\n");
        builder.append("</dataGroup>\r\n");
        return builder.toString();
    }
}
