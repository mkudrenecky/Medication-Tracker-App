package com.example.pccmedicare20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button start;
    ArrayList<String[]> meds = new ArrayList<String[]>();
    String[] aa = {"Add Medication","","","","","","","","",""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meds.add(aa);


        start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                show();//go to settings page
            }
        });

    }

    private void show(){

        Intent intent = new Intent(this,show.class);
        Bundle bundle = new Bundle();
        intent.putExtra("xxx", meds);
        //intent.putExtras(bundle);

        startActivity(intent);
    }
}