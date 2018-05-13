package com.example.yuvaa.e_krishi;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yuvaa on 4/8/2017.
 */

public class foreq extends StringRequest {
    private static String URL = "https://ekrishi.000webhostapp.com/forgot.php";

    Map<String,String> params;
    public foreq(String Email, Response.Listener<String> list, Response.ErrorListener errlist) {
        super(Method.POST,URL, list, errlist);
        params = new HashMap<>();
        params.put("Email",Email);

    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

