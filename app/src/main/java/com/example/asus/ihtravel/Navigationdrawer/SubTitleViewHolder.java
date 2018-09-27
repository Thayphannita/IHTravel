package com.example.asus.ihtravel.Navigationdrawer;

import android.view.View;
import android.widget.TextView;

import com.example.asus.ihtravel.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class SubTitleViewHolder extends ChildViewHolder {
    private TextView subTitleTextView;

    public SubTitleViewHolder(View itemView) {
        super(itemView);
        subTitleTextView = (TextView) itemView.findViewById(R.id.main_nav_submenu_item_title);
    }

    public void setSubTitletName(String name) {
        subTitleTextView.setText(name);
    }
}
