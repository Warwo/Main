package com.example.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class Expabdable_Task extends AppCompatActivity implements addTask {

    ExpandableListView expandableListView;
    ExpandableListAdapter adapter;
    List<String> list_task_Header;
    HashMap<String,List<String>> list_task_Child;
    FloatingActionButton floatingActionButton;

    TextView textView ;



    //Коллекции
    List<String> First = new ArrayList<String>();
    List<String> Second = new ArrayList<String>();
    List<String> Three = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expabdable__task);

        //ОБЪЯВЛЕНИЕ КОМПОНЕНТОВ
        expandableListView = findViewById(R.id.exp);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        textView = findViewById(R.id.mActivityText);


        prepareListData();

        adapter = new CustomExpandable(this, list_task_Header, list_task_Child);
        expandableListView.setAdapter(adapter);



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ExpandableActivity", "onClick: opening dialog");

                First.add("sadsasd");
                Log.d("123", list_task_Header.listIterator().toString());
                list_task_Child.put(list_task_Header.get(0),First);
                String[] asd = {"dsad","asda"};
                ((BaseExpandableListAdapter)expandableListView.getExpandableListAdapter()).notifyDataSetChanged();
               /* MyCustomDialog myCustomDialog = new MyCustomDialog();
               // myCustomDialog.show(getFragmentManager(), "MyCustomDialog");
                Bundle args = new Bundle();
                args.putStringArrayList("task_header", (ArrayList<String>) list_task_Header);
                myCustomDialog.setArguments(args);
                myCustomDialog.show(getSupportFragmentManager(),"MyCustomTAG");
                ((BaseExpandableListAdapter)expandableListView.getExpandableListAdapter()).notifyDataSetChanged();*/

            }
        });
    }
        private void prepareListData() {

            list_task_Header = new ArrayList<String>();
            list_task_Child= new HashMap<String, List<String>>();



            list_task_Header.add("First");
            list_task_Header.add("Second");
            list_task_Header.add("Three");

            First.add("Ондрий");
            First.add("Вано");
            First.add("Сяся");

            Second.add("1");
            Second.add("2");
            Second.add("3");


            Three.add("Кипр");
            Three.add("Россия");
            Three.add("Барнаул");

            list_task_Child.put(list_task_Header.get(0),First);
            list_task_Child.put(list_task_Header.get(1),Second);
            list_task_Child.put(list_task_Header.get(2),Three);

    }


    @Override
    public void add(int i, List<String> m) {

        //ArrayList<String> list =  list_task_Header.get(i);

        list_task_Child.put(list_task_Header.get(i),m);
        ((BaseExpandableListAdapter)expandableListView.getExpandableListAdapter()).notifyDataSetChanged();
//        textView.setText(list_task_Header.size());
    }
}
