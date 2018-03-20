package com.example.samsun81.hayamohamedclothes.myfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.samsun81.hayamohamedclothes.Data.SetAdapter;
import com.example.samsun81.hayamohamedclothes.Data.VoteAdapter;
import com.example.samsun81.hayamohamedclothes.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class VoteFragment extends Fragment {
    private ListView listVoteView;
    private VoteAdapter voteAdapter;


    public VoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_vote, container, false);
        listVoteView=(ListView) view.findViewById(R.id.listVoteView);
        voteAdapter = new VoteAdapter(getContext(),R.layout.vot_item);
        listVoteView.setAdapter(voteAdapter);
        // Inflate the layout for this fragment
        readAndListen();
        return view;
    }

    private void readAndListen() {

        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
    }

}
