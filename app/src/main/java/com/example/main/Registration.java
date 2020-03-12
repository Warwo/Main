package com.example.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import org.w3c.dom.Text;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private String email, password;

    private EditText Login, Email, Pass;
    private Button SignUp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //EditText
        Login = findViewById(R.id.reg_log);
        Email = findViewById(R.id.reg_email);
        Pass = findViewById(R.id.reg_pass);
        //Button
        SignUp = findViewById(R.id.reg_btn);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
            if(v.getId() == R.id.reg_btn){
                Reg(Email.getText().toString(),Pass.getText().toString());
            }
    }

    private void Reg(String email, String password){
        Log.d("REG", "createAccount:" + email);
        if (!validateForm()) {
            return;
        }



        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("REG","createUserWithEmail : success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    //updateUI(user);
                }
                else{
                    Log.w("REG","createUserWithEmail:failure", task.getException());
                    Toast.makeText(Registration.this, "Autentification failed.",Toast.LENGTH_SHORT).show();
                    //updateUI(null);
                }

            }
        });
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);


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

        /*String login = Login.getText().toString();
        if(TextUtils.isEmpty(login)){
            Login.setError("Required.");
            valid = false;
        }else{
            Login.setError(null);
        }*/

        return valid;
    }

}
