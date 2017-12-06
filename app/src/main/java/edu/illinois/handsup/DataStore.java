package edu.illinois.handsup;

import android.opengl.Visibility;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashSet;
import java.util.TreeMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by ehennenfent on 11/29/2017.
 */

public class DataStore {

    private static Map<String, Integer> keysToDrawables;
    private static Map<Integer, User> studentList;
    private static Map<Integer, Integer> visibility;
    private static Boolean onStartUp;
    private static Set<Integer> volunteers, randomGroup;
    private static FirebaseDatabase database;
    private static DatabaseReference ref;

    public String getStudentName(Integer idx){
        return studentList.get(idx).name;
    }

    public Integer getStudentScore(Integer idx){
        return studentList.get(idx).score;
    }

    public Integer getLayoutVisibility(Integer idx){
        return visibility.get(idx);
    }

    public Boolean getOnStartUp() {return onStartUp;}

    public Set<Integer> getRandomGroup() {return randomGroup;}

    public Set<Integer> getVolunteers() {return volunteers;}

    public void setStudentScore(Integer idx, Integer newScore){
        Log.d("HandsUp", "Setting " + getStudentName(idx) + "'s score to " + newScore);
        studentList.get(idx).score = newScore;
        ref.child(studentList.get(idx).uid).setValue(studentList.get(idx));
    }

    public Integer setLayoutVisibility(Integer idx, Integer v){
        return visibility.put(idx, v);
    }

    public Set<Integer> getStudentReferences(){
        return studentList.keySet();
    }

    public void setOnStartUp(Boolean b) {onStartUp = b;}

    public void addToRandomGroup(Set<Integer> a) {randomGroup.addAll(a);}

    public DataStore(){
        initData();
    }

    public Integer getAverage() {
        Integer sum = 0;
        for (User student : studentList.values()) {
            sum += student.score;
        }

        return sum/studentList.values().size();
    }

    private void initData() {
        onStartUp = Boolean.TRUE;
        if (keysToDrawables == null) {
            keysToDrawables = new TreeMap<String, Integer>();
            keysToDrawables.put("aishwarya", R.drawable.aishwarya);
            keysToDrawables.put("brad_pitt", R.drawable.brad_pitt);
            keysToDrawables.put("charlize_theron", R.drawable.charlize_theron);
            keysToDrawables.put("chris", R.drawable.chris);
            keysToDrawables.put("emma", R.drawable.emma);
            keysToDrawables.put("felicity_jones", R.drawable.felicity_jones);
            keysToDrawables.put("gal_gadot", R.drawable.gal_gadot);
            keysToDrawables.put("george_clooney", R.drawable.george_clooney);
            keysToDrawables.put("johnny", R.drawable.johnny);
            keysToDrawables.put("liam_neeson", R.drawable.liam_neeson);
            keysToDrawables.put("margot", R.drawable.margot);
            keysToDrawables.put("matt_damon", R.drawable.matt_damon);
            keysToDrawables.put("meryl_streep", R.drawable.meryl_streep);
            keysToDrawables.put("priyanka", R.drawable.priyanka);
            keysToDrawables.put("robert_downey_jr", R.drawable.robert_downey_jr);
            keysToDrawables.put("ryan_gosling", R.drawable.ryan_gosling);
            keysToDrawables.put("scarlett", R.drawable.scarlett);
            keysToDrawables.put("stone_emma", R.drawable.stone_emma);
            keysToDrawables.put("tom_cruise", R.drawable.tom_cruise);
            keysToDrawables.put("will_smith", R.drawable.will_smith);
        }

        if (studentList == null) {
            studentList = new TreeMap<Integer, User>();
            Log.d("HandsUp", "Initialized student list ");
        }

        if (visibility == null) {
            visibility = new TreeMap<Integer, Integer>();
            Log.d("HandsUp", "Initialized layout visibility ");
        }


        database = FirebaseDatabase.getInstance();
        ref = database.getReference("/users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (String key : keysToDrawables.keySet()){
                    User data = dataSnapshot.child(key).getValue(User.class);
                    data.set_supplemental(key, keysToDrawables.get(key));
                    studentList.put(keysToDrawables.get(key), data);
                    visibility.put(keysToDrawables.get(key), View.VISIBLE);
                }
                Log.d("HandsUp", "Initialized Data from Firebase!");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("HandsUp", "Failed to read value.", error.toException());
            }
        });






        volunteers = new HashSet<Integer>();
        volunteers.add(R.drawable.aishwarya);
        volunteers.add(R.drawable.felicity_jones);

        randomGroup = new HashSet<Integer>();
        randomGroup.addAll(volunteers);
    }

    private static final DataStore store = new DataStore();
    public static DataStore getInstance() {return store;}

}
