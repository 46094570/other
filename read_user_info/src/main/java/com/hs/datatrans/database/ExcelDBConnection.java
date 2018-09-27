package com.hs.datatrans.database;

import com.hs.datatrans.config.DxiangInfoBasicConfig;
import com.hs.datatrans.entity.excel.*;
import com.hs.datatrans.excel.PrepareStatementHandler;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.UnixCrypt;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Row;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * 封装处理Excel插入数据库相关内容
 */
public class ExcelDBConnection {

    private Properties config;
    private String tUserSql;
    private String tUserAccountSql;
    private String tUserBasisSql;
    private String tUserSurveySql;
    private String tUserExtSql;

    private CountDownLatch insertExcelLatch;
    private Date now;
    private SimpleDateFormat sdf;
    private DecimalFormat df;

    public ExcelDBConnection() {
        insertExcelLatch = new CountDownLatch(5);
        now = new Date();
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df = new DecimalFormat("0");
        config = DxiangInfoBasicConfig.getConfig();
    }

    public TUser parseTUser(String userId, Row row, TUser tuser) throws ParseException {
        /**
         关于Excel获取Cell是否为空的问题，需要用三目表达式做一个判断，懒得用if了

         关于Excel时间的转换，要先从Cell从getNumericCellValue()，然后通过HSSFDateUtil.getJavaDate()
         转换成Date类型，再继续进一步处理 ……
         */
//        tuser.clean();
        tuser.setBlackReason(null);
        tuser.setChannelCore("0");
        tuser.setDateLastLogin(row.getCell(9) == null ? null :
                sdf.parse(sdf.format(HSSFDateUtil.getJavaDate(row.getCell(9).getNumericCellValue()))));
        tuser.setDateLogin(row.getCell(7) == null ? null :
                sdf.parse(sdf.format(HSSFDateUtil.getJavaDate(row.getCell(7).getNumericCellValue()))));
        tuser.setDateRegister(row.getCell(7) == null ? null :
                sdf.parse(sdf.format(HSSFDateUtil.getJavaDate(row.getCell(7).getNumericCellValue()))));
        tuser.setDefaultRechargeWay("0");
        tuser.setEmail(row.getCell(5) == null ? null : row.getCell(5).toString());
        tuser.setFailLoginCount(0);
        tuser.setFirstLoginTime(row.getCell(8) == null ? null :
                sdf.parse(sdf.format(HSSFDateUtil.getJavaDate(row.getCell(8).getNumericCellValue()))));
        tuser.setInitPasswordState("0");
        tuser.setIp(row.getCell(10) == null ? null : row.getCell(10).toString());
        tuser.setOpSource("3");
        tuser.setPassword(row.getCell(2) == null ? "" : UnixCrypt.crypt(row.getCell(2).toString(), DigestUtils.sha256Hex("dimeng")));
        tuser.setPhone(row.getCell(4) == null ? " " : df.format(row.getCell(4).getNumericCellValue()));
        tuser.setPwdErrorCount(0);
        tuser.setSource("1");
        tuser.setToken(null);
        tuser.setTradePwd(row.getCell(3) == null ? "" : UnixCrypt.crypt(row.getCell(3).toString(), DigestUtils.sha256Hex("dimeng")));
        tuser.setUserId(userId);
        tuser.setUserName(row.getCell(0) == null ? null : row.getCell(0).toString());
        tuser.setUserStatus("0");
        tuser.setUserType("3");
        return tuser;
    }

    public TUserAccount parseTUserAccout(String userId, Row row, TUserAccount tUserAccount) {
//        tUserAccount.clean();
        tUserAccount.setUserId(userId);
        return tUserAccount;
    }

    public TUserBasis parseTUserBasic(String userId, Row row, TUserBasis tUserBasis) {
//        tUserBasis.clean();
        tUserBasis.setUserId(userId);
        tUserBasis.setIsIdCardCheckThrough("1");
        tUserBasis.setFaceRecogniteResult("0");//0未识别、1：识别成功、2：识别失败
        tUserBasis.setIsContactInfo("1");
        tUserBasis.setIsBasicInfo("1");
        tUserBasis.setIsBankCardBind("1");
        tUserBasis.setIsUserAttach("1");
        tUserBasis.setIsAllCheckThrough("1");
        tUserBasis.setIsFirstAuth("1");
        tUserBasis.setIsSesameAuth("1");
        tUserBasis.setCreateTime(sdf.format(now));
        return tUserBasis;
    }

    public TUserSurvey parseTUserSurvey(String userId, Row row, TUserSurvey tUserSurvey) {
//        tUserSurvey.clean();
        tUserSurvey.setUserId(userId);
        return tUserSurvey;
    }

    public TUserExt parseTUserExt(String userId, Row row, TUserExt tUserExtser) {
//        tUserExtser.clean();
        tUserExtser.setUserId(userId);
        tUserExtser.setQianpenId(row.getCell(11) == null ? "" : df.format(row.getCell(11).getNumericCellValue()));
        tUserExtser.setQianpenAccountName(row.getCell(12) == null ? "" : row.getCell(12).toString());
        tUserExtser.setBankCardName(row.getCell(13) == null ? "" : row.getCell(13).toString());
        tUserExtser.setBankCard(row.getCell(14) == null ? "" : row.getCell(14).toString());
        return tUserExtser;
    }

    public PrepareStatementHandler insertTUser(PreparedStatement statement, TUser tuser) throws SQLException {
        statement.setObject(1, tuser.getUserId());
        statement.setObject(2, tuser.getUserName());
        statement.setObject(3, tuser.getPassword());
        statement.setObject(4, tuser.getTradePwd());
        statement.setObject(5, tuser.getPhone());
        statement.setObject(6, tuser.getEmail());
        statement.setObject(7, tuser.getSource());
        statement.setObject(8, tuser.getDateRegister());
        statement.setObject(9, tuser.getUserStatus());
        statement.setObject(10, tuser.getDateLogin());
        statement.setObject(11, tuser.getDateLastLogin());
        statement.setObject(12, tuser.getUserType());
        statement.setObject(13, tuser.getFailLoginCount());
        statement.setObject(14, tuser.getPwdErrorCount());
        statement.setObject(15, tuser.getInitPasswordState());
        statement.setObject(16, tuser.getDefaultRechargeWay());
        statement.setObject(17, tuser.getBlackReason());
        statement.setObject(18, tuser.getChannelCore());
        statement.setObject(19, tuser.getFirstLoginTime());
        statement.setObject(20, tuser.getIp());
        insertExcelLatch.countDown();
        return new PrepareStatementHandler(statement, "TUser");
    }

    public PrepareStatementHandler insertTUserAccount(PreparedStatement statement, TUserAccount tUserAccount) throws SQLException {
        statement.setObject(1, tUserAccount.getUserId());
        insertExcelLatch.countDown();
        return new PrepareStatementHandler(statement, "TUserAccount");
    }

    public PrepareStatementHandler insertTUserBasis(PreparedStatement statement, TUserBasis tUserBasis) throws SQLException {
        statement.setObject(1, tUserBasis.getUserId());
        statement.setObject(2, tUserBasis.getIsIdCardCheckThrough());
        statement.setObject(3, tUserBasis.getFaceRecogniteResult());
        statement.setObject(4, tUserBasis.getIsContactInfo());
        statement.setObject(5, tUserBasis.getIsBasicInfo());
        statement.setObject(6, tUserBasis.getIsBankCardBind());
        statement.setObject(7, tUserBasis.getIsUserAttach());
        statement.setObject(8, tUserBasis.getIsAllCheckThrough());
        statement.setObject(9, tUserBasis.getIsFirstAuth());
        statement.setObject(10, tUserBasis.getIsSesameAuth());
        statement.setObject(11, tUserBasis.getCreateTime());
        insertExcelLatch.countDown();
        return new PrepareStatementHandler(statement, "TUserBasis");
    }

    public PrepareStatementHandler insertTUserExt(PreparedStatement statement, TUserExt tUserExt) throws SQLException {
        statement.setObject(1, tUserExt.getUserId());
        statement.setObject(2, tUserExt.getQianpenId());
        statement.setObject(3, tUserExt.getQianpenAccountName());
        statement.setObject(4, tUserExt.getBankCardName());
        statement.setObject(5, tUserExt.getBankCard());
        insertExcelLatch.countDown();
        return new PrepareStatementHandler(statement, "TUserExt");
    }

    public PrepareStatementHandler insertTUserSurvey(PreparedStatement statement, TUserSurvey tUserSurvey) throws SQLException {
        statement.setObject(1, tUserSurvey.getUserId());
        insertExcelLatch.countDown();
        return new PrepareStatementHandler(statement, "TUserSurvey");
    }

    public CountDownLatch getInsertExcelLatch() {
        return insertExcelLatch;
    }

    public String getTUserSql() {
        return config.getProperty("db.sql.insertTUser");
    }

    public String getTUserAccountSql() {
        return config.getProperty("db.sql.insertTUserAccount");
    }

    public String getTUserBasisSql() {
        return config.getProperty("db.sql.insertTUserBasis");
    }

    public String getTUserSurveySql() {
        return config.getProperty("db.sql.insertTUserSurvey");
    }

    public String getTUserExtSql() {
        return config.getProperty("db.sql.insertTUserExt");
    }

}
