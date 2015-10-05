package com.example.android.gridviewtest;


import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements Fragment_A.Communicationinterface{

    android.support.v7.app.ActionBar actionBar;
    String selkey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Contacts Book: ");

        ColorDrawable colorDrawable = new ColorDrawable();
        colorDrawable.setColor(Color.parseColor("#2196F3"));
//        FragmentManager manager=getFragmentManager();
        if (savedInstanceState==null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            Fragment_A fragmentLeft = new Fragment_A();
            transaction.add(R.id.gridfragment,fragmentLeft,null);

            Fragment_B fragmentsRight = new Fragment_B();
            transaction.add(R.id.lstFragment, fragmentsRight,null);

            transaction.commit();

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

    @Override
    public void onSentText(String key) {
        Fragment_B fragment_b = (Fragment_B) getSupportFragmentManager().findFragmentById(R.id.lstFragment);
        //fragment_b.searchkwy = selkey;


    }

    public void setActionBarTitle(String title) {
        selkey = title;
        actionBar.setTitle("Contacts Book: " + title);
    }

}
