package com.example.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.tasks.Tasks;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.awt.font.TextAttribute;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Expabdable_Task extends AppCompatActivity implements addTask, View.OnClickListener {

    //Сохранение
    public static final String APP_PREFERENCES = "MYSAVE";


    ExpandableListView expandableListView;
    ExpandableListAdapter adapter;
    List<String> list_task_Header;
    HashMap<String,List<String>> list_task_Child;
    FloatingActionButton floatingActionButton;

    ArrayList<My_Task> list_task;
    HashMap<My_Task, List<My_Subtask>> list_sub_task;
    List<My_Subtask> list_mySub;

    //Коллекции
    ArrayList<String> First = new ArrayList<String>();
    ArrayList<String> Second = new ArrayList<String>();
    ArrayList<String> Three = new ArrayList<String>();

    ArrayList<String> headerTaskName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expabdable__task);

        Bundle arguments = getIntent().getExtras();


       First =loadArrayList("First");
       Second =loadArrayList("Second");
       Three =loadArrayList("Three");

        //SharedPreferences preferences = this.getSharedPreferences()

        //ОБЪЯВЛЕНИЕ КОМПОНЕНТОВ
        expandableListView = findViewById(R.id.exp);
        floatingActionButton = findViewById(R.id.floatingActionButton);


        prepareListData();
        //АДАПТЕР
        // adapter = new CustomExpandable(this, list_task_Header, list_task_Child);
        adapter = new _Expandable_Task_Adapter(this, list_task, list_sub_task );




        expandableListView.setAdapter(adapter);
        /*int count = adapter.getGroupCount();
        for ( int i = 0; i < count; i++ )
            expandableListView.expandGroup(i);*/


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        if(Build.VERSION.SDK_INT< Build.VERSION_CODES.JELLY_BEAN_MR2){
            expandableListView.setIndicatorBounds(width - GetPixelFromDips(50), width - GetPixelFromDips(10));
        }
        else{
            expandableListView.setIndicatorBounds(width - GetPixelFromDips(50), width - GetPixelFromDips(10));
        }

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ExpandableActivity", "onClick: opening dialog");

               /* First.add("sadsasd");
                Log.d("123", list_task_Header.listIterator().toString());
                list_task_Child.put(list_task_Header.get(0),First);
                String[] asd = {"dsad","asda"};
                ((BaseExpandableListAdapter)expandableListView.getExpandableListAdapter()).notifyDataSetChanged();*/
                MyCustomDialog myCustomDialog = new MyCustomDialog();
               // myCustomDialog.show(getFragmentManager(), "MyCustomDialog");
                Bundle args = new Bundle();
                for(int i =0;i <list_task.size(); i++){
                    headerTaskName = new ArrayList<>();
                    headerTaskName.add(list_task.get(i).getName());
                    ArrayList<String> t_l = new ArrayList<>();
                    t_l.add(list_mySub.get(i).getName());
                    args.putStringArrayList(""+i,t_l);
                }
                args.putStringArrayList("task_header",headerTaskName);
                args.putStringArrayList("0", (ArrayList<String>) First);
                args.putStringArrayList("1", (ArrayList<String>) Second);
                args.putStringArrayList("2", (ArrayList<String>) Three);
                //args.putStringArrayList("task_header", (ArrayList<String>) list_task_Header);
                myCustomDialog.setArguments(args);
                myCustomDialog.show(getSupportFragmentManager(),"MyCustomTAG");
                ((BaseExpandableListAdapter)expandableListView.getExpandableListAdapter()).notifyDataSetChanged();

            }
        });


    }

    public int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_expandable_menu,menu);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item1){
            disconnect();
        }
        return true;
        //return super.onOptionsItemSelected(item);
    }

    private void prepareListData() {

            list_task_Header = new ArrayList<String>();
            list_task_Child= new HashMap<String, List<String>>();

            list_task = new ArrayList<>();
            list_sub_task = new HashMap<>();
            list_mySub = new ArrayList<>();
            list_mySub.add(new My_Subtask("Подзадача","Отложенная","Завтра"));
            list_mySub.add(new My_Subtask("Подзадача2","Отложенная","Завтра"));
            list_task.add(new My_Task("Задача","Отложенная","Завтра",list_mySub));
            list_task.add(new My_Task("Задача2","Отложенная","Завтра",list_mySub));

            for (int i =0; i<list_task.size(); i++){
            list_sub_task.put(list_task.get(i),list_task.get(i).getList_sub());
            }
            Log.d("Vanesa", ""+list_mySub.size());

            list_task_Header.add("First");
            list_task_Header.add("Second");
            list_task_Header.add("Three");

            /*First.add("Съесть тортик");
            First.add("Погулять с собакоц");
            First.add("Принять душ");

            Second.add("1");
            Second.add("2");
            Second.add("3");


            Three.add("Коронавирус");
            Three.add("Эбола");
            Three.add("СПИД");*/


            list_task_Child.put(list_task_Header.get(0),First);
            list_task_Child.put(list_task_Header.get(1),Second);
            list_task_Child.put(list_task_Header.get(2),Three);

    }

    public void disconnect(){
        FirebaseAuth.getInstance().signOut();
        Intent back = new Intent(Expabdable_Task.this, MainActivity.class);
        startActivity(back);
    }

    @Override
    public void add(int i, List<String> m) {

        //ArrayList<String> list =  list_task_Header.get(i);

        list_task_Child.put(list_task_Header.get(i),m);
        ((BaseExpandableListAdapter)expandableListView.getExpandableListAdapter()).notifyDataSetChanged();
//        textView.setText(list_task_Header.size());
    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.radioButton2){
            RadioButton radioButton = v.findViewById(R.id.radioButton2);

            String str = (String)radioButton.getText();

            if(First.size() == 0){First =null;}
            else {
                for(int i =0; i<First.size(); i++)
                {
                    if(str == First.get(0));
                    First.remove(str);
//                    Log.d("1", ""+ First.size() + First.get(i));
                }}

            if (Second.size() == 0) {
                Second = null;
            }else{
                for(int i =0; i<Second.size(); i++)
                {
                    if(str == Second.get(0));
                    Second.remove(str);

                }}

            if (Three.size() == 0) {
                Three = null;
            }else{
                for(int i =0; i<Three.size(); i++)
                {
                    if(str == Three.get(0));
                    Three.remove(str);

                }}
            ((BaseExpandableListAdapter)expandableListView.getExpandableListAdapter()).notifyDataSetChanged();
               /* for(String ss: First)
                {
                    if(str == ss){
                        First.remove(str);
                        ((BaseExpandableListAdapter)expandableListView.getExpandableListAdapter()).notifyDataSetChanged();
                    }
                }*/
            Log.d("TAG", str);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveArrayList("First",First);
        saveArrayList("Second",Second);
        saveArrayList("Three",Three);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void saveArrayList(String name , ArrayList<String> list){
        SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        StringBuilder sb = new StringBuilder();
        for(String s :list) sb.append(s).append("<s>");
        sb.delete(sb.length() -3,sb.length());
        editor.putString(name,sb.toString()).apply();
    }
    private ArrayList<String> loadArrayList(String name) {
        SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        String[] strings = prefs.getString(name, "").split("<s>");
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(strings));
        return list;
    }


}
