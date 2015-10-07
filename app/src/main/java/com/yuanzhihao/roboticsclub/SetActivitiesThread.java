package com.yuanzhihao.roboticsclub;

import com.yuanzhihao.jdbc.JdbcUtils;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yuanzhihao on 15/10/6.
 */
public class SetActivitiesThread extends Thread {

    private String date;

    private String activityContent;

    public SetActivitiesThread(String date, String activityContent) {
        this.date=date;
        this.activityContent=activityContent;
    }

    public void run() {
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        Date dateSql=Date.valueOf(date);
        ResultSet resultSet=jdbcUtils.selectActivityByDate(dateSql);
        try {
            if(resultSet.next())
                jdbcUtils.updateCalendar(dateSql, activityContent);
            else
                jdbcUtils.insertActivity(dateSql, activityContent);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
