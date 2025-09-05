package com.example.constellation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class YearActivity extends AppCompatActivity {

    // 定义TextView控件
    TextView tv_name, tv_date, tv_health, tv_love, tv_career, tv_finance, tv_info, tv_text;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year);  // 设置布局

        // 初始化TextView控件
        tv_name = findViewById(R.id.tv_name);
        tv_date = findViewById(R.id.tv_date);
        tv_health = findViewById(R.id.tv_health);
        tv_love = findViewById(R.id.tv_love);
        tv_career = findViewById(R.id.tv_career);
        tv_finance = findViewById(R.id.tv_finance);
        tv_info = findViewById(R.id.tv_info);
        tv_text = findViewById(R.id.tv_text);

        // 获取Intent中的数据
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String data = bundle.getString("信息");  // 从Bundle中获取数据

        // 解析JSON数据并显示在TextView上
        parseJsonDataAndShow(data);
    }

    // 解析JSON数据的方法
    public void parseJsonDataAndShow(String jsonStr) {
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);  // 创建JSONObject对象
            String name = jsonObject.optString("name");
            String date = jsonObject.optString("date");
            String health = jsonObject.optString("health");
            String love = jsonObject.optString("love");
            String career = jsonObject.optString("career");
            String finance = jsonObject.optString("finance");

            // 将解析的数据显示到对应的TextView上
            tv_name.setText(name);
            tv_date.setText(date);
            tv_health.setText(health);
            tv_love.setText(love);
            tv_career.setText(career);
            tv_finance.setText(finance);

            String mima = jsonObject.optString("mima");  // 获取包含额外信息的JSON字符串

            try {
                JSONObject jsonObject_mima = new JSONObject(mima);  // 解析额外的JSON字符串
                String info = jsonObject_mima.optString("info");
                String text = jsonObject_mima.optString("text");

                // 将额外的信息显示到对应的TextView上
                tv_info.setText(info);
                tv_text.setText(text);

            } catch (JSONException e) {
                e.printStackTrace();  // 处理异常
            }

        } catch (JSONException e) {
            e.printStackTrace();  // 处理异常
        }
    }
}