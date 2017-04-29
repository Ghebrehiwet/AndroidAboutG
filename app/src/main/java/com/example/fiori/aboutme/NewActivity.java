package com.example.fiori.aboutme;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.RelativeLayout;

public class NewActivity extends AppCompatActivity {

    Context context;
    RecyclerView recyclerView;
//    RelativeLayout relativeLayout;
//    LinearLayoutManager mLinearLayoutManager;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recylerViewLayoutManager;
    String[][] subjects =
            {
                    { "ANDROID", "1" },
                    { "PHP", "2" },
                    { "JSON", "3" },
                    { "SWIFT", "4" },
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

//        recyclerView.setHasFixedSize(true);

        context = getApplicationContext();

//        mLinearLayoutManager = (RelativeLayout) findViewById(R.id.relativelayout1);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);

        // use a linear layout manager
        recylerViewLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(recylerViewLayoutManager);

        // specify an adapter
        recyclerViewAdapter = new RecyclerViewAdapter(context, subjects);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
