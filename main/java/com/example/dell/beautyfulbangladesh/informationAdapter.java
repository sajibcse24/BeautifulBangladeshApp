package com.example.dell.beautyfulbangladesh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 4/4/2017.
 */

public class informationAdapter extends ArrayAdapter {
    List list =new ArrayList();
    public informationAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(Information object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object getItem(int position) {
        return list.get(position) ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row=convertView;
        InformationHolder informationHolder;

        if(row==null){
            LayoutInflater layoutInflater=(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.summary_layout,parent,false);
            informationHolder=new InformationHolder();
            informationHolder.tx_module=(TextView)row.findViewById(R.id.tx_module);

            row.setTag(informationHolder);
        }
        else{
            informationHolder=(InformationHolder)row.getTag();
        }

        Information information =(Information)this.getItem(position);
        informationHolder.tx_module.setText(information.getImodule());

        return row;
    }

    static class InformationHolder{
        TextView tx_module;

    }
}
