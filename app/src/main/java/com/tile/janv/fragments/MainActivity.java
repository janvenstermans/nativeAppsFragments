package com.tile.janv.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnParadoxSelectedListener {

    @Nullable //does not have to find this
    @Bind(R.id.paradox_frame_layout)
    protected FrameLayout singlePaneLayout;

    private ParadoxListFragment paradoxListFragment;
    private ParadoxDescriptionFragment paradoxDescriptionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paradoxes);
        ButterKnife.bind(this);
        paradoxDescriptionFragment = (ParadoxDescriptionFragment)
                getSupportFragmentManager().findFragmentById(R.id.paradox_description_fragment);

        if (singlePaneLayout != null) {
            Log.i(Constants.TAG, "single pane layout");
            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of ParadoxListFragment
            ParadoxListFragment paradoxListFragment = new ParadoxListFragment();
            paradoxListFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.paradox_frame_layout, paradoxListFragment).commit();
            Log.i(Constants.TAG, "ParadoxListFragment added to main layout");
        } else {
            Log.i(Constants.TAG, "two pane layout");
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
    public void paradoxSelected(int index) {
        if (paradoxDescriptionFragment != null) {
            paradoxDescriptionFragment.updateDescriptionView(index);
            Log.i(Constants.TAG, "ParadoxDescriptionFragment updated in two pane view");
        } else {
            //create new fragment
            ParadoxDescriptionFragment newFragment = ParadoxDescriptionFragment.getNewInstance(index);

            getSupportFragmentManager().beginTransaction()
                    //http://stackoverflow.com/questions/8876126/swap-fragment-in-an-activity-via-animation
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                    .replace(R.id.paradox_frame_layout, newFragment)
                    .addToBackStack(null).commit();
            Log.i(Constants.TAG, "ParadoxListFragment replaced by ParadoxDescriptionFragment in main layout single pane");
        }
    }
}
