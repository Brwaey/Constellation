package com.example.constellation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AlterActivity extends AppCompatActivity {

    // 定义用于输入用户信息的EditText控件
    EditText password, age, constellation;

    // 定义用于触发修改操作的Button控件
    Button alter;

    // 数据库名称
    String DB_NAME = "userinfo.db";

    // 数据库辅助类实例
    MySQLiteOpenHelper helper;

    // SQLite数据库实例
    SQLiteDatabase db;

    // 在onCreate方法中初始化视图和数据
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter); // 设置布局文件

        // 初始化EditText控件，绑定到对应的UI元素
        password = findViewById(R.id.password);
        age = findViewById(R.id.age);
        constellation = findViewById(R.id.constellation);
        alter = findViewById(R.id.alter);

        // 创建MySQLiteOpenHelper实例，用于管理数据库
        helper = new MySQLiteOpenHelper(this, DB_NAME, null, 1);
        db = helper.getReadableDatabase(); // 获取数据库可读实例

        // 实例化UserInfo类，用于处理用户信息操作
        UserInfo info = new UserInfo(db);

        // 获取Intent传递的数据
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String str_username = bundle.getString("username");

        // 设置Button点击事件监听器
        alter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取EditText中的文本
                String str_password = password.getText().toString();
                String str_age = age.getText().toString();
                String str_constellation = constellation.getText().toString();

                // 更新用户信息
                info.update(new User(str_username, str_password, str_age, str_constellation));

                // 跳转至LoginActivity
                Intent intent = new Intent(AlterActivity.this, LoginActivity.class);
                startActivity(intent);

                // 显示提示信息
                Toast.makeText(AlterActivity.this, "修改成功,请重新登录", Toast.LENGTH_SHORT).show();
            }
        });
    }
}