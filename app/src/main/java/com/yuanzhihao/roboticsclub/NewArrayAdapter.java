package com.yuanzhihao.roboticsclub;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.ArraySwipeAdapter;

import java.util.List;

/**
 * Created by yuanzhihao on 15/10/20.
 */
public class NewArrayAdapter<T> extends ArraySwipeAdapter {

    private Context context;

    public NewArrayAdapter(Context context, int resource) {
        super(context, resource);
        this.context=context;
    }

    public NewArrayAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
        this.context=context;
    }

    public NewArrayAdapter(Context context, int resource, Object[] objects) {
        super(context, resource, objects);
        this.context=context;
    }

    public NewArrayAdapter(Context context, int resource, int textViewResourceId, Object[] objects) {
        super(context, resource, textViewResourceId, objects);
        this.context=context;
    }

    public NewArrayAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.context=context;
    }

    public NewArrayAdapter(Context context, int resource, int textViewResourceId, List objects) {
        super(context, resource, textViewResourceId, objects);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=super.getView(position, convertView, parent);
        ImageView delete=(ImageView)view.findViewById(R.id.trash);
        TextView textView=(TextView)view.findViewById(R.id.text_one);
        delete.setTag(textView.getText());
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,(String)v.getTag(),Toast.LENGTH_SHORT).show();
                AdminActivity adminActivity=(AdminActivity)context;
                UserFragment userFragment=(UserFragment)adminActivity.getUserManageFragment();
                userFragment.deleteUser((String)v.getTag());
                adminActivity=null;
                userFragment=null;
            }
        });
        return view;
    }

    @Override
    public int getSwipeLayoutResourceId(int i) {
        return R.id.swipe;
    }


}
