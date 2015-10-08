package com.yuanzhihao.roboticsclub;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.yuanzhihao.roboticsclub.CalendarView.OnItemClickListener;
import java.util.Date;
import java.util.HashMap;

import android.content.Intent;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CalendarView calendarView;

    private ImageButton calendarLeft;

    private ImageButton calendarRight;

    private HashMap<String,String> map=new HashMap<String, String>();

    private TextView textView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalendarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarFragment newInstance(String param1, String param2) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        map.put("Jan","01");
        map.put("Feb","02");
        map.put("Mar","03");
        map.put("Apr","04");
        map.put("May","05");
        map.put("Jun","06");
        map.put("Jul","07");
        map.put("Aug","08");
        map.put("Sep","09");
        map.put("Oct","10");
        map.put("Nov","11");
        map.put("Dec","12");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_calendar, container, false);
        calendarView=(CalendarView)view.findViewById(R.id.calendar_view);
        calendarView.setOnItemClickListener(new calendarItemClickListener());
        calendarLeft=(ImageButton)view.findViewById(R.id.calendarLeft);
        calendarRight=(ImageButton)view.findViewById(R.id.calendarRight);
        calendarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String leftYearAndmonth = calendarView.clickLeftMonth();
                textView.setText(leftYearAndmonth);
            }
        });
        calendarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rightYearAndmonth = calendarView.clickRightMonth();
                textView.setText(rightYearAndmonth);
            }
        });
        textView=(TextView)view.findViewById(R.id.calendarCenter);
        textView.setText(calendarView.getYearAndmonth());
        return view;
    }

    class calendarItemClickListener implements OnItemClickListener{
        @Override
        public void OnItemClick(Date date) {
            Intent intent=new Intent(getActivity(), SetActivitiesActivity.class);
            String[] DATE=date.toString().split(" ");
            String onlyDate=DATE[5];
            onlyDate+="-"+map.get(DATE[1])+"-"+DATE[2];
            String identity=CalendarFragment.this.getActivity().getIntent().getStringExtra("identity");
            intent.putExtra("Date",onlyDate);
            intent.putExtra("identity", identity);
            startActivity(intent);
            calendarView.invalidate();
        }
    }
}
