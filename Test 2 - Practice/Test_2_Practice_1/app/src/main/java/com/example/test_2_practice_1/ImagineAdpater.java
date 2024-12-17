package com.example.test_2_practice_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImagineAdpater extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ImagineItem> items;

    public ImagineAdpater(Context context, int layout, List<ImagineItem> items) {
        this.context = context;
        this.layout = layout;
        this.items = items;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public List<ImagineItem> getItems() {
        return items;
    }

    public void setItems(List<ImagineItem> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, parent, false);

        ImagineItem imagineItem = items.get(position);

        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageBitmap(imagineItem.getImagine());

        TextView textView = view.findViewById(R.id.textView);
        textView.setText(imagineItem.getText());

        return view;
    }
}
