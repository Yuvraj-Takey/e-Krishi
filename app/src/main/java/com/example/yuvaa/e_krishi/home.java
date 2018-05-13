package com.example.yuvaa.e_krishi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class home extends AppCompatActivity implements View.OnClickListener {

    Button bfarm, bdeal;
    TextView tabout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bfarm = (Button)findViewById(R.id.bfarm);
        bdeal = (Button)findViewById(R.id.bdeal);
        tabout = (TextView)findViewById(R.id.tabout);

        tabout.setOnClickListener(this);
        bdeal.setOnClickListener(this);
        bfarm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.bdeal:
            {
                startActivity(new Intent(this, login.class));
                break;
            }
            case R.id.bfarm:
            {
                startActivity(new Intent(this, FarmerName.class));
                break;
            }
            case R.id.tabout:
            {
                startActivity(new Intent(this, about.class));
                break;
            }
        }
    }
}
