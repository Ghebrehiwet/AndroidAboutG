package com.example.fiori.aboutme;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.RelativeLayout;

import static com.example.fiori.aboutme.R.styleable.RecyclerView;
import static com.example.fiori.aboutme.R.styleable.RecyclerView;

public class NewActivity extends AppCompatActivity {

    Context context;
    RecyclerView recyclerView;
//    RelativeLayout relativeLayout;
//    LinearLayoutManager mLinearLayoutManager;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recylerViewLayoutManager;
    String[][] subjects =
            {
                    { "ANDROID", "Android is a mobile operating system developed by Google, based on the Linux kernel and designed primarily for touchscreen mobile devices such as smartphones and tablets" },
                    { "PHP", "PHP (recursive acronym for PHP: Hypertext Preprocessor) is a widely-used open source general-purpose scripting language that is especially suited for web development and can be embedded into HTML." },
                    { "JSON", "JSON, or JavaScript Object Notation, is a minimal, readable format for structuring data. It is used primarily to transmit data between a server and web application, as an alternative to XML." },
                    { "SWIFT", "Swift is a powerful and intuitive programming language for macOS, iOS, watchOS and tvOS" },
                    { "OBJECTIVE-C", "5" },
                    { "SQL", "" },
                    { "JAVA", "" },
                    { "JAVASCRIPT", "" },
                    { "REACT", "" },
                    { "PYTHON", "" },
                    { "ANGULAR", "" },
                    { "JQUERY", "" },
                    { "CANVAS", "" },
                    { "D3", "" },
                    { "MATPLOTLIB", "" },
                    { "NODE", "" }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.activity_new);

        //Toolbar displays the navigation
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);

        // use a linear layout manager
        recylerViewLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(recylerViewLayoutManager);

        // specify an adapter
        recyclerViewAdapter = new RecyclerViewAdapter(context, subjects);
        recyclerView.setAdapter(recyclerViewAdapter);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_menu, menu);

        // hide menu item for this page
        MenuItem itemToHide = menu.findItem(R.id.action_recycler);
        itemToHide.setVisible(false);

        return true;
    }
}
