package com.example.santosh.lab1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

//References from https://developers.google.com/identity/sign-in/android/sign-in were used in this project for Google Sign-In
/Code was derived and referenced from Google Developers: https://developers.google.com/identity/sign-in/android/sign-in
//Code was derived and referenced from Google Developers: (Github Link)https://github.com/googlesamples/google-services/blob/master/android/signin/app/src/main/java/com/google/samples/quickstart/signin/SignInActivity.java#L51-L55
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.button:
                signOut();
                break;

        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

public class Login extends AppCompatActivity implements GoogleSignInOptionsExtension, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener{

    //General fields for Google Sign In (GoogleApiClient)
    private GoogleApiClient mGoogleApiClient;
    private static final int SignInParam = 9001;
    private TextView mStatusTextView;
    private ProgressDialog mProgressDialog;

    String username, password, firstname, lastname;

    EditText un, pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //Initialize GoogleApiClient
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        //Initialize SignInButton from Google
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.button).setOnClickListener(this);

        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");
        firstname = getIntent().getStringExtra("firstname");
        lastname = getIntent().getStringExtra("lastname");
    }

    //Main Login function
    public void login(View v)
    {

        un= (EditText) findViewById(R.id.editText);
        pw = (EditText) findViewById(R.id.editText3);
        if(un.getText().toString().equals(username) && pw.getText().toString().equals(password))
        {
            //Obtain username, password, firstName, and lastName from one intent to the Home Page
            Intent indexPage = new Intent(Login.this, Index.class);
            indexPage.putExtra("username",username);
            indexPage.putExtra("password", password);
            indexPage.putExtra("firstname",firstname);
            indexPage.putExtra("lastName",lastname);
            startActivity(indexPage);

        }
        else
        {
            Toast.makeText(getApplicationContext(), "Login Failed",
                    Toast.LENGTH_LONG).show();
        }

    }

    //Main Sign-in function taken from (https://developers.google.com/identity/sign-in/android/sign-in)
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);;
        startActivityForResult(signInIntent, SignInParam);
    }

    //Main Sign-out function taken from (https://developers.google.com/identity/sign-in/android/sign-in)
    public void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {

                    }
                });
    }

    //Onclick feature taken from (https://developers.google.com/identity/sign-in/android/sign-in)
//Code was derived and referenced from Google Developers: https://developers.google.com/identity/sign-in/android/sign-in
    //Code was derived and referenced from Google Developers: (Github Link)https://github.com/googlesamples/google-services/blob/master/android/signin/app/src/main/java/com/google/samples/quickstart/signin/SignInActivity.java#L51-L55
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.button:
                signOut();
                break;

        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

    @Override
    public void onClick( View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.button2:
                signOut();
                break;
            case R.id.button:
                register(v);
                break;

        }
    }

    public void register(View v)
    {

        Intent loginPage = new Intent(Login.this, RegisterPage.class);
        startActivity(loginPage);
    }

    @Override
    public Bundle toBundle() {
        return null;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
