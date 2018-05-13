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

public class login extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button blogin;
    Button breg;
    Button forgot;
    public static String DealerID;
    Spinner spi;
    String select;              // for select City name
    EditText rname, rpass, remail, rmo, radd, logemail, logpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TabHost tabs = (TabHost)findViewById(R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("tab1");
        spec.setContent(R.id.login);    //lauout name_id
        spec.setIndicator("LOGIN");     //Display name for tab1
        tabs.addTab(spec);

        spec = tabs.newTabSpec("tab2");
        spec.setContent(R.id.register);
        spec.setIndicator("REGISTER");
        tabs.addTab(spec);

//////////////////////////////////////////////////////////////////////////////////////////////////
        logemail = (EditText)findViewById(R.id.logemail);
        logpass = (EditText)findViewById(R.id.logpass);
//////////////////////////////////////////////////////////////////////////////////////////////////
        rname = (EditText)findViewById(R.id.rname);
        radd = (EditText)findViewById(R.id.radd);
        rpass = (EditText)findViewById(R.id.rpass);
        remail = (EditText)findViewById(R.id.remail);
        rmo = (EditText)findViewById(R.id.rmo);
/////////////////////////////////////////////////////////////////////////////////////////////////
        blogin = (Button)findViewById(R.id.blogin);     // button Login
        blogin.setOnClickListener(this);

        breg  = (Button)findViewById(R.id.breg);        // button registration
        breg.setOnClickListener(this);

        forgot = (Button)findViewById(R.id.bforgot);
        forgot.setOnClickListener(this);
/////////////////////////////////////////////////////////////////////////////////////////////////

        spi = (Spinner)findViewById(R.id.rcity);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.city, android.R.layout.simple_spinner_dropdown_item);

        spi.setAdapter(adapter);
        //adapter.setDropDownViewResource();
        spi.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //TextView rr = (TextView) view;
        select = ((TextView) view).getText().toString();
       // Toast.makeText(login.this, City,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.bforgot:
            {
                Intent intent = new Intent(login.this,forgot.class);    // from this window, to this window
                startActivity(intent);
                break;
            }
            case R.id.blogin:
            {
                final ProgressDialog progressDialog = ProgressDialog.show(login.this,"Getting Info...","Please Wait..",false,false);

                String Email = logemail.getText().toString();
                String Password = logpass.getText().toString();

                Response.Listener <String> responselist1 = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        //response = response.toString();
                        final String success = "Invalid User";                           // valid user ValId USer // VALID USER
                        if(!response.equalsIgnoreCase(success))
                        {
                            DealerID = response;

                            //DealerID = Integer.parseInt(response);
                           // Toast.makeText(login.this,"Welcome",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(login.this,addproduct.class);    // from this window, to this window
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(login.this,response,Toast.LENGTH_LONG).show();
                        }
                    }
                };
                Response.ErrorListener responseerror1 = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(login.this,"Network Unavailable",Toast.LENGTH_LONG).show();

                    }
                };

//////////////////////////////////////////TEST CASES////////////////////////////////////////////////

                String ematch = "[a-z0-9._]+@[a-z]+\\.+[a-z]+";//////////////////// MATCH WITH///////
                //String pmatch = "[a-zA-Z]";
                ////////////////////////////////////EMPTY FIELDS////////////////////////////////////

                if(Email.isEmpty() || Password.isEmpty())
                {
                    progressDialog.dismiss();
                    if(Email.isEmpty() && Password.isEmpty())
                    {
                        logemail.setError("Please Enter Email ");
                        logemail.requestFocus();
                        logpass.setError("Please Enter Password ");
                        //Toast.makeText(login.this, "Please Enter Email and Password", Toast.LENGTH_LONG).show();
                        break;
                    }
                    else if(Email.isEmpty())
                    {
                        logemail.setError("Please Enter Email ");
                        logemail.requestFocus();
                       // Toast.makeText(login.this,"Please Enter the Email",Toast.LENGTH_LONG).show();
                        break;
                    }
                    else
                    {
                        logpass.setError("Please Enter Password ");
                        logpass.requestFocus();
                       // Toast.makeText(login.this,"Please Enter the Password",Toast.LENGTH_LONG).show();
                        break;
                    }
                }///// if fields are Empty

                ////////////////////////////////////NOT EMPTY///////////////////////////////////////
               /////////////////////////////////////WORNG INPUT/////////////////////////////////////

                if(Email.matches(ematch))
                {
                  //  progressDialog.dismiss();
                    if(Email.endsWith("@gmail.com") || Email.endsWith("@yahoo.com") ||
                            Email.endsWith("@hotmail.com") || Email.endsWith("@rediff.com") ||
                            Email.endsWith("@ymail.com") || Email.endsWith("@outlook.com") || Email.endsWith("@facebook.com"))
                    {

                        //Toast.makeText(login.this, "Done !! All OK", Toast.LENGTH_LONG).show();

                    }
                    else
                    {
                        progressDialog.dismiss();
                        logemail.setError("Please Enter Valid Domain Name");
                        logemail.requestFocus();
                        //Toast.makeText(login.this, "Please Enter Valid Domain Name", Toast.LENGTH_LONG).show();
                        break;
                    }
                }
                else
                {
                    progressDialog.dismiss();
                    logemail.setError("Please Enter Valid Email ID");
                    logemail.requestFocus();
                    break;
                }

               /* if(Email.contains("@gmail.com"))
                {
                    Toast.makeText(login.this,"valid email",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(login.this,"invalid email",Toast.LENGTH_LONG).show();
                    break;
                }*/
////////////////////////////////////////////////////////////////////////////////////////////////////
                //  abc.xyz@gmail.com
                //  abc.25xyz@gmail.com
                //  abc.xyz@@gmail.com     // @@
                //  abc.xyz@gmail..com     // ..
                //  abc.xyz@gmailcom       //
                //  abc.xyz@gmail.commm    // commm
                //  */%abc.xyz@gmail.com   // */% (starts)
                //  ABC.XYZ@gmail.com      // CAPITAL NAME
                //  abc.xyz@GMAIL.COM      // CAPITAL DOMAIN
                //  abc.xyz@gmail.com*%    // *% (ends)
////////////////////////////////////////////////////////////////////////////////////////////////////

                loginRequest logreq = new loginRequest(Email, Password, responselist1,responseerror1);
                RequestQueue reqqu = Volley.newRequestQueue(login.this);
                reqqu.add(logreq);

                break;
            }

///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////

            case R.id.breg: // when i click on this button, the following action will perform
            {
                final ProgressDialog progressDialog = ProgressDialog.show(login.this,"Getting Info...","Please Wait..",false,false);
                /** all EditText(UI) fields copied into our string*/
                String Name = rname.getText().toString();     //get the name form EditText(UI)
                String Email = remail.getText().toString();   //get email from EditText(UI)
                String Password = rpass.getText().toString(); //copy password from UI to java string
                String Phoneno = rmo.getText().toString();    //get phone no
                String Address = radd.getText().toString();   // get address
                String City = select.toString();

                Response.Listener <String> responselist2 = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        final String success2 = "not register";                           // valid user ValId USer // VALID USER
                        final String success3 = "Email exist";
                        if (response.equalsIgnoreCase(success3))
                        {
                            Toast.makeText(login.this,"Email is already exist",Toast.LENGTH_LONG).show();
                        }
                        else if(!response.equalsIgnoreCase(success2))
                        {
                            DealerID = response;

                            //DealerID = Integer.parseInt(response);
                            Toast.makeText(login.this,response,Toast.LENGTH_LONG).show();
                            //Toast.makeText(login.this,"Welcome",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(login.this,login.class);    // from this window, to this window
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(login.this,response,Toast.LENGTH_LONG).show();
                        }

                    }
                };

                final Response.ErrorListener responseerror2 = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(login.this,"Network Unavailable",Toast.LENGTH_LONG).show();
                    }
                };
//////////////////////////////////////////TEST CASES////////////////////////////////////////////////

                String ematch = "[a-z0-9._]+@[a-z]+\\.+[a-z]+";//////////////////// MATCH WITH///////
                String nmatch = "[a-zA-Z ]+$";   //"[0-9.]+$";
                ////////////////////////////////////EMPTY FIELDS////////////////////////////////////

                if(Name.isEmpty() || Email.isEmpty() || Password.isEmpty() || Phoneno.isEmpty() || Address.isEmpty())
                {
                    progressDialog.dismiss();
                    if(Name.isEmpty() && Email.isEmpty() && Password.isEmpty() && Phoneno.isEmpty() && Address.isEmpty())
                    {
                        Toast.makeText(login.this, "All Fields are Mandatory", Toast.LENGTH_LONG).show();
                        break;
                    }   // all Empty
                    else if (Name.isEmpty()) //
                    {
                        rname.setError("Please Enter your Name");
                        rname.requestFocus();
                        //Toast.makeText(login.this,"Please Ente-r your Name",Toast.LENGTH_LONG).show();
                        break;
                    }   // Name
                    else if(Email.isEmpty())
                    {
                        remail.setError("Please Enter your Email");
                        remail.requestFocus();
                        //Toast.makeText(login.this,"Please Enter the Email",Toast.LENGTH_LONG).show();
                        break;
                    }   // Email
                    else if(Password.isEmpty())
                    {
                        rpass.setError("Please Enter Password");
                        rpass.requestFocus();
                        //Toast.makeText(login.this,"Please Enter the Password",Toast.LENGTH_LONG).show();
                        break;
                    }   // Password
                    else if (Phoneno.isEmpty())//
                    {
                        rmo.setError("Please provide contact info");
                        rmo.requestFocus();
                        //Toast.makeText(login.this,"Please Enter Your Contact Details",Toast.LENGTH_LONG).show();
                        break;
                    }   // Phone Number
                    else if (Address.isEmpty())
                    {
                        radd.setError("Please Enter your address");
                        radd.requestFocus();
                        //Toast.makeText(login.this,"Please Enter Your Address",Toast.LENGTH_LONG).show();
                        break;
                    }   // Address
                }///// if fields are Empty

                ///////////////////////////////////////////////////////////////////////////////////////
                //      if all Fields are filled but Name(phone,address) contain only 1 character then
                ////////////////////////////////////////////////////////////////////////////////////


                if(Name.length() < 3  || Phoneno.length() <= 7 || Password.length() <= 8 || Address.length() < 5)
                {
                    progressDialog.dismiss();
                    if(Name.length() < 3)
                    {
                        rname.setError("Please enter valid Name");
                        rname.requestFocus();
                    }
                   else if (Password.length() <= 8)
                    {
                        rpass.setError("Password must be atleast 8 character");
                        rpass.requestFocus();
                       // Toast.makeText(login.this,"",Toast.LENGTH_LONG).show();
                        break;
                    }   // Password length
                    else if (Phoneno.length() <= 7)
                    {
                        rmo.setError("Invalid Phone number");
                        rmo.requestFocus();
                       // Toast.makeText(login.this,"",Toast.LENGTH_LONG).show();
                        break;
                    }   // Phone Number
                    else if (Address.length() < 5)
                    {
                        radd.setError("Enter your Valid Address");
                        radd.requestFocus();
                        //Toast.makeText(login.this,"Please Enter your Valid Address",Toast.LENGTH_LONG).show();
                        break;
                    }   // Address
                   else
                    {
                        Toast.makeText(login.this,"Please Enter the Valid information",Toast.LENGTH_LONG).show();
                        break;
                    }
                }//     if all Fields are filled but Name(phone,address) contain only 1 character then

                ////////////////////////////////////NOT EMPTY///////////////////////////////////////
                /////////////////////////////////////WORNG INPUT/////////////////////////////////////

                if(Email.matches(ematch))
                {
                    //progressDialog.dismiss();
                    if(Email.endsWith("@gmail.com") || Email.endsWith("@yahoo.com") ||
                            Email.endsWith("@hotmail.com") || Email.endsWith("@rediff.com") ||
                            Email.endsWith("@ymail.com") || Email.endsWith("@outlook.com") || Email.endsWith("@facebook.com"))
                    {

                        //Toast.makeText(login.this, "Done !! All OK", Toast.LENGTH_LONG).show();

                    }
                    else
                    {
                        progressDialog.dismiss();
                        remail.setError("Please Enter Valid Domain Name");
                        remail.requestFocus();
                        //Toast.makeText(login.this, "Please Enter Valid Domain Name", Toast.LENGTH_LONG).show();
                        break;
                    }
                }
                else
                {
                    progressDialog.dismiss();
                    remail.setError("Please Enter Valid Email ID");
                    remail.requestFocus();
                    break;
                }

                if(!Name.matches(nmatch))
                {
                    progressDialog.dismiss();
                    rname.setError("Name is invalid");
                    rname.requestFocus();
                    break;
                }
                if(City.contains("Select City"))
                {
                    progressDialog.dismiss();
                    Toast.makeText(login.this,"Please Select City",Toast.LENGTH_LONG).show();
                    break;
                }



                RegisterRequest regreq = new RegisterRequest(Name,Email,Password,Phoneno,Address,City, responselist2,responseerror2);
                // we pass all this fields to Regester Request class

                RequestQueue Queue = Volley.newRequestQueue(login.this);
                //request to php for accept my data

                Queue.add(regreq);

                //we add this object in this queue

                break;
            }
        }
    }
}
