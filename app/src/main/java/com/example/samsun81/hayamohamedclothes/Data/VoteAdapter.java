package com.example.samsun81.hayamohamedclothes.Data;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.samsun81.hayamohamedclothes.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class VoteAdapter extends ArrayAdapter<Set> {


    public VoteAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.vot_item, parent, false);


        ImageView imVote = (ImageView) view.findViewById(R.id.imVote);
        ImageButton btLike = (ImageButton) view.findViewById((R.id.btLike));
        ImageButton btDislike = (ImageButton) view.findViewById((R.id.btDislike));

        final Set s = getItem(position);

        btLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.setLike(s.getLike() + 1); // TODO                // lazem a3rf lmen tabe3 leno lazm a3rf wen drg3ha
                DatabaseReference reference;
                reference = FirebaseDatabase.getInstance().getReference();

                reference.child("mySet").child(s.getKeyId()).setValue(s).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), " Like is Done", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), " Like Failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
        btDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.setDilike(s.getDilike() + 1);
                DatabaseReference reference;
                reference = FirebaseDatabase.getInstance().getReference();
                reference.child("mySet").child(s.getKeyId()).setValue(s).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), " Disike is Done", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Disike  Failed", Toast.LENGTH_SHORT).show();


                        }
                    }


                });
            }
        });
        return  view;

    }
}
