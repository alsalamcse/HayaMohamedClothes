package com.example.samsun81.hayamohamedclothes.Data;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samsun81.hayamohamedclothes.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static com.example.samsun81.hayamohamedclothes.R.id.imageView;

/**
 * Created by user on 01/03/2018.
 */

public class SetAdapter extends ArrayAdapter<Set>
{

    FirebaseStorage storage;
    StorageReference storageReference;

        public SetAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference();
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
        StorageReference ref = storageReference.child("images/"+s.getImgPath());

        downloadInMemory(ref,imvSet);
        tvName.setText(s.getName());
        tvWather.setText(s.getWather());
        tvOccasion.setText(s.getOccasion());




        return view;




    }
    private void downloadInMemory(StorageReference fileRef, final ImageView imvSet) {
        if (fileRef != null) {
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Downloading...");
            progressDialog.setMessage(null);
            progressDialog.show();

            final long ONE_MEGABYTE = 1024 * 1024;
            fileRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    imvSet.setImageBitmap(bmp);
                    progressDialog.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } else
            Toast.makeText(getContext(), "Upload file before downloading", Toast.LENGTH_LONG).show();
    }

    private void downloadToLocalFile(StorageReference fileRef , final ImageView imvSet) {
        if (fileRef != null) {
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Downloading...");
            progressDialog.setMessage(null);
            progressDialog.show();

            try {
                final File localFile = File.createTempFile("images", "jpg");

                fileRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Bitmap bmp = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        imvSet.setImageBitmap(bmp);
                        progressDialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }).addOnProgressListener(new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // progress percentage
                        @SuppressWarnings("VisibleForTests")
                        double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                        // percentage in progress dialog
                        progressDialog.setMessage("Downloaded " + ((int) progress) + "%...");
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getContext(), "Upload file before downloading", Toast.LENGTH_LONG).show();
        }
    }

}

