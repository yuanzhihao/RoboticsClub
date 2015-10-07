package com.yuanzhihao.roboticsclub;

import com.yuanzhihao.jdbc.JdbcUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yuanzhihao on 15/10/5.
 */
public class SelectThread extends Thread {
    private ThreadListener threadListener;

    public static interface ThreadListener{
        public void onThreadComplete(String content);
    }

    public SelectThread(ThreadListener threadListener) {
        this.threadListener=threadListener;
    }

    @Override
    public void run() {
        String content="";
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        ResultSet resultSet = jdbcUtils.selectAllUser();
        try {
            while (resultSet.next()) {
                content=content+":"+resultSet.getString(1);
            }
            jdbcUtils.releaseConnection();
            threadListener.onThreadComplete(content);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
