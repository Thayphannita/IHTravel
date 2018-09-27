package com.example.asus.ihtravel.Navigationdrawer;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class TitleMenu extends ExpandableGroup<SubTitle> {
    private int imageResource = 0;

    public TitleMenu(String title, List<SubTitle> items, int imageResource) {
        super(title, items);
        this.imageResource = imageResource;
    }

    public int getImageResource() {
        return imageResource;
    }
}

