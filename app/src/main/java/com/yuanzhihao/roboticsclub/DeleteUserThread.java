package com.yuanzhihao.roboticsclub;

import com.yuanzhihao.jdbc.JdbcUtils;

import java.sql.SQLException;

/**
 * Created by yuanzhihao on 15/10/26.
 */
public class DeleteUserThread extends Thread{
    public String username;

    public DeleteUserThread(String username) {
        this.username=username;
    }

    @Override
    public void run() {
        JdbcUtils jdbcUtils=new JdbcUtils();
        jdbcUtils.getConnection();
        jdbcUtils.deleteUser(username);
        try {
            jdbcUtils.releaseConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
