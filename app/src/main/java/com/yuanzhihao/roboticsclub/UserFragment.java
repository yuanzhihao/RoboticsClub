package com.yuanzhihao.roboticsclub;


import android.app.ProgressDialog;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment implements SelectThread.ThreadListener, SearchView.OnQueryTextListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Toolbar toolbar;

    ProgressDialog progressDialog;

    private ArrayList<String> mAllList=new ArrayList<String>();

    private SearchView searchView;

    private ListView listView;

    private String content;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_user, container, false);
        this.toolbar=(Toolbar)view.findViewById(R.id.admin_toolbar);
        AppCompatActivity appCompatActivity=(AppCompatActivity)getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        searchView=(SearchView)view.findViewById(R.id.search_user);
        listView=(ListView)view.findViewById(R.id.user_list);
        SelectThread selectThread=new SelectThread(this);
        selectThread.start();
        showProgress(true);
        try {
            selectThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String[] s=content.split(":");
        for(int i=1;i<s.length;i++) {
            mAllList.add(s[i]);
        }
        s=new String[mAllList.size()];
        s=mAllList.toArray(s);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, s));
        showProgress(false);
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(false);
        return view;
    }

    private void showProgress(final boolean show) {
        if(show==true)
            progressDialog= ProgressDialog.show(getActivity(), "", "Loading data. Please wait.");
        else
            progressDialog.dismiss();
    }

    @Override
    public void onThreadComplete(String content) {
        this.content=content;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Object[] obj = searchItem(newText);
        updateLayout(obj);
        return false;
    }

    public Object[] searchItem(String name) {
        ArrayList<String> mSearchList = new ArrayList<String>();
        for (int i = 0; i < mAllList.size(); i++) {
            int index = mAllList.get(i).indexOf(name);
            // 存在匹配的数据
            if (index != -1) {
                mSearchList.add(mAllList.get(i));
            }
        }
        return mSearchList.toArray();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        // TODO Auto-generated method stub
        return false;
    }

    public void updateLayout(Object[] obj) {
        listView.setAdapter(new ArrayAdapter<Object>(getActivity(),	android.R.layout.simple_expandable_list_item_1, obj));
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            String msg="";
            switch (item.getItemId()) {
                case R.id.action_add:
                    msg+="click add";
                    break;
                case R.id.action_logout:
                    msg+="click logout";
                    break;
                case R.id.action_help:
                    msg+="click help";
                    break;
            }
            return true;
        }
    };
}
