package com.example.yuvaa.e_krishi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

public class FarmerName extends AppCompatActivity implements View.OnClickListener{

    EditText Fname, Fcity;
    Button Fbutton;
    public static String farmcity, farmname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_name);

        Fname = (EditText)findViewById(R.id.fname);
        Fcity = (EditText)findViewById(R.id.fcity);
        Fbutton = (Button) findViewById(R.id.farmsubmit);

        Fbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.farmsubmit:
                final String Name = Fname.getText().toString();
                final String City = Fcity.getText().toString();
                String Fnm = "[a-zA-Z ]+$";

                if(Name.isEmpty())
                {
                    Fname.setError("Please provide your name");
                    Fname.requestFocus();
                    break;
                }
                if(City.isEmpty())
                {
                    Fcity.setError("Please provide your city");
                    Fcity.requestFocus();
                    break;
                }
                if(! Name.matches(Fnm))
                {
                    Fname.setError("Please provide your valid name");
                    Fname.requestFocus();
                    break;
                }
                if(! City.matches(Fnm))
                {
                    Fcity.setError("Please provide your valid city");
                    Fcity.requestFocus();
                    break;
                }
                final ProgressDialog progressDialog = ProgressDialog.show(FarmerName.this,"Updating Info...","Please Wait..",false,false);

                Response.Listener <String> responselist1 = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //response = response.toString();
                        progressDialog.dismiss();
                        final String success = "Thank you";                           // valid user ValId USer // VALID USER
                        if(response.equalsIgnoreCase(success))
                        {
                            farmname = Name;
                            farmcity = City;
                            //DealerID = Integer.parseInt(response);
                            // Toast.makeText(login.this,"Welcome",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(FarmerName.this,search.class);    // from this window, to this window
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(FarmerName.this,response,Toast.LENGTH_LONG).show();
                        }
                    }
                };
                Response.ErrorListener responseerror1 = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(FarmerName.this,"Network Unavailable",Toast.LENGTH_LONG).show();

                    }
                };

                reqestFarmerName regfarmname = new reqestFarmerName(Name,City, responselist1,responseerror1);
                // we pass all this fields to Regester Request class

                RequestQueue Queue = Volley.newRequestQueue(FarmerName.this);
                //request to php for accept my data

                Queue.add(regfarmname);

                break;
        }
    }
}
