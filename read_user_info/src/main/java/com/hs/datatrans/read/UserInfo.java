package com.hs.datatrans.read;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.hs.datatrans.config.DBConnectionConfig;
import com.hs.datatrans.config.ReadConfig;
import com.hs.datatrans.entity.*;
import com.hs.datatrans.utils.UserIdUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.UnixCrypt;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;


public class UserInfo {
    private static Logger log = Logger.getLogger(UserInfo.class);
    private static ReadConfig config = new ReadConfig();
    private int startIndex=0;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Date now = new Date();
    private DecimalFormat df = new DecimalFormat("0");
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private CountDownLatch latch;

    public static void main(String[] args) throws Exception {

        UserInfo rui = new UserInfo();
        rui.insertExcel();

//        String[] title = rui.getTitle();
//        Sheet sheet = rui.readExcel();
//        rui.showContent(sheet,title);

    }

    public String[] getTitle(){
        String firstLine = config.getConfig().getProperty("firstLine");
        if (null == firstLine) {
            throw new RuntimeException("请定义好firstLine的内容");
        }
        String[] title = firstLine.split(",");
        return title;
    }

    public Sheet readExcel() throws IOException {
        Sheet sheet = null;

        /*
            用于判断是否跳过记录中的首行，一般首行是标题，可不读数据
         */
        String skipFirstLine = config.getConfig().getProperty("isSkipFirstLine");
        if ("1".equals(skipFirstLine)) {
            startIndex = 1;
        }
        String filepath = config.getConfig().getProperty("filePath");
        Workbook wb = null;
        InputStream is = null;
        is = new FileInputStream(filepath);
        String ext = filepath.substring(filepath.lastIndexOf("."));
        if (".xls".equals(ext)) {
            wb = new HSSFWorkbook(is);
        } else if (".xlsx".equals(ext)) {
            wb = new XSSFWorkbook(is);
        }
        sheet = wb.getSheetAt(0);
        return sheet;
    }

    public void showContent(Sheet sheet,String[] title) {
        if (title.length != sheet.getRow(0).getPhysicalNumberOfCells()) {
            throw new RuntimeException("标题与列无法对应，请检查标题内容");
        }

        for (int i = startIndex; i <= sheet.getPhysicalNumberOfRows(); i++) {
            String userId = UserIdUtils.generateShortUuid();
            Row row = sheet.getRow(i);
            Cell cell;
            if (null != row) {
                for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                    cell = row.getCell(j);
                    if (null != cell) {
                        System.out.print(title[j] + ":" + cell.toString() + ",");
                        System.out.print(title[j] + ":" + cell.getCellTypeEnum() + ",");
                    } else {
                        System.out.print(title[j] + ":  ,");
                    }
                }
                System.out.println();
            }
        }
    }

    public void insertExcel() {
        try {
            //读取Excel信息
            Sheet sheet = readExcel();
            //创建信息处理载体
            TUser tUser = new TUser();
            TUserAccount tUserAccount = new TUserAccount();
            TUserBasis tUserBasis = new TUserBasis();
            TUserSurvey tUserSurvey = new TUserSurvey();
            TUserExt tUserExt = new TUserExt();
            DruidDataSource dataSource = DBConnectionConfig.getDataSource();
            DruidPooledConnection connection = dataSource.getConnection();
            String tUserSql = config.getConfig().getProperty("db.sql.insertTUser");
            String tUserAccountSql = config.getConfig().getProperty("db.sql.insertTUserAccount");
            String tUserBasisSql = config.getConfig().getProperty("db.sql.insertTUserBasis");
            String tUserSurveySql = config.getConfig().getProperty("db.sql.insertTUserSurvey");
            String tUserExtSql = config.getConfig().getProperty("db.sql.insertTUserExt");
            int count = 0;
            //准备执行语句
            PreparedStatement tUserStatement = connection.prepareStatement(tUserSql);
            PreparedStatement tUserAccountStatement = connection.prepareStatement(tUserAccountSql);
            PreparedStatement tUserBasisStatement = connection.prepareStatement(tUserBasisSql);
            PreparedStatement tUserSurveyStatement = connection.prepareStatement(tUserSurveySql);
            PreparedStatement tUserExtStatement = connection.prepareStatement(tUserExtSql);
            for (int i = startIndex; i <= sheet.getPhysicalNumberOfRows(); i++) {
                String userId = UserIdUtils.generateShortUuid();
                Row row = sheet.getRow(i);
                latch = new CountDownLatch(5);
                if (null != row) {
                    //处理TUser需要的信息
                    tUser = parseTUser(userId,row, tUser);
                    tUserAccount = parseTUserAccout(userId,row, tUserAccount);
                    tUserBasis = parseTUserBasic(userId,row, tUserBasis);
                    tUserSurvey = parseTUserSurvey(userId,row, tUserSurvey);
                    tUserExt = parseTUserExt(userId,row, tUserExt);
//                    System.out.println(tuser.toString());
                    //将TUser数据封装进执行语句中
                    PrepareStatementHandler tUserHandler = insertTUser(tUserStatement, tUser);
                    //将TUserAccount数据封装进执行语句中
                    PrepareStatementHandler tUserAccountHandler = insertTUserAccount(tUserAccountStatement, tUserAccount);
                    //将TUserBasis数据封装进执行语句中
                    PrepareStatementHandler tUserBasisHandler = insertTUserBasis(tUserBasisStatement, tUserBasis);
                    //将TUserSurvey数据封装进执行语句中
                    PrepareStatementHandler tUserSurveyHandler = insertTUserSurvey(tUserSurveyStatement, tUserSurvey);
                    //将TUserExt数据封装进执行语句中
                    PrepareStatementHandler tUserExtHandler = insertTUserExt(tUserExtStatement, tUserExt);
                    latch.await();
                    executorService.execute(tUserHandler);
                    executorService.execute(tUserAccountHandler);
                    executorService.execute(tUserBasisHandler);
                    executorService.execute(tUserSurveyHandler);
                    executorService.execute(tUserExtHandler);
                    count++;
                    log.info("处理完一条记录，该记录是第 " +count+ " 条记录");

                }
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException("读取Excel内容错误，请确认文件是否正常");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("获取文件路径错误，请确认文件是否存在");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读取文件错误，请确认文件是否正常");
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("Excel内容转换错误，请确认单元格记录格式");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("获取数据库连接失败");
        } catch (InterruptedException e){
            e.printStackTrace();
            throw new RuntimeException("Excel封装SQL失败");
        }finally {
            executorService.shutdown();
        }
    }

    private TUser parseTUser(String userId,Row row, TUser tuser) throws ParseException {
        /**
         关于Excel获取Cell是否为空的问题，需要用三目表达式做一个判断，懒得用if了

         关于Excel时间的转换，要先从Cell从getNumericCellValue()，然后通过HSSFDateUtil.getJavaDate()
         转换成Date类型，再继续进一步处理 ……
         */
        tuser.clean();
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
        tuser.setPassword(UnixCrypt.crypt(row.getCell(2) == null ? "" : row.getCell(2).toString(), DigestUtils.sha256Hex("dimeng")));
        tuser.setPhone(row.getCell(4) == null ? " " : row.getCell(4).toString());
        tuser.setPwdErrorCount(0);
        tuser.setSource("1");
        tuser.setToken(null);
        tuser.setTradePwd(UnixCrypt.crypt(row.getCell(3) == null ? "" : row.getCell(3).toString(), DigestUtils.sha256Hex("dimeng")));
        tuser.setUserId(userId);
        tuser.setUserName(row.getCell(0) == null ? null : row.getCell(0).toString());
        tuser.setUserStatus("0");
        tuser.setUserType("3");
        return tuser;
    }

    private TUserAccount parseTUserAccout(String userId,Row row, TUserAccount tUserAccount) {
        tUserAccount.clean();
        tUserAccount.setUserId(userId);
        return tUserAccount;
    }

    private TUserBasis parseTUserBasic(String userId,Row row, TUserBasis tUserBasis) {
        tUserBasis.clean();
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

    private TUserSurvey parseTUserSurvey(String userId,Row row, TUserSurvey tUserSurvey) {
        tUserSurvey.clean();
        tUserSurvey.setUserId(userId);
        return tUserSurvey;
    }

    private TUserExt parseTUserExt(String userId,Row row, TUserExt tUserExtser) {
        tUserExtser.clean();
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
        latch.countDown();
        return new PrepareStatementHandler(statement,"TUser");
    }

    public PrepareStatementHandler insertTUserAccount(PreparedStatement statement, TUserAccount tUserAccount) throws SQLException {
        statement.setObject(1,tUserAccount.getUserId());
        latch.countDown();
        return new PrepareStatementHandler(statement,"TUserAccount");
    }

    public PrepareStatementHandler insertTUserBasis(PreparedStatement statement, TUserBasis tUserBasis) throws SQLException {
        statement.setObject(1,tUserBasis.getUserId());
        statement.setObject(2,tUserBasis.getIsIdCardCheckThrough());
        statement.setObject(3,tUserBasis.getFaceRecogniteResult());
        statement.setObject(4,tUserBasis.getIsContactInfo());
        statement.setObject(5,tUserBasis.getIsBasicInfo());
        statement.setObject(6,tUserBasis.getIsBankCardBind());
        statement.setObject(7,tUserBasis.getIsUserAttach());
        statement.setObject(8,tUserBasis.getIsAllCheckThrough());
        statement.setObject(9,tUserBasis.getIsFirstAuth());
        statement.setObject(10,tUserBasis.getIsSesameAuth());
        statement.setObject(11,tUserBasis.getCreateTime());
        latch.countDown();
        return new PrepareStatementHandler(statement,"TUserBasis");
    }

    public PrepareStatementHandler insertTUserExt(PreparedStatement statement, TUserExt tUserExt) throws SQLException {
        statement.setObject(1,tUserExt.getUserId());
        statement.setObject(2,tUserExt.getQianpenId());
        statement.setObject(3,tUserExt.getQianpenAccountName());
        statement.setObject(4,tUserExt.getBankCardName());
        statement.setObject(5,tUserExt.getBankCard());
        latch.countDown();
        return new PrepareStatementHandler(statement,"TUserExt");
    }

    public PrepareStatementHandler insertTUserSurvey(PreparedStatement statement, TUserSurvey tUserSurvey) throws SQLException {
        statement.setObject(1,tUserSurvey.getUserId());
        latch.countDown();
        return new PrepareStatementHandler(statement,"TUserSurvey");
    }

}
