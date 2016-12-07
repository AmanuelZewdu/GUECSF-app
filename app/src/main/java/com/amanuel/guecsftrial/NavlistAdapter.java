package com.amanuel.guecsftrial;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Amanuel on 8/9/2016.
 */
public class NavlistAdapter extends ArrayAdapter<NavItem> {

    Context context;
    int resLayout;
    List<NavItem> listNavItems;

    public NavlistAdapter(Context context, int resLayout,    List<NavItem> listNavItems) {
        super(context, resLayout, listNavItems );
        this.context = context;
        this.resLayout=resLayout;
        this.listNavItems= listNavItems;
    }

    @SuppressLint("ViewHolder")@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v =View.inflate(context,resLayout,null);

        TextView title=(TextView)v.findViewById(R.id.title);
        ImageView navIcon=(ImageView)v.findViewById(R.id.nav_icon);

        NavItem navItem=listNavItems.get(position);

        title.setText(navItem.getTitle());
       navIcon.setImageResource(navItem.getResIcon());


        return v;
    }
}
