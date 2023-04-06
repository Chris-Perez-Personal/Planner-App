package com.example.planapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {
    TextInputEditText editTextEmail;
    TextView loginTextView;
    Button resetButton;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        loginTextView = findViewById(R.id.loginNow);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email);
        resetButton = findViewById(R.id.reset_btn);

        resetButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mAuth.sendPasswordResetEmail(editTextEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(ResetPassword.this,"Password reset email has been sent.",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(ResetPassword.this,"User doesn't exist.",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        loginTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();

            }
        });
    }
}

