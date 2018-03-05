package com.example.samsun81.hayamohamedclothes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeScreen extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail ;
    private EditText etPassword ;
    private Button BtLogin;
    private  Button btSignup;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        BtLogin = (Button) findViewById(R.id.BtLogin);
        btSignup=(Button)findViewById(R.id. btSignup);
        BtLogin.setOnClickListener(this);
        btSignup.setOnClickListener(this);
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();

    }

    private void dataHandler() {

        String stemail = etEmail.getText().toString();
        String stpassword = etPassword.getText().toString();
        signIn(stemail, stpassword);
    }

    private void signIn(String email, String passw) {
        auth.signInWithEmailAndPassword(email,passw).addOnCompleteListener(HomeScreen.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(HomeScreen.this, "signIn Successful.", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getBaseContext(),MyClost.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(HomeScreen.this, "signIn failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

        if (view == BtLogin) {
            dataHandler();
        }

        if (view == btSignup) {
            Intent i = new Intent(this, SignUp.class);
            startActivity(i);


        }
    }
}
