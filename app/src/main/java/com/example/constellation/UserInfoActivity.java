package com.example.constellation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserInfoActivity extends AppCompatActivity {

    TextView userinfo; // 显示用户信息的文本视图
    Button logout, alter, delete; // 登出、修改和删除按钮
    String DB_NAME = "userinfo.db"; // 数据库名称
    MySQLiteOpenHelper helper; // SQLite帮助类实例
    SQLiteDatabase db; // 数据库实例

    @SuppressLint({"MissingInflatedId", "WrongViewCast"}) // 注释：忽略某些检查警告
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo); // 设置布局文件

        alter = findViewById(R.id.alter); // 初始化修改按钮
        logout = findViewById(R.id.logout); // 初始化登出按钮
        delete = findViewById(R.id.delete); // 初始化删除按钮
        userinfo = findViewById(R.id.userinfo); // 初始化显示用户信息的文本视图

        helper = new MySQLiteOpenHelper(this, DB_NAME, null, 1); // 创建数据库帮助类实例
        db = helper.getReadableDatabase(); // 打开数据库用于读取

        UserInfo info = new UserInfo(db); // 创建UserInfo对象

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String str_userinfo = bundle.getString("userinfo"); // 获取从上一个活动传递过来的用户信息字符串
        String str_username = bundle.getString("username"); // 获取用户名

        userinfo.setText(str_userinfo); // 显示用户信息

        // 修改按钮点击事件处理
        alter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_user = new Intent(UserInfoActivity.this, AlterActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("username", str_username); // 将用户名放入Bundle中
                intent_user.putExtras(bundle1);
                startActivity(intent_user); // 跳转到修改页面
            }
        });

        // 登出按钮点击事件处理
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_user = new Intent(UserInfoActivity.this, LoginActivity.class);
                startActivity(intent_user); // 跳转到登录页面
                Toast.makeText(UserInfoActivity.this, "退出成功", Toast.LENGTH_SHORT).show(); // 显示退出成功的提示
            }
        });

        // 删除按钮点击事件处理
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.delete(str_username); // 删除指定用户名的记录
                Intent intent_user = new Intent(UserInfoActivity.this, LoginActivity.class);
                startActivity(intent_user); // 跳转到登录页面
                Toast.makeText(UserInfoActivity.this, "注销成功", Toast.LENGTH_SHORT).show(); // 显示注销成功的提示
            }
        });
    }
}