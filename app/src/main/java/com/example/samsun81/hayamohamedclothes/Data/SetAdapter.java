package com.example.samsun81.hayamohamedclothes.Data;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.samsun81.hayamohamedclothes.R;

/**
 * Created by user on 01/03/2018.
 */

public class SetAdapter extends ArrayAdapter<Set>
{


        public SetAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View view= LayoutInflater.from(getContext()).inflate(R.layout.set_item,parent,false);

        ImageView imvSet=(ImageView) view.findViewById(R.id.imvSet);
        TextView tvName=(TextView)view.findViewById(R.id.tvName);
        TextView tvWather=(TextView)view.findViewById(R.id.tvWather);
        TextView tvOccasion=(TextView)view.findViewById(R.id.tvOccasion);



        Set s=getItem(position);
        tvName.setText(s.getName());
        tvWather.setText(s.getWather());
        tvOccasion.setText(s.getOccasion());



        return view;




    }
}

