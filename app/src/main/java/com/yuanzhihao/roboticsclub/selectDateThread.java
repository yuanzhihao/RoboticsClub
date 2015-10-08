package com.yuanzhihao.roboticsclub;

import com.yuanzhihao.jdbc.JdbcUtils;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.RecursiveTask;

/**
 * Created by yuanzhihao on 15/10/7.
 */
public class selectDateThread extends RecursiveTask<ArrayList> {

    private ArrayList<String> list=new ArrayList<String>();

    private String yearAndMonth;

    private HashMap<String,String> map=new HashMap<String, String>();

    public selectDateThread(String yearAndMonth) {
        this.yearAndMonth=yearAndMonth;
    }

    @Override
    protected ArrayList<String> compute() {
        map.put("Jan","01");
        map.put("Feb","02");
        map.put("Mar","03");
        map.put("Apr","04");
        map.put("May","05");
        map.put("Jun","06");
        map.put("Jul","07");
        map.put("Aug","08");
        map.put("Sep","09");
        map.put("Oct","10");
        map.put("Nov","11");
        map.put("Dec","12");
        String[] temp=yearAndMonth.split("-");
        String newYearAndMonth=temp[0]+"-"+map.get(temp[1]);
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        ResultSet resultSet=jdbcUtils.selectAllDate();
        try {
            while (resultSet.next()) {
                if(resultSet.getDate(1).toString().contains(newYearAndMonth))
                    list.add(resultSet.getDate(1).toString().split("-")[2]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
