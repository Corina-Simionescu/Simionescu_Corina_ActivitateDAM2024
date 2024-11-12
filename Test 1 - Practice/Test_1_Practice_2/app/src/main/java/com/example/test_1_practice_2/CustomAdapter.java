package com.example.test_1_practice_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private List<InventiveClassName> listOfInstances = null;
    private int layoutResource;

    public CustomAdapter(Context context, List<InventiveClassName> listOfInstances, int layoutResource) {
        this.context = context;
        this.listOfInstances = listOfInstances;
        this.layoutResource = layoutResource;
    }

    @Override
    public int getCount() {
        return listOfInstances.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfInstances.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutResource, parent, false);

//        finding each view from the layout
        TextView textViewStringField = view.findViewById(R.id.textViewStringField);
        TextView textViewIntField = view.findViewById(R.id.textViewIntField);
        TextView textViewDoubleField = view.findViewById(R.id.textViewDoubleField);
        TextView textViewFloatField = view.findViewById(R.id.textViewFloatField);

        InventiveClassName instance = (InventiveClassName) getItem(position);

//        populate views with the instance fields
        textViewStringField.setText(instance.getStringField());
        textViewIntField.setText(String.valueOf(instance.getIntField()));
        textViewDoubleField.setText(String.valueOf(instance.getDoubleField()));
        textViewFloatField.setText(String.valueOf(instance.getFloatField()));

        return view;
    }
}
