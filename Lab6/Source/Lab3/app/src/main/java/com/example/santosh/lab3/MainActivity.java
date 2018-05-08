package com.example.santosh.lab3;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.santosh.lab3.SQLLiteDB;
import java.util.ArrayList;
import java.util.Locale;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech toSpeech;
    int result;
    EditText editText,ediText2;
    TextView textView1;
    String text;
    SQLiteDatabase myDatabase;
    private static final int REQ_CODE_SPEECH_INPUT = 300;

    SQLLiteDB sqlLiteDB;
    int catNumb = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView)findViewById(R.id.textView2);

        sqlLiteDB = new SQLLiteDB(this);
        //sqlLiteDB.deleteEntries();

        displayDatabase();



        toSpeech = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i==TextToSpeech.SUCCESS)
                {
                    result=toSpeech.setLanguage(Locale.UK);
                    Toast.makeText(getApplicationContext(),"WINNER",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Feature nto supported in your device", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void TTS(View view)
    {
        switch(view.getId())
        {
            case R.id.button:
                if(result==TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                {
                    Toast.makeText(getApplicationContext(),"Feature nto supported in your device", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    text = editText.getText().toString();
                    sqlLiteDB.create(text,"Python");
                    Cursor cursor = sqlLiteDB.retrieve();


                    if (cursor.moveToFirst()) {
                        do {



                            Log.i("MainString",cursor.getString(1));

                        }while (cursor.moveToNext());
                    }
                    toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
                }
                break;
            case R.id.button2:
                if(toSpeech !=null)
                {
                    toSpeech.stop();
                }
                break;
        }
    }

    public void recordPython(View view)
    {
        catNumb = 1;
        startVoiceInput();
    }

    public void recordWeb(View view)
    {
        catNumb = 2;
        startVoiceInput();
    }

    public void recordAP(View view)
    {
        catNumb = 3;
        startVoiceInput();
    }

    public void recordDL(View view)
    {
        catNumb = 4;
        startVoiceInput();
    }

    public void defPython(View view)
    {

        searchDatabase(1);
    }

    public void defWeb(View view)
    {

        searchDatabase(2);
    }

    public void defAP(View view)
    {

        searchDatabase(3);
    }

    public void defDL(View view)
    {

        searchDatabase(4);
    }

    public void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    textView1.setText(result.get(0));
                    int totalNum = result.size() - 1;
                    if(catNumb==1)
                    {
                        sqlLiteDB.update(1,result.get(totalNum),"Python");
                    }
                    else if(catNumb==2)
                    {
                        sqlLiteDB.update(2,result.get(totalNum),"Web");
                    }
                    else if(catNumb==3)
                    {
                        //sqlLiteDB.create(result.get(totalNum),"AP");
                        sqlLiteDB.update(3,result.get(totalNum),"AP");
                    }
                    else if(catNumb==4)
                    {
                        //sqlLiteDB.create(result.get(totalNum),"DL");
                        sqlLiteDB.update(4,result.get(totalNum),"DL");
                    }
                    catNumb = 0;
                    displayDatabase();
                }
                break;
            }

        }
    }

    public void displayDatabase()
    {
        Cursor cursor = sqlLiteDB.retrieve();


        if (cursor.moveToFirst()) {
            do {



                Log.i("MainString",cursor.getInt(0) + ", " + cursor.getString(1) + ", " + cursor.getString(2));

            }while (cursor.moveToNext());
        }
    }

    public void searchDatabase(int number)
    {
        Cursor cursor = sqlLiteDB.retrieve();


        if (cursor.moveToFirst()) {
            do {
                if(cursor.getInt(0)==number)
                {
                    toSpeech.speak(cursor.getString(1),TextToSpeech.QUEUE_FLUSH,null);
                }


                Log.i("MainString",cursor.getInt(0) + ", " + cursor.getString(1) + ", " + cursor.getString(2));

            }while (cursor.moveToNext());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(toSpeech != null)
        {
            toSpeech.stop();
            toSpeech.shutdown();
        }
    }
}
