package com.hs.datatrans.excel;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.hs.datatrans.config.DBConnectionConfig;
import com.hs.datatrans.config.BasicConfig;
import com.hs.datatrans.config.DxiangInfoBasicConfig;
import com.hs.datatrans.database.ExcelDBConnection;
import com.hs.datatrans.entity.excel.*;
import com.hs.datatrans.utils.UserIdUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.*;


public class ReadUserInfo {
    private Logger log = Logger.getLogger(ReadUserInfo.class);
    private Properties config = BasicConfig.getConfig();
    private Sheet sheet;
    private int startIndex;
    private String[] title;
    //初始化线程池
//    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private ExecutorService executorService = new ThreadPoolExecutor(1, 40,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

    private DecimalFormat decimalFormat = new DecimalFormat("###################.###########");

    public static void main(String[] args) throws Exception {

        ReadUserInfo info = new ReadUserInfo();
//        info.showContent();
        info.insertExcel();

    }

    public ReadUserInfo() {
        title = getTitle();
        String skipFirstLine = config.getProperty("isSkipFirstLine");
        if ("1".equals(skipFirstLine)) {
            startIndex = 1;
        } else if ("0".equals(skipFirstLine)) {
            startIndex = 0;
        }
    }

    public String[] getTitle() {
        String[] title = {};
        String headLine = config.getProperty("headLine");

        try {
            sheet = readExcel();//加载文件内容
        } catch (IOException e) {
            e.printStackTrace();
        }
        Row sheetHeadLine = sheet.getRow(0);
        int headLineNum = sheetHeadLine.getPhysicalNumberOfCells();//从前向后数
        int headLineLastNum = sheetHeadLine.getLastCellNum();//从后向前数
        //headLineNum==headLineLastNum，表示行记录无null格
        if (null == headLine) {//未添加配置文件，以文件内容为准
            if (headLineLastNum != headLineNum) {
                throw new RuntimeException("请检查配置文件标题栏的内容或excel中标题栏内容，标题栏禁用空字段");
            } else {//每行记录无null格，配置文件未配置标题信息
                //这里如果配置文件中未配置标题行信息，以文件内标题行为准，文件内标题行可能会存在首行为标题或数据，允许跳过首行内容

                List<String> list = new ArrayList<String>();
                for (int i = 0; i < headLineLastNum; i++) {
                    list.add(sheetHeadLine.getCell(i).toString());
                }
                title = Arrays.toString(list.toArray()).replaceAll("\\[", "").replaceAll("\\]", "").split(",");
            }
        } else {//添加配置文件，以配置文件为准
                 /*
                    这里需要判断是否跳过记录中的首行，一般首行是标题，可不读数据
                    配置文件中配置有内容，且文件中首行不相等
                */
            title = headLine.split(",");
        }
        return title;
    }

    public Sheet readExcel() throws IOException {

        String filepath = config.getProperty("filePath");
        Workbook wb = null;
        InputStream is;
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

    public void showContent() {
        for (int i = startIndex; i <= sheet.getLastRowNum(); i++) {
            String userId = UserIdUtils.generateShortUuid();
            Row row = sheet.getRow(i);
            Cell cell;
            if (null != row) {
                for (int j = 0; j < title.length; j++) {
                    cell = row.getCell(j);
                    if (null == cell) {
                        System.out.print(title[j] + ":" + cell + "," + cell + ";");
                    } else if ("STRING".equals(cell.getCellTypeEnum().toString())) {
                        System.out.print(title[j] + ":" + cell.toString() + "," + cell.getCellTypeEnum() + ";");
                    } else if ("NUMERIC".equals(cell.getCellTypeEnum().toString())) {
                        System.out.print(title[j] + ":" + decimalFormat.format(cell.getNumericCellValue()) + "," + cell.getCellTypeEnum() + ";");
                    }
                }
                System.out.println();
            }
        }
    }

    public void insertExcel() {
        DruidPooledConnection connection = null;
        try {
            connection = DBConnectionConfig.getDataSource().getConnection();
            //执行插入Excel记录的语句都在ExcelDBConnection中
            ExcelDBConnection excelDBConnection = new ExcelDBConnection();
            String tUserSql = excelDBConnection.getTUserSql();
            String tUserAccountSql = excelDBConnection.getTUserAccountSql();
            String tUserBasisSql = excelDBConnection.getTUserBasisSql();
            String tUserSurveySql = excelDBConnection.getTUserSurveySql();
            String tUserExtSql = excelDBConnection.getTUserExtSql();
            int count = 0;
            for (int i = startIndex; i <= sheet.getPhysicalNumberOfRows(); i++) {
                String userId = UserIdUtils.generateShortUuid();
                Row row = sheet.getRow(i);
                if (null != row && null != connection) {
                    //将TUser数据封装进执行语句中
                    PrepareStatementHandler tUserHandler = excelDBConnection.insertTUser(connection.prepareStatement(tUserSql),
                            excelDBConnection.parseTUser(userId, row, new TUser()));
                    //将TUserAccount数据封装进执行语句中
                    PrepareStatementHandler tUserAccountHandler = excelDBConnection.insertTUserAccount(connection.prepareStatement(tUserAccountSql),
                            excelDBConnection.parseTUserAccout(userId, row, new TUserAccount()));
                    //将TUserBasis数据封装进执行语句中
                    PrepareStatementHandler tUserBasisHandler = excelDBConnection.insertTUserBasis(connection.prepareStatement(tUserBasisSql),
                            excelDBConnection.parseTUserBasic(userId, row, new TUserBasis()));
                    //将TUserSurvey数据封装进执行语句中
                    PrepareStatementHandler tUserSurveyHandler = excelDBConnection.insertTUserSurvey(connection.prepareStatement(tUserSurveySql),
                            excelDBConnection.parseTUserSurvey(userId, row, new TUserSurvey()));
                    //将TUserExt数据封装进执行语句中
                    PrepareStatementHandler tUserExtHandler = excelDBConnection.insertTUserExt(connection.prepareStatement(tUserExtSql),
                            excelDBConnection.parseTUserExt(userId, row, new TUserExt()));
                    excelDBConnection.getInsertExcelLatch().await();
                    executorService.execute(tUserHandler);
                    executorService.execute(tUserAccountHandler);
                    executorService.execute(tUserBasisHandler);
                    executorService.execute(tUserSurveyHandler);
                    executorService.execute(tUserExtHandler);
                    count++;
                    log.info("处理完一条记录，该记录是第 " + count + " 条记录");
                    excelDBConnection = new ExcelDBConnection();
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException("读取Excel内容错误，请确认文件是否正常");
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("Excel内容转换错误，请确认单元格记录格式");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("获取数据库连接失败");
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Excel封装SQL失败");
        } finally {
            executorService.shutdown();
            System.err.println("线程池已关闭");
            try {
                while (true) {
                    System.err.println("executorService.isTerminated():" + executorService.isTerminated());
                    if (executorService.isTerminated()) {
                        connection.close();
                        System.err.println("连接池已关闭");
                        break;
                    }
                    Thread.sleep(10000);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取 Excel 中的 qianpenId 信息
     *
     * @return 以 list 形式返回 qianpenId 信息
     */
    public List<String> getQianpenIds() {
        List<String> qianpenIds = new ArrayList<String>();
        for (int i = startIndex; i <= sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            Cell cell;
            if (null != row) {
                cell = row.getCell(Integer.parseInt(config.getProperty("qianpenIdPosition")));
                if (null == cell) {
                    qianpenIds.add("");
                } else if ("STRING".equals(cell.getCellTypeEnum().toString())) {
                    qianpenIds.add(cell.getStringCellValue());
                } else if ("NUMERIC".equals(cell.getCellTypeEnum().toString())) {
                    qianpenIds.add(decimalFormat.format(cell.getNumericCellValue()));
                }
            }
        }
        return qianpenIds;
    }
}
