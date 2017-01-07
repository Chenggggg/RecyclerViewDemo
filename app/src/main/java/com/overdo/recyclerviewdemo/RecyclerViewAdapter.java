package com.overdo.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;


class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private int mType;
    private Context mContext;
    private List mDatas;
    private final int[] mColors = {R.color.black, R.color.colorPrimaryDarkGreen, R.color.colorPrimaryBlue,
            R.color.colorPrimaryBrown, R.color.colorPrimaryDark, R.color.colorPrimaryDarkdeepOrange,
            R.color.white,R.color.colorPrimaryDarkPink};
    private Random mRandom;

    public RecyclerViewAdapter() {
    }

    public RecyclerViewAdapter(Context context, List list, int type) {
        this.mContext = context;
        mDatas = list;
        mType = type;
        mRandom = new Random();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item_normal, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.relativalayout.setBackgroundColor(mColors[position%7]);
        holder.tv.setText(mDatas.get(position).toString());

        if (mType == 1) {
            holder.tv.setText(getRandomLengthName(mDatas.get(position).toString()));
        }
    }

    private String getRandomLengthName(String name) {

        int length = mRandom.nextInt(20) + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(name);
        }
        return sb.toString();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        RelativeLayout relativalayout;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
            relativalayout = (RelativeLayout) view.findViewById(R.id.id_relativalayout);
        }
    }


    public void addData(int position) {
        mDatas.add(position, "Insert One");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }
}