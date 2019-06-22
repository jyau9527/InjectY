package com.yau.injecty.recyclerview;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * author: yau
 * time: 2019/06/22 22:20
 * desc:
 */
public abstract class Adapter<T> extends android.support.v7.widget.RecyclerView.Adapter<ViewHolder> {

    private RecyclerView.OnItemClickListener<T> mOnItemClickListener;
    private RecyclerView.OnItemLongClickListener<T> mOnItemLongClickListener;
    private List<T> mDataList;

    @NonNull
    @Override
    public final ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ViewHolder viewHolder = ViewHolder.createViewHolder(viewGroup.getContext(), getItemLayoutId(), viewGroup);
        initView(viewHolder);
        return viewHolder;
    }

    private void initView(final ViewHolder viewHolder) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    if (position > 0) {
                        mOnItemClickListener.onItemClick(v, mDataList.get(position), position);
                    }
                }
            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    if (position > 0) {
                        mOnItemLongClickListener.onItemLongClick(v, mDataList.get(position), position);
                    }
                }
                return false;
            }
        });
    }

    @Override
    public final void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        onBindViewHolder(viewHolder, mDataList.get(position), position);
    }

    @Override
    public final void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        onBindViewHolder(holder, mDataList.get(position), position, payloads);
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    public void setData(List<T> data) {
        mDataList = data;
        notifyDataSetChanged();
    }

    void setOnItemClickListener(RecyclerView.OnItemClickListener<T> onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    void setOnItemLongClickListener(RecyclerView.OnItemLongClickListener<T> onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    protected abstract int getItemLayoutId();

    protected abstract void onBindViewHolder(@NonNull ViewHolder viewHolder, T data, int position);

    protected void onBindViewHolder(@NonNull ViewHolder holder, T data, int position, @NonNull List<Object> payloads) {}
}
