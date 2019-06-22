package com.yau.injecty.demo;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yau.injecty.annotation.ContentView;
import com.yau.injecty.annotation.FindView;
import com.yau.injecty.annotation.OnClick;
import com.yau.injecty.annotation.OnLongClick;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @FindView(R.id.tv_app_name)
    private TextView mTvAppName;
    @FindView(R.id.btn_test)
    private Button mBtnTest;

    @OnClick(R.id.btn_test)
    private void onViewClick(View view) {
        if (view.getId() == R.id.btn_test) {
            Toast.makeText(MainActivity.this,
                    "click: " + mBtnTest.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.tv_app_name)
    private void onViewClick2(View view) {
        if (view.getId() == R.id.tv_app_name) {
            Toast.makeText(MainActivity.this,
                    "click: " + mTvAppName.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    @OnLongClick({R.id.tv_app_name, R.id.btn_test})
    private boolean onViewLongClick(View view) {
        switch (view.getId()) {
            case R.id.tv_app_name:
                Toast.makeText(MainActivity.this,
                        "longClick: " + mTvAppName.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_test:
                Toast.makeText(MainActivity.this,
                        "longClick: " + mBtnTest.getText(), Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
