package com.yuanzhihao.roboticsclub;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout userManage;
    private LinearLayout calendar;

    private ImageView icUserManage;
    private ImageView icCalendar;

    private TextView textUserManage;
    private TextView textCalendar;

    private Fragment userManageFragment;
    private Fragment calendarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user);

        initView();
        initEvent();
        initFragment(0);
        icUserManage.setImageResource(R.drawable.user_press);
        textUserManage.setTextColor(0xff1B940A);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater menuInflater=this.getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initView() {
        this.userManage=(LinearLayout)this.findViewById(R.id.user_manage);
        this.calendar=(LinearLayout)this.findViewById(R.id.calendar);
        this.icUserManage=(ImageView)this.findViewById(R.id.ic_user_manage);
        this.icCalendar=(ImageView)this.findViewById(R.id.ic_calendar);
        this.textUserManage=(TextView)this.findViewById(R.id.text_user_manage);
        this.textCalendar=(TextView)this.findViewById(R.id.text_calendar);
    }

    private void initEvent() {
        userManage.setOnClickListener(this);
        calendar.setOnClickListener(this);
    }

    private void initFragment(int index) {
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        switch (index) {
            case 0:
                if(userManageFragment==null) {
                    userManageFragment=new UserFragment();
                    fragmentTransaction.add(R.id.content, userManageFragment);
                }
                else {
                    fragmentTransaction.show(userManageFragment);
                }
                break;
            case 1:
                if(calendarFragment==null) {
                    calendarFragment=new CalendarFragment();
                    fragmentTransaction.add(R.id.content, calendarFragment);
                }
                else {
                    fragmentTransaction.show(calendarFragment);
                }
        }

        fragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if(userManageFragment!=null)
            fragmentTransaction.hide(userManageFragment);
        if(calendarFragment!=null)
            fragmentTransaction.hide(calendarFragment);
    }

    @Override
    public void onClick(View view) {
        reset();
        switch (view.getId()) {
            case R.id.user_manage:
                icUserManage.setImageResource(R.drawable.user_press);
                textUserManage.setTextColor(0xff1B940A);
                initFragment(0);
                break;
            case R.id.calendar:
                icCalendar.setImageResource(R.drawable.calendar_press);
                textCalendar.setTextColor(0xff1B940A);
                initFragment(1);
                break;
        }
    }

    private void reset() {
        icCalendar.setImageResource(R.drawable.calendar);
        icUserManage.setImageResource(R.drawable.user);
        textUserManage.setTextColor(getResources().getColor(R.color.black));
        textCalendar.setTextColor(getResources().getColor(R.color.black));
    }

    public Fragment getUserManageFragment() {
        return userManageFragment;
    }

    public void setUserManageFragment(Fragment userManageFragment) {
        this.userManageFragment = userManageFragment;
    }
}
