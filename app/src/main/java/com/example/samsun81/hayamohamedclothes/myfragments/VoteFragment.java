package com.example.samsun81.hayamohamedclothes.myfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.samsun81.hayamohamedclothes.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class VoteFragment extends Fragment {


    public VoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vote, container, false);
    }

}