package com.example.yuvaa.e_krishi;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;

/**
 * Created by Yuvaa on 2/11/2017.
 */

public class RegisterRequest extends StringRequest {
    private static String URL = "https://ekrishi.000webhostapp.com/registration.php";
    HashMap<String,String> params;

    RegisterRequest(String Name, String Email, String Password, String Phoneno, String Address, String City, Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("Name", Name);
        params.put("Email", Email);
        params.put("Password", Password);
        params.put("Phoneno", Phoneno);
        params.put("Address",Address);
        params.put("City",City);
    }

    @Override
    public HashMap<String, String> getParams() {
        return params;
    }
}
