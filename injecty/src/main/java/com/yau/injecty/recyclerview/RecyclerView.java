package com.yau.injecty.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author: yau
 * time: 2019/06/22 22:20
 * desc:
 */
public class RecyclerView extends android.support.v7.widget.RecyclerView {

    private com.yau.injecty.recyclerview.Adapter mAdapter;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public RecyclerView(@NonNull Context context) {
        super(context);
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter != null) {
            mAdapter = (com.yau.injecty.recyclerview.Adapter) adapter;
            if (mOnItemClickListener != null) {
                mAdapter.setOnItemClickListener(mOnItemClickListener);
            }
            if (mOnItemLongClickListener != null) {
                mAdapter.setOnItemLongClickListener(mOnItemLongClickListener);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void setOnItemClickListener(RecyclerView.OnItemClickListener onItemClickListener) {
        if (mAdapter == null) {
            mOnItemClickListener = onItemClickListener;
        } else {
            mAdapter.setOnItemClickListener(onItemClickListener);
        }
    }

    @SuppressWarnings("unchecked")
    public void setOnItemLongClickListener(RecyclerView.OnItemLongClickListener onItemLongClickListener) {
        if (mAdapter == null) {
            mOnItemLongClickListener = onItemLongClickListener;
        } else {
            mAdapter.setOnItemLongClickListener(onItemLongClickListener);
        }
    }

    public interface OnItemClickListener<T> {
        void onItemClick(View view, T data, int position);
    }

    public interface OnItemLongClickListener<T> {
        void onItemLongClick(View view, T data, int position);
    }
}
