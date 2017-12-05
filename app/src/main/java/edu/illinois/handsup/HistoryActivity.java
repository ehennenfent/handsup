package edu.illinois.handsup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout mainLL;
    private static Map<LinearLayout, Integer> layout_to_id;
    Button course, student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mainLL = (LinearLayout) findViewById(R.id.history_list);

        course = (Button) findViewById(R.id.course_history);
        course.setOnClickListener(this);

        student = (Button) findViewById(R.id.student_history);
        student.setOnClickListener(this);

        layout_to_id = new HashMap<>();
        int j = 2;
        for (Integer key : DataStore.getInstance().getStudentReferences()) {
            LinearLayout child = (LinearLayout) mainLL.getChildAt(j);
            layout_to_id.put(child, key);
            child.setVisibility(DataStore.getInstance().getLayoutVisibility(key));
            TextView marks = (TextView) child.getChildAt(3);
            String score = String.valueOf(DataStore.getInstance().getStudentScore(key));
            marks.setText(score);
            j++;
        }
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.course_history:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.student_history:
                intent = new Intent(this, RandomSelectActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void clearEntry(View view) {
        LinearLayout ll = (LinearLayout) view.getParent();
        ll.setVisibility(View.GONE);
        DataStore.getInstance().setLayoutVisibility(layout_to_id.get(ll), View.GONE);
    }

    public void decreaseScore(View view) {
        LinearLayout ll = (LinearLayout) view.getParent();
        Integer id = layout_to_id.get(ll);
        TextView textView = (TextView) ll.getChildAt(3);
        Integer score = Integer.valueOf(textView.getText().toString());
        Integer newScore = score - 1;
        textView.setText(String.valueOf(newScore));
        DataStore.getInstance().setStudentScore(id, newScore);

    }

    public void increaseScore(View view) {
        LinearLayout ll = (LinearLayout) view.getParent();
        Integer id = layout_to_id.get(ll);
        TextView textView = (TextView) ll.getChildAt(3);
        Integer score = Integer.valueOf(textView.getText().toString());
        Integer newScore = score + 1;
        textView.setText(String.valueOf(newScore));
        DataStore.getInstance().setStudentScore(id, newScore);
    }

    public void onStudentSerch(View view) {
        LinearLayout ll = (LinearLayout) view.getParent();
        TextView textView = (TextView) ll.getChildAt(0);
        String query = textView.getText().toString().toLowerCase();
        for (LinearLayout key : layout_to_id.keySet()) {
            TextView marks = (TextView) key.getChildAt(1);
            String student = marks.getText().toString().toLowerCase();
            if (student.contains(query)) {
                key.setVisibility(View.VISIBLE);
            } else {
                key.setVisibility(View.GONE);
            }
        }
    }
}