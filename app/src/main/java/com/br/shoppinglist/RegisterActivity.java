package com.br.shoppinglist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class RegisterActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button register;

    private FirebaseAuth user = FirebaseAuth.getInstance();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.emailAddress);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);

        register.setOnClickListener(v -> {
            createUser();
        });
    }

    public void createUser(){
        user.createUserWithEmailAndPassword(
                email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.i("RegisterUser", "Register Successful");
                            Toast.makeText(RegisterActivity.this, R.string.succesRegister, Toast.LENGTH_SHORT).show();
                        }else {
                            Log.e("RegisterUser", "Register Error");
                            Toast.makeText(RegisterActivity.this, R.string.errorRegister, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}