package com.example.dell.beautyfulbangladesh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<PlacePopulation> worldpopulationlist = null;
    private ArrayList<PlacePopulation> arraylist;

    public ListViewAdapter(Context context,
                           List<PlacePopulation> worldpopulationlist) {
        mContext = context;
        this.worldpopulationlist = worldpopulationlist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<PlacePopulation>();
        this.arraylist.addAll(worldpopulationlist);
    }

    public class ViewHolder {
        TextView name;
        TextView division;
        TextView description;
        ImageView place_image;
    }

    @Override
    public int getCount() {
        return worldpopulationlist.size();
    }

    @Override
    public PlacePopulation getItem(int position) {
        return worldpopulationlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.division = (TextView) view.findViewById(R.id.division);
            //holder.description = (TextView) view.findViewById(R.id.description);

            //holder.place_image = (ImageView)view.findViewById(R.id.photo);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(worldpopulationlist.get(position).getName());
        holder.division.setText(worldpopulationlist.get(position).getDivision());
       // holder.description.setText(worldpopulationlist.get(position).getDescription());


        // Listen for ListView Item Click
      /*  view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, SingleItemView.class);
                // Pass all data rank
                intent.putExtra("rank",
                        (worldpopulationlist.get(position).getLocation()));
                // Pass all data country
                intent.putExtra("country",
                        (worldpopulationlist.get(position).getAddress()));

                // Start SingleItemView Class
                mContext.startActivity(intent);
            }
        }); */

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        worldpopulationlist.clear();
        if (charText.length() == 0) {
            worldpopulationlist.addAll(arraylist);
        } else {
            for (PlacePopulation wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault())
                        .contains(charText) || wp.getDivision().toLowerCase(Locale.getDefault())
                        .contains(charText) ) {
                    worldpopulationlist.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}