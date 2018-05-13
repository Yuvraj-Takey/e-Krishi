package com.example.yuvaa.e_krishi;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;

/**
 * Created by Yuvaa on 4/3/2017.
 */

public class viewrequest extends StringRequest {
    private static String URL = "https://ekrishi.000webhostapp.com/chart.php";
    HashMap<String,String> params;


    viewrequest(String crop, String city, Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("crop", crop);
        params.put("city", city);

    }

    @Override
    public HashMap<String, String> getParams() {
        return params;
    }
}
