package com.example.luhai.daggerdemo;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import javax.inject.Inject;


public class MainActivity extends ActionBarActivity implements View.OnClickListener,IAppService.ICallback<String> {
    @Inject
    LocationManager locationManager;
    @Inject
    IAppService appService;
    @Inject
    @ForApplication
    Context application;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((DemoApplication) getApplication()).inject(this);
        Log.e("tag",String.valueOf(locationManager == null));
        initView();
        Log.e("tag","appService is null ??"+String.valueOf(appService==null));
//        Toast.makeText(this,"appService.getName"+appService.getName(),Toast.LENGTH_SHORT).show();
//        appService.showName(this);
        Toast.makeText(this,"Test"+String.valueOf(application==null),Toast.LENGTH_SHORT).show();
    }

    private void initView(){
        Button btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void callBack(String returnObj) {
        Toast.makeText(this,"returnStr"+returnObj,Toast.LENGTH_SHORT).show();
    }
}
