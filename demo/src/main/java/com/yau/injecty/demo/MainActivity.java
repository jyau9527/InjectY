package com.yau.injecty.demo;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yau.injecty.annotation.ContentView;
import com.yau.injecty.annotation.FindView;
import com.yau.injecty.annotation.GetString;
import com.yau.injecty.annotation.OnClick;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @FindView(R.id.tv_app_name)
    private TextView mTvAppName;
    @GetString(R.string.app_name)
    private String mStrAppName;

    @Override
    protected void onResume() {
        super.onResume();
        mTvAppName.setText(mStrAppName);
    }

    @OnClick({R.id.tv_app_name, R.id.btn_jump})
    private void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_app_name:
                Toast.makeText(MainActivity.this,
                        "click: " + mTvAppName.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_jump:
                startActivity(new Intent(this, ListActivity.class));
                break;
        }
    }
}
