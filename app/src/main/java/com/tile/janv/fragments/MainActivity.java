package com.tile.janv.fragments;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements OnParadoxSelectedListener {

    private boolean largeMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paradoxes);
        //paradox_description
        View descriptionFrameLayout = findViewById(R.id.paradox_description_frame_layout);
        largeMode = descriptionFrameLayout != null && descriptionFrameLayout.getVisibility() == View.VISIBLE;
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
        if (largeMode) {
            // Code like in  http://developer.android.com/guide/components/fragments.html
            ParadoxDescriptionFragment descriptionFragment = (ParadoxDescriptionFragment)
                    getFragmentManager().findFragmentById(R.id.paradox_description_frame_layout);

            // Make new fragment to show this selection.
            descriptionFragment = ParadoxDescriptionFragment.newInstance(index);

            // Execute a transaction, replacing any existing fragment
            // with this one inside the frame.
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.paradox_description_frame_layout, descriptionFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            // FIXME: leads to Exception:
            // java.lang.IllegalStateException: The specified child already has a parent. You must call removeView() on the child's parent first.
        } else {
            // create a new Activity
            Intent intent = new Intent();
            intent.setClass(this, ParadoxDetailActivity.class);
            intent.putExtra("index", index);
            startActivity(intent);
        }
    }
}
