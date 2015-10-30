package com.yuanzhihao.roboticsclub;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link ExtendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExtendFragment extends Fragment {

    private String username;

    private String password;

    private String identity;

    private EditText name;

    private Toolbar toolbar;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @param password
     * @param identity
     * @param username
     * @return A new instance of fragment ExtendFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExtendFragment newInstance(String username,String password,String identity) {
        ExtendFragment fragment = new ExtendFragment();
        Bundle args = new Bundle();
        args.putString("identity", identity);
        args.putString("username", username);
        args.putString("password", password);
        fragment.setArguments(args);
        return fragment;
    }

    public ExtendFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments()!=null) {
            this.username=getArguments().getString("username");
            this.password=getArguments().getString("password");
            this.identity=getArguments().getString("identity");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        if(identity.equals("student"))
            view=inflater.inflate(R.layout.fragment_extend_student, container, false);
        else if(identity.equals("volunteer"))
            view=inflater.inflate(R.layout.fragment_extend_volunteer, container, false);
        else if(identity.equals("sponsor"))
            view=inflater.inflate(R.layout.fragment_extend_sponsor,container,false);
        else {
            view=inflater.inflate(R.layout.fragment_extend_admin,container,false);
            name=(EditText)view.findViewById(R.id.editText_admin_name);
        }
        toolbar=(Toolbar)view.findViewById(R.id.submit_toolbar);
        AppCompatActivity appCompatActivity=(AppCompatActivity)getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        return view;
    }

    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.submit,menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_submit:
                    User user=null;
                    if(identity.equals("admin"))
                        user=new Admin(username,password,identity,name.getText().toString());
                    AddUserThread addUserThread=new AddUserThread(user);
                    addUserThread.start();
                    Intent intent=new Intent();
                    intent.putExtra("new", username);
                    getActivity().setResult(0x1001, intent);
                    getActivity().finish();
                    break;
            }
            return true;
        }
    };
}
