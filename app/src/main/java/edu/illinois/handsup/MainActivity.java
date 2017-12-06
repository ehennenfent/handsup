package edu.illinois.handsup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    LinearLayout androidDropDownMenuIconItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidDropDownMenuIconItem = (LinearLayout) findViewById(R.id.horizontal_dropdown_icon_menu_items);
        DataStore.getInstance();
    }

    public void horizontalDropDownIconMenu(View view) {
        if (androidDropDownMenuIconItem.getVisibility() == View.VISIBLE) {
            androidDropDownMenuIconItem.setVisibility(View.INVISIBLE);
        } else {
            androidDropDownMenuIconItem.setVisibility(View.VISIBLE);
        }
    }

    public void menuItemClick(View view) {

        int id = view.getId();
        if (id == R.id.select_student) {
            Intent intent = new Intent(this, RandomSelectActivity.class);
            startActivity(intent);
        } else if (id == R.id.history) {
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
        } else if (id == R.id.create) {
            if (DataStore.getInstance().getOnStartUp()) {
                generateRandomStudentGroup();
                DataStore.getInstance().setOnStartUp(Boolean.FALSE);
            }
            Intent intent = new Intent(this, GroupActivity.class);
            startActivity(intent);
        }

    }

    public void generateRandomStudentGroup() {

        // select bottom scorers then select some middle scorers
        Set<Integer> bottomScorers = new HashSet<Integer>();
        Set<Integer> topScorers = new HashSet<Integer>();
        Integer average = DataStore.getInstance().getAverage();
        Log.d("average is ", average.toString());
        Random random = new Random();
        ArrayList<Integer> keys = new ArrayList<Integer>(DataStore.getInstance().getStudentReferences());
        while(bottomScorers.size() < 5) {
            Integer randomKey   = keys.get( random.nextInt(keys.size()));
            if (DataStore.getInstance().getStudentScore(randomKey) <= average) {
                bottomScorers.add(randomKey);
            }
        }

        while (topScorers.size() < 3) {
            Integer randomKey   = keys.get( random.nextInt(keys.size()));
            if (DataStore.getInstance().getStudentScore(randomKey) > average) {
                topScorers.add(randomKey);
            }
        }

        DataStore.getInstance().addToRandomGroup(bottomScorers);
        DataStore.getInstance().addToRandomGroup(topScorers);
    }
}