package com.example.fiori.aboutme;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;


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

        final TextView tv = (TextView) findViewById(R.id.tv);



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
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);

//                mBuilder.setIcon(android.R.drawable.ic_menu_preferences);
//
//                mBuilder.setTitle(R.string.dialog_title);
//
//                mBuilder.setMessage(R.string.dialog_message);




                // String array for alert dialog multi choice items
                String[] colors = new String[]{
                        "Red",
                        "Green",
                        "Blue",
                        "Purple",
                        "Olive"
                };

                // Boolean array for initial selected items
                final boolean[] checkedColors = new boolean[]{
                        false, // Red
                        true, // Green
                        false, // Blue
                        true, // Purple
                        false // Olive

                };

                // Convert the color array to list
                final List<String> colorsList = Arrays.asList(colors);

                // Set multiple choice items for alert dialog


//                        This method will be invoked when an item in the dialog is clicked.
//
//                        Parameters
//                        dialog The dialog where the selection was made.
//                        which The position of the item in the list that was clicked.
//                        isChecked True if the click checked the item, else false.
//                 */
                mBuilder.setMultiChoiceItems(colors, checkedColors, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        // Update the current focused item's checked status
                        checkedColors[which] = isChecked;

                        // Get the current focused item
                        String currentItem = colorsList.get(which);

                        // Notify the current action
                        Toast.makeText(getApplicationContext(),
                                currentItem + " " + isChecked, Toast.LENGTH_SHORT).show();
                    }
                });

                // Specify the dialog is not cancelable
                mBuilder.setCancelable(false);

                // Set a title for alert dialog
                mBuilder.setTitle("Your preferred colors?");

                // Set the positive/yes button click listener
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when click positive button
                        tv.setText("Your preferred colors..... \n");
                        for (int i = 0; i<checkedColors.length; i++){
                            boolean checked = checkedColors[i];
                            if (checked) {
                                tv.setText(tv.getText() + colorsList.get(i) + "\n");
                                Log.d(TAG, "Your preferred colors are " +  colorsList.get(i)  );
                            }
                        }
                    }
                });

                // Set the negative/no button click listener
                mBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when click the negative button
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, "A No has been clicked", Toast.LENGTH_SHORT).show();

                    }
                });

                // Set the neutral/cancel button click listener
                mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when click the neutral button
                        dialog.cancel();
                        Toast.makeText(MainActivity.this, "A cancel has been clicked", Toast.LENGTH_SHORT).show();
                    }
                });




//                mBuilder.setCancelable(false);
//
//                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Log.d(TAG, "An OK is Positive");
//                    }
//                });
//
//                mBuilder.setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Log.d(TAG, "A Neutral is Impartial");
//                    }
//                });
//
//                mBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Log.d(TAG, "A No is No");
//                    }
//                });

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

         if(item.getItemId() == R.id.action_recycler2){
             startActivity(new Intent(MainActivity.this, DataActivity.class));

         }

         if(item.getItemId() == R.id.action_map){
             startActivity(new Intent(MainActivity.this, MapsActivity.class));

         }
        return super.onOptionsItemSelected(item);
     }


////....................................................................................
//    //JSON and RecyclerView Related
//
//    private class AsyncFetch extends AsyncTask<String, String, String> {
//        ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);
//        HttpURLConnection conn;
//        URL url = null;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//            //this method will be running on UI thread
//            pdLoading.setMessage("\tLoading...");
//            pdLoading.setCancelable(false);
//            pdLoading.show();
//
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            try {
//
//                // Enter URL address where your json file resides
//                // Even you can make call to php file which returns json data
//                url = new URL("http://gebgebonline.com/projects/example.json");
//
//            } catch (MalformedURLException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                return e.toString();
//            }
//            try {
//
//                // Setup HttpURLConnection class to send and receive data from php and mysql
//                conn = (HttpURLConnection) url.openConnection();
//                conn.setReadTimeout(READ_TIMEOUT);
//                conn.setConnectTimeout(CONNECTION_TIMEOUT);
//                conn.setRequestMethod("GET");
//
//                // setDoOutput to true as we recieve data from json file
//                conn.setDoOutput(true);
//
//            } catch (IOException e1) {
//                // TODO Auto-generated catch block
//                e1.printStackTrace();
//                return e1.toString();
//            }
//
//            try {
//
//                int response_code = conn.getResponseCode();
//
//                // Check if successful connection made
//                if (response_code == HttpURLConnection.HTTP_OK) {
//
//                    // Read data sent from server
//                    InputStream input = conn.getInputStream();
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//                    StringBuilder result = new StringBuilder();
//                    String line;
//
//                    while ((line = reader.readLine()) != null) {
//                        result.append(line);
//                    }
//
//                    // Pass data to onPostExecute method
//                    return (result.toString());
//
//                } else {
//
//                    return ("unsuccessful");
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//                return e.toString();
//            } finally {
//                conn.disconnect();
//            }
//
//
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//
//            //this method will be running on UI thread
//
//            pdLoading.dismiss();
//            List<DataFish> data=new ArrayList<>();
//
//            pdLoading.dismiss();
//            try {
//
//                JSONArray jArray = new JSONArray(result);
//
//                // Extract data from json and store into ArrayList as class objects
//                for(int i=0;i<jArray.length();i++){
//                    JSONObject json_data = jArray.getJSONObject(i);
//                    DataFish fishData = new DataFish();
//                    fishData.fishImage= json_data.getString("fish_img");
//                    fishData.fishName= json_data.getString("fish_name");
//                    fishData.catName= json_data.getString("cat_name");
//                    fishData.sizeName= json_data.getString("size_name");
//                    fishData.price= json_data.getInt("price");
//                    data.add(fishData);
//                }
//
//                // Setup and Handover data to recyclerview
//                mRVFishPrice = (RecyclerView)findViewById(R.id.fishPriceList);
//                mAdapter = new AdapterFish(MainActivity.this, data);
//                mRVFishPrice.setAdapter(mAdapter);
//                mRVFishPrice.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//
//            } catch (JSONException e) {
//                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
//            }
//
//        }
//
//    }
//
////....................................................................................

}
