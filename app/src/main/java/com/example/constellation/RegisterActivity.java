package com.example.constellation;

// 导入必要的Android库
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// 导入AppCompatActivity类以支持更现代的UI组件
import androidx.appcompat.app.AppCompatActivity;

// 定义注册活动类
public class RegisterActivity extends AppCompatActivity {

    // 声明用于存储用户输入数据的EditText控件
    EditText username, password, age, constellation;

    // 注册按钮
    Button register;

    // 数据库名称
    String DB_NAME = "userinfo.db";

    // 自定义SQLiteOpenHelper子类实例
    MySQLiteOpenHelper helper;

    // SQLite数据库实例
    SQLiteDatabase db;

    // 重写onCreate方法，当Activity被创建时调用
    @SuppressLint("MissingInflatedId") // 这个注解用于忽略一些Lint警告
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // 调用父类的onCreate方法
        setContentView(R.layout.activity_register); // 设置当前布局

        // 初始化EditText控件
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        age = findViewById(R.id.age);
        constellation = findViewById(R.id.constellation);
        register = findViewById(R.id.register);

        // 创建SQLiteOpenHelper子类实例并获取可读数据库
        helper = new MySQLiteOpenHelper(this, DB_NAME, null, 1);
        db = helper.getReadableDatabase();

        // 创建UserInfo对象
        UserInfo info = new UserInfo(db);

        // 设置注册按钮点击事件监听器
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取EditText中的文本
                String str_username = username.getText().toString();
                String str_password = password.getText().toString();
                String str_age = age.getText().toString();
                String str_constellation = constellation.getText().toString();

                // 检查是否有空输入
                if (str_username.equals("") || str_password.equals("") || str_age.equals("") || str_constellation.equals("")) {
                    Toast.makeText(RegisterActivity.this, "用户信息不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    // 检查用户名是否已存在
                    if (info.loginCheck(str_username)) {
                        Toast.makeText(RegisterActivity.this, "用户名已存在，请重新输入", Toast.LENGTH_SHORT).show();
                    } else {
                        // 插入新用户信息到数据库
                        info.insert(new User(str_username, str_password, str_age, str_constellation));

                        // 跳转至登录界面
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);

                        // 显示注册成功的提示
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}