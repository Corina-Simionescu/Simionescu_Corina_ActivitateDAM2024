package com.example.seminar_8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImagineAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ImaginiDomeniu> objectsImagini;

    public ImagineAdapter(Context context, int layout, List<ImaginiDomeniu> objectsImagini) {
        this.context = context;
        this.layout = layout;
        this.objectsImagini = objectsImagini;
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

    public List<ImaginiDomeniu> getObjectsImagini() {
        return objectsImagini;
    }

    public void setObjectsImagini(List<ImaginiDomeniu> objectsImagini) {
        this.objectsImagini = objectsImagini;
    }


    @Override
    public int getCount() {
        return objectsImagini.size();
    }

    @Override
    public Object getItem(int position) {
        return objectsImagini.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, parent, false);

        ImaginiDomeniu imagineDomeniu = objectsImagini.get(position);

        ImageView imageView = view.findViewById(R.id.imagineView);
        TextView textViewDetalii = view.findViewById(R.id.textViewDetalii);

        imageView.setImageBitmap(imagineDomeniu.getImagine());
        textViewDetalii.setText(imagineDomeniu.getTextAfisat());

        return view;
    }
}
