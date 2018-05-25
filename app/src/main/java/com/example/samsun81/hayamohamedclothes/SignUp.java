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

public class SignUp extends AppCompatActivity implements  View.OnClickListener {
    private EditText etEmail;
    private EditText etPassword;
    private EditText etRe;
    private Button btSave;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etRe = (EditText) findViewById(R.id.etRe);
        btSave = (Button) findViewById(R.id.btSave);
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();


        btSave.setOnClickListener(this);

    }
// to deal with input data
    private void dataHandler() {
        String stEmail = etEmail.getText().toString();
        String stPassword = etPassword.getText().toString();
        String stRepass = etRe.getText().toString();
        boolean isOk = true;// yo chek if all are correcttly
        if (stEmail.length() == 0 || stEmail.indexOf('@') < 1) {
            etEmail.setError("WRONG EMAIL");
            isOk = false;
        }
        if (stPassword.length() < 8 || stPassword.equals(stRepass)==false) {
            etPassword.setError("BAD PASSWORD");
            isOk = false;
        }
        if (isOk) {
            creatAcount(stEmail, stPassword);
        }
    }

    private void creatAcount(String email, String password) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUp.this, "...YOUR WELCOME...", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(SignUp.this, "Authentication Failed." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }

            }
        });

    }

    @Override
    public void onClick(View view) {

        if (btSave == view) {
            dataHandler();

        }
    }
}







