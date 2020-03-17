package com.example.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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

import com.google.android.gms.tasks.Tasks;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.awt.font.TextAttribute;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Expabdable_Task extends AppCompatActivity implements addTask, View.OnClickListener {

    //Сохранение
    public static final String APP_PREFERENCES = "MYSAVE";
    public static final ArrayList<String> APP_PREFERENCES_FIRSTLIST = new ArrayList<>();
    public static final ArrayList<String> APP_PREFERENCES_SECONDLIST = new ArrayList<>();
    public static final ArrayList<String> APP_PREFERENCES_THREELIST = new ArrayList<>();
    private SharedPreferences mSave;
    
    

    ExpandableListView expandableListView;
    ExpandableListAdapter adapter;
    List<String> list_task_Header;
    HashMap<String,List<String>> list_task_Child;
    FloatingActionButton floatingActionButton;

    //Коллекции
    List<String> First = new ArrayList<String>();
    List<String> Second = new ArrayList<String>();
    List<String> Three = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expabdable__task);


        //SharedPreferences preferences = this.getSharedPreferences()

        //ОБЪЯВЛЕНИЕ КОМПОНЕНТОВ
        expandableListView = findViewById(R.id.exp);
        floatingActionButton = findViewById(R.id.floatingActionButton);


        prepareListData();

        adapter = new CustomExpandable(this, list_task_Header, list_task_Child);
        expandableListView.setAdapter(adapter);



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
                args.putStringArrayList("0", (ArrayList<String>) First);
                args.putStringArrayList("1", (ArrayList<String>) Second);
                args.putStringArrayList("2", (ArrayList<String>) Three);
                args.putStringArrayList("task_header", (ArrayList<String>) list_task_Header);
                myCustomDialog.setArguments(args);
                myCustomDialog.show(getSupportFragmentManager(),"MyCustomTAG");
                ((BaseExpandableListAdapter)expandableListView.getExpandableListAdapter()).notifyDataSetChanged();

            }
        });


        mSave =getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }
        private void prepareListData() {

            list_task_Header = new ArrayList<String>();
            list_task_Child= new HashMap<String, List<String>>();



            list_task_Header.add("First");
            list_task_Header.add("Second");
            list_task_Header.add("Three");

            First.add("Съесть тортик");
            First.add("Погулять с собакоц");
            First.add("Принять душ");

            Second.add("1");
            Second.add("2");
            Second.add("3");


            Three.add("Коронавирус");
            Three.add("Эбола");
            Three.add("СПИД");


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

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.radioButton2){
            RadioButton radioButton = v.findViewById(R.id.radioButton2);

            String str = (String)radioButton.getText();

                for(int i =0; i<First.size(); i++)
                {
                    if(str == First.get(0));
                    First.remove(str);
                    Log.d("d", ""+ First.size() + First.get(i));
                }
                for(int i =0; i<Second.size(); i++)
                {
                    if(str == Second.get(0));
                    Second.remove(str);
                }

                for(int i =0; i<First.size(); i++)
                {
                    if(str == Three.get(0));
                    Three.remove(str);
                }
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

       /* SharedPreferences.Editor editor = mSave.edit();
        try{
            Seri
        editor.putString(APP_PREFERENCES_FIRSTLIST, ObjectSerializer.);
        editor.apply();}
        catch (IOException ex){
            ex.printStackTrace();
        }*/
    }
}
