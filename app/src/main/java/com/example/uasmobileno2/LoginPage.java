package com.example.uasmobileno2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class LoginPage extends AppCompatActivity {
    private EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AppCompatButton login = (AppCompatButton) findViewById(R.id.loginbutton);
        username = (EditText) findViewById(R.id.usernameText);
        password = (EditText) findViewById(R.id.passwordText);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("Jelshen") &&
                        password.getText().toString().equals("Jelshen04")) {
                    startActivity(new Intent(LoginPage.this, MainActivity.class));
                }

                else if(username.getText().toString().equals("")) {
                    Toast.makeText(LoginPage.this, "Please enter your username", Toast.LENGTH_SHORT).show();
                }

                else if(password.getText().toString().equals("")) {
                    Toast.makeText(LoginPage.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(LoginPage.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

