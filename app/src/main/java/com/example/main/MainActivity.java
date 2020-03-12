package com.example.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private EditText Email, Pass;
    private Button SignIn;
    String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        //EditText
        Email = findViewById(R.id.mainlog);
        Pass = findViewById(R.id.mainpas);
    }

    private void SignIn(String email, String password){
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                   Log.d(TAG, "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(MainActivity.this,"Autentification success", Toast.LENGTH_LONG).show();


                }
                else{
                    Log.w(TAG,"signInWithEmail:failing",task.getException());
                    Toast.makeText(MainActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private void signOut() {
        mAuth.signOut();
        //updateUI(null);
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.textView) {
                Intent intent= new Intent(MainActivity.this,Registration.class);
                startActivity(intent);
            }
        if(v.getId() == R.id.button){
            //ПЕРЕХОД
            Intent intent = new Intent(MainActivity.this, Expabdable_Task.class);
            startActivity(intent);

           // SignIn(Email.getText().toString(), Pass.getText().toString());
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }


    private boolean validateForm(){
        boolean valid = true;

        String email = Email.getText().toString();
        if(TextUtils.isEmpty(email)){
            Email.setError("Required. ");
            valid = false;
        }else{
            Email.setError(null);
        }

        String password = Pass.getText().toString();
        if(TextUtils.isEmpty(password)){
            Pass.setError("Required.");
            valid = false;
        }else{
            Pass.setError(null);
        }



        return valid;
    }
}
