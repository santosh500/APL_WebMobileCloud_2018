package com.example.santosh.lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterPage extends AppCompatActivity {

    EditText username, password, firstName, lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        username = (EditText) findViewById(R.id.Username);
        password = (EditText) findViewById(R.id.editText9);
        firstName = (EditText) findViewById(R.id.editText5);
        lastName = (EditText) findViewById(R.id.editText6);
    }

    public void register(View v)
    {

        Intent loginPage = new Intent(RegisterPage.this, Login.class);
        loginPage.putExtra("username",username.getText().toString());
        loginPage.putExtra("password",password.getText().toString());
        loginPage.putExtra("firstname",firstName.getText().toString());
        loginPage.putExtra("lastname",lastName.getText().toString());
        startActivity(loginPage);
    }
}
