package com.hs.datatrans.http;

import com.alibaba.fastjson.JSONObject;
import com.hs.datatrans.config.DxiangInfoBasicConfig;
import com.hs.datatrans.entity.base.DUserBase;
import com.hs.datatrans.entity.bd.DAccountBDList;
import com.hs.datatrans.entity.qianpen.vo.MessageVo;
import com.hs.datatrans.entity.qianpen.vo.TokenVo;
import com.hs.datatrans.excel.ReadUserInfo;
import com.hs.datatrans.utils.qianpen.utils.AesUtil;
import com.hs.datatrans.utils.qianpen.utils.HttpTools;
import com.hs.datatrans.utils.qianpen.utils.QianPenExecutor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这是用来测试的，其他的具体请求接口数据在其他细分类里
 */
public class DxiangInfo {

    private final Logger log = LoggerFactory.getLogger(QianPenExecutor.class);


    public static void main(String[] args) throws Exception {
//        DxiangInfo userInfo = new DxiangInfo();
//        userInfo.sendPost();
//        userInfo.getUserBdList(sheet);
        TokenVo token = QianPenExecutor.getToken();
        System.out.println("token:" + token);
//        ReadUserInfo info = new ReadUserInfo();
//        Sheet sheet = info.readExcel();
//        userInfo.getUserBd(sheet);
//        DAccountBDList accountBDList = JSONObject.parseObject(userInfo.testCase, DAccountBDList.class);
//        System.out.println("accountBDList:\r\n"+accountBDList);
    }

    /**
     * 这是一个测试的方法，测试与钱盆数据接口连通性
     *
     * @throws Exception
     */
    public static void sendPost() throws Exception {
        String token = DxiangInfoBasicConfig.getToken();
        String appId = DxiangInfoBasicConfig.getAppId();

        Map<String, String> bdParam = new HashMap<>();
//        bdParam.put("qianpenId", "327185");
//        bdParam.put("qianpenId", "326664");
        bdParam.put("creditType", "CREDIT_TYPE_CASH_LOAN");
        bdParam.put("userId", "82038");
        //operator
//        bdParam.put("userId", "82199");
        //tongdun
//        bdParam.put("userId", "82255");

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("sign", AesUtil.aesEncode(JSONObject.toJSONString(bdParam), token));
        requestMap.put("appId", appId);
        String resultBd;
//        resultBd = HttpTools.doFormPost("http://116.1.201.186:8768/out/dxiang/account/bd/list/", requestMap);
        resultBd = HttpTools.doFormPost("http://116.1.201.186:8768/out/dxiang/credit/data/base/", requestMap);
//        resultBd = HttpTools.doFormPost("http://116.1.201.186:8768/out/dxiang/credit/data/face/", requestMap);
//        resultBd = HttpTools.doFormPost("http://116.1.201.186:8768/out/dxiang/credit/data/linkman/", requestMap);
//        resultBd = HttpTools.doFormPost("http://116.1.201.186:8768/out/dxiang/credit/data/operator/", requestMap);
//        resultBd = HttpTools.doFormPost("http://116.1.201.186:8768/out/dxiang/credit/data/tongdun/", requestMap);
        MessageVo msg = JSONObject.parseObject(resultBd, MessageVo.class);
        if (0 != msg.getErrCode()) {
            System.err.println("qianpenId:" + bdParam.get("userId") + ",ErrCode:" + msg.getErrCode() + ",ErrMsg:" + msg.getErrMsg());
            return;
        }
        System.out.println("msg:\r\n" + msg);
        String securityData = msg.getSecurityData();
        System.out.println("securityData:\r\n" + securityData);
        String decodeInfo = AesUtil.aesDecode(securityData, token);
        System.out.println("decodeInfo:\r\n" + decodeInfo);
        if ("[]".equals(decodeInfo) || null == decodeInfo || "null".equals(decodeInfo)) return;
        DUserBase reportInfoVo = JSONObject.parseObject(decodeInfo, DUserBase.class);
//        List<DAccountBDList> accountBDList = JSONObject.parseObject(decodeInfo, DAccountBDList.class);
//        List<DLinkman> reportInfoVo = JSONObject.parseArray(decodeInfo, DLinkman.class);
        System.out.println("\r\nreportInfoVo:\r\n" + reportInfoVo);
    }

    /**
     * 这是一个测试的方法，从Excel中读取钱盆ID
     *
     * @param sheet
     * @param title
     */
    public void readQianPenId(Sheet sheet, String[] title) {
        if (null == sheet) throw new RuntimeException("读取不到Excel记录");
        int startIndex = 1;
        for (int i = startIndex; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(11);
            if (null != row) {
                if ("STRING".equals(cell.getCellTypeEnum().toString())) {
                    System.out.print(title[11] + ":" + cell.toString());
                } else if ("NUMERIC".equals(cell.getCellTypeEnum().toString())) {
                    System.out.print(title[11] + ":" + DxiangInfoBasicConfig.getDecimalFormat().format(cell.getNumericCellValue()));
                }
                System.out.println();
            }
        }
    }

    /**
     * 根据Excel中钱盆ID去接口取数据
     *
     * @param sheet Excel记录 {@link ReadUserInfo#readExcel()}
     * @throws IOException
     * @throws ParseException
     */
    public void getUserBdList(Sheet sheet) throws IOException, ParseException {
        String appId = DxiangInfoBasicConfig.getAppId();
        String token = DxiangInfoBasicConfig.getToken();
        //初始化发送请求参数
        Map<String, String> bdParam = new HashMap<>();
        Map<String, String> requestMap = new HashMap<>();
        int startIndex = 0;
        if ("1".equals(DxiangInfoBasicConfig.getConfig().getProperty("isSkipFirstLine"))) {
            startIndex = 1;
        }
        //读取Excel中钱盆id信息，并遍历
        for (int i = startIndex; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
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
            bdParam.put("qianpenId", qpId);
            requestMap.put("sign", AesUtil.aesEncode(JSONObject.toJSONString(bdParam), token));
            requestMap.put("appId", appId);
            //发送请求
            String resultBd = HttpTools.doFormPost(DxiangInfoBasicConfig.getBDInfoUrl(), requestMap);
            //处理返回请求
            MessageVo msg = JSONObject.parseObject(resultBd, MessageVo.class);
            if (0 != msg.getErrCode()) {
                System.err.println("qianpenId:" + qpId + ",ErrCode:" + msg.getErrCode() + ",ErrMsg:" + msg.getErrMsg());
                continue;
            }
            //解码
            String decodeInfo = AesUtil.aesDecode(msg.getSecurityData(), token);
            if ("[]".equals(decodeInfo) || null == decodeInfo || "null".equals(decodeInfo)) continue;
            //转换
            List<DAccountBDList> accountBDList = JSONObject.parseArray(decodeInfo, DAccountBDList.class);
            System.out.println("qianpenId:" + qpId + ",accountBDList:\r\n" + accountBDList);

        }

    }

}
