package com.hs.datatrans.excel;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.hs.datatrans.config.BasicConfig;
import com.hs.datatrans.config.DBConnectionConfig;
import com.hs.datatrans.database.BaseDBConnection;
import com.hs.datatrans.entity.base.DUserBase;
import com.hs.datatrans.entity.base.TUserAddr;
import com.hs.datatrans.entity.base.TUserContact;
import com.hs.datatrans.utils.UserIdUtils;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 处理Dx UserBase 接口信息
 */
public class DxUserBase {
    private Logger log = Logger.getLogger(DxUserBase.class);
    private ExecutorService addrExecutorService = Executors.newSingleThreadExecutor();
    private ExecutorService contactExecutorService = Executors.newSingleThreadExecutor();
    private String addrSql = BasicConfig.getConfig().getProperty("db.sql.insertTUserAddress");
    private String contactSql = BasicConfig.getConfig().getProperty("db.sql.insertTUserContact");
    private DruidPooledConnection connection;


    public static void main(String[] args) {
//        new DxUserBase().insertBaseInfoFromExcel();
        new DxUserBase().insertBaseInfoFromDB();
    }

    /**
     * 从 Excel 中取出qianpenId，执行查询并插入数据库
     */
    public void insertBaseInfoFromExcel() {

        try {
            if (null == connection)
                connection = DBConnectionConfig.getDataSource().getConnection();
            BaseDBConnection base = new BaseDBConnection();
            // 从 excel 获取 qianpenId
            List<String> qianpenIds = new ReadUserInfo().getQianpenIds();
            for (String qianpenId : qianpenIds) {
                //从数据库获取 userId
                String userId = base.getUserId(connection, qianpenId);
                //通过接口获取 DUserBase 信息
                DUserBase userBase = base.getDUserBase(qianpenId);
                log.info("qianpenId:\t" + qianpenId + ",\tuserId:\t" + userId + "\r\nuserBase:\r\n" + userBase);
//                log.info("userId:\t" + userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从数据库取出qianpenId，查询数据并执行插入数据库
     */
    public void insertBaseInfoFromDB() {
        try {
            if (null == connection)
                connection = DBConnectionConfig.getDataSource().getConnection();
            BaseDBConnection baseConnection = new BaseDBConnection();
            String userId = UserIdUtils.generateShortUuid();
//            List<String> userAndQianpenIds = baseConnection.getUserAndQianpenIds(connection);
            //这里使用qianpenId
            DUserBase userBase = baseConnection.getDUserBase("82038");
            //这里使用 yjq 的用户id
            TUserAddr userAddr = baseConnection.parseDUserBase(userId, userBase);
            List<TUserContact> userContacts = baseConnection.parseContact(userId, userBase);
            for (TUserContact contact : userContacts) {
                PrepareStatementHandler userContact = baseConnection.insertTUserContact(connection.prepareStatement(contactSql), contact);
                contactExecutorService.execute(userContact);
            }
            PrepareStatementHandler handler = baseConnection.insertTUserAddr(connection.prepareStatement(addrSql), userAddr);
            addrExecutorService.execute(handler);

            log.info("userBase:\t" + userBase);
            log.info("userAddr:\t" + userAddr);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (!addrExecutorService.isShutdown()) {
                    addrExecutorService.shutdown();
                    System.err.println("addr线程池已关闭");
                }
                if (!contactExecutorService.isShutdown()) {
                    contactExecutorService.shutdown();
                    System.err.println("contact线程池已关闭");
                }
                while (true) {
                    System.err.println("executorService.isTerminated():" + addrExecutorService.isTerminated());
                    System.err.println("contactExecutorService.isTerminated():" + contactExecutorService.isTerminated());
                    if (addrExecutorService.isTerminated() && contactExecutorService.isTerminated()) {
                        connection.close();
                        System.err.println("连接池已关闭");
                        break;
                    }
                    Thread.sleep(5000);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
