package com.androidfizz.recyclerviewdemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidfizz.recyclerviewdemo.R;
import com.androidfizz.recyclerviewdemo.models.AndroidVersion;

import java.util.List;

/**
 * Created by jitendra.singh on 1/10/2018
 * for RecyclerViewDemo
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    //for handing long click and simple click on an item
    public interface OnItemClickListener {
        void onItemClick(int position);

        void onItemLongPress(int position);
    }

    private Context context;
    private List<AndroidVersion> list;
    private OnItemClickListener onItemClickListener;

    public ListAdapter(Context context, List<AndroidVersion> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder/* implements View.OnClickListener,
            View.OnLongClickListener */{

        private ImageView ivImage;
        private TextView tvCodeName;
        private TextView tvVersionName;
        private TextView tvApiLevel;

        ViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
            tvCodeName = (TextView) itemView.findViewById(R.id.tvCodeName);
            tvVersionName = (TextView) itemView.findViewById(R.id.tvVersionName);
            tvApiLevel = (TextView) itemView.findViewById(R.id.tvApiLevel);
            /*
            for handling click and long press
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            */
        }

        //a function to set values to view
        void setData(AndroidVersion item) {
            ivImage.setImageResource(item.getImgResId());
            tvCodeName.setText(item.getCodeName());
            tvVersionName.setText(context.getString(R.string.precio_producto, item.getVersionName()));
            tvApiLevel.setText(context.getString(R.string.des_producto, item.getApiLevel()));
        }

        /*

        @Override
        public boolean onLongClick(View view) {
            if (onItemClickListener != null)
                onItemClickListener.onItemLongPress(getAdapterPosition());
            return true; //it's compulsory to return true else onClick will also called
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(getAdapterPosition());
        }
        */
    }
}
