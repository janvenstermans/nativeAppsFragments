package com.tile.janv.fragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements OnParadoxSelectedListener {

    private ParadoxDescriptionFragment descriptionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paradoxes);
        descriptionFragment = (ParadoxDescriptionFragment) getSupportFragmentManager().findFragmentById(R.id.paradox_description);
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
    public void paradoxSelected(int index) {
        if (descriptionFragment != null) {

        } else {
            // create a new Activity
            Intent intent = new Intent();
            intent.setClass(this, ParadoxDetailActivity.class);
            intent.putExtra("index", index);
            startActivity(intent);
        }
    }
}
