package com.tile.janv.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParadoxDescriptionFragment extends Fragment {

    private TextView textView;

    public static final String PARADOX_DESCRIPTION_RECOVERY_TEXT = "PARADOX_DESCRIPTION_RECOVERY_TEXT";

    public ParadoxDescriptionFragment() {
        // Required empty public constructor
        setArguments(new Bundle());
    }

    public static ParadoxDescriptionFragment newInstance(int index) {
        ParadoxDescriptionFragment newFragment = new ParadoxDescriptionFragment();
        String description = Paradoxes.ParadoxDescription[index];
        newFragment.getArguments().putString(Constants.PARADOX_DESCRIPTION_TEXT, description);
        return newFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paradox_description, container);
        textView = (TextView) view.findViewById(R.id.paradox_description_text);
        if (savedInstanceState != null && savedInstanceState.containsKey(PARADOX_DESCRIPTION_RECOVERY_TEXT)) {
            textView.setText(savedInstanceState.getCharSequence(PARADOX_DESCRIPTION_RECOVERY_TEXT));
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getArguments().containsKey(Constants.PARADOX_DESCRIPTION_TEXT)) {
            textView.setText(getArguments().getString(Constants.PARADOX_DESCRIPTION_TEXT));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putCharSequence(PARADOX_DESCRIPTION_RECOVERY_TEXT, textView.getText());

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

}
