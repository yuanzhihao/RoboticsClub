package com.yuanzhihao.roboticsclub;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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
        return inflater.inflate(R.layout.fragment_extend, container, false);
    }



}
