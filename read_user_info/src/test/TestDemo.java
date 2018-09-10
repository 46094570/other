import com.hs.datatrans.config.DBConnectionConfig;
import com.hs.datatrans.config.ReadConfig;
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
import java.io.InputStreamReader;
import java.util.Properties;

public class TestDemo {
    private static Logger log = Logger.getLogger(TestDemo.class);
    private static ReadConfig config = new ReadConfig();
    private int startIndex = 0;

    public static void main(String[] args) throws Exception {
/*//        String sql = "INSERT INTO `t_user_dx`(`user_id`, `user_name`, `password`, `trade_pwd`, `phone`, `email`, `source`, `date_register`, `user_status`, `date_login`, `date_last_login`, `user_type`, `fail_login_count`, `pwd_error_count`, `init_password_state`, `default_recharge_way`, `black_reason`, `channel_core`, `first_login_time`, `ip`) VALUES";
        TestDemo info = new TestDemo();
        Row row = info.readExcel().getRow(1);
        String[] titles = info.getTitle();
        System.out.println(row.getPhysicalNumberOfCells());
        for(int i = 0;i<titles.length;i++) {
            if(null!=row.getCell(i))
                System.out.println(titles[i]+" "+row.getCell(i).getCellTypeEnum()+" "+row.getCell(i).toString());
//            System.out.println(row.getCell(i).getNumericCellValue());
//            System.out.println(new DecimalFormat("0").format(row.getCell(11).getNumericCellValue()));
//            System.out.println(row.getCell(i).toString());
        }*/
        Properties pro = new Properties();
        InputStreamReader is = new InputStreamReader(DBConnectionConfig.class.getClassLoader().getResourceAsStream("db.properties"), "UTF-8");
        pro.load(is);
        String s = pro.getProperty("timeBetweenEvictionRunsMillis");
        System.out.println("s:"+s);
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

}
