package com.example.santosh.lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Index extends AppCompatActivity {

    String username, password, firstname, lastname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");
        firstname = getIntent().getStringExtra("firstname");
        lastname = getIntent().getStringExtra("lastname");
    }

    public void homePage(View v)
    {

        Intent homePage = new Intent(Index.this, Home.class);
        homePage.putExtra("username",username);
        homePage.putExtra("password", password);
        homePage.putExtra("firstname",firstname);
        homePage.putExtra("lastname",lastname);
        startActivity(homePage);
    }
    public void SettingsPage(View v)
    {

        Intent settingsPage = new Intent(Index.this, Settings.class);
        settingsPage.putExtra("username",username);
        settingsPage.putExtra("password", password);
        settingsPage.putExtra("firstname",firstname);
        settingsPage.putExtra("lastname",lastname);
        startActivity(settingsPage);
    }
    public void HelpPage(View v)
    {

        Intent helpPage = new Intent(Index.this, Help.class);
        helpPage.putExtra("username",username);
        helpPage.putExtra("password", password);
        helpPage.putExtra("firstname",firstname);
        helpPage.putExtra("lastname",lastname);
        startActivity(helpPage);
    }
}
