package com.example.CinemaGuide.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.CinemaGuide.R;

public class LoginActivity extends AppCompatActivity {
    EditText usernameedit, passwordedit;
    Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        usernameedit = findViewById(R.id.edittextusername);
        passwordedit = findViewById(R.id.edittextpassword);
        loginbtn = findViewById(R.id.loginButton);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameedit.getText().toString().trim();
                String password = passwordedit.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please fill username and password!", Toast.LENGTH_SHORT).show();
                } else if (!username.matches("[a-zA-Z]+")) {
                    // Check if username contains only letters
                    Toast.makeText(LoginActivity.this, "Username should only contain letters!", Toast.LENGTH_SHORT).show();
                } else if (!password.matches("[a-zA-Z0-9]{4,7}")) {
                    // Check if password is between 4 to 7 characters and contains only letters and digits
                    Toast.makeText(LoginActivity.this, "Password should be between 4 to 7 characters and contain only letters and digits!", Toast.LENGTH_SHORT).show();
                } else {
                    // If validation passes, proceed to the next activity
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }

        });
    }
}
