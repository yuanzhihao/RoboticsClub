package com.yuanzhihao.roboticsclub;

import com.yuanzhihao.jdbc.JdbcUtils;

import java.sql.SQLException;

/**
 * Created by yuanzhihao on 15/10/19.
 */
public class AddUserThread extends Thread {
    private User user;

    public  AddUserThread(User user) {
        this.user=user;
    }

    private String[] getDetail()
    {
        String identity=user.getIdentity();
        if(identity.equals("admin")) {
            return getDetailByAdmin();
        }
        return null;
    }

    private String[] getDetailByAdmin() {
        String[] args=new String[4];
        Admin admin=(Admin)user;
        args[0]=admin.getUsername();
        args[1]=admin.getPassword();
        args[2]=admin.getIdentity();
        args[3]=admin.getName();
        return args;
    }

    @Override
    public void run() {
        String[] args=getDetail();
        if(args[2].equals("admin")) {
            JdbcUtils jdbcUtils=new JdbcUtils();
            jdbcUtils.getConnection();
            jdbcUtils.insertAdmin(args);
            try {
                jdbcUtils.releaseConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
