package com.example.constellation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MonthActivity extends AppCompatActivity {

    // 定义TextView变量用于显示不同的信息
    TextView tv_name, tv_date, tv_health, tv_love, tv_work, tv_money, tv_all;

    // 使用@SuppressLint注解来忽略一些Lint警告
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置当前Activity的布局文件
        setContentView(R.layout.activity_month);

        // 初始化TextView变量，关联到布局文件中的控件
        tv_name = findViewById(R.id.tv_name);
        tv_date = findViewById(R.id.tv_date);
        tv_health = findViewById(R.id.tv_health);
        tv_love = findViewById(R.id.tv_love);
        tv_work = findViewById(R.id.tv_work);
        tv_money = findViewById(R.id.tv_money);
        tv_all = findViewById(R.id.tv_all);

        // 获取传递给该Activity的Intent，并从中提取Bundle
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        // 从Bundle中获取JSON字符串数据
        String data = bundle.getString("信息");
        // 解析并显示JSON数据
        parseJsonDataAndShow(data);
    }

    // 解析JSON字符串并设置到对应的TextView上
    public void parseJsonDataAndShow(String jsonStr) {
        try {
            // 将JSON字符串解析成JSONObject对象
            JSONObject jsonObject = new JSONObject(jsonStr);
            // 从JSONObject中获取对应键的值，并设置到TextView上
            String name = jsonObject.optString("name");
            String date = jsonObject.optString("date");
            String health = jsonObject.optString("health");
            String love = jsonObject.optString("love");
            String work = jsonObject.optString("work");
            String money = jsonObject.optString("money");
            String all = jsonObject.optString("all");

            tv_name.setText(name);
            tv_date.setText(date);
            tv_health.setText(health);
            tv_love.setText(love);
            tv_work.setText(work);
            tv_money.setText(money);
            tv_all.setText(all);

        } catch (JSONException e) {
            // 如果解析过程中出现错误，则打印异常堆栈信息
            e.printStackTrace();
        }
    }
}