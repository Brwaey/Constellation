package com.example.constellation;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {

    // 数据库对象
    SQLiteDatabase db;

    // 表名
    String table_name = "userinfo_tb";

    // 构造方法，初始化数据库对象
    public UserInfo(SQLiteDatabase db) {
        this.db = db;
    }

    // 插入用户信息到数据库
    public void insert(User user) {
        ContentValues cv = new ContentValues();
        cv.put("password", user.getpassword());
        cv.put("username", user.getusername());
        cv.put("age", user.getAge());
        cv.put("constellation", user.getConstellation());
        db.insert(table_name, null, cv);
    }

    // 更新用户信息
    public void update(User user) {
        ContentValues cv = new ContentValues();
        cv.put("password", user.getpassword());
        cv.put("age", user.getAge());
        cv.put("constellation", user.getConstellation());
        db.update(table_name, cv, "username=?", new String[]{user.getusername()});
    }

    // 根据用户名删除用户信息
    public void delete(String username) {
        db.delete(table_name, "username=?", new String[]{username});
    }

    // 查询所有与给定用户名匹配的用户信息
    public List<User> queryAll(String username) {
        List<User> list = new ArrayList<>();
        Cursor cursor = db.query(table_name, null, "username=?", new String[]{username}, null, null, null);
        while (cursor.moveToNext()) {
            int uid = cursor.getInt(0);
            String str_username = cursor.getString(1);
            String str_password = cursor.getString(2);
            String str_age = cursor.getString(3);
            String str_constellation = cursor.getString(4);
            list.add(new User(uid, str_username, str_password, str_age, str_constellation));
        }
        return list;
    }

    // 检查用户名是否存在
    public boolean loginCheck(String username) {
        Cursor cursor = db.query(table_name, null, "username=?", new String[]{username}, null, null, null);
        return cursor.moveToNext(); // 如果存在记录则返回true
    }

    // 验证用户名和密码是否正确
    public boolean loginVerify(String username, String password) {
        Cursor cursor = db.query(table_name, null, "username=? and password=?", new String[]{username, password}, null, null, null);
        return cursor.moveToNext(); // 如果存在匹配记录则返回true
    }
}