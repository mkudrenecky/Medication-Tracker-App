package com.example.pccmedicare20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class show extends AppCompatActivity {

    ArrayList<String[]> meds = new ArrayList<String[]>();
    // String[][] meds = new String [10][];
    Spinner spinner1;
    Button sbut;
    Button abut;
    Button ebut;
    Button tbut;
    TextView a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        a=findViewById(R.id.textView2);

       // list.add(aa);
       //list.add(bb);
        sbut = findViewById(R.id.sbut);
        abut = findViewById(R.id.abut);
        ebut = findViewById(R.id.ebut);
        tbut = findViewById(R.id.tbut);
        spinner1 = (Spinner) findViewById(R.id.spinner);

        Intent in = getIntent();
        meds = (ArrayList<String[]>) in.getSerializableExtra("xxx");// get curent list of meds
        String B = "";
        for(String[] a: meds) {
            B=B+"\n"+"- "+a[0]+" "+a[1]+" "+a[2];
        }
        a.setText(B);

        ArrayList<String> meds1 = new ArrayList<>();
        // meds.forEach((n) -> meds1.add(n[0]));
        meds1.add("select medication ");
        for(String[] a: meds){
            if(a[0].equals("Add Medication"))
                continue;
            meds1.add(a[0]);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, meds1);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter);
// Apply the adapter to the spinner

        sbut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setings();
            }
        });

        abut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                add(); //go to add page
            }
        });

        tbut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                track();
            }
        });

        ebut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               String se= (String) spinner1.getSelectedItem();
                ed(se);
            }
        });

    }
    private void add(){

        Intent inte = new Intent(this,add.class);
        Bundle bundle = new Bundle();

        inte.putExtra("xxx", meds);
        bundle.putString("op","add");
        inte.putExtras(bundle);

        startActivity(inte);
    }

    private void ed(String me){

        Intent inte = new Intent(this,add.class);
        Bundle bundle = new Bundle();

        inte.putExtra("xxx", meds);
        bundle.putString("op","ed");
        bundle.putString("me",me);
        inte.putExtras(bundle);


        startActivity(inte);
    }

    private void track(){

        Intent inten = new Intent(this,track.class);
        Bundle bundle = new Bundle();

        inten.putExtra("xxx", meds);
        // bundle.putString("op","ed");
        // bundle.putString("me",me);
        inten.putExtras(bundle);

        startActivity(inten);
    }
    private void setings(){

        Intent intente = new Intent(this,settings.class);
        Bundle bundle = new Bundle();

        intente.putExtra("xxx", meds);
        // bundle.putString("op","ed");
        // bundle.putString("me",me);
        intente.putExtras(bundle);

        startActivity(intente);
    }

}