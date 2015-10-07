package com.yuanzhihao.roboticsclub;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLException;

public class SetActivitiesActivity extends AppCompatActivity implements SearchActivityThread.SearchActivityThreadListener{

    private Toolbar toolbar;

    private TextView textView;

    private EditText editText;

    private String activityContent;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_activities);

        this.textView=(TextView)findViewById(R.id.activity_date);
        String temp=getIntent().getStringExtra("Date");
        textView.setText(temp);
        this.editText=(EditText)findViewById((R.id.activity_content));
        if(!getIntent().getStringExtra("identity").equals("admin")) {
            editText.setEnabled(false);
        }
        showProgress(true);
        Thread thread=new SearchActivityThread(this,temp);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {

        }
        showProgress(false);
        editText.setText(activityContent);


        this.toolbar=(Toolbar)this.findViewById(R.id.activity_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater menuInflater=this.getMenuInflater();
        menuInflater.inflate(R.menu.set_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_save:
                    new SetActivitiesThread(textView.getText().toString(), editText.getText().toString()).start();
                    SetActivitiesActivity.this.finish();
                    break;
            }
            return true;
        }
    };

    public void onThreadComplete(String content) {
        this.activityContent=content;
    }

    private void showProgress(final boolean show) {
        if(show==true)
            progressDialog= ProgressDialog.show(SetActivitiesActivity.this, "", "loading. Please wait.");
        else
            progressDialog.dismiss();
    }
}
