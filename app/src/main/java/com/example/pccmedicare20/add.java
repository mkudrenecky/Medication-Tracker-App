package com.example.pccmedicare20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class add extends AppCompatActivity {
    ArrayList<String[]> meds = new ArrayList<String[]>();
    String op;
    Button sub;
    Button dbut;
    EditText name;
    EditText dose;
    EditText sdate;
    EditText refdate;
    EditText note;
    TextView Title;
    Spinner freq;
    Spinner nfs;
    Spinner rema;
    Spinner syear;
    Spinner smonth;
    Spinner sday;
    Spinner ryear;
    Spinner rmonth;
    Spinner rday;
    ToggleButton rem;
    ToggleButton rrem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Intent in = getIntent();
        meds = (ArrayList<String[]>) in.getSerializableExtra("xxx");// get curent list of meds
        Bundle bundle = in.getExtras();
        op = bundle.getString("op");
        Title = findViewById(R.id.Title);
        // tv.setText(meds.get(0)[0]);

        sub = (Button) findViewById(R.id.sub);
        dbut = (Button) findViewById(R.id.dbut);
        name = (EditText) findViewById(R.id.Name);
        dose = (EditText) findViewById(R.id.dose);
        refdate = (EditText) findViewById(R.id.refdate);
        sdate = (EditText) findViewById(R.id.sdate);
        note = (EditText) findViewById(R.id.notes);
        rem = (ToggleButton) findViewById(R.id.remtf);
        rrem = (ToggleButton) findViewById(R.id.rtf);
        freq = (Spinner) findViewById(R.id.freq);
        nfs = (Spinner) findViewById(R.id.nfsound);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.freq,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        freq.setAdapter(adapter);

        rema = (Spinner) findViewById(R.id.rema);
        ArrayAdapter<CharSequence> adap = ArrayAdapter.createFromResource(this, R.array.ra,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        rema.setAdapter(adap);

        nfs = (Spinner) findViewById(R.id.nfsound);
        ArrayAdapter<CharSequence> ada = ArrayAdapter.createFromResource(this, R.array.sounds,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        nfs.setAdapter(ada);

        syear = (Spinner) findViewById(R.id.syear);
        ArrayAdapter<CharSequence> years = ArrayAdapter.createFromResource(this, R.array.years,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        syear.setAdapter(years);

        smonth = (Spinner) findViewById(R.id.smonth);
        ArrayAdapter<CharSequence> months = ArrayAdapter.createFromResource(this, R.array.months,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        smonth.setAdapter(months);

        sday = (Spinner) findViewById(R.id.sday);
        ArrayAdapter<CharSequence> days = ArrayAdapter.createFromResource(this, R.array.days31,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        sday.setAdapter(days);

        ryear = (Spinner) findViewById(R.id.ryear);
        ArrayAdapter<CharSequence> yearr = ArrayAdapter.createFromResource(this, R.array.years,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        ryear.setAdapter(yearr);

        rmonth = (Spinner) findViewById(R.id.rmonth);
        ArrayAdapter<CharSequence> monthr = ArrayAdapter.createFromResource(this, R.array.months,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        rmonth.setAdapter(monthr);

        rday = (Spinner) findViewById(R.id.rday);
        ArrayAdapter<CharSequence> dayr = ArrayAdapter.createFromResource(this, R.array.days31,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        rday.setAdapter(dayr);

        if (op.equals("ed")) {// edit option was sellected
            Title.setText("Edit Medication");
            String me = bundle.getString("me");

            for (String[] a : meds) {
                if (me.equals(""))
                    break;
                if (a[0].equals(me)) {
                    if (a[0].equals("Add Medication")) {
                        meds.remove(a);
                        break;
                    }
                    meds.remove(a);
                    name.setText(a[0]);
                    dose.setText(a[1]);
                    freq.setSelection(frin(a[2]));
                    // rem.setText(a[3]);
                    if (a[3].equals("yes"))
                        rem.setChecked(true);
                    else
                        rem.setChecked(false);
                    // sdate.setText(a[4]);
                    ssdt(a[4]);
                    rema.setSelection(rin(a[5]));
                    // refdate.setText(a[6]);
                    rsdt(a[6]);
                    if (a[7].equals("yes"))
                        rrem.setChecked(true);
                    else
                        rrem.setChecked(false);

                    nfs.setSelection(nfin(a[8]));
                    note.setText(a[9]);
                }

            }

        }

        sub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean dtok = true;
                String Medname = name.getText().toString();
                String Dose = dose.getText().toString();
                String Fr = (String) freq.getSelectedItem();
                String Re;
                if (rem.isChecked())
                    Re = "yes";
                else
                    Re = "No";
                // String SD = sdate.getText().toString();
                String SD = sdt();
                if (SD.equals("nd")) {
                    dtok = false;
                    Toast.makeText(getApplicationContext(), "Day not in month.", Toast.LENGTH_SHORT).show();
                }

                String Ra = (String) rema.getSelectedItem();
                // String RD = refdate.getText().toString();
                String RD = rdt();
                if (RD.equals("nd")) {
                    dtok = false;
                    Toast.makeText(getApplicationContext(), "Day not in month.", Toast.LENGTH_SHORT).show();
                }

                String Rf;
                if (rrem.isChecked())
                    Rf = "yes";
                else
                    Rf = "No";
                String Nf = (String) nfs.getSelectedItem();
                String Nt = note.getText().toString();
                if (dtok) {
                    meds.add(new String[] { Medname, Dose, Fr, Re, SD, Ra, RD, Rf, Nf, Nt });
                    if (meds.get(0)[0].equals("Add Medication"))
                        meds.remove(0);
                    home();
                }
            }
        });
        dbut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                home();
            }
        });

    }

    private String sdt() {
        String dt = "";
        String month = (String) smonth.getSelectedItem();
        String day = (String) sday.getSelectedItem();
        if (month.equals("04") || month.equals("06") || month.equals("09") || month.equals("11")) {
            if (day.equals("31"))
                return ("nd");
        }
        if (month.equals("02")) {
            if (day.equals("31") || day.equals("30") || day.equals("39"))
                return ("nd");
        }
        dt = syear.getSelectedItem() + "-" + smonth.getSelectedItem() + "-" + sday.getSelectedItem();

        return dt;
    }

    private void ssdt(String a) {
        String full = a;
        String year;
        String month;
        String day;
        year = a.substring(0, 4);
        month = a.substring(5, a.length() - 3);
        day = a.substring(a.length() - 2, a.length());

        String[] b = getResources().getStringArray(R.array.years);
        for (int i = 0; i < (b).length; i++) {
            if (year.equals(b[i]))
                syear.setSelection(i);
        }

        String[] c = getResources().getStringArray(R.array.months);
        for (int i = 0; i < (c).length; i++) {
            if (month.equals(c[i]))
                smonth.setSelection(i);
        }

        String[] d = getResources().getStringArray(R.array.days31);
        for (int i = 0; i < (d).length; i++) {
            if (day.equals(d[i]))
                sday.setSelection(i);
        }

    }

    private String rdt() {
        String dt = "";
        String month = (String) rmonth.getSelectedItem();
        String day = (String) rday.getSelectedItem();
        if (month.equals("04") || month.equals("06") || month.equals("09") || month.equals("11")) {
            if (day.equals("31"))
                return ("nd");
        }
        if (month.equals("02")) {
            if (day.equals("31") || day.equals("30") || day.equals("39"))
                return ("nd");
        }
        dt = ryear.getSelectedItem() + "-" + rmonth.getSelectedItem() + "-" + rday.getSelectedItem();

        return dt;
    }

    private void rsdt(String a) {
        String full = a;
        String year;
        String month;
        String day;
        year = a.substring(0, 4);
        month = a.substring(5, a.length() - 3);
        day = a.substring(a.length() - 2, a.length());

        String[] b = getResources().getStringArray(R.array.years);
        for (int i = 0; i < (b).length; i++) {
            if (year.equals(b[i]))
                ryear.setSelection(i);
        }

        String[] c = getResources().getStringArray(R.array.months);
        for (int i = 0; i < (c).length; i++) {
            if (month.equals(c[i]))
                rmonth.setSelection(i);
        }

        String[] d = getResources().getStringArray(R.array.days31);
        for (int i = 0; i < (d).length; i++) {
            if (day.equals(d[i]))
                rday.setSelection(i);
        }

    }

    private int frin(String s) {
        String[] a = getResources().getStringArray(R.array.freq);
        for (int i = 0; i < (a).length; i++) {
            if (s.equals(a[i]))
                return i;
        }

        return 0;
    }

    private int rin(String s) {
        String[] a = getResources().getStringArray(R.array.ra);
        for (int i = 0; i < (a).length; i++) {
            if (s.equals(a[i]))
                return i;
        }

        return 0;
    }

    private int nfin(String s) {
        String[] a = getResources().getStringArray(R.array.sounds);
        for (int i = 0; i < (a).length; i++) {
            if (s.equals(a[i]))
                return i;
        }

        return 0;
    }

    private void home() {
        Intent intent = new Intent(this, show.class);
        Bundle bundle = new Bundle();

        intent.putExtra("xxx", meds);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}