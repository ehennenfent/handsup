package edu.illinois.handsup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {

    LinearLayout androidDropDownMenuIconItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidDropDownMenuIconItem = (LinearLayout) findViewById(R.id.horizontal_dropdown_icon_menu_items);
    }

    public void horizontalDropDownIconMenu(View view) {
        if (androidDropDownMenuIconItem.getVisibility() == View.VISIBLE) {
            androidDropDownMenuIconItem.setVisibility(View.INVISIBLE);
        } else {
            androidDropDownMenuIconItem.setVisibility(View.VISIBLE);
        }
    }

    public void menuItemClick(View view) {
        ;
    }

    public void selectButtonClick(View view) {

        Intent intent = new Intent(this, RandomSelect.class);
        startActivity(intent);
    }

    public void historyButtonClick(View view) {

        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
}