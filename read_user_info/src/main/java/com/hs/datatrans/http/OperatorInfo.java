package com.hs.datatrans.http;

import com.alibaba.fastjson.JSONObject;
import com.hs.datatrans.config.DxiangInfoBasicConfig;
import com.hs.datatrans.entity.operator.DUserCallRecordsVo;
import com.hs.datatrans.entity.qianpen.vo.MessageVo;
import com.hs.datatrans.excel.ReadUserInfo;
import com.hs.datatrans.utils.qianpen.utils.AesUtil;
import com.hs.datatrans.utils.qianpen.utils.HttpTools;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class OperatorInfo {


    public static void main(String[] args) throws IOException, ParseException {
        Sheet sheet = DxiangInfoBasicConfig.getSheet();
        OperatorInfo bd = new OperatorInfo();
        bd.getUserBdList(sheet);
    }

    /**
     * 根据Excel中钱盆ID去接口取数据
     *
     * @param sheet Excel记录 {@link ReadUserInfo#readExcel()}
     * @throws IOException
     * @throws ParseException
     */
    public void getUserBdList(Sheet sheet) throws IOException, ParseException {
        //初始化发送请求参数
        Map<String, String> bdParam = new HashMap<>();
        Map<String, String> requestMap = new HashMap<>();
        String appId = DxiangInfoBasicConfig.getAppId();
        String token = DxiangInfoBasicConfig.getToken();
        int startIndex = 0;
        if ("1".equals(DxiangInfoBasicConfig.getConfig().getProperty("isSkipFirstLine"))) {
            startIndex = 1;
        }
        //读取Excel中钱盆id信息，并遍历
        for (int i = startIndex; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            //Excel中钱盆ID位置
            Cell cell = row.getCell(11);
            String qpId = null;
            if (null != row) {
                if ("STRING".equals(cell.getCellTypeEnum().toString())) {
                    qpId = cell.toString();
                } else if ("NUMERIC".equals(cell.getCellTypeEnum().toString())) {
                    qpId = DxiangInfoBasicConfig.getDecimalFormat().format(cell.getNumericCellValue());
                }
            }
            //填入请求数据
            bdParam.put("userId", qpId);
            MessageVo msg = null;
            try {
                msg = InterConnection.sendPostRequest(DxiangInfoBasicConfig.getFaceInfoUrl(), bdParam);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (0 != msg.getErrCode()) {
                System.err.println("qianpenId:" + qpId + ",ErrCode:" + msg.getErrCode() + ",ErrMsg:" + msg.getErrMsg());
                continue;
            }
            //解码
            String decodeInfo = AesUtil.aesDecode(msg.getSecurityData(), token);
            if ("[]".equals(decodeInfo)||null==decodeInfo||"null".equals(decodeInfo)) continue;
            //转换
            DUserCallRecordsVo callRecords = JSONObject.parseObject(decodeInfo, DUserCallRecordsVo.class);
            System.out.println("qianpenId:" + qpId + ",callRecords:\r\n" + callRecords);

        }

    }

}
