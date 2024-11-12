package com.example.test_1_practice_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private int layoutResource;
    private List<Dog> dogList = null;

    public CustomAdapter(Context context, int layoutResource, List<Dog> dogList) {
        this.context = context;
        this.layoutResource = layoutResource;
        this.dogList = dogList;
    }

    @Override
    public int getCount() {
        return dogList.size();
    }

    @Override
    public Object getItem(int position) {
        return dogList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(layoutResource, viewGroup, false);

        Dog dogToAddInCustomListView = dogList.get(position);

        TextView textViewName = v.findViewById(R.id.textViewName);
        textViewName.setText(dogToAddInCustomListView.getName());
        textViewName.setClickable(false);

        CheckBox checkBoxIsAggressive = v.findViewById(R.id.checkBoxIsAggressive);
        checkBoxIsAggressive.setChecked(dogToAddInCustomListView.isAggressive());
        checkBoxIsAggressive.setEnabled(false);

        TextView textViewBreed = v.findViewById(R.id.textViewBreed);
        textViewBreed.setText(dogToAddInCustomListView.getBreed());
        textViewBreed.setClickable(false);

        return v;
    }
}
