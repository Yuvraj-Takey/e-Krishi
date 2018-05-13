package com.example.yuvaa.e_krishi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class thnku extends AppCompatActivity implements View.OnClickListener {

    public String dname;
    TextView name;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thnku);

        dname = addproduct.thnknote;

        name = (TextView)findViewById(R.id.dname);
        name.setText("Hello "+dname+"..");


        logout = (Button)findViewById(R.id.blogout);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.blogout:
                startActivity(new Intent(thnku.this, home.class));
                break;
        }
    }
}
