package com.assassinq.cydiajavahook;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Object obj = "AssassinQ";
        Class clazz = obj.getClass();
        Method[] mPubMethods = clazz.getMethods();
        try {
            clazz.getMethod("charAt", int.class);
        } catch (Exception e) {
            ;
        }
        Method[] mDeclareMethods = clazz.getDeclaredMethods();
        Field[] mPubFields = clazz.getFields();
        Field[] mDeclareFields = clazz.getDeclaredFields();
        Log.d("DEBUG", "--------------------------1--------------------------");
        for (Method m : mPubMethods) {
            Log.d("DEBUG", m.getName());
            Log.d("DEBUG", m.toString());
        }
        Log.d("DEBUG", "--------------------------2--------------------------");
        for (Method m : mDeclareMethods) {
            Log.d("DEBUG", m.getName());
            Log.d("DEBUG", m.toString());
        }
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
}
