package com.hs.datatrans.read;

import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class PrepareStatementHandler implements Runnable {
    private static Logger log = Logger.getLogger(PrepareStatementHandler.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private PreparedStatement statement;
    private String table;

    public PrepareStatementHandler(PreparedStatement statement,String table) {
        this.statement=statement;
        this.table=table;
    }

    @Override
    public void run() {
        try {
            int result = statement.executeUpdate();
            if(1>=result){
                log.info(Thread.currentThread().getName()+" "+sdf.format(System.currentTimeMillis())+" 成功插入 "+ table +" "+result+" 条记录 ");
            }else {
                log.info(Thread.currentThread().getName()+" "+sdf.format(System.currentTimeMillis())+" "+result+" 条记录插入 "+table+" 失败 ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(Thread.currentThread().getName()+" "+sdf.format(System.currentTimeMillis())+" "+"插入操作失败,线程停止:"+table);
        }
    }
}
