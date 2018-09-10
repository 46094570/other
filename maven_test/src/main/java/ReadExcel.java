import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;

public class ReadExcel {
    public static void main(String[] args) {
        String[] title = {"真实姓名", "身份证号", "登录密码", "交易密码", "电话号码", "邮箱", "注册来源", "注册时间", "首次登录时间", "最后一次登录时间", "登录ip", "钱盆ID", "钱盆账号名称", "开户行", "银行卡号"};
        String filepath = "C:/Users/Administrator/Desktop/dx_user.xlsx";
        Workbook wb = null;
        InputStream is = null;
        try {
            is = new FileInputStream(filepath);
            String ext = filepath.substring(filepath.lastIndexOf("."));
            if (".xls".equals(ext)) {
                wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(ext)) {
                wb = new XSSFWorkbook(is);
            }
            Sheet sheet = wb.getSheetAt(0);
            Cell cell;
            Cell cell1;
            Cell cell0;
            int count = 0;
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
//                if(null!=row){
//                    for(int j = 0;j<row.getPhysicalNumberOfCells();j++){
//                        cell = row.getCell(j);
//                        if(null!=cell){
//                            System.out.print(title[j]+":"+cell.toString()+",");
//                        }else {
//                            System.out.print(title[j]+":  ,");
//                        }
//                    }
//                    System.out.println();
//                }
                if (null != row) {
                    cell = row.getCell(2);
                    cell1 = row.getCell(1);
                    cell0 = row.getCell(0);
                    if (null != cell) {
                        System.out.println(title[0] + ":" + cell0.toString() + "," + title[1] + ":" + cell1.toString() + "," + title[2] + ":" + cell.toString() + ",");
                    }
                }
                count+=1;
            }
            System.out.println("count:"+count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
