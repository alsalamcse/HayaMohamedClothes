package com.example.samsun81.hayamohamedclothes;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyClothsFragment extends Fragment {
    private ImageSwitcher ImSw;
    private Button  btBack;
    private  Button  btNex;


    public MyClothsFragment() {

        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_my_cloths, container, false);
        ImSw= (ImageSwitcher)view.findViewById(R.id.ImSw);
        btBack=(Button)view.findViewById(R.id.btBack);
        btNex=(Button)view.findViewById(R.id.btNex);

        ImSw.setFactory(new ViewSwitcher.ViewFactory() {

//// TODO: imageviewww
            @Override
            public View makeView() {
                ImageView imageView=new ImageView(getContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                return imageView;
            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImSw.setImageResource(R.drawable.);
            }
        });

        btNex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImSw.setImageResource(R.drawable.);
            }
        });



        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_my_cloths, container, false);
    }

}
