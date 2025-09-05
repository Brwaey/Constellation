package com.example.constellation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class API {

    // 执行星座信息的GET请求
    public static String doGet(String constellation, String day_type) {
        String result = "";
        BufferedReader reader = null;
        try {
            HttpURLConnection httpURLConnection = null;
            // 构建请求URL
            String url = "http://web.juhe.cn/constellation/getAll?consName=" + constellation + "&type=" + day_type + "&key=db87614e1e6901e2580bebafe428740d";
            URL requestUrl = new URL(url);
            httpURLConnection = (HttpURLConnection) requestUrl.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();

            // 获取输入流并读取响应内容
            InputStream inputStream = httpURLConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                return null;
            }
            result = builder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 执行星座运势查询的GET请求
    public static String doGetCons(String date) {
        String result = "";
        BufferedReader reader = null;
        try {
            HttpURLConnection httpURLConnection = null;
            // 构建请求URL
            String url = "http://apis.juhe.cn/fapig/constellation/query?keyword=" + date + "&key=801a4db3c00e2046cb6039cb6321becf";
            URL requestUrl = new URL(url);
            httpURLConnection = (HttpURLConnection) requestUrl.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();

            // 获取输入流并读取响应内容
            InputStream inputStream = httpURLConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                return null;
            }
            result = builder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 执行星座配对查询的GET请求
    public static String doGetPair(String men, String women) {
        String result = "";
        BufferedReader reader = null;
        try {
            HttpURLConnection httpURLConnection = null;
            // 构建请求URL
            String url = "https://apis.juhe.cn/xzpd/query?men=" + men + "&women=" + women + "&key=1a72994a81543695a1cb3bf59b073a6e";
            URL requestUrl = new URL(url);
            httpURLConnection = (HttpURLConnection) requestUrl.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();

            // 获取输入流并读取响应内容
            InputStream inputStream = httpURLConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                return null;
            }
            result = builder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}