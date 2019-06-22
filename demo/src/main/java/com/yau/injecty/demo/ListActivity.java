package com.yau.injecty.demo;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.yau.injecty.annotation.ContentView;
import com.yau.injecty.annotation.FindView;
import com.yau.injecty.annotation.OnItemClick;
import com.yau.injecty.annotation.OnItemLongClick;
import com.yau.injecty.demo.adapter.TestAdapter;
import com.yau.injecty.recyclerview.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * author: yau
 * time: 2019/06/22 22:53
 * desc:
 */
@ContentView(R.layout.activity_list)
public class ListActivity extends BaseActivity {

    @FindView(R.id.rv_list)
    RecyclerView mRvList;

    @Override
    protected void onResume() {
        super.onResume();
        mRvList.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        TestAdapter adapter = new TestAdapter();
        adapter.setData(getData());
        mRvList.setAdapter(adapter);
    }

    private List<String> getData() {
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            dataList.add("item");
        }
        return dataList;
    }

    @OnItemClick(R.id.rv_list)
    private void onItemClick(View view, String data, int position) {
        Toast.makeText(ListActivity.this, "click: " + data + position, Toast.LENGTH_SHORT).show();
    }

    @OnItemLongClick(R.id.rv_list)
    private void onItemLongClick(View view, String data, int position) {
        Toast.makeText(ListActivity.this, "longClick: " + data + position, Toast.LENGTH_SHORT).show();
    }
}
