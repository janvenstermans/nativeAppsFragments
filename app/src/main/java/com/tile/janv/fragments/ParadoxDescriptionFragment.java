package com.tile.janv.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParadoxDescriptionFragment extends Fragment {

    public static final String PARADOX_SELECTED_POSITION = "ParadoxPositionSelected";

    private int currentPosition = -1;

    @Bind(R.id.paradox_description_title)
    protected TextView title;
    @Bind(R.id.paradox_description_text)
    protected TextView description;

    public static ParadoxDescriptionFragment getNewInstance(int index) {
        ParadoxDescriptionFragment newFragment = new ParadoxDescriptionFragment();
        Bundle args = new Bundle();
        args.putInt(PARADOX_SELECTED_POSITION, index);
        newFragment.setArguments(args);
        return newFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(PARADOX_SELECTED_POSITION);
        }
        View view = inflater.inflate(R.layout.fragment_paradox_description, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        // best update textview here
        Bundle args = getArguments();
        if (args != null) {
            currentPosition = args.getInt(PARADOX_SELECTED_POSITION);
        }
        updateDescriptionView();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);

        // Save the current selection. FYI: not really necessary in current use, because View values are saved anyway
        savedInstanceState.putInt(PARADOX_SELECTED_POSITION, currentPosition);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void updateDescriptionView(int index) {
        currentPosition = index;
        updateDescriptionView();
    }

    private void updateDescriptionView() {
        if (currentPosition >= 0 && currentPosition < Paradoxes.ParadoxNames.length) {
            title.setText(Paradoxes.ParadoxNames[currentPosition]);
            description.setText(Paradoxes.ParadoxDescription[currentPosition]);
        }
    }

}
