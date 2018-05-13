package com.example.yuvaa.e_krishi;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yuvaa on 2/13/2017.
 */

public class loginRequest extends StringRequest {
    private static String URL = "https://ekrishi.000webhostapp.com/login.php";

    Map<String,String> params;

    loginRequest(String Email, String Password, Response.Listener <String> list, Response.ErrorListener errlist)
    {
        super(Method.POST,URL, list, errlist);
        params = new HashMap<>();
        params.put("Email",Email);
        params.put("Password",Password);
    }


    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
