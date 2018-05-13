package com.example.yuvaa.e_krishi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.R.id.hint;
import static android.R.id.tabhost;
import static android.R.id.tabs;

public class forgot extends AppCompatActivity implements View.OnClickListener{


    Button bforgot;

    EditText logemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        bforgot = (Button) findViewById(R.id.bforgot);
        bforgot.setOnClickListener(this);
        logemail = (EditText)findViewById(R.id.emaili);
/////////////////////////////////////////////////////////////////////////////////////////////////

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bforgot: {

                final ProgressDialog progressDialog = ProgressDialog.show(this,"Resetting Password...","Please Wait..",false,false);
                String Email = logemail.getText().toString();

                Response.Listener<String> responselist1 = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(forgot.this, response, Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                        //response = response.toString();
                        final String success = "Invalid User";                           // valid user ValId USer // VALID USER
                        if (!response.equalsIgnoreCase(success)) {


                            //DealerID = Integer.parseInt(response);

                        } else {
                            Toast.makeText(forgot.this, response, Toast.LENGTH_LONG).show();
                        }
                    }
                };
                Response.ErrorListener responseerror1 = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(forgot.this, "Network Unavailable", Toast.LENGTH_LONG).show();

                    }
                };

//////////////////////////////////////////TEST CASES////////////////////////////////////////////////

                String ematch = "[a-z0-9._]+@[a-z]+\\.+[a-z]+";//////////////////// MATCH WITH///////
                //String pmatch = "[a-zA-Z]";
                ////////////////////////////////////EMPTY FIELDS////////////////////////////////////

                if (Email.isEmpty()) {
                    if (Email.isEmpty()) {
                        Toast.makeText(forgot.this, "Please Enter Email", Toast.LENGTH_LONG).show();
                        break;
                    } else if (Email.isEmpty()) {
                        Toast.makeText(forgot.this, "Please Enter the Email", Toast.LENGTH_LONG).show();
                        break;
                    }
                }///// if fields are Empty

                ////////////////////////////////////NOT EMPTY///////////////////////////////////////
                /////////////////////////////////////WORNG INPUT/////////////////////////////////////

                if (Email.matches(ematch)) {
                    if (Email.endsWith("@gmail.com") || Email.endsWith("@yahoo.com") ||
                            Email.endsWith("@hotmail.com") || Email.endsWith("@rediff.com") ||
                            Email.endsWith("@ymail.com") || Email.endsWith("@outlook.com") || Email.endsWith("@facebook.com")) {

                        //Toast.makeText(login.this, "Done !! All OK", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(forgot.this, "Please Enter Valid Domain Name", Toast.LENGTH_LONG).show();
                        break;
                    }
                } else {
                    Toast.makeText(forgot.this, "Please Enter Valid Email ID", Toast.LENGTH_LONG).show();
                    break;
                }




                foreq fore = new foreq(Email, responselist1, responseerror1);
                RequestQueue reqqu = Volley.newRequestQueue(forgot.this);
                reqqu.add(fore);


                break;
            }

///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////

        }
    }
}