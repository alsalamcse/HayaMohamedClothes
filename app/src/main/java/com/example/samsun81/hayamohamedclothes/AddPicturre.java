package com.example.samsun81.hayamohamedclothes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.samsun81.hayamohamedclothes.Data.Set;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPicturre extends AppCompatActivity{

     Button BtTake;
    ImageView imageView;
    private Button btAdd;
    private EditText etName;
    private TextView tvTime;
    private ImageButton ibWinter,ibAutumn,ibSpring,ibSummer,ibSwitch,ibSwitch1,ibSwitch2;
    private TextView tvOc;
    private ImageButton ibSad,ibJoy,ibTrip,ibOther;
    private DatabaseReference databaseReference;
    private  static  final int CAN_REQUEST=1313;
    private  String season;
    private String occasion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_picturre);
        BtTake=(Button) findViewById(R.id.BtButton);
        imageView=(ImageView)findViewById(R.id.imageView);
        btAdd=(Button) findViewById(R.id.btAdd);
        etName=(EditText) findViewById(R.id.etName);
        tvTime=(TextView) findViewById(R.id.tvTime);
        ibWinter=(ImageButton) findViewById(R.id.ibWinter);
        ibAutumn=(ImageButton) findViewById(R.id.ibAutumn);
        ibSpring=(ImageButton) findViewById(R.id.ibSpring);
        ibSummer=(ImageButton) findViewById(R.id.ibSummer);
        ibSwitch=(ImageButton) findViewById(R.id.ibSwitch);
        ibSwitch1=(ImageButton) findViewById(R.id.ibSwitch1);
        ibSwitch2=(ImageButton) findViewById(R.id.ibSwitch2);
        tvOc=(TextView) findViewById(R.id.tvOc);
        ibSad=(ImageButton) findViewById(R.id.ibSad);
        ibJoy=(ImageButton) findViewById(R.id.ibJoy);
        ibTrip=(ImageButton) findViewById(R.id.ibTrip);
        ibOther=(ImageButton) findViewById(R.id.ibOther);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        btAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
            }
        });

        ibWinter.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view)
                                        {
                                          season="Winter";
                                        }
                                    }
        );

        ibAutumn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                season="Autumn";
            }
        });

        ibSpring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                season="Spring";
            }
        });

        ibSummer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                season="Summer";
            }
        });

        ibSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                season="Winter-Autumn";
            }
        });

        ibSwitch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                season="Autumn-Spring";
            }
        });

        ibSwitch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                season="Spring-Summer";
            }
        });

        ibSad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                occasion="Sad";
            }
        });

        ibJoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                occasion="Joy";
            }
        });

        ibTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                occasion="Trip";
            }
        });

        ibOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                occasion="Other";
            }
        });
        BtTake.setOnClickListener(new btnTakePhotoClicker());
    }
    public void dataHandler(){
        String stName=etName.getText().toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==CAN_REQUEST){
            Bitmap bitmap=(Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }

    class btnTakePhotoClicker implements Button .OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,CAN_REQUEST);
        }
    }
    //moving data to database
}
