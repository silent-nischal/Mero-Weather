package com.meroweather.meroweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.meroweather.meroweather.Fragments.ForecastFragment;


public class MainActivity extends ActionBarActivity {
    private final String LOG_TAG=MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG,"inside onCreate event handler");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState==null){
            ForecastFragment forecastFragment=new ForecastFragment();
            FragmentManager manager=getSupportFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            transaction.add(R.id.container_main,forecastFragment);
            transaction.commit();
        }
    }

    @Override
    protected void onStart() {
        Log.d(LOG_TAG,"inside onStart event handler");
        super.onStart();
    }
    @Override
    protected void onResume() {
        Log.d(LOG_TAG,"inside onResume event handler");
        super.onResume();
    }
    @Override
      protected void onPause() {
        Log.d(LOG_TAG,"inside onPause event handler");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(LOG_TAG,"inside onStop event handler");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d(LOG_TAG,"inside onRestart event handler");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d(LOG_TAG,"inside onDestroy event handler");
        super.onDestroy();
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
            Intent intent=new Intent(this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_map) {
            openPreferredLocationInMap();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void openPreferredLocationInMap(){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String location=preferences.getString(getString(R.string.preferences_location_key),getString(R.string.preferences_location_default));
        // Using the URI scheme for showing a location found on a map.  This super-handy
        // intent can is detailed in the "Common Intents" page of Android's developer site:
        // http://developer.android.com/guide/components/intents-common.html#Maps
        Uri geoLocation = Uri.parse("geo:0,0?").buildUpon()
                .appendQueryParameter("q", location)
                .build();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(LOG_TAG, "Couldn't call " + location + ", no receiving apps installed!");
        }

    }

}
