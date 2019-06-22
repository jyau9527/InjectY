# InjectY

### 简介

一个简单的注入框架。

### 用法

##### 1、页面注入

###### （1）启动注入

```
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectManager.getInstance().inject(this);
    }
}
```

###### （2）设置ContentView

```
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
}
```

###### （3）View注入

```
@FindView(R.id.tv_app_name)
private TextView mTvAppName;
@FindView(R.id.btn_test)
private Button mBtnTest;
```

###### （4）事件注入

```
@OnClick(R.id.btn_test)
private void onViewClick(View view) {
    if (view.getId() == R.id.btn_test) {
        Toast.makeText(MainActivity.this,
                "click: " + mBtnTest.getText(), Toast.LENGTH_SHORT).show();
    }
}

@OnLongClick({R.id.tv_app_name, R.id.btn_test})
private boolean onViewLongClick(View view) {
    switch (view.getId()) {
        case R.id.tv_app_name:
            Toast.makeText(MainActivity.this,
                    "longclick: " + mTvAppName.getText(), Toast.LENGTH_SHORT).show();
            break;
        case R.id.btn_test:
            Toast.makeText(MainActivity.this,
                    "longclick: " + mBtnTest.getText(), Toast.LENGTH_SHORT).show();
            break;
    }
    return true;
}
```