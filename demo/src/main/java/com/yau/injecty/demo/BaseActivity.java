package com.yau.injecty.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yau.injecty.InjectManager;

/**
 * author: yau
 * time: 2019/06/22 14:18
 * desc:
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectManager.getInstance().inject(this);
    }
}
