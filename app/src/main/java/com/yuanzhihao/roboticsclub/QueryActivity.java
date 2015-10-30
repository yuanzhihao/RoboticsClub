package com.yuanzhihao.roboticsclub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class QueryActivity extends AppCompatActivity {

    private TextView textView1;

    private TextView textView2;

    private TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        textView1=(TextView)findViewById(R.id.editText);
        textView2=(TextView)findViewById(R.id.editText2);
        textView3=(TextView)findViewById(R.id.editText3);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String username=bundle.getString("username");

    }
}
