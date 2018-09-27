package com.example.asus.ihtravel.MyRecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.ihtravel.R;
import com.example.asus.ihtravel.SpaceCraft;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.RecyclerVH>{

    Context context;
    private List<SpaceCraft> spaceCraftList;

    public MyAdapter(Context context, List<SpaceCraft> spaceCraftListc) {
        this.context = context;
        this.spaceCraftList = spaceCraftListc;
    }

    @Override
    public RecyclerVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.model,parent,false);
        return new RecyclerVH(v);
    }

    @Override
    public void onBindViewHolder(RecyclerVH holder, int position) {
        // holder.nameTxt.setText(spaceCraftListc[position]);
        final SpaceCraft spaceCraft = spaceCraftList.get(position);

        //loading the image
        Glide.with(context)
                .load(spaceCraft.getImageUrl())
                .into(holder.imageView);

        holder.textViewName.setText(spaceCraft.getName());
        holder.textViewAddress.setText(spaceCraft.getAddress());
        holder.textViewTel.setText(String.valueOf(spaceCraft.getTel()));
        holder.textViewGoldcard.setText(String.valueOf(spaceCraft.getGoldcard()));
        holder.textViewStandardcard.setText(String.valueOf(spaceCraft.getStandardcard()));

//        Picasso.with(context)
//                .load(spaceCraft.getImageUrl())
//                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        int l = 0;
        if (spaceCraftList != null ){
            l = spaceCraftList.size();}
        return l;
    }


    /*
    VIEWHOLDER CLASS
     */
    public class RecyclerVH extends RecyclerView.ViewHolder
    {
        public TextView textViewName, textViewAddress, textViewTel, textViewGoldcard,textViewStandardcard;
        public ImageView imageView;

        public RecyclerVH(View itemView) {
            super(itemView);

            textViewName =  itemView.findViewById(R.id.nameTxt);
            textViewAddress = itemView.findViewById(R.id.textViewAddress);
            textViewTel = itemView.findViewById(R.id.textViewTel);
            textViewGoldcard = itemView.findViewById(R.id.textViewGoldcard);
            textViewStandardcard = itemView.findViewById(R.id.textViewStandardcard);
            imageView =  itemView.findViewById(R.id.bros_sart);
        }
    }
}
