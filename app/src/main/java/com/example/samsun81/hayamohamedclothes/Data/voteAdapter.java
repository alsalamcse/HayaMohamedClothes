package com.example.samsun81.hayamohamedclothes.Data;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.samsun81.hayamohamedclothes.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class voteAdapter extends AppCompatActivity {

    FirebaseStorage storage;
    StorageReference storageReference;

    public voteAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_adapter);
    }
}
