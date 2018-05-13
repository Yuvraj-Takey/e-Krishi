package com.example.yuvaa.e_krishi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class popup extends AppCompatActivity {

    TextView ename,eemail,ephone,ecity,ecrop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8),(int) (height*.5));

        String pname = cropview.mname;
        String pemail = cropview.memail;
        String pphone = cropview.mph;
        String pcity = cropview.mcity;
        String pcrop = cropview.mcrop;
       // Toast.makeText(popup.this,pname+pemail+pphone+pcity+pcrop, Toast.LENGTH_LONG).show();

        ename = (TextView) findViewById(R.id.bdname);
        ecrop = (TextView) findViewById(R.id.bdcrop);
        eemail = (TextView) findViewById(R.id.bdemail);
        ephone = (TextView) findViewById(R.id.bdphone);
        ecity = (TextView) findViewById(R.id.bdcity);

        ename.setText("Name : "+pname);
        ecrop.setText("Crop Rs : "+pcrop);
        eemail.setText("Email ID : "+pemail);
        ephone.setText("Phone Number : "+pphone);
        ecity.setText("City : "+pcity);


    }
}
