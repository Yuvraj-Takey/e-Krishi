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

public class addproduct extends AppCompatActivity implements View.OnClickListener {

    EditText wheat,rice, maize, millet, cotton, soybean, ragi;
    Button CropSubmit;

    public String DealerID;
    public static String thnknote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);

        DealerID = login.DealerID;

        wheat = (EditText)findViewById(R.id.valwheat);
        rice = (EditText)findViewById(R.id.valrice);
        maize = (EditText)findViewById(R.id.valmaize);
        millet = (EditText)findViewById(R.id.valmillet);
        cotton = (EditText)findViewById(R.id.valcotton);
        soybean = (EditText)findViewById(R.id.valsoya);
        ragi = (EditText)findViewById(R.id.valragi);

        CropSubmit = (Button)findViewById(R.id.cropsubmit);


        CropSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.cropsubmit:
                String Wheat = wheat.getText().toString();
                String Rice = rice.getText().toString();
                String Maize = maize.getText().toString();
                String Millet = millet.getText().toString();
                String Cotton = cotton.getText().toString();
                String Soybean = soybean.getText().toString();
                String Ragi = ragi.getText().toString();
              //  Float ff = Float.parseFloat(Wheat);

                final ProgressDialog progressDialog = ProgressDialog.show(addproduct.this,"Updating Info...","Please Wait..",false,false);

                Response.Listener <String> responselist = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        thnknote = response;
                        progressDialog.dismiss();
                        //Toast.makeText(addproduct.this,response,Toast.LENGTH_LONG).show();
                        startActivity(new Intent(addproduct.this,thnku.class));

                    }
                };
                Response.ErrorListener errorlist = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(addproduct.this,"Network Unavailable",Toast.LENGTH_LONG).show();
                    }
                };


                ///////////////////////////////////////////////////////////////////////////////////
                // TEST CASES
                ///////////////////////////////////////////////////////////////////////////////////

                if(Wheat.endsWith("."))
                {
                    // Example => 2522.
                    progressDialog.dismiss();
                    wheat.setError("Wrong Price");
                    wheat.requestFocus();
                    break;
                }
                if(Wheat.startsWith("0") || Wheat.startsWith("."))
                {
                    // Example => .25 // 0.25 // 0
                    progressDialog.dismiss();
                    wheat.setError("Wrong Price");
                    wheat.requestFocus();
                    break;
                }
                if(Wheat.length() > 7)
                {
                    // Example => 22222222.22222222
                    progressDialog.dismiss();
                    wheat.setError("Wrong Price");
                    wheat.requestFocus();
                    break;

                }
                if (Rice.endsWith(".") || Rice.startsWith("0") || Rice.startsWith(".") || Rice.length() > 7)
                {
                    progressDialog.dismiss();
                    rice.setError("Wrong Price");
                    rice.requestFocus();
                    break;
                }
                if (Cotton.endsWith(".") || Cotton.startsWith("0") || Cotton.startsWith(".") || Cotton.length() > 7)
                {
                    progressDialog.dismiss();
                    cotton.setError("Wrong Price");
                    cotton.requestFocus();
                    break;
                }
                if (Millet.endsWith(".") || Millet.startsWith("0") || Millet.startsWith(".") || Millet.length() > 7)
                {
                    progressDialog.dismiss();
                    millet.setError("Wrong Price");
                    millet.requestFocus();
                    break;
                }
                if (Maize.endsWith(".") || Maize.startsWith("0") || Maize.startsWith(".") || Maize.length() > 7)
                {
                    progressDialog.dismiss();
                    maize.setError("Wrong Price");
                    maize.requestFocus();
                    break;
                }
                if (Soybean.endsWith(".") || Soybean.startsWith("0") || Soybean.startsWith(".") || Soybean.length() > 7)
                {
                    progressDialog.dismiss();
                    soybean.setError("Wrong Price");
                    soybean.requestFocus();
                    break;
                }
                if (Ragi.endsWith(".") || Ragi.startsWith("0") || Ragi.startsWith(".") || Ragi.length() > 7)
                {
                    progressDialog.dismiss();
                    ragi.setError("Wrong Price");
                    ragi.requestFocus();
                    break;
                }



                if(Wheat.isEmpty())
                {
                    Wheat = "0";
                }
                if(Rice.isEmpty())
                {
                    Rice = "0";
                }
                if(Maize.isEmpty())
                {
                    Maize = "0";
                }
                if(Millet.isEmpty())
                {
                    Millet = "0";
                }
                if(Cotton.isEmpty())
                {
                    Cotton = "0";
                }
                if(Soybean.isEmpty())
                {
                    Soybean = "0";
                }
                if(Ragi.isEmpty())
                {
                    Ragi = "0";
                }

            // if Wheat < 10        // enter valid data
                // if(string starts with 0 || . ) || ends with .

                requestcrop reqcrop = new requestcrop(DealerID,Wheat,Rice,Maize,Millet,Cotton,Soybean,Ragi,responselist,errorlist);
                // we pass all this fields to Regester Request class

                RequestQueue Queue = Volley.newRequestQueue(addproduct.this);
                //request to php for accept my data

                Queue.add(reqcrop);
                //we add this object in this queue

                break;
        }
    }
}
