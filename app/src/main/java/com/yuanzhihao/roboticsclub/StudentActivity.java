package com.yuanzhihao.roboticsclub;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Fragment;

public class StudentActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout courseChosen;
    private LinearLayout fundraising;
    private LinearLayout calenarStudent;

    private ImageView icCourseChosen;
    private ImageView icFundraising;
    private ImageView icCalendarStudent;

    private TextView textCourseChosen;
    private TextView textFundraising;
    private TextView textCalendarStudent;

    private Fragment calendarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        initView();
        initEvent();
        initFragment(2);
        icCalendarStudent.setImageResource(R.drawable.user_press);
        textCalendarStudent.setTextColor(0xff1B940A);
    }

    private void initView() {
        this.courseChosen=(LinearLayout)this.findViewById(R.id.course_chosen);
        this.fundraising=(LinearLayout)this.findViewById(R.id.fundraising);
        this.calenarStudent=(LinearLayout)this.findViewById(R.id.calendar_student);
        this.icCourseChosen=(ImageView)this.findViewById(R.id.ic_course_chosen);
        this.icFundraising=(ImageView)this.findViewById(R.id.ic_fundraising);
        this.icCalendarStudent=(ImageView)this.findViewById(R.id.ic_calendar_student);
        this.textCourseChosen=(TextView)this.findViewById(R.id.text_course_chosen);
        this.textFundraising=(TextView)this.findViewById(R.id.text_fundraising);
        this.textCalendarStudent=(TextView)this.findViewById(R.id.text_calendar_student);
    }

    private void initEvent() {
        courseChosen.setOnClickListener(this);
        fundraising.setOnClickListener(this);
        calenarStudent.setOnClickListener(this);
    }

    private void initFragment(int index) {
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        switch (index) {
            case 0:
                /*if(userManageFragment==null) {
                    userManageFragment=new UserFragment();
                    fragmentTransaction.add(R.id.content, userManageFragment);
                }
                else {
                    fragmentTransaction.show(userManageFragment);
                }*/
                break;
            case 1:
                /*if(calendarFragment==null) {
                    calendarFragment=new CalendarFragment();
                    fragmentTransaction.add(R.id.content, calendarFragment);
                }
                else {
                    fragmentTransaction.show(calendarFragment);
                }*/
                break;
            case 2:
                if(calendarFragment==null) {
                    calendarFragment=new CalendarFragment();
                    fragmentTransaction.add(R.id.content_student, calendarFragment);
                }
                else {
                    fragmentTransaction.show(calendarFragment);
                }
        }

        fragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if(calendarFragment!=null)
            fragmentTransaction.hide(calendarFragment);
    }

    public void onClick(View view) {
        reset();
        switch (view.getId()) {
            case R.id.course_chosen:
                break;
            case R.id.fundraising:
                break;
            case R.id.calendar_student:
                icCalendarStudent.setImageResource(R.drawable.calendar_press);
                textCalendarStudent.setTextColor(0xff1B940A);
                initFragment(2);
                break;
        }
    }

    private void reset() {
        icCourseChosen.setImageResource(R.drawable.calendar);
        icFundraising.setImageResource(R.drawable.user);
        icCalendarStudent.setImageResource(R.drawable.calendar);
        textCourseChosen.setTextColor(getResources().getColor(R.color.black));
        textFundraising.setTextColor(getResources().getColor(R.color.black));
        textCalendarStudent.setTextColor(getResources().getColor(R.color.black));
    }
}
