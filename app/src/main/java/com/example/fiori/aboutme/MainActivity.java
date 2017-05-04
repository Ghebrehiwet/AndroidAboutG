package com.example.fiori.aboutme;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    public static String EXTRA_MESSAGE = "com.example.fiori.aboutme";
    private static final String TAG = SecondActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));


        //Upon click each element of the gridview (except the first one that has to take us to another activity where
        //there reclerview is depicted) show a Toast
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


        //This is FAB
        //Upon clicking the FAB, alert dialog is created
        //The alert dialog has three buttons (representing positive, neutral, and negative)
        //Besides, the alert dialog has a title and message, and using the setCancellable function to false
        //only an action done with the board of the alert dialog will enable further action
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);

                mBuilder.setIcon(android.R.drawable.sym_def_app_icon);

                mBuilder.setTitle(R.string.dialog_title);

                mBuilder.setMessage(R.string.dialog_message);

                mBuilder.setCancelable(false);

                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "An OK is Positive");
                    }
                });

                mBuilder.setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "A Neutral is Impartial");
                    }
                });

                mBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "A No is No");
                    }
                });

                //for the dialog to display, we have to first create and then show it
                AlertDialog alertDialog = mBuilder.create();
                alertDialog.show();
            }
        });
    }


    //This is a method that takes us to another screen upon clicking a UI element
    public void sendMessage(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextEnterText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }



    //In this method an inflator is used and helps in adding a 'Search' option in the Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }


    //In the menu each element uponclick takes us to another screen
     @Override
    public boolean onOptionsItemSelected(MenuItem item){
         if(item.getItemId() == R.id.action_me){
             startActivity(new Intent(MainActivity.this, SecondActivity.class));

         }

         if(item.getItemId() == R.id.action_recycler){
             startActivity(new Intent(MainActivity.this, NewActivity.class));

         }

         if(item.getItemId() == R.id.action_about){
             startActivity(new Intent(MainActivity.this, About.class));

         }
        return super.onOptionsItemSelected(item);
     }



}
