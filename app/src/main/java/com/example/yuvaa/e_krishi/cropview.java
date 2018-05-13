package com.example.yuvaa.e_krishi;

        import android.app.Dialog;
        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListAdapter;
        import android.widget.ListView;
        import android.widget.SimpleAdapter;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.Volley;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;
        import org.json.*;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Objects;

public class cropview extends AppCompatActivity implements View.OnClickListener{

    private TextView textViewResult;
    Button best;


    public static final String NAME = "Name";
    public static final String EMAIL = "Email";
    public static final String PHONENO = "Phoneno";
    public static final String CITY = "City";
    public static final String CROP = "Crop";
    public static String mname = null;
    public static String memail = null;
    public static String mph = null;
    public static String mcity = null;
    public static String mcrop = null;


    public static final String JSON_ARRAY = "result";

    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    ListView list;

    String cityy;
    String cropy;
    String data[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cropview);

        final ProgressDialog progressDialog = ProgressDialog.show(this,"Getting Info...","Please Wait..",false,false);

        list = (ListView) findViewById(R.id.listView);
          //textViewResult = (TextView) findViewById(R.id.textView4);
        personList = new ArrayList<HashMap<String, String>>();
        // getData();
        textViewResult = (TextView)findViewById(R.id.sorry);
        best = (Button)findViewById(R.id.bbest);
        best.setOnClickListener(this);

        cityy = search.city;
        cropy = search.crop;

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                showJSON(response);
                progressDialog.dismiss();
                //Toast.makeText(cropview.this,response,Toast.LENGTH_LONG).show();
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(cropview.this, "Network Unavailable", Toast.LENGTH_LONG).show();

            }
        };

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        viewrequest view = new viewrequest(cropy, cityy, listener, errorListener);
        // we pass all this fields to Regester Request class

        RequestQueue VQueue = Volley.newRequestQueue(cropview.this);
        //request to php for accept my data

        VQueue.add(view);
        //we add this object in this queue


    }

    private void showJSON(String response) {
        String rname = null;
        String remail = null;
        String rph = null;
        String rcity = null;
        String rcrop = null;
        float max = 0;
        float value = 0;

        response = response.replaceAll("\\[|\\{|\\}|\\]", "");
        String[] splitted = response.split(",");
     //   HashMap<String, String> persons = new HashMap<String, String>();

        String hello;


        try {
           for (String w : splitted)
          // for (int i=0; i<splitted.length; i++)
            {
               // String w = splitted[i];
                String[] ww = w.split(":");
                HashMap<String, String> persons = new HashMap<String, String>();


                if ((ww[0].substring(1, ww[0].length() - 1).equalsIgnoreCase("Name"))) {
                    rname = ww[1].substring(1, ww[1].length() - 1);
                    //persons.put(NAME, ww[1].substring(1, ww[1].length() - 1));

                    //  persons.put(NAME, ww[0]);
                   // Toast.makeText(cropview.this,rname, Toast.LENGTH_LONG).show();
//"yuvraj takey"
                }
                else if ((ww[0].substring(1, ww[0].length() - 1).equalsIgnoreCase("email"))) {
                    remail = ww[1].substring(1, ww[1].length() - 1);
                   // Toast.makeText(cropview.this,remail, Toast.LENGTH_LONG).show();
                    // persons.put(EMAIL, ww[1]);

                }
                else if ((ww[0].substring(1, ww[0].length() - 1).equalsIgnoreCase("phoneno"))) {
                    rph = ww[1].substring(1, ww[1].length() - 1);
                   // Toast.makeText(cropview.this,rph, Toast.LENGTH_LONG).show();

                    // persons.put(PHONENO, ww[2]);

                }
                else if ((ww[0].substring(1, ww[0].length() - 1).equalsIgnoreCase("city"))) {
                    rcity = ww[1].substring(1, ww[1].length() - 1);
                  //  Toast.makeText(cropview.this,rcity, Toast.LENGTH_LONG).show();

                    //persons.put(CITY, ww[3]);

                }
                else {
                    rcrop = ww[1].substring(1, ww[1].length() - 1);

                    // persons.put(CROP, ww[4]);
                     }
                /**/

                if(rname!=null && remail!=null && rph!=null & rcity!=null && rcrop!=null)
                {
                    value = Float.parseFloat(rcrop);
                    if (value > 0) // to avoid wheat = 0;
                    {
                        best.setVisibility(View.VISIBLE);
                        persons.put(NAME,"Name : "+rname);
                        persons.put(EMAIL,"Email ID : "+remail);
                        persons.put(PHONENO,"Phone Number : "+rph);
                        persons.put(CITY,"City : "+rcity);
                        persons.put(CROP,cropy+" : Rs. "+rcrop);
                        personList.add(persons);

                    }

                    if (value > max)
                    {
                        max = value;
                        mname = rname;
                        memail = remail;
                        mph = rph;
                        mcity = rcity;
                        mcrop = rcrop;
                    }
                    rname=null;
                    remail=null;
                    rph=null;
                    rcity=null;
                    rcrop=null;
                }

            }

            ListAdapter adapter = new SimpleAdapter(cropview.this, personList, R.layout.list_item, new String[]{NAME, EMAIL, PHONENO, CITY, CROP}, new int[]{R.id.Name, R.id.Email, R.id.Phoneno, R.id.City, R.id.Crop});
            list.setAdapter(adapter);
            //Toast.makeText(cropview.this,mname+memail+mph+mcity+mcrop, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("length=0")) {
               // Toast.makeText(cropview.this,"Sorry : No records found", Toast.LENGTH_LONG).show();
                textViewResult.setText("Sorry : No records found");

            }
            else
            {
                Toast.makeText(cropview.this,"Network Unavailable", Toast.LENGTH_LONG).show();
            }

        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bbest:
                startActivity(new Intent(cropview.this,popup.class));
                break;
        }
    }
}