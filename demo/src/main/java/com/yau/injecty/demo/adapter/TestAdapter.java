package com.yau.injecty.demo.adapter;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.yau.injecty.recyclerview.Adapter;
import com.yau.injecty.recyclerview.ViewHolder;

/**
 * author: yau
 * time: 2019/06/22 23:12
 * desc:
 */
public class TestAdapter extends Adapter<String> {
    @Override
    protected int getItemLayoutId() {
        return android.R.layout.simple_list_item_1;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, String data, int position) {
        TextView textView = viewHolder.getView(android.R.id.text1);
        textView.setText(data + position);
    }
}
