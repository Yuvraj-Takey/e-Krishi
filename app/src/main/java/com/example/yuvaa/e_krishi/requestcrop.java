package com.example.yuvaa.e_krishi;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;

/**
 * Created by Yuvaa on 4/1/2017.
 */


public class requestcrop extends StringRequest {
    private static String URL = "https://ekrishi.000webhostapp.com/crop.php";
    HashMap<String,String> params;


    requestcrop(String DealerID, String Wheat,String Rice,String Maize,String Millet,String Cotton,String Soybean,String Ragi, Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("DealerID", DealerID);
        params.put("Wheat", Wheat);
        params.put("Rice", Rice);
        params.put("Maize", Maize);
        params.put("Millet", Millet);
        params.put("Cotton",Cotton);
        params.put("Soybean",Soybean);
        params.put("Ragi",Ragi);
    }

    @Override
    public HashMap<String, String> getParams() {
        return params;
    }
}
