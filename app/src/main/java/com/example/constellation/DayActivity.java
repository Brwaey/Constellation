package com.example.constellation;

// 导入必要的Android和JSON库
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class DayActivity extends AppCompatActivity {

    // 声明用于显示数据的TextView变量
    TextView tv_name, tv_QFriend, tv_color, tv_datetime, tv_health, tv_love, tv_work, tv_money, tv_number, tv_summary, tv_all;

    // 创建视图时调用的方法
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day); // 设置布局

        // 初始化TextView组件
        tv_name = findViewById(R.id.tv_name);
        tv_QFriend = findViewById(R.id.tv_QFriend);
        tv_color = findViewById(R.id.tv_color);
        tv_datetime = findViewById(R.id.tv_datetime);
        tv_health = findViewById(R.id.tv_health);
        tv_love = findViewById(R.id.tv_love);
        tv_work = findViewById(R.id.tv_work);
        tv_money = findViewById(R.id.tv_money);
        tv_number = findViewById(R.id.tv_number);
        tv_summary = findViewById(R.id.tv_summary);
        tv_all = findViewById(R.id.tv_all);

        // 获取传递给Activity的数据
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String data = bundle.getString("信息");

        // 解析并显示数据
        parseJsonDataAndShow(data);
    }

    // 解析JSON字符串并设置TextView内容的方法
    public void parseJsonDataAndShow(String jsonStr) {
        try {
            // 将JSON字符串解析为JSONObject
            JSONObject jsonObject = new JSONObject(jsonStr);

            // 提取JSON中的各个字段值
            String name = jsonObject.optString("name");
            String QFriend = jsonObject.optString("QFriend");
            String color = jsonObject.optString("color");
            String datetime = jsonObject.optString("datetime");
            String health = jsonObject.optString("health");
            String love = jsonObject.optString("love");
            String work = jsonObject.optString("work");
            String money = jsonObject.optString("money");
            String number = jsonObject.optString("number");
            String summary = jsonObject.optString("summary");
            String all = jsonObject.optString("all");

            // 将提取到的数据设置到对应的TextView中
            tv_name.setText(name);
            tv_QFriend.setText(QFriend);
            tv_color.setText(color);
            tv_datetime.setText(datetime);
            tv_health.setText(health);
            tv_love.setText(love);
            tv_work.setText(work);
            tv_money.setText(money);
            tv_number.setText(number);
            tv_summary.setText(summary);
            tv_all.setText(all);

        } catch (JSONException e) {
            e.printStackTrace(); // 捕获并打印JSON解析异常
        }
    }
}