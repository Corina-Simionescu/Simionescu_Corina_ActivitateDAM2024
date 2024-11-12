package com.example.test_1_practice_4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.ChecksSdkIntAtLeast;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private int resursaLayout;
    private List<Locuinta> locuintaList = null;

    public CustomAdapter(Context context, int resursaLayout, List<Locuinta> locuintaList) {
        this.context = context;
        this.resursaLayout = resursaLayout;
        this.locuintaList = locuintaList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getResursaLayout() {
        return resursaLayout;
    }

    public void setResursaLayout(int resursaLayout) {
        this.resursaLayout = resursaLayout;
    }

    public List<Locuinta> getLocuintaList() {
        return locuintaList;
    }

    public void setLocuintaList(List<Locuinta> locuintaList) {
        this.locuintaList = locuintaList;
    }

    @Override
    public String toString() {
        return "CustomAdapter{" +
                "context=" + context +
                ", resursaLayout=" + resursaLayout +
                ", locuintaList=" + locuintaList +
                '}';
    }

    @Override
    public int getCount() {
        return locuintaList.size();
    }

    @Override
    public Object getItem(int position) {
        return locuintaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resursaLayout, parent, false);

        Locuinta locuinta = locuintaList.get(position);

        TextView textViewTip = view.findViewById(R.id.textViewTip);
        textViewTip.setText(locuinta.getTip());
        textViewTip.setClickable(false);

        CheckBox checkboxDeLux = view.findViewById(R.id.checkboxDeLux);
        checkboxDeLux.setChecked(locuinta.isDeLux());
        checkboxDeLux.setEnabled(false);

        TextView textViewAdresa = view.findViewById(R.id.textViewAdresa);
        textViewAdresa.setText(locuinta.getAdresa());
        textViewAdresa.setClickable(false);

        return view;
    }
}
