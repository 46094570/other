package com.hs.datatrans.database;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.hs.datatrans.config.DBConnectionConfig;
import com.hs.datatrans.config.DxiangInfoBasicConfig;
import com.hs.datatrans.entity.base.DReferenceLinkman;
import com.hs.datatrans.entity.base.DUserBase;
import com.hs.datatrans.entity.base.TUserAddr;
import com.hs.datatrans.entity.base.TUserContact;
import com.hs.datatrans.excel.PrepareStatementHandler;
import com.hs.datatrans.http.BaseInfo;
import com.hs.datatrans.utils.UserIdUtils;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseDBConnection {

    private Logger log = Logger.getLogger(BaseDBConnection.class);
    /**
     * 通过数据库获取UserId
     *
     * @param qianpenId qianpenId
     * @return
     */
    public String getUserId(DruidPooledConnection connection, String qianpenId) {
        return new UserIdConnection().getUserId(connection, qianpenId);
    }

    /**
     * 通过数据接口获取 Dx 用户信息
     *
     * @param qianpenId  用于向接口取数据的ID
     * @return 用户信息
     * @throws Exception
     */
    public DUserBase getDUserBase(String qianpenId) throws Exception {
        BaseInfo baseInfo = new BaseInfo();
        DUserBase userBase = baseInfo.getDxUserBaseInfo(qianpenId);
        return userBase;
    }

    /**
     * 通过数据库获取 qianpen_id
     *
     * @param connection 数据库连接
     * @return qianpen_id 列表
     * @throws SQLException
     */
    public List<String> getQianpenIds(DruidPooledConnection connection) throws SQLException {
        List<String> list = new ArrayList<>();
        String qianpenIdSql = DxiangInfoBasicConfig.getConfig().getProperty("db.sql.getQianpenIds");
        PreparedStatement statement = connection.prepareStatement(qianpenIdSql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next())
            list.add(resultSet.getString("qianpen_id"));
        return list;
    }

    /**
     * 通过 t_user_ext_dx 表获取 qianpen_id 和 user_id
     *
     * @param connection 数据库连接
     * @return qianpen_id 列表
     * @throws SQLException
     */
    public List<String> getUserAndQianpenIds(DruidPooledConnection connection) throws SQLException {
        List<String> list = new ArrayList<>();
        String qianpenIdSql = DxiangInfoBasicConfig.getConfig().getProperty("db.sql.getUserAndQianpenId");
        PreparedStatement statement = connection.prepareStatement(qianpenIdSql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next())
            list.add(resultSet.getString("user_id") + "," + resultSet.getString("qianpen_id"));
        return list;
    }

    /**
     * 接口信息转换
     *
     * @param userBase 接口返回对象信息
     */
    public TUserAddr parseDUserBase(String userId,DUserBase userBase) {
        TUserAddr userAddr = new TUserAddr();
        StringBuffer buffer = new StringBuffer();
        if (null != userBase.getCompanyAddress()) {
            buffer.append(userBase.getCompanyAddress() + " ");
        }
        if (null != userBase.getCompanyAddSuffix()) {
            buffer.append(userBase.getCompanyAddSuffix() + " ");
        }
        userAddr.setLiveDetailedAddress(buffer.toString().trim());

        buffer = new StringBuffer();
        if (null != userBase.getHomeAddress()) {
            buffer.append(userBase.getHomeAddress() + " ");
        }
        if (null != userBase.getHomeAddSuffix()) {
            buffer.append(userBase.getHomeAddSuffix() + " ");
        }
        userAddr.setNativePlaceDetailedAddress(buffer.toString().trim());
        userAddr.setId(UserIdUtils.general25UUID());
        userAddr.setUserId(userId);
        userAddr.setCreateTime(new Date());
        return userAddr;
    }

    /**
     *  写入 t_user_addr 表
     * @param statement SQL执行语句
     * @param userAddr  写入对象
     * @return  执行对象
     * @throws SQLException
     */
    public PrepareStatementHandler insertTUserAddr(PreparedStatement statement, TUserAddr userAddr) throws SQLException {
        statement.setObject(1, userAddr.getId());
        statement.setObject(2, userAddr.getUserId());
        statement.setObject(3, userAddr.getLiveDetailedAddress());
        statement.setObject(4, userAddr.getNativePlaceDetailedAddress());
        statement.setObject(5, userAddr.getCreateTime());
        return new PrepareStatementHandler(statement, "TUserAddr");
    }

    /**
     * 转换联系人
     *
     * @param userBase  dx返回的联系人列表
     * @return  yjq执行写入的联系人列表
     */
    public List<TUserContact> parseContact(String userId,DUserBase userBase) {
        List<TUserContact> userContacts = new ArrayList<TUserContact>();
        TUserContact contact;
        String strResult1 = null;
        for (DReferenceLinkman linkman : userBase.getLinkmans()) {
            contact = new TUserContact();
            if (linkman.getRelation().contains("父") || linkman.getRelation().contains("母")) {
                strResult1 = "0";
            }
            if (linkman.getRelation().contains("配偶")) {
                strResult1 = "2";
            }
            if (linkman.getRelation().contains("兄弟")) {
                strResult1 = "3";
            }
            if (linkman.getRelation().contains("子女")) {
                strResult1 = "5";
            }
            if (linkman.getRelation().contains("朋友")) {
                strResult1 = "6";
            }
            if (linkman.getRelation().contains("其他")) {
                strResult1 = "8";
            }
            contact.setId(UserIdUtils.generateShortUuid());
            contact.setUserId(userId);
            contact.setContactRelationship(strResult1);
            contact.setContactPeopleName(linkman.getName());
            contact.setContactPeoplePhone(linkman.getMobilePhone());
            userContacts.add(contact);
        }
        return userContacts;
    }

    /**
     *  写入 t_user_contact 表
     * @param statement SQL执行语句
     * @param contact  写入对象
     * @return  执行对象
     * @throws SQLException
     */
    public PrepareStatementHandler insertTUserContact(PreparedStatement statement, TUserContact contact) throws SQLException {
        statement.setObject(1, contact.getId());
        statement.setObject(2, contact.getUserId());
        statement.setObject(3, contact.getContactPeopleName());
        statement.setObject(4, contact.getContactPeoplePhone());
        statement.setObject(5, contact.getContactRelationship());
        statement.setObject(6, new Date());
        return new PrepareStatementHandler(statement, "TUserContact");
    }


    public static void main(String[] args) throws Exception {
        DruidPooledConnection connection = DBConnectionConfig.getDataSource().getConnection();
        BaseDBConnection baseConnection = new BaseDBConnection();
        List<String> qianpenIds = baseConnection.getQianpenIds(connection);
        for (String id:qianpenIds
             ) {
            DUserBase base = baseConnection.getDUserBase("82038");
            System.out.println(base);
        }
    }
}
