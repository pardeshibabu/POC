package com.example.poc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ProgressBar progressBar;
    EditText editTextEmail, edittextpassword;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Write a message to the database

// Write a message to the database

        setContentView(R.layout.activity_main);

        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        edittextpassword = (EditText)findViewById(R.id.editTextPassword);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.textViewSignup).setOnClickListener(this);
        findViewById(R.id.buttonLogin).setOnClickListener(this);



    }



    private void userlogin()
    {
        String username= editTextEmail.getText().toString().trim();
        String password= edittextpassword.getText().toString().trim();

        if(username.isEmpty())
        {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(username).matches())
        {
            editTextEmail.setError("Please enter valid email!");
            editTextEmail.requestFocus();
            return;

        }

        if(password.isEmpty())
        {
            edittextpassword.setError("Password is required");
            edittextpassword.requestFocus();
            return;
        }

        if(password.length()<6)
        {
            edittextpassword.setError("please enter correct password!");
            edittextpassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful())
                {

                    startActivity(new Intent(MainActivity.this,DataBaseActivity.class));
                    //Intent intent =new Intent (MainActivity.this,ProfileActivity.class);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //startActivities(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.textViewSignup:
                startActivity(new Intent(this,SignUp.class));
                break;

            case R.id.buttonLogin:
                userlogin();
                break;
        }

    }
}
