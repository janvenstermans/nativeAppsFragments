package com.tile.janv.fragments;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParadoxListFragment extends ListFragment {

    private OnParadoxSelectedListener callback;

    @Override
    public void onStart() {
        super.onStart();

        // When in two-pane layout, set the listview to highlight the selected list item
        // (We do this during onStart because at the point the listview is available.)
        if (getFragmentManager().findFragmentById(R.id.paradox_description_fragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnParadoxSelectedListener) getActivity();
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("ParadoxListFragment can only " +
                    "be attached to an activity implementing OnParadoxSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        http://androidexample.com/Create_A_Simple_Listview_-_Android_Example/index.php?view=article_discription&aid=65&aaid=90
//        see example from http://developer.android.com/training/basics/fragments/fragment-ui.html

        // We need to use a different list item layout for devices older than Honeycomb
        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

        // Create an array adapter for the list view, using the Ipsum headlines array
        setListAdapter(new ArrayAdapter<String>(getActivity(), layout,  Arrays.asList(Paradoxes.ParadoxNames)));
    }

    /*@Override
    public void onDetach() {
        super.onDetach();
        //unreference listeners => what do you mean
    }*/

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.i(Constants.TAG, String.format("paradox list item %d clicked", position));
        callback.paradoxSelected(position);
        // Set the item as checked to be highlighted when in two-pane layout
        getListView().setItemChecked(position, true);
    }
}
