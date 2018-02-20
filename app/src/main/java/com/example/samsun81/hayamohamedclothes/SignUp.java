package com.example.samsun81.hayamohamedclothes;

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

public class SignUp extends AppCompatActivity implements  View.OnClickListener{
    private  EditText etEmail;
    private  EditText etPassword ;
    private  EditText etRepass;
    private Button btSave ;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etRepass = (EditText) findViewById(R.id.etRepass);
         auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();


        btSave.setOnClickListener();

    }
    private void dataHandler() {
        String stemail = etEmail.getText().toString();
        String stpassword = etPassword.getText().toString();
        String repassword = etRepass.getText().toString();
        boolean isOk = true;
        if (stemail.length() == 0 || stemail.indexOf('@') < 1) {
            etEmail.setError("WRONG EMAIL");
            isOk = false;
        }
        if (stpassword.length() < 8) {
            etPassword.setError("BAD PASSWORD");
            isOk = false;
        }
        if (isOk) {
            creatAcount(stemail, stpassword);
        }
    }

    private void creatAcount(String email, String password) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUp.this, "Authentication Successful.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(SignUp.this, "Authentication Failed." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }

            });
            FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();




    @Override
    public void onClick(View view) {
       if (btSave==view)
          {
             dataHandler();
          }

    }