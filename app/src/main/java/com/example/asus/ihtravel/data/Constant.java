package com.example.asus.ihtravel.data;

import android.os.Bundle;

import com.example.asus.ihtravel.Fragment.IHPartnerFragment;
import com.example.asus.ihtravel.Model.Category;
import com.example.asus.ihtravel.Navigationdrawer.NavMenuModel;
import com.example.asus.ihtravel.R;

import java.util.ArrayList;

public class Constant {
    public static ArrayList<NavMenuModel> getMenuNavigasi(ArrayList<Category> categoryArrayLists){
        ArrayList<NavMenuModel> menu = new ArrayList<>();

        //  menu.add(new NavMenuModel("Hotel", R.drawable.ic_beranda, BerandaFragment.newInstance()));

        IHPartnerFragment fragment = new IHPartnerFragment();

        menu.add(new NavMenuModel("Tour promotion", R.drawable.ic_gift, fragment));

        // Category
        for(int i=0;i<categoryArrayLists.size();i++){
            String name = categoryArrayLists.get(i).getName();
            String id = categoryArrayLists.get(i).getId();

            Bundle bundle = new Bundle();
            bundle.putString("id", id);
            fragment.setArguments(bundle);
            menu.add(new NavMenuModel(name, R.drawable.ic_gift, fragment));

        }

// This is static expandable listview
      /* menu.add(new NavMenuModel("IH Travel's Partner", R.drawable.ic_teman,
                new ArrayList<NavMenuModel.SubMenuModel>() {{
                   add(new NavMenuModel.SubMenuModel("Hotel", HotelFragment.newInstance()));
                    add(new NavMenuModel.SubMenuModel("Spa and message", TemanDekatFragment.newInstance()));
                    add(new NavMenuModel.SubMenuModel("Coffee and Restaurant", TemanDekatSekaliFragment.newInstance()));
                   add(new NavMenuModel.SubMenuModel("Beauty", BeautyFragment.newInstance()));
                    add(new NavMenuModel.SubMenuModel("Optic", OpticFragment.newInstance()));

        }}));*/
        return menu;
    }
}
