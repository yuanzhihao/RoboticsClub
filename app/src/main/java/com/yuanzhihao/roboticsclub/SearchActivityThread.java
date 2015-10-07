package com.yuanzhihao.roboticsclub;

import com.yuanzhihao.jdbc.JdbcUtils;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yuanzhihao on 15/10/6.
 */
public class SearchActivityThread extends Thread {
    private SearchActivityThreadListener searchActivityThreadListener;

    private String date;

    public static interface SearchActivityThreadListener {
        public void onThreadComplete(String content);
    }

    public SearchActivityThread(SearchActivityThreadListener searchActivityThreadListener, String date) {
        this.searchActivityThreadListener=searchActivityThreadListener;
        this.date=date;
    }

    @Override
    public void run() {
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        Date dateSql=Date.valueOf(date);
        ResultSet resultSet=jdbcUtils.selectActivityByDate(dateSql);
        try {
            if(resultSet.next())
                searchActivityThreadListener.onThreadComplete(resultSet.getString(3));
            else
                searchActivityThreadListener.onThreadComplete(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
