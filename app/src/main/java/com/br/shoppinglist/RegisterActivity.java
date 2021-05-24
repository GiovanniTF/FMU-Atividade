package com.br.shoppinglist;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private Button register;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{6,20}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    private FirebaseAuth user = FirebaseAuth.getInstance();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.emailAddress);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        register = findViewById(R.id.register);

        register.setOnClickListener(v -> {
            if (validateEmailAndPassword()) {
                createUser();
            } else{
                password.getText().clear();
                confirmPassword.getText().clear();
            }
        });
    }

    public boolean validateEmailAndPassword(){
        if(validateEmail(email.getText().toString()) && validatePassword()){
            return true;
        }else if(!validateEmail(email.getText().toString())){
            Log.e("RegisterUser", "Register Email Error");
            Toast.makeText(RegisterActivity.this, R.string.errorEmailValidation, Toast.LENGTH_SHORT).show();
            return false;
        }else if(!validatePassword()){
            Log.e("RegisterUser", "Register Password Error");
            Toast.makeText(RegisterActivity.this, R.string.errorPasswordValidation, Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return false;
        }
    }

    public static boolean validateEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    public static boolean isValidPassword(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean validatePassword() {
        if (password.getText().toString().equals(confirmPassword.getText().toString())
        && isValidPassword(password.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }

    public void createUser() {
        user.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i("RegisterUser", "Register Successful");
                            Toast.makeText(RegisterActivity.this, R.string.succesRegister, Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("RegisterUser", "Register Error");
                            Toast.makeText(RegisterActivity.this, R.string.errorRegister, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}