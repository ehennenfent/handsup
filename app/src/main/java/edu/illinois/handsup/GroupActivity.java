package edu.illinois.handsup;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class GroupActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout groupLL;
    private static Map<LinearLayout, Integer> groupLayout_to_id;
    Button history, select_student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        history = (Button) findViewById(R.id.group_to_history);
        history.setOnClickListener(this);

        select_student = (Button) findViewById(R.id.select_from_group);
        select_student.setOnClickListener(this);

        groupLL = (LinearLayout) findViewById(R.id.group_list);
        groupLayout_to_id = new HashMap<>();
        int j = 1;
        for (Integer key : DataStore.getInstance().getStudentReferences()) {
            LinearLayout child = (LinearLayout) groupLL.getChildAt(j);
            groupLayout_to_id.put(child, key);
            TextView marks = (TextView) child.getChildAt(3);
            String score = String.valueOf(DataStore.getInstance().getStudentScore(key));
            marks.setText(score);
            if (DataStore.getInstance().getRandomGroup().contains(key)) {
                if (DataStore.getInstance().getVolunteers().contains(key)) {
                    Button volunteer = (Button) child.getChildAt(0);
                    volunteer.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cursor_pointer, 0, 0, 0);
                }
                child.setVisibility(View.VISIBLE);
            } else {
                child.setVisibility(View.GONE);
            }
            j++;
        }
    }


    public void decreaseScore(View view) {
        LinearLayout ll = (LinearLayout) view.getParent();
        Integer id = groupLayout_to_id.get(ll);
        TextView textView = (TextView) ll.getChildAt(3);
        Integer score = Integer.valueOf(textView.getText().toString());
        Integer newScore = score - 1;
        textView.setText(String.valueOf(newScore));
        DataStore.getInstance().setStudentScore(id, newScore);

    }

    public void increaseScore(View view) {
        LinearLayout ll = (LinearLayout) view.getParent();
        Integer id = groupLayout_to_id.get(ll);
        TextView textView = (TextView) ll.getChildAt(3);
        Integer score = Integer.valueOf(textView.getText().toString());
        Integer newScore = score + 1;
        textView.setText(String.valueOf(newScore));
        DataStore.getInstance().setStudentScore(id, newScore);
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.group_to_history:
                intent = new Intent(this, HistoryActivity.class);
                startActivity(intent);
                break;

            case R.id.select_from_group:
                intent = new Intent(this, RandomSelectActivity.class);
                startActivity(intent);
                break;
        }
    }
}
