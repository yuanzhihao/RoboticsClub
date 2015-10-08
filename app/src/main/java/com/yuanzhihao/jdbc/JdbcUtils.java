package com.yuanzhihao.jdbc;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

/**
 * Created by yuanzhihao on 15/10/1.
 */
public class JdbcUtils {
    //数据库用户名
    private final String username = "root";

    //数据库密码
    private final String password = "123456";

    //数据库驱动信息
    private final String driver = "com.mysql.jdbc.Driver";

    //数据库地址
    private final String url = "jdbc:mysql://172.31.239.32:3306/robotics_club";

    //数据库的连接
    Connection connection;

    //SQL语句的执行对象
    PreparedStatement preparedStatement;

    //查询返回的结果集
    ResultSet resultSet;

    //注册驱动程序
    public JdbcUtils() {
        try {
            Class.forName(driver);
            Log.v("yzy", "注册驱动成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接数据库
     *
     * @return 数据库的连接对象
     */
    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //关闭数据库
    public void releaseConnection() throws SQLException {
        if(resultSet != null) {
            resultSet.close();
        }

        if(preparedStatement != null) {
            preparedStatement.close();
        }

        if(connection != null) {
            connection.close();
        }
    }

    public ResultSet selectAllDate() {
        connection=this.getConnection();
        try {
            preparedStatement = connection.prepareStatement("select date from calendar");
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet selectUserByUsername(String username) {
        connection = this.getConnection();
        try {
            preparedStatement = connection.prepareStatement("select username,password,identity from user where user.username=?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet selectAllUser() {
        connection=this.getConnection();
        try {
            preparedStatement = connection.prepareStatement("select username from user");
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet selectActivityByDate(Date date) {
        connection=getConnection();
        try {
            preparedStatement=connection.prepareStatement("select * from calendar where calendar.date=?");
            preparedStatement.setDate(1, date);
            resultSet=preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void updateCalendar(Date date, String activityContent) {
        connection=getConnection();
        try {
            preparedStatement=connection.prepareStatement("update calendar set activity_content=? where date=?");
            preparedStatement.setString(1,activityContent);
            preparedStatement.setDate(2, date);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertActivity(Date date, String activityContent) {
        connection=getConnection();
        try {
            preparedStatement=connection.prepareStatement("insert into calendar (date,activity_content) values(?,?)");
            preparedStatement.setString(2,activityContent);
            preparedStatement.setDate(1, date);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
