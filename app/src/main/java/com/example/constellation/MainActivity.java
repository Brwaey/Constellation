package com.example.constellation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // 定义用于显示星座名称、速配星座、颜色等信息的TextView组件
    TextView tv_name, tv_QFriend, tv_color, test, username, tv;
    // 定义用于选择星座、日期范围等的Spinner组件
    Spinner sp_stars, sp_stars_cons, sp_men, sp_women;
    // 定义日期选择监听器
    DatePickerDialog.OnDateSetListener setListener;
    // 数据库文件名
    String DB_NAME = "userinfo.db";
    // SQLite数据库帮助类实例
    MySQLiteOpenHelper helper;
    // 可读的SQLite数据库实例
    SQLiteDatabase db;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);// 设置布局文件
        // 初始化UI组件
        tv_name = findViewById(R.id.tv_name);
        tv_QFriend = findViewById(R.id.tv_QFriend);
        tv_color = findViewById(R.id.tv_color);
        sp_stars = findViewById(R.id.sp_stars);
        sp_stars_cons = findViewById(R.id.sp_stars_cons);
        sp_men = findViewById(R.id.sp_men);
        sp_women = findViewById(R.id.sp_women);
        test = findViewById(R.id.test);
        username = findViewById(R.id.username);
        tv = findViewById(R.id.tv);
        // 初始化数据库帮助类和可读数据库实例
        helper = new MySQLiteOpenHelper(this, DB_NAME, null, 1);
        db = helper.getReadableDatabase();
        // 创建用户信息管理对象
        UserInfo info = new UserInfo(db);
        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        // 设置日期选择框点击事件
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth, setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        // 设置日期选择监听器
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String str_month = "";
                String str_day = "";
                month = month + 1;
                if (month < 10) {
                    str_month = "0" + month;
                } else {
                    str_month = "" + month;
                }
                if (dayOfMonth < 10) {
                    str_day = "0" + dayOfMonth;
                } else {
                    str_day = "" + dayOfMonth;
                }
                String date = year + "-" + str_month + "-" + str_day;
                tv.setText(date);
            }
        };
    }

    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0) {
                String strData = (String) msg.obj;
                // 根据不同的时间范围启动不同的Activity
                if (day.equals("今日") || day.equals("明日")) {
                    Intent intent = new Intent(MainActivity.this, DayActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("信息", strData);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                } else if (day.equals("一周")) {
                    Intent intent = new Intent(MainActivity.this, WeekActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("信息", strData);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                } else if (day.equals("本月")) {
                    Intent intent = new Intent(MainActivity.this, MonthActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("信息", strData);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, YearActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("信息", strData);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
    // 处理星座查询结果的消息处理器
    private Handler mHandler_cons = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String strData = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(strData);
                    String result = jsonObject.optString("result");
                    // 启动新的Activity并传递解析后的数据
                    Intent intent = new Intent(MainActivity.this, ConsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("信息", result);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    };
    // 处理配对查询结果的消息处理器
    private Handler mHandler_pair = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String strData = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(strData);
                    String result = jsonObject.optString("result");
                    // 启动新的Activity并传递解析后的数据
                    Intent intent = new Intent(MainActivity.this, PairActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("信息", result);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    };
    // 开始执行网络请求的方法
    public void start(View view) {
        new Thread(new Runnable() {
            //            子线程
            @Override
            public void run() {
                String stringFromNet = getStringFromNet(); // 获取网络数据

                Message message = new Message();
                message.what = 0;
                message.obj = stringFromNet;

                mHandler.sendMessage(message); // 发送消息到主线程处理

            }
        }).start();
    }
    // 开始执行星座查询的方法
    public void start_cons(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String stringCons = getStringCons(); // 获取星座查询数据

                Message message = new Message();
                message.what = 0;
                message.obj = stringCons;

                mHandler_cons.sendMessage(message);
            }
        }).start();
    }
    // 开始执行配对查询的方法
    public void start_pair(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String stringPair = getStringPair(); // 获取配对查询数据

                Message message = new Message();
                message.what = 0;
                message.obj = stringPair;

                mHandler_pair.sendMessage(message);
            }
        }).start();
    }

    String day;
    // 获取网络数据的方法
    private String getStringFromNet() {
        String constellation = sp_stars_cons.getSelectedItem().toString(); // 获取选中的星座
        day = sp_stars.getSelectedItem().toString(); // 获取选中的时间范围
        String day_type = ""; // 定义时间范围类型变量
        // 根据选中的时间范围设置时间范围类型
        if (day.equals("今日")) {
            day_type = "today";
        } else if (day.equals("明日")) {
            day_type = "tomorrow";
        } else if (day.equals("一周")) {
            day_type = "week";
        } else if (day.equals("本月")) {
            day_type = "month";
        } else if (day.equals("今年")) {
            day_type = "year";
        }
        return API.doGet(constellation, day_type); // 调用API获取数据
    }
    // 获取星座查询数据的方法
    private String getStringCons() {
        String date = tv.getText().toString(); // 获取选中的日期
        return API.doGetCons(date); // 调用API获取数据
    }
    // 获取配对查询数据的方法
    private String getStringPair() {
        String men = sp_men.getSelectedItem().toString(); // 获取选中的男性星座
        String women = sp_women.getSelectedItem().toString(); // 获取选中的女性星座
        return API.doGetPair(men, women); // 调用API获取数据
    }
    // 进入用户信息页面的方法
    public void goUserInfo(View view) {
        UserInfo info = new UserInfo(db); // 创建用户信息管理对象
        Intent intent = getIntent(); // 获取当前Intent
        Bundle bundle = intent.getExtras(); // 获取Intent中的额外数据
        String str_username = bundle.getString("username"); // 获取用户名
        List<User> list = info.queryAll(str_username); // 查询所有用户信息
        StringBuffer sb = new StringBuffer();
        sb.append(list.get(0).toString()); // 将查询到的信息转换为字符串
        Intent intent_userinfo = new Intent(MainActivity.this, UserInfoActivity.class); // 创建进入用户信息页面的Intent
        Bundle bundle1 = new Bundle();
        bundle1.putString("username", str_username); // 将用户名放入Bundle中
        bundle1.putString("userinfo", sb.toString()); // 将用户信息放入Bundle中
        intent_userinfo.putExtras(bundle1); // 将Bundle放入Intent中
        startActivity(intent_userinfo); // 启动用户信息页面
    }
}