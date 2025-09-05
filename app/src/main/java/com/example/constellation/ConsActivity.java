package com.example.constellation;

// 导入必要的Android库以及JSON处理相关的库
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity; // 导入AppCompatActivity，这是继承自的基类

import org.json.JSONException;
import org.json.JSONObject; // 导入JSON解析库

// 定义ConsActivity类，该类继承自AppCompatActivity
public class ConsActivity extends AppCompatActivity {

    // 声明TextView变量，用于显示从JSON字符串中获取的数据
    TextView tv_range, tv_name, tv_zxtd, tv_sssx, tv_zggw, tv_yysx, tv_zdtz, tv_zgxx, tv_xyys, tv_jssw, tv_xyhm, tv_kyjs, tv_bx, tv_yd, tv_qd, tv_jbtz, tv_jttz, tv_xsfg, tv_gxmd, tv_zj;

    // 重写onCreate方法，当Activity被创建时调用
    @SuppressLint("MissingInflatedId") // 注意：此注解用于提醒开发者可能忽略了资源ID的正确性检查，谨慎使用
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // 调用父类的onCreate方法
        setContentView(R.layout.activity_cons); // 设置当前Activity的布局文件

        // 初始化TextView变量，通过findViewById方法找到对应的视图组件
        tv_range = findViewById(R.id.tv_range);
        tv_name = findViewById(R.id.tv_name);
        tv_zxtd = findViewById(R.id.tv_zxtd);
        tv_sssx = findViewById(R.id.tv_sssx);
        tv_zggw = findViewById(R.id.tv_zggw);
        tv_yysx = findViewById(R.id.tv_yysx);
        tv_zdtz = findViewById(R.id.tv_zdtz);
        tv_zgxx = findViewById(R.id.tv_zgxx);
        tv_xyys = findViewById(R.id.tv_xyys);
        tv_jssw = findViewById(R.id.tv_jssw);
        tv_xyhm = findViewById(R.id.tv_xyhm);
        tv_kyjs = findViewById(R.id.tv_kyjs);
        tv_bx = findViewById(R.id.tv_bx);
        tv_yd = findViewById(R.id.tv_yd);
        tv_qd = findViewById(R.id.tv_qd);
        tv_jbtz = findViewById(R.id.tv_jbtz);
        tv_jttz = findViewById(R.id.tv_jttz);
        tv_xsfg = findViewById(R.id.tv_xsfg);
        tv_gxmd = findViewById(R.id.tv_gxmd);
        tv_zj = findViewById(R.id.tv_zj);

        // 获取Intent对象，并从中提取附加数据（Bundle）
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String data = bundle.getString("信息"); // 从Bundle中获取名为"信息"的字符串数据
        parseJsonDataAndShow(data); // 解析并显示JSON数据
    }

    // 定义方法用于解析JSON数据并更新TextView的内容
    public void parseJsonDataAndShow(String jsonStr) {
        try {
            // 将JSON字符串转换为JSONObject对象
            JSONObject jsonObject = new JSONObject(jsonStr);

            // 使用optString方法从JSONObject中安全地获取每个字段的值，并设置到相应的TextView中
            String range = jsonObject.optString("range");
            String name = jsonObject.optString("name");
            String zxtd = jsonObject.optString("zxtd");
            String sssx = jsonObject.optString("sssx");
            String zggw = jsonObject.optString("zggw");
            String yysx = jsonObject.optString("yysx");
            String zdtz = jsonObject.optString("zdtz");
            String zgxx = jsonObject.optString("zgxx");
            String xyys = jsonObject.optString("xyys");
            String jssw = jsonObject.optString("jssw");
            String xyhm = jsonObject.optString("xyhm");
            String kyjs = jsonObject.optString("kyjs");
            String bx = jsonObject.optString("bx");
            String yd = jsonObject.optString("yd");
            String qd = jsonObject.optString("qd");
            String jbtz = jsonObject.optString("jbtz");
            String jttz = jsonObject.optString("jttz");
            String xsfg = jsonObject.optString("xsfg");
            String gxmd = jsonObject.optString("gxmd");
            String zj = jsonObject.optString("zj");

            // 更新TextView内容
            tv_range.setText(range);
            tv_name.setText(name);
            tv_zxtd.setText(zxtd);
            tv_sssx.setText(sssx);
            tv_zggw.setText(zggw);
            tv_yysx.setText(yysx);
            tv_zdtz.setText(zdtz);
            tv_zgxx.setText(zgxx);
            tv_xyys.setText(xyys);
            tv_jssw.setText(jssw);
            tv_xyhm.setText(xyhm);
            tv_kyjs.setText(kyjs);
            tv_bx.setText(bx);
            tv_yd.setText(yd);
            tv_qd.setText(qd);
            tv_jbtz.setText(jbtz);
            tv_jttz.setText(jttz);
            tv_xsfg.setText(xsfg);
            tv_gxmd.setText(gxmd);
            tv_zj.setText(zj);

        } catch (JSONException e) { // 捕获JSON解析异常
            e.printStackTrace(); // 打印堆栈跟踪信息
        }
    }
}