package com.hs.datatrans.http;

import com.alibaba.fastjson.JSONObject;
import com.hs.datatrans.config.DxiangInfoBasicConfig;
import com.hs.datatrans.database.BaseDBConnection;
import com.hs.datatrans.entity.base.DUserBase;
import com.hs.datatrans.entity.qianpen.vo.MessageVo;
import com.hs.datatrans.excel.ReadUserInfo;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class BaseInfo {
    private Logger log = Logger.getLogger(BaseInfo.class);

    public static void main(String[] args) throws IOException, ParseException {
//        new BaseInfo().getUserBase(DxiangInfoConfig.getSheet());
        DUserBase baseInfo = new BaseInfo().getDxUserBaseInfo("82038");
        System.out.println("baseInfo:\r\n" + baseInfo);
    }

    /**
     * 根据Excel中钱盆ID去接口取数据
     *
     * @param sheet Excel记录 {@link ReadUserInfo#readExcel()}
     * @throws IOException
     * @throws ParseException
     */
    public void getUserBase(Sheet sheet) throws IOException, ParseException {
        //初始化发送请求参数
        Map<String, String> bdParam = new HashMap<>();
        int startIndex = 0;
        if ("1".equals(DxiangInfoBasicConfig.getConfig().getProperty("isSkipFirstLine"))) {
            startIndex = 1;
        }
        //读取Excel中钱盆id信息，并遍历
        for (int i = startIndex; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            //Excel中钱盆ID位置
            Cell cell = row.getCell(11);
            String userId = null;
            if (null != row) {
                if ("STRING".equals(cell.getCellTypeEnum().toString())) {
                    userId = cell.toString();
                } else if ("NUMERIC".equals(cell.getCellTypeEnum().toString())) {
                    userId = DxiangInfoBasicConfig.getDecimalFormat().format(cell.getNumericCellValue());
                }
            }
            DUserBase userBaseInfo = getDxUserBaseInfo(userId);
            System.out.println("userBaseInfo:\r\n" + userBaseInfo);
        }
    }

    public DUserBase getDxUserBaseInfo(String userId) {
        DUserBase userBase = null;
        //初始化发送请求参数
        Map<String, String> bdParam = new HashMap<>();
        //填入请求数据
        bdParam.put("creditType", "CREDIT_TYPE_CASH_LOAN");
        bdParam.put("userId", userId);
        //发送请求
        MessageVo msg = null;
        try {
            msg = InterConnection.sendPostRequest(DxiangInfoBasicConfig.getBaseInfoUrl(), bdParam);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("发送Post请求异常，请核查");
        }
        if (0 != msg.getErrCode()) {
            System.err.println("qianpenId:" + userId + ",ErrCode:" + msg.getErrCode() + ",ErrMsg:" + msg.getErrMsg());
            return userBase;
        }
        //解码
        String decodeInfo = InterConnection.decryptMessage(msg.getSecurityData());
        if ("[]".equals(decodeInfo) || null == decodeInfo || "null".equals(decodeInfo)) {
            System.err.println("qianpenId:" + userId + ",返回数据为空");
            return userBase;
        }
        log.info(decodeInfo);
        //转换
        userBase = JSONObject.parseObject(decodeInfo, DUserBase.class);
        return userBase;
    }
}
