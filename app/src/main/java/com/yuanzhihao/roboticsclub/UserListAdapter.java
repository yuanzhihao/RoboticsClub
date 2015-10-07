package com.yuanzhihao.roboticsclub;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;
import java.util.List;

/**
 * Created by yuanzhihao on 15/10/5.
 */
public class UserListAdapter extends ArrayAdapter<String> {
    private List<String> listTag;

    public UserListAdapter(Context context, List<String> objects, List<String> tags) {
        super(context, 0, objects);
        listTag=tags;
    }

    
}
