package com.yuanzhihao.roboticsclub;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BasicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BasicFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Toolbar toolbar;

    private BasicListener basicListener;

    private EditText username;

    private EditText password;

    private EditText confirmPassword;

    private static final String[] identity={"admin","student","volunteer","sponsor"};

    private TextView textView;

    private Spinner spinner;

    private String i;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BasicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BasicFragment newInstance(String param1, String param2) {
        BasicFragment fragment = new BasicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public BasicFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_basic, container, false);
        toolbar=(Toolbar)view.findViewById(R.id.extend_toolbar);
        AppCompatActivity appCompatActivity=(AppCompatActivity)getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        username=(EditText)view.findViewById(R.id.register_email);
        password=(EditText)view.findViewById(R.id.register_password);
        confirmPassword=(EditText)view.findViewById(R.id.confirm_password);
        textView=(TextView)view.findViewById(R.id.spinnerText);
        spinner=(Spinner)view.findViewById(R.id.spinner01);
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                i = identity[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                i=identity[0];
            }
        });
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,BasicFragment.identity);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return view;
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_next:
                    String usernameText,passwordText,confirmPasswordText,identity;
                    usernameText=username.getText().toString();
                    passwordText=password.getText().toString();
                    confirmPasswordText=confirmPassword.getText().toString();
                    if(passwordText!=null&&passwordText.equals(confirmPasswordText)) {
                        basicListener=(BasicListener)getActivity();
                        basicListener.onNext(usernameText,passwordText,i);
                    }
                    else {
                        password.setText(null);
                        confirmPassword.setText(null);
                        password.setHint("incosistent password");
                        confirmPassword.setHint("incosistent password");
                    }
                    break;
            }
            return true;
        }
    };

    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.next,menu);
        super.onCreateOptionsMenu(menu,inflater);
    }


    public static interface BasicListener {
        public void onNext(String username, String password,String identity);
    }
}
