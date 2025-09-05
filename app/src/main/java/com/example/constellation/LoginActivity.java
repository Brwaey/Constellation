package com.example.constellation;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// 用户登录界面类
public class LoginActivity extends AppCompatActivity {

    // 定义用于输入用户名和密码的EditText控件
    EditText username, password;

    // 定义用于登录和注册的Button控件
    Button login, register;

    // 数据库名称
    String DB_NAME = "userinfo.db";

    // SQLite数据库帮助类实例
    MySQLiteOpenHelper helper;

    // SQLite数据库实例
    SQLiteDatabase db;

    // 在创建活动时调用的方法
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置活动的布局文件
        setContentView(R.layout.activity_login);

        // 初始化EditText控件
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        // 初始化Button控件
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        // 创建SQLite数据库帮助类实例并获取可读数据库
        helper = new MySQLiteOpenHelper(this, DB_NAME, null, 1);
        db = helper.getReadableDatabase();

        // 创建UserInfo实例，用于处理用户信息
        UserInfo info = new UserInfo(db);

        // 注册按钮点击事件处理器
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 启动注册界面
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // 登录按钮点击事件处理器
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取输入的用户名和密码
                String str_username = username.getText().toString();
                String str_password = password.getText().toString();

                // 检查用户名或密码是否为空
                if (str_username.equals("") || str_password.equals("")) {
                    // 显示提示信息
                    Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    // 验证用户名和密码
                    if (info.loginVerify(str_username, str_password)) {
                        // 登录验证成功，启动主界面
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        // 将用户名传递给主界面
                        Bundle bundle = new Bundle();
                        bundle.putString("username", str_username);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        // 显示登录成功的提示信息
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 显示登录失败的提示信息
                        Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}