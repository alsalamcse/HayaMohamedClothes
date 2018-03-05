package com.example.samsun81.hayamohamedclothes;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewSwitcher;

import com.example.samsun81.hayamohamedclothes.Data.SetAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyClothsFragment extends Fragment {
    private ListView lstvCloths;
    private SetAdapter setAdapter;


    public MyClothsFragment() {

        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_my_cloths, container, false);
        lstvCloths=(ListView) view.findViewById(R.id.lstvCloths);
        lstvCloths.setAdapter(setAdapter);


        // Inflate the layout for this fragment
        readAndListen();
       return view;
    }

    private void readAndListen() {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        String email = user .getEmail();
        email=email.replace('.','*');
        //6.bulding  data reference=data path=data address
        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference();








    }

}
