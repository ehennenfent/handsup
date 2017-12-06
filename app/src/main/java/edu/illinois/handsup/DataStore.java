package edu.illinois.handsup;

import android.opengl.Visibility;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.HashSet;
import java.util.TreeMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by ehennenfent on 11/29/2017.
 */

public class DataStore {

    private static Map<Integer, String> studentList;
    private static Map<Integer, Integer> studentMarks;
    private static Map<Integer, Integer> visibility;
    private static Boolean onStartUp;
    private static Set<Integer> volunteers, randomGroup;

    public String getStudentName(Integer idx){
        return studentList.get(idx);
    }

    public Integer getStudentScore(Integer idx){
        return studentMarks.get(idx);
    }

    public Integer getLayoutVisibility(Integer idx){
        return visibility.get(idx);
    }

    public Boolean getOnStartUp() {return onStartUp;}

    public Set<Integer> getRandomGroup() {return randomGroup;}

    public Set<Integer> getVolunteers() {return volunteers;}

    public void setStudentScore(Integer idx, Integer newScore){
        Log.d("HandsUp", "Setting " + getStudentName(idx) + "'s score to " + newScore);
        studentMarks.put(idx, newScore);
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
        for (Integer marks : studentMarks.values()) {
            sum += marks;
        }

        return sum/studentMarks.values().size();
    }

    private void initData() {
        onStartUp = Boolean.TRUE;
        if (studentList == null) {
            studentList = new TreeMap<Integer, String>();
            studentList.put(R.drawable.aishwarya, "Aishwarya Rai");
            studentList.put(R.drawable.brad_pitt, "Brad Pitt");
            studentList.put(R.drawable.charlize_theron, "Charlize Theron");
            studentList.put(R.drawable.chris, "Chris Evans");
            studentList.put(R.drawable.emma, "Emma Watson");
            studentList.put(R.drawable.felicity_jones, "Felicity Jones");
            studentList.put(R.drawable.gal_gadot, "Gal Gadot");
            studentList.put(R.drawable.george_clooney, "George Clooney");
            studentList.put(R.drawable.johnny, "Johnny Depp");
            studentList.put(R.drawable.liam_neeson, "Liam Neeson");
            studentList.put(R.drawable.margot, "Margot Robbie");
            studentList.put(R.drawable.matt_damon, "Matt Damon");
            studentList.put(R.drawable.meryl_streep, "Meryl Streep");
            studentList.put(R.drawable.priyanka, "Priyanka Chopra");
            studentList.put(R.drawable.robert_downey_jr, "Robert Downey Jr");
            studentList.put(R.drawable.ryan_gosling, "Ryan Gosling");
            studentList.put(R.drawable.scarlett, "Scarlett Johansson");
            studentList.put(R.drawable.stone_emma, "Emma Stone");
            studentList.put(R.drawable.tom_cruise, "Tom Cruise");
            studentList.put(R.drawable.will_smith, "Will Smith");
            Log.d("HandsUp", "Initialized student list ");
        }

        if (studentMarks == null) {
            studentMarks = new TreeMap<Integer, Integer>();
            studentMarks.put(R.drawable.aishwarya, 2);
            studentMarks.put(R.drawable.brad_pitt, -4);
            studentMarks.put(R.drawable.charlize_theron, 3);
            studentMarks.put(R.drawable.chris, -2);
            studentMarks.put(R.drawable.emma, -5);
            studentMarks.put(R.drawable.felicity_jones, -1);
            studentMarks.put(R.drawable.gal_gadot, 0);
            studentMarks.put(R.drawable.george_clooney, 0);
            studentMarks.put(R.drawable.johnny, 4);
            studentMarks.put(R.drawable.liam_neeson, 1);
            studentMarks.put(R.drawable.margot, 1);
            studentMarks.put(R.drawable.matt_damon, 1);
            studentMarks.put(R.drawable.meryl_streep, 0);
            studentMarks.put(R.drawable.priyanka, 2);
            studentMarks.put(R.drawable.robert_downey_jr, 0);
            studentMarks.put(R.drawable.ryan_gosling, 3);
            studentMarks.put(R.drawable.scarlett, 0);
            studentMarks.put(R.drawable.stone_emma, 2);
            studentMarks.put(R.drawable.tom_cruise, 6);
            studentMarks.put(R.drawable.will_smith, -1);
            Log.d("HandsUp", "Initialized student marks list ");
        }

        if (visibility == null) {
            visibility = new TreeMap<Integer, Integer>();
            visibility.put(R.drawable.aishwarya, View.VISIBLE);
            visibility.put(R.drawable.brad_pitt, View.VISIBLE);
            visibility.put(R.drawable.charlize_theron, View.VISIBLE);
            visibility.put(R.drawable.chris, View.VISIBLE);
            visibility.put(R.drawable.emma, View.VISIBLE);
            visibility.put(R.drawable.felicity_jones, View.VISIBLE);
            visibility.put(R.drawable.gal_gadot, View.VISIBLE);
            visibility.put(R.drawable.george_clooney, View.VISIBLE);
            visibility.put(R.drawable.johnny, View.VISIBLE);
            visibility.put(R.drawable.liam_neeson, View.VISIBLE);
            visibility.put(R.drawable.margot, View.VISIBLE);
            visibility.put(R.drawable.matt_damon, View.VISIBLE);
            visibility.put(R.drawable.meryl_streep, View.VISIBLE);
            visibility.put(R.drawable.priyanka, View.VISIBLE);
            visibility.put(R.drawable.robert_downey_jr, View.VISIBLE);
            visibility.put(R.drawable.ryan_gosling, View.VISIBLE);
            visibility.put(R.drawable.scarlett, View.VISIBLE);
            visibility.put(R.drawable.stone_emma, View.VISIBLE);
            visibility.put(R.drawable.tom_cruise, View.VISIBLE);
            visibility.put(R.drawable.will_smith, View.VISIBLE);
            Log.d("HandsUp", "Initialized layout visibility ");
        }

        volunteers = new HashSet<Integer>();
        volunteers.add(R.drawable.aishwarya);
        volunteers.add(R.drawable.felicity_jones);

        randomGroup = new HashSet<Integer>();
        randomGroup.addAll(volunteers);
    }

    private static final DataStore store = new DataStore();
    public static DataStore getInstance() {return store;}

}
