package com.example.pccmedicare20;



import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;

import java.util.ArrayList;

public class calendar extends AppCompatActivity {

    //private static final String TAG = "MainActivity";
    private static final String TAG = "calendar";
    String dt = "2022/11/14";
    ArrayList<String[]> meds = new ArrayList<String[]>();
    private TextView thedate, medInfo;
    private Button buttonBack;
    private CalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        thedate = (TextView) findViewById(R.id.textViewDate);
        medInfo = (TextView) findViewById(R.id.textViewMedInfo);
        buttonBack = (Button) findViewById(R.id.buttonBack);

        Intent incoming = getIntent();
        String MedDate = incoming.getStringExtra("MedDate"); // date value pulled from medication list
        //thedate.setText(date);
        meds = (ArrayList<String[]>) incoming.getSerializableExtra("xxx");// get curent list of meds

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
                String date = year + "/" + month + "/" + dayOfMonth;
                //Log.d(TAG, "onSelectedDayChange: yyyy/mm/dd:" + date);
                Intent intent = new Intent(calendar.this, MainActivity.class);
                intent.putExtra("date", date);
                //startActivity(intent);
                thedate.setText(date);
//                if (medDay==dayOfMonth) { // condition to display medication for selected day.
//                    medInfo.setText();
//                }
            }
        });
    }
    public void goBack (View view) {
        Intent intent = new Intent(this, show.class);
        startActivity(intent);
    }
}