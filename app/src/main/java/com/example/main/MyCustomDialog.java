package com.example.main;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class MyCustomDialog extends DialogFragment {

    private static String TAG = "ExpabdableActivity";

    //widgets//
    private EditText mInput;
    private Spinner mSpinner;
    private TextView mActionCancel, mActionOk;

    private ArrayList<String> task_spinner, task_main;
    //
    private addTask addTask;
    private ExpandableListAdapter adapter;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        addTask = (addTask) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_alert_task,container,false);
        mInput = view.findViewById(R.id.editText);
        mSpinner = view.findViewById(R.id.spinner);
        mActionCancel = view.findViewById(R.id.cancel);
        mActionOk = view.findViewById(R.id.ok);

        //ЗАПОЛНЕНИЕ СПИНЕРА
        task_spinner = getArguments().getStringArrayList("task_header");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, task_spinner);

        mSpinner.setAdapter(arrayAdapter);

        //КНОПКИ ДИАЛОГОВОГО ОКНА
        mActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: closing dialog" );
                getDialog().dismiss();
            }
        });

        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: capturing task" );
                String input = mInput.getText().toString();
                int positionSpinner = mSpinner.getSelectedItemPosition();
                String spin = mSpinner.getSelectedItem().toString();
                task_main = getArguments().getStringArrayList(spin);
                task_main.add(input);




                if(!input.equals("")){
                   // ((Expabdable_Task)getActivity()).textView.setText(input);
                    // ((Expabdable_Task)getActivity()).list_task_Child.put(((Expabdable_Task) getActivity()).list_task_Header.get(positionSpinner),task_spinner);
                    addTask.add(positionSpinner,task_main);
                }
                getDialog().dismiss();
            }
        });

        return view;
    }
}
