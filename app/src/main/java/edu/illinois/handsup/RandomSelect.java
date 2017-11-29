package edu.illinois.handsup;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.*;

import android.content.Intent;

public class RandomSelect extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private Button course;
    private Button right;
    private Button nextStudent;

    private Map<Integer, String> studentList;
    private Map<Integer, Integer> studentMarks;
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

        random = new Random();

        studentList = new HashMap<Integer, String>();
        studentList.put(R.drawable.chris, "Chris Evans");
        studentList.put(R.drawable.emma, "Emma Watson");
        studentList.put(R.drawable.gal_gadot, "Gal Gadot");
        studentList.put(R.drawable.robert_downey_jr, "Robert Downey Jr");

        studentMarks = new HashMap<Integer, Integer>();

        keys  = new ArrayList<Integer>(studentList.keySet());
    }

    public void onClick(View view) {
        int marks;
        TextView text;
        switch (view.getId()) {
            case R.id.next_student:
                ImageView image = (ImageView) findViewById(R.id.pic);
                text = (TextView) findViewById(R.id.name);
                Integer randomKey   = keys.get( random.nextInt(keys.size()));

                image.setImageResource(randomKey);
                lastMember = randomKey;

                marks = studentMarks.get(randomKey) == null ? 0 : studentMarks.get(randomKey);
                text.setText(studentList.get(randomKey) + " \nMarks : " + marks);
                break;

            case R.id.course:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.right:
                marks = studentMarks.get(lastMember) == null ? 1 : studentMarks.get(lastMember)+1;
                studentMarks.put(lastMember, marks);
                text = (TextView) findViewById(R.id.name);
                text.setText(studentList.get(lastMember) + " \nMarks : " + marks);
                break;
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
