package com.hs.datatrans.config;

import com.hs.datatrans.excel.ReadUserInfo;
import org.apache.poi.ss.usermodel.Sheet;

import java.text.DecimalFormat;
import java.util.Properties;
import java.util.ResourceBundle;

public class DxiangInfoBasicConfig {
    private static ResourceBundle resource;
    private static String riskAppCode;
    private static String riskAppKey;
    private static String riskRequestUrl;
    private static String nodeId;
    private static String baseUrl;
    private static String bdInfo;
    private static String baseInfo;
    private static String faceInfo;
    private static String linkmanInfo;
    private static String operatorInfo;
    private static String tongdunInfo;
    private static String token;
    private static String appId;
    private static Sheet sheet;
    private static Properties config;
    private static DecimalFormat decimalFormat = new DecimalFormat("###################.###########");

    static {
        resource = ResourceBundle.getBundle("qianpen");
        riskAppCode = resource.getString("riskAppCode");
        riskAppKey = resource.getString("riskAppKey");
        riskRequestUrl = resource.getString("riskRequestUrl");
        nodeId = resource.getString("nodeId");
        baseUrl = resource.getString("BaseURL");
        bdInfo = resource.getString("bdInfo");
        baseInfo = resource.getString("baseInfo");
        faceInfo = resource.getString("faceInfo");
        linkmanInfo = resource.getString("linkmanInfo");
        operatorInfo = resource.getString("operatorInfo");
        tongdunInfo = resource.getString("tongdunInfo");
        token = resource.getString("token");
        appId = resource.getString("appId");
        ReadUserInfo userInfo = new ReadUserInfo();
        config = BasicConfig.getConfig();
        try {
            sheet = userInfo.readExcel();
        } catch (Exception e) {
            throw new RuntimeException("初始化Excel失败");
        }
    }

    public static ResourceBundle getResource() {
        return resource;
    }

    public static String getRiskAppCode() {
        return riskAppCode;
    }

    public static String getRiskAppKey() {
        return riskAppKey;
    }


    public static String getNodeId() {
        return nodeId;
    }

    public static String getToken() {
        return token;
    }

    public static String getAppId() {
        return appId;
    }

    public static Sheet getSheet() {
        return sheet;
    }

    public static Properties getConfig() {
        return config;
    }

    public static DecimalFormat getDecimalFormat() {
        return decimalFormat;
    }

    public static String getBDInfoUrl() {
        return baseUrl + bdInfo;
    }

    public static String getBaseInfoUrl() {
        return baseUrl + baseInfo;
    }

    public static String getFaceInfoUrl() {
        return baseUrl + faceInfo;
    }

    public static String getLinkmanInfoUrl() {
        return baseUrl + linkmanInfo;
    }

    public static String getTDInfoUrl() {
        return baseUrl + tongdunInfo;
    }

    public static String getOperatorInfoUrl() {
        return baseUrl + operatorInfo;
    }
}
