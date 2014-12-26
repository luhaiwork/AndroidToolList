package com.example.luhai.daggerdemo;

import android.app.Application;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import javax.inject.Inject;

/**
 * Created by luhai on 2014/12/24.
 */
public class AppServiceImpl1 implements IAppService {
    private DemoApplication application;

    @Inject
    public AppServiceImpl1(@ForApplication Context application) {
        this.application = (DemoApplication) application;
    }

    @Override
    public void showName(final ICallback<String> callback) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(application);
        String url = "http://www.baidu.com";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        callback.callBack(s);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    public String getName() {
        return "app service impl 1 name";
    }


}
