package com.example.santosh.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    TextView main;
    String username, password, firstname, lastname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Obtain username, password, firstName, and lastName from Login to the Home Page
        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");
        firstname = getIntent().getStringExtra("firstname");
        lastname = getIntent().getStringExtra("lastname");

        main = (TextView) findViewById(R.id.textView10);
        main.setText("Welcome " + firstname );
    }
}
