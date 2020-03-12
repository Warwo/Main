package com.example.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Expabdable_Task extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter adapter;
    List<String> list_task_Header;
    HashMap<String,List<String>> list_task_Child;
    FloatingActionButton floatingActionButton;

    //Коллекции
    List<String> First = new ArrayList<String>();
    List<String> Second = new ArrayList<String>();
    List<String> Three = new ArrayList<String>();


    Spinner spinner ;
    EditText taskEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expabdable__task);

        expandableListView = findViewById(R.id.exp);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        
        prepareListData();

        adapter = new CustomExpandable(this,list_task_Header,list_task_Child);
        expandableListView.setAdapter(adapter);


        /////////////////ДИАЛОГОВОЕ//////////////////

/*

        spinner = findViewById(R.id.spinner);
        taskEdit = findViewById(R.id.editText);

        //Заполнение спинера
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Expabdable_Task.this,android.R.layout.simple_spinner_item,list_task_Header);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);*/
        ///////////////////////////////////////


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Настройка диалогового окна
                AlertDialog.Builder builder = new AlertDialog.Builder(Expabdable_Task.this);
                LayoutInflater inflater = getLayoutInflater();

                //Log.d("Сяся", "{S{S{S{S{{S{S");
                builder.setView(inflater.inflate(R.layout.my_alert_task,null));
                builder.setTitle("Add Task");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Expabdable_Task.this);
                        builder.setTitle("Add Task");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String edit = taskEdit.getText().toString();

                                String collection = list_task_Header.get(spinner.getSelectedItemPosition());
                                First.add(""+edit);
                                list_task_Child.put(list_task_Header.get(0),First);
                                //list_task_Child.put(list_task_Header.get(spinner.getSelectedItemPosition(),)
                            }
                        });
                    }
                }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create();
                AlertDialog dialog = builder.create();
                dialog.show();
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
}
