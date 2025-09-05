package com.example.constellation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class WeekActivity extends AppCompatActivity {

    // 定义用于显示数据的TextView组件
    TextView tv_name, tv_date, tv_health, tv_love, tv_work, tv_money;

    @SuppressLint("MissingInflatedId")  // 忽略检查缺少的id
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);  // 设置当前活动使用的布局

        // 初始化TextView组件
        tv_name = findViewById(R.id.tv_name);
        tv_date = findViewById(R.id.tv_date);
        tv_health = findViewById(R.id.tv_health);
        tv_love = findViewById(R.id.tv_love);
        tv_work = findViewById(R.id.tv_work);
        tv_money = findViewById(R.id.tv_money);

        // 获取意图并解析传入的数据
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String data = bundle.getString("信息");  // 从Bundle中获取字符串数据
        parseJsonDataAndShow(data);  // 解析并显示JSON数据
    }

    // 解析JSON数据并更新TextView组件
    public void parseJsonDataAndShow(String jsonStr) {
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);  // 创建JSONObject对象
            // 使用optString方法获取JSON中的字符串值，如果键不存在则返回空字符串
            String name = jsonObject.optString("name");
            String date = jsonObject.optString("date");
            String health = jsonObject.optString("health");
            String love = jsonObject.optString("love");
            String work = jsonObject.optString("work");
            String money = jsonObject.optString("money");

            // 更新TextView组件以显示获取的数据
            tv_name.setText(name);
            tv_date.setText(date);
            tv_health.setText(health);
            tv_love.setText(love);
            tv_work.setText(work);
            tv_money.setText(money);

        } catch (JSONException e) {
            e.printStackTrace();  // 打印异常堆栈信息
        }
    }
}