package com.hs.datatrans.database;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.hs.datatrans.config.DxiangInfoBasicConfig;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserIdConnection {

    public String getUserId(DruidPooledConnection connection, String qianpenId) {
        try {
            PreparedStatement statement = connection.prepareStatement(getSelectQianpenIdSql());
            statement.setObject(1, qianpenId);
            ResultSet resultSet = statement.executeQuery();
            String user_id = "";
            if (resultSet.next())
                user_id = resultSet.getString("user_id");
            return user_id;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("selectQianpenIdSql 异常，请核查");
        }
    }

    public String getSelectQianpenIdSql() {
        return DxiangInfoBasicConfig.getConfig().getProperty("db.sql.getUserId");
    }
}
