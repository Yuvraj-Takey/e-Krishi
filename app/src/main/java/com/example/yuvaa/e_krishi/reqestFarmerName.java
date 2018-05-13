package com.example.yuvaa.e_krishi;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yuvaa on 4/24/2017.
 */

public class reqestFarmerName extends StringRequest {
    private static String URL = "https://ekrishi.000webhostapp.com/farmer.php";

    Map<String,String> params;

    public reqestFarmerName(String Fname, String Fcity, Response.Listener<String> responselist1, Response.ErrorListener responseerror1) {
        super(Method.POST,URL, responselist1, responseerror1);
        params = new HashMap<>();
        params.put("Fname",Fname);
        params.put("Fcity",Fcity);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
