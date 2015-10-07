package com.yuanzhihao.roboticsclub;

import android.app.Activity;

import java.sql.ResultSet;
import com.yuanzhihao.jdbc.JdbcUtils;
import java.sql.SQLException;

/**
 * Created by yuanzhihao on 15/10/3.
 */
public class LoginThread extends Thread {
    private ThreadStateListener threadStateListener;

    private String username;

    private String password;

    public static interface ThreadStateListener {
        public void onThreadComplete(boolean isSuccess, String identity);
    }

    public LoginThread(ThreadStateListener threadStateListener, String username, String password) {
        this.threadStateListener=threadStateListener;
        this.username=username;
        this.password=password;
    }

    @Override
    public void run() {
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        ResultSet resultSet = jdbcUtils.selectUserByUsername(username);
        try {
            while (resultSet.next()) {
                if(username.equals(resultSet.getString(1))&&password.equals(resultSet.getString(2))) {
                    String temp=resultSet.getString(3);
                    resultSet.close();
                    jdbcUtils.releaseConnection();
                    threadStateListener.onThreadComplete(true,temp);
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        threadStateListener.onThreadComplete(false,null);
    }
}
