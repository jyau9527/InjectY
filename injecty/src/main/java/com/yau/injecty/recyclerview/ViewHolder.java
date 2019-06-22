package com.yau.injecty.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * author: yau
 * time: 2019/06/22 22:06
 * desc:
 */
public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {

    private SparseArray<View> mViews;

    private ViewHolder(@NonNull View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    static ViewHolder createViewHolder(Context context, int layoutId, ViewGroup parent) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
}
