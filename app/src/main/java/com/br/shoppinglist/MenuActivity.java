package com.br.shoppinglist;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuActivity extends AppCompatActivity {
    private Button logout;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(v ->{
           signOut();
        });

    }

    public void signOut(){
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(MenuActivity.this, LoginActivity.class);
        startActivity(i);
    }
}