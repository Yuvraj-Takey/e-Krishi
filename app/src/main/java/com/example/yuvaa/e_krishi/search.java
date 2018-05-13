package com.example.yuvaa.e_krishi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class search extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    Button bsubmit;
    Spinner scity;
    Spinner scrop;
    TextView hello;
    public static String city;
    public static String crop, frmname, frmcity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        frmname = FarmerName.farmname;
        frmcity = FarmerName.farmcity;

        hello = (TextView)findViewById(R.id.hello);
        hello.setText("Hello, "+frmname);
        bsubmit = (Button)findViewById(R.id.bsubmit);
        scity = (Spinner)findViewById(R.id.bdcity);
        scrop = (Spinner)findViewById(R.id.bdcrop);

        bsubmit.setOnClickListener(this);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.city, android.R.layout.simple_spinner_dropdown_item);

        scity.setAdapter(adapter);
        //adapter.setDropDownViewResource();
        scity.setOnItemSelectedListener(this);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,R.array.crop, android.R.layout.simple_spinner_dropdown_item);

        scrop.setAdapter(adapter2);
        scrop.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId())
        {
            case R.id.bdcity:
                city = ((TextView) view).getText().toString();
                break;
            case R.id.bdcrop:
                crop = ((TextView) view).getText().toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bsubmit:

                if((crop.contains("Select Crop")) && (city.contains("Select City")))
                {
                    Toast.makeText(this,"Please select required fields",Toast.LENGTH_LONG).show();
                    break;
                }
                else if(city.contains("Select City"))
                {
                    Toast.makeText(this,"Please select City Name",Toast.LENGTH_LONG).show();
                    break;
                }
                else if(crop.contains("Select Crop"))
                {
                    Toast.makeText(this,"Please select Crop Name",Toast.LENGTH_LONG).show();
                    break;
                }


                startActivity(new Intent(this, cropview.class));
                break;
        }
    }
}