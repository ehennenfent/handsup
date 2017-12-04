package edu.illinois.handsup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.util.Log;

import java.util.*;

import android.content.Intent;

public class RandomSelectActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private Button course;
    private Button right;
    private Button wrong;
    private Button history;
    private Button nextStudent;

    private Random random;
    private List<Integer> keys;

    private int lastMember = R.drawable.chris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_random_select);
        toolbar = (Toolbar) findViewById(R.id.toolbar_menu);
        setActionBar(toolbar);

        nextStudent = (Button) findViewById(R.id.next_student);
        nextStudent.setOnClickListener(this);

        course = (Button) findViewById(R.id.course);
        course.setOnClickListener(this);

        right = (Button) findViewById(R.id.right);
        right.setOnClickListener(this);

        wrong = (Button) findViewById(R.id.wrong);
        wrong.setOnClickListener(this);

        history = (Button) findViewById(R.id.history);
        history.setOnClickListener(this);

        random = new Random();

        keys  = new ArrayList<Integer>(DataStore.getInstance().getStudentReferences());
        ImageView image = (ImageView) findViewById(R.id.pic);
        TextView text = (TextView) findViewById(R.id.name);
        Integer randomKey   = keys.get( random.nextInt(keys.size()));

        image.setImageResource(randomKey);
        lastMember = randomKey;

        int marks = DataStore.getInstance().getStudentScore(randomKey);
        text.setText(DataStore.getInstance().getStudentName(randomKey) + " \nMarks : " + marks);

    }

    public void onClick(View view) {
        int marks;
        TextView text;
        switch (view.getId()) {
            case R.id.next_student:
                ImageView image = (ImageView) findViewById(R.id.pic);
                text = (TextView) findViewById(R.id.name);
                Integer randomKey   = keys.get( random.nextInt(keys.size()));
                while (randomKey == lastMember){
                    randomKey   = keys.get( random.nextInt(keys.size()));
                }

                image.setImageResource(randomKey);
                lastMember = randomKey;

                marks = DataStore.getInstance().getStudentScore(randomKey);
                text.setText(DataStore.getInstance().getStudentName(randomKey) + " \nMarks : " + marks);
                break;

            case R.id.course:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.history:
                Log.d("HandsUp", "touched history button");
                Intent i2 = new Intent(this, HistoryActivity.class);
                startActivity(i2);
                break;

            case R.id.right:
                marks = DataStore.getInstance().getStudentScore(lastMember)+1;
                DataStore.getInstance().setStudentScore(lastMember, marks);
                text = (TextView) findViewById(R.id.name);
                text.setText(DataStore.getInstance().getStudentName(lastMember) + " \nMarks : " + marks);
                break;

            case R.id.wrong:
                marks = DataStore.getInstance().getStudentScore(lastMember)-1;
                DataStore.getInstance().setStudentScore(lastMember, marks);
                text = (TextView) findViewById(R.id.name);
                text.setText(DataStore.getInstance().getStudentName(lastMember) + " \nMarks : " + marks);
                break;

            default:
                Log.d("HandsUp", "Unrecognized click event!");
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_overflow_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.option1) {
            /*Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;*/
        }
        return true;
    }

}