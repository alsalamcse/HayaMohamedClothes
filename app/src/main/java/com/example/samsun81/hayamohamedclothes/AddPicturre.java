package com.example.samsun81.hayamohamedclothes;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samsun81.hayamohamedclothes.Data.Set;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class AddPicturre extends AppCompatActivity {

    ImageButton BtTake;
    ImageView imageView;
    private Button btAdd;
    private EditText etName;
    private TextView tvTime;
    private ImageButton ibWinter, ibAutumn, ibSpring, ibSummer, ibSwitch, ibSwitch1, ibSwitch2;
    private TextView tvOc;
    private ImageButton ibSad, ibJoy, ibTrip, ibOther;
    private DatabaseReference databaseReference;
    private static final int CAN_REQUEST = 1313;
    private String season;
    private String occasion;
    private Uri filePath;
    FirebaseStorage storage;
    StorageReference storageReference;

    private final int PICK_IMAGE_REQUEST = 71;
    private final int CAPTURE_IMAGE_REQUEST = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_picturre);
        BtTake = (ImageButton) findViewById(R.id.BtButton);
        imageView = (ImageView) findViewById(R.id.imageView);
        btAdd = (Button) findViewById(R.id.btAdd);
        etName = (EditText) findViewById(R.id.etName);
        tvTime = (TextView) findViewById(R.id.tvTime);
        ibWinter = (ImageButton) findViewById(R.id.ibWinter);
        ibAutumn = (ImageButton) findViewById(R.id.ibAutumn);
        ibSpring = (ImageButton) findViewById(R.id.ibSpring);
        ibSummer = (ImageButton) findViewById(R.id.ibSummer);
        ibSwitch = (ImageButton) findViewById(R.id.ibSwitch);
        ibSwitch1 = (ImageButton) findViewById(R.id.ibSwitch1);
        ibSwitch2 = (ImageButton) findViewById(R.id.ibSwitch2);
        tvOc = (TextView) findViewById(R.id.tvOc);
        ibSad = (ImageButton) findViewById(R.id.ibSad);
        ibJoy = (ImageButton) findViewById(R.id.ibJoy);
        ibTrip = (ImageButton) findViewById(R.id.ibTrip);
        ibOther = (ImageButton) findViewById(R.id.ibOther);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        ibWinter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                season = "Winter";

            }
        });
        ibAutumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                season = "Autumn";
            }
        });
        ibSpring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                season = "Spring";
            }
        });
        ibSummer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                season = "Summer";
            }
        });
        ibSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                season = "Winter-Autumn";
            }
        });
        ibSwitch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                season = "Autumn-Spring";
            }
        });
        ibSwitch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                season = "Spring-Summer";
            }
        });
        ibSad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                occasion = "Sad";
            }
        });
        ibJoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                occasion = "Joy";
            }
        });
        ibTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                occasion = "Trip";
            }
        });
        ibOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                occasion = "Other";
            }
        });
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             uploadImage();
            }
        });

        BtTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageDialog();
            }
        });
    }
    public void imageDialog ()
    {
        final String [] items           = new String [] {"From Camera", "From  gallary"};
        ArrayAdapter<String> adapter  = new ArrayAdapter<String> (this, android.R.layout.select_dialog_item,items);
        AlertDialog.Builder builder     = new AlertDialog.Builder(this);

        builder.setTitle("Select Image");
        builder.setAdapter( adapter, new DialogInterface.OnClickListener() {
            public void onClick( DialogInterface dialog, int item ) {
                if (item == 0) {

                    chooseImageCamera();
                    dialog.cancel();
                } else {
                   chooseImageFile();
                    dialog.cancel();

                }
            }
        } );

        final AlertDialog dialog = builder.create();
        dialog.show();


    }

    public void dataHandler(String imgname)
    {
        String stName = etName.getText().toString();

        Set s=new Set();
        s.setName(stName);
        s.setOccasion(occasion);
        s.setWather(season);
        s.setImgPath(imgname);
        s.setToVote(false);
        FirebaseAuth auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        String email=user.getEmail();
        s.setEmail(email);
        email=email.replace('.','*');

        DatabaseReference reference;
        reference=FirebaseDatabase.getInstance().getReference();
        String id=   reference.child(email).child("mySet").push().getKey();
        s.setKeyId(id);
        s.setEmail(email);
        reference.child("SetList").child(id).setValue(s).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(AddPicturre.this,"Add Set Successful",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(AddPicturre.this, "Add Set Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                BtTake.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        if(requestCode == CAPTURE_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null   )
        {

            if(data.getData() == null) {
                filePath = data.getData();
                String path = filePath.getPath();
                Bitmap bitmap = BitmapFactory.decodeFile(path);

                //   Uri selectedImage = data.getData();
                //BtTake.setImageURI(filePath.getPath());
                BtTake.setImageBitmap(bitmap);
            }



        }
    }

//    class btnTakePhotoClicker implements Button.OnClickListener  {
//        @Override
//        public void onClick(View view) {
//            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            startActivityForResult(intent, CAN_REQUEST);
//        }
//    }

    private void chooseImageFile() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    private void chooseImageCamera() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, CAPTURE_IMAGE_REQUEST);//zero can be replaced with any action code
    }
    private void uploadImage() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            final String imgName=UUID.randomUUID().toString();
            StorageReference ref = storageReference.child("images/"+ imgName);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(AddPicturre.this, "Uploaded", Toast.LENGTH_SHORT).show();
                            @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            dataHandler(imgName);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(AddPicturre.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            @SuppressWarnings("VisibleForTests")
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }


}

