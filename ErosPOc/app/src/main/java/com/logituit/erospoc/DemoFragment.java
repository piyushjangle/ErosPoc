package com.logituit.erospoc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class DemoFragment extends Fragment {
    /*
     * UI elements
     */
    View view;
    Button btnPipMode;

    public DemoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_demo, container, false);

        btnPipMode = view.findViewById(R.id.btnPipMode);
        btnPipMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToPiPMode();
            }
        });

        return view;
    }

    public void switchToPiPMode() {
        getActivity().enterPictureInPictureMode();
    }
}