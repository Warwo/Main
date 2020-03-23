package com.example.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _Expandable_Task_Adapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<My_Task> my_task;
    private HashMap<My_Task,List<My_Subtask>> hashMap;

    RadioButton radioButton;

    public _Expandable_Task_Adapter(Context _context, List<My_Task> my_task, HashMap<My_Task, List<My_Subtask>> hashMap) {
        this._context = _context;
        this.my_task = my_task;
        this.hashMap = hashMap;
    }




    @Override
    public int getGroupCount() {
        return my_task.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        try{
            return this.hashMap.get(my_task.get(groupPosition)).size();
        }
        catch (Exception ex){
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return my_task.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return hashMap.get(my_task.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        My_Task headerTitle = (My_Task) getGroup(groupPosition);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.group_list, null);
        }
        TextView textView = convertView.findViewById(R.id.radioButton);
        textView.setText(headerTitle.getName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        My_Subtask childText = (My_Subtask) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_list, null);
        }

        radioButton = convertView.findViewById(R.id.radioButton2);
        radioButton.setText(childText.getName());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
