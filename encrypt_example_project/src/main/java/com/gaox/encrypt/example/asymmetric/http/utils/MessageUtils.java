package com.gaox.encrypt.example.asymmetric.http.utils;

public class MessageUtils {
    public static String generateMessage(){
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
        return builder.toString();
    }
}
