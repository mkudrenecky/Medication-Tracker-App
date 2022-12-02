package com.example.pccmedicare20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class settings extends AppCompatActivity {
    Button gobackb;
    ArrayList<String[]> meds = new ArrayList<String[]>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        gobackb = (Button)findViewById(R.id.gobackb);
        Intent in = getIntent();
        meds = (ArrayList<String[]>) in.getSerializableExtra("xxx");// get curent list of meds

        gobackb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                home();
            }
        });
    }
    private void home(){
        Intent intent = new Intent(this,show.class);
        Bundle bundle = new Bundle();

        intent.putExtra("xxx", meds);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}