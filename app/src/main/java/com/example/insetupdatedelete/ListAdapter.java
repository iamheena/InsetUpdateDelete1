package com.example.insetupdatedelete;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> ID;
    ArrayList<String> Name;
    ArrayList<String> Sirname;
    public ListAdapter(Context context2,ArrayList<String> id,ArrayList<String> name,ArrayList<String> sirnamr)
    {
        this.context = context2;
        this.ID = id;
        this.Name = name;
        this.Sirname = sirnamr;
    }
    @Override
    public int getCount() {
        return ID.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;

        LayoutInflater layoutInflater;

        if (view == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = layoutInflater.inflate(R.layout.items, null);

            holder = new Holder();

            holder.ID_TextView = (TextView) view.findViewById(R.id.textViewID);
            holder.Name_TextView = (TextView) view.findViewById(R.id.textViewNAME);
            holder.Sirname_Textview = (TextView) view.findViewById(R.id.textViewPHONE_NUMBER);
            view.setTag(holder);

        } else {

            holder = (Holder) view.getTag();
        }
        holder.ID_TextView.setText(ID.get(i));
        holder.Name_TextView.setText(Name.get(i));
        holder.Sirname_Textview.setText(Sirname.get(i));

        return view;

    }

    public class Holder {

        TextView ID_TextView;
        TextView Name_TextView;
        TextView Sirname_Textview;
    }
}
