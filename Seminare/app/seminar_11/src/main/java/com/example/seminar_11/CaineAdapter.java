package com.example.seminar_11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class CaineAdapter extends BaseAdapter {
    private List<Caine> caini = null;
    private Context context;
    private int resursaLayout;

    public CaineAdapter(List<Caine> caini, Context context, int resursaLayout) {
        this.caini = caini;
        this.context = context;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return caini.size();
    }

    @Override
    public Object getItem(int position) {
        return caini.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resursaLayout, parent, false);

        TextView textViewNume = view.findViewById(R.id.textViewNume);
        CheckBox checkBoxEsteAgresiv = view.findViewById(R.id.checkBoxEsteAgresiv);
        TextView textViewRasa = view.findViewById(R.id.textViewRasa);
        TextView textViewDimensiune = view.findViewById(R.id.textViewDimensiune);
        TextView textViewNivelDragutenie = view.findViewById(R.id.textViewNivelDragutenie);
        CheckBox checkBoxEsteJucaus = view.findViewById(R.id.checkBoxEsteJucaus);

        // Disable clicks on all views
        checkBoxEsteAgresiv.setEnabled(false);
        checkBoxEsteJucaus.setEnabled(false);
        view.setClickable(false);
        textViewNume.setClickable(false);
        textViewRasa.setClickable(false);
        textViewDimensiune.setClickable(false);
        textViewNivelDragutenie.setClickable(false);

        Caine caine = (Caine) getItem(position);

        textViewNume.setText(caine.getNume());
        checkBoxEsteAgresiv.setChecked(caine.isEsteAgresiv());
        textViewRasa.setText(caine.getRasa());
        textViewDimensiune.setText(caine.getDimensiune());
        textViewNivelDragutenie.setText(String.valueOf(caine.getNivelDeDragutenie()));
        checkBoxEsteJucaus.setChecked(caine.isEsteJucaus());

//        pt valori int sau float
//         int a = 7
//         telefonTV.setText(String.valueOf(a));
//        telefonTV.setText(""+a);
        return view;
    }
}