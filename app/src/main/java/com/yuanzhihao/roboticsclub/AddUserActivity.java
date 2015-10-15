package com.yuanzhihao.roboticsclub;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

public class AddUserActivity extends AppCompatActivity implements BasicFragment.BasicListener{

    private BasicFragment basicFragment;

    private ExtendFragment extendFragment;

    private String username, password,identity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        initFragment(0);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater menuInflater=this.getMenuInflater();
        menuInflater.inflate(R.menu.next, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onNext(String username, String password, String identity) {
        this.username=username;
        this.password=password;
        this.identity=identity;
        initFragment(1);
    }

    private void initFragment(int index) {
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        switch (index) {
            case 0:
                basicFragment=new BasicFragment();
                fragmentTransaction.replace(R.id.user_information, basicFragment);
                break;
            case 1:
                extendFragment=ExtendFragment.newInstance(username,password,identity);
                fragmentTransaction.replace(R.id.content, extendFragment);
                fragmentTransaction.show(extendFragment);
                break;
        }

        fragmentTransaction.commit();
    }
}
