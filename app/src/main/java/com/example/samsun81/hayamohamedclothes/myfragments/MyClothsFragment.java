package com.example.samsun81.hayamohamedclothes.myfragments;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewSwitcher;

import com.example.samsun81.hayamohamedclothes.Data.Set;
import com.example.samsun81.hayamohamedclothes.Data.SetAdapter;
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
public class MyClothsFragment extends Fragment implements View.OnClickListener {
    private ListView lstvCloths;
    private SetAdapter setAdapter;


    public MyClothsFragment() {

        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_cloths, container, false);
        lstvCloths = (ListView) view.findViewById(R.id.lstvCloths);
        setAdapter = new SetAdapter(getContext(), R.layout.set_item);
        lstvCloths.setAdapter(setAdapter);


        // Inflate the layout for this fragment
        readAndListen();
        return view;
    }

    private void readAndListen() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String email = user.getEmail();
        email = email.replace('.', '*');
        //6.bulding  data reference=data path=data address

        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference();

        reference.child(email).child("mySet").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                setAdapter.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Set s = ds.getValue(Set.class);
                    Log.d("ST", s.toString());

                    setAdapter.add(s);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.ad_image_view: {
            }
        }
    }
}
