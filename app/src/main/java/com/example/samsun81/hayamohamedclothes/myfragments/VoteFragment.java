package com.example.samsun81.hayamohamedclothes.myfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.samsun81.hayamohamedclothes.Data.Set;
import com.example.samsun81.hayamohamedclothes.Data.SetAdapter;
import com.example.samsun81.hayamohamedclothes.Data.VoteAdapter;
import com.example.samsun81.hayamohamedclothes.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


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
        listVoteView=(ListView) view.findViewById(R.id.lstvVote);
        voteAdapter = new VoteAdapter(getContext(),R.layout.vot_item);
        listVoteView.setAdapter(voteAdapter);

        // Inflate the layout for this fragment
        readAndListen();
        return view;
    }

    private void readAndListen() {

//        FirebaseAuth auth= FirebaseAuth.getInstance();
//        FirebaseUser user=auth.getCurrentUser();


        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("SetList").orderByChild("toVote").equalTo(true).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               voteAdapter.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Set s = ds.getValue(Set.class);
                    //Log.d("ST", s.toString());

                   voteAdapter.add(s);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
