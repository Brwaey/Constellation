package com.example.constellation;

// 导入必要的Android和JSON库
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class PairActivity extends AppCompatActivity {

    // 声明所有TextView对象
    TextView tv_men, tv_women, tv_zhishu, tv_bizhong, tv_xiangyue, tv_tcdj, tv_jieguo, tv_lianai, tv_zhuyi;

    @SuppressLint("MissingInflatedId") // 警告：可能缺少ID检查
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair); // 设置布局文件

        // 初始化所有TextView对象
        tv_men = findViewById(R.id.tv_men);
        tv_women = findViewById(R.id.tv_women);
        tv_zhishu = findViewById(R.id.tv_zhishu);
        tv_bizhong = findViewById(R.id.tv_bizhong);
        tv_xiangyue = findViewById(R.id.tv_xiangyue);
        tv_tcdj = findViewById(R.id.tv_tcdj);
        tv_jieguo = findViewById(R.id.tv_jieguo);
        tv_lianai = findViewById(R.id.tv_lianai);
        tv_zhuyi = findViewById(R.id.tv_zhuyi);

        // 获取Intent中的数据
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String data = bundle.getString("信息"); // 获取名为"信息"的字符串数据
        parseJsonDataAndShow(data); // 解析并显示JSON数据
    }

    // 解析JSON字符串，并将数据设置到对应的TextView中
    public void parseJsonDataAndShow(String jsonStr) {
        try {
            JSONObject jsonObject = new JSONObject(jsonStr); // 创建JSONObject对象

            // 从JSON对象中获取各个字段值，并使用optString方法处理可能的异常情况
            String men = jsonObject.optString("men");
            String women = jsonObject.optString("women");
            String zhishu = jsonObject.optString("zhishu");
            String bizhong = jsonObject.optString("bizhong");
            String xiangyue = jsonObject.optString("xiangyue");
            String tcdj = jsonObject.optString("tcdj");
            String jieguo = jsonObject.optString("jieguo");
            String lianai = jsonObject.optString("lianai");
            String zhuyi = jsonObject.optString("zhuyi");

            // 将获取到的数据设置到对应的TextView中
            tv_men.setText(men);
            tv_women.setText(women);
            tv_zhishu.setText(zhishu);
            tv_bizhong.setText(bizhong);
            tv_xiangyue.setText(xiangyue);
            tv_tcdj.setText(tcdj);
            tv_jieguo.setText(jieguo);
            tv_lianai.setText(lianai);
            tv_zhuyi.setText(zhuyi);

        } catch (JSONException e) {
            e.printStackTrace(); // 打印异常堆栈信息
        }
    }
}