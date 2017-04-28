package com.example.fiori.aboutme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    public static String EXTRA_MESSAGE = "com.example.fiori.aboutme";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if(position==0){
                    startActivity(new Intent(MainActivity.this, NewActivity.class));
                    Toast.makeText(MainActivity.this, "" + "Item : " + (position + 1) + "Take me to another activity",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "" + "Item : " + (position + 1),
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextEnterText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);



    }

//    public void goToActivity2(View view) {
//        Intent intent = new Intent(this, NewActivity.class);
////        EditText editText = (EditText) findViewById(R.id.editTextEnterText);
////        String message = editText.getText().toString();
////        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//
//
//
//    }



}
