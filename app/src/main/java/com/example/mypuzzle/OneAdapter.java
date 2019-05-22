package com.example.mypuzzle;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Vector;



class OneAdapter extends RecyclerView.Adapter<OneAdapter.OneHolder> {

    private Vector<One> mOne = new Vector<>();

    private Activity activity;
    private Context context;


    private int width = 0, height = 0;
    int[] imgs;

    public OneAdapter(Activity activity) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
    }

    @NonNull
    @Override
    public OneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_game4_one, parent,false);
        RecyclerView.ViewHolder viewHolder =null;

        viewHolder = new OneHolder(view);
        return (OneHolder) viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OneHolder holder, int position) {
        if (width != 0 && height != 0) {
            holder.itemCardView.getLayoutParams().width = width;
            holder.itemCardView.getLayoutParams().height = height;
        }
        One one = mOne.get(position);
        int image = one.getImage();
        holder.itemCardView.setBackgroundResource(image);
    }

    @Override
    public int getItemCount() {
        return mOne.size();
    }

    void update(Vector<One> ones) {
        this.mOne = ones;
        notifyDataSetChanged();
    }

    void setLength(int length) {
        this.width = length;
        this.height = length;
    }

    void change(int oldPos, int newPos) {
//6, 9
        One oldOne = mOne.get(oldPos);
        One newOne = mOne.get(newPos);
        mOne.remove(newPos);
        mOne.add(newPos, oldOne);
        mOne.remove(oldPos);
        mOne.add(oldPos, newOne);
        notifyDataSetChanged();
    }

    Vector<One> currentOne() {
        return this.mOne;
    }

    void finish(int pos) {
        mOne.get(pos).setImage(imgs[pos]);
        notifyItemChanged(pos);
    }

    class OneHolder extends RecyclerView.ViewHolder {


        private CardView itemCardView;

        OneHolder(View view) {
            super(view.getRootView());
            itemCardView = view.findViewById(R.id.itemCardView);


        }

    }
}