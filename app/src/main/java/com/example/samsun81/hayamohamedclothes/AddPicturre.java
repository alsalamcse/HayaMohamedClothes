package com.example.samsun81.hayamohamedclothes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AddPicturre extends AppCompatActivity {

     Button BtTake;
    ImageView imageView;
    private  static  final int CAN_REQUEST=1313;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_picturre);

        BtTake=(Button) findViewById(R.id.button);
        imageView=(ImageView)findViewById(R.id.imageView);
        BtTake.setOnClickListener(new btnTakePhotoClicker());
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
}
