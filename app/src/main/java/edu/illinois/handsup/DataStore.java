package edu.illinois.handsup;

import android.util.Log;

import java.util.TreeMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by ehennenfent on 11/29/2017.
 */

public class DataStore {

    private static Map<Integer, String> studentList;
    private static Map<Integer, Integer> studentMarks;

    public String getStudentName(Integer idx){
        return studentList.get(idx);
    }

    public Integer getStudentScore(Integer idx){
        return studentMarks.get(idx);
    }

    public void setStudentScore(Integer idx, Integer newScore){
        Log.d("HandsUp", "Setting " + getStudentName(idx) + "'s score to " + newScore);
        studentMarks.put(idx, newScore);
    }

    public Set<Integer> getStudentReferences(){
        return studentList.keySet();
    }

    public DataStore(){
        initData();
    }

    private void initData() {
        if (studentList == null) {
            studentList = new TreeMap<Integer, String>();
            studentList.put(R.drawable.chris, "Chris Evans");
            studentList.put(R.drawable.emma, "Emma Watson");
            studentList.put(R.drawable.gal_gadot, "Gal Gadot");
            studentList.put(R.drawable.robert_downey_jr, "Robert Downey Jr");
            Log.d("HandsUp", "Initialized student list ");
        }

        if (studentMarks == null) {
            studentMarks = new TreeMap<Integer, Integer>();
            studentMarks.put(R.drawable.chris, 0);
            studentMarks.put(R.drawable.emma, 0);
            studentMarks.put(R.drawable.gal_gadot, 0);
            studentMarks.put(R.drawable.robert_downey_jr, 0);
            Log.d("HandsUp", "Initialized student marks list ");
        }
    }

    private static final DataStore store = new DataStore();
    public static DataStore getInstance() {return store;}

}
