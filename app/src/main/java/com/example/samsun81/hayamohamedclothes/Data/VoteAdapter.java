package com.example.samsun81.hayamohamedclothes.Data;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samsun81.hayamohamedclothes.AddPicturre;
import com.example.samsun81.hayamohamedclothes.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;

public class VoteAdapter extends ArrayAdapter<Set> {
    private TextView tvYes;
    private TextView tvNo;

    FirebaseStorage storage;
    StorageReference storageReference;
    public VoteAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.vot_item, parent, false);


        ImageView imVote = (ImageView) view.findViewById(R.id.imVote);
        ImageButton btLike = (ImageButton) view.findViewById((R.id.btLike));
        ImageButton btDislike = (ImageButton) view.findViewById((R.id.btDislike));
        TextView tvYes=(TextView) view.findViewById(R.id.tvYes);
        TextView tvNo = (TextView) view.findViewById(R.id.tvNo);


        final Set s=getItem(position);
        StorageReference ref = storageReference.child("images/"+s.getImgPath());
        downloadInMemory(ref,imVote);
        tvYes.setText(s.getLike()+"");
        tvNo.setText(s.getDislike()+"");


        btLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              checkisvote(s,true);
            }
        });
        btDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkisvote(s,false);
            }
        });
        return  view;



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
    private void checkisvote(final Set s, final boolean type){
        FirebaseAuth auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        String email=user.getEmail();
       // s.setEmail(email);
        email=email.replace('.','*');

        DatabaseReference reference;
        reference=FirebaseDatabase.getInstance().getReference();
        reference.child(email).orderByKey().equalTo(s.getKeyId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()==false)
                {
                    if (type==true)
                    s.setLike(s.getLike() + 1);// TODO                // lazem a3rf lmen tabe3 leno lazm a3rf wen drg3ha
                    else
                        s.setDislike(s.getDislike()+1);
                    DatabaseReference reference;
                    reference = FirebaseDatabase.getInstance().getReference();

                    reference.child("SetList").child(s.getKeyId()).setValue(s).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), " Like is Done", Toast.LENGTH_SHORT).show();
                                updateVote(s,true);
                            } else {
                                Toast.makeText(getContext(), " Like Failed", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(getContext(),"You Voted Before",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void updateVote(Set s, boolean type){
        FirebaseAuth auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        String email=user.getEmail();
        s.setEmail(email);
        email=email.replace('.','*');

        DatabaseReference reference;
        reference=FirebaseDatabase.getInstance().getReference();
        reference.child(email).child(s.getKeyId()).setValue(type).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getContext(),"updateVote Successful",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), "updateVote Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
