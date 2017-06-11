package com.cjlaaa.onewordweather;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

/**
 * Created by cjlaaa on 2017/6/11.
 */
public class DBHelper {
//    private static String DB_PATH = "/data/data/com.cjlaaa.onewordweather/assets/";
//    private final String DB_PATH = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/assets/";
//    private final String DB_NAME = "xiaomi_weather.db";

    public SQLiteDatabase getWritableDatabase(){
        String myPath = DB_PATH + DB_NAME;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        return db;
    }

    public SQLiteDatabase getReadableDatabase(){
        String myPath = DB_PATH + DB_NAME;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        return db;
    }
}

//public class DBHelper extends SQLiteOpenHelper {
//    //数据库版本号
//    private static final int DATABASE_VERSION=1;
//
//    //数据库名称
//    private static final String DATABASE_NAME="xiaomi_weather.db";
//
//    public DBHelper(Context context){
//        super(context,DATABASE_NAME,null,DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        //创建数据表
//        String CREATE_TABLE_STUDENT="CREATE TABLE "+ City.TABLE+"("
//                +City.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
//                +City.KEY_province_id+" INTEGER, "
//                +City.KEY_name+" TEXT, "
//                +City.KEY_city_num+" TEXT)";
//        db.execSQL(CREATE_TABLE_STUDENT);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        //如果旧表存在，删除，所以数据将会消失
//        db.execSQL("DROP TABLE IF EXISTS "+ City.TABLE);
//
//        //再次创建表
//        onCreate(db);
//    }
//}

