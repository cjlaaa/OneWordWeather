package com.cjlaaa.onewordweather;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cjlaaa on 2017/6/11.
 */

public class CityRepo {
    private DBHelper dbHelper;

    public CityRepo(Context context){
        dbHelper=new DBHelper();
    }

    public int insert(City city){
        //打开连接，写入数据
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(City.KEY_ID,city.id);
        values.put(City.KEY_province_id,city.province_id);
        values.put(City.KEY_name,city.name);
        values.put(City.KEY_city_num,city.city_num);
        //
        long city_Id=db.insert(City.TABLE,null,values);
        db.close();
        return (int)city_Id;
    }

    public void delete(int city_Id){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete(City.TABLE,City.KEY_ID+"=?", new String[]{String.valueOf(city_Id)});
        db.close();
    }
    public void update(City city){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(City.KEY_ID,city.id);
        values.put(City.KEY_province_id,city.province_id);
        values.put(City.KEY_name,city.name);
        values.put(City.KEY_city_num,city.city_num);

        db.update(City.TABLE,values,City.KEY_ID+"=?",new String[] { String.valueOf(city.id) });
        db.close();
    }

    public ArrayList<HashMap<String, String>> getCityList(){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                City.KEY_ID+","+
                City.KEY_province_id+","+
                City.KEY_name+","+
                City.KEY_city_num+" FROM "+City.TABLE;
        ArrayList<HashMap<String,String>> cityList=new ArrayList<HashMap<String, String>>();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> city=new HashMap<String,String>();
                city.put("id",cursor.getString(cursor.getColumnIndex(City.KEY_ID)));
                city.put("name",cursor.getString(cursor.getColumnIndex(City.KEY_name)));
                cityList.add(city);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return cityList;
    }

    public City getCityById(int Id){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                City.KEY_ID + "," +
                City.KEY_province_id+ "," +
                City.KEY_name + "," +
                City.KEY_city_num +
                " FROM " + City.TABLE
                + " WHERE " +
                City.KEY_ID + "=?";
        int iCount=0;
        City city=new City();
        Cursor cursor=db.rawQuery(selectQuery,new String[]{String.valueOf(Id)});
        if(cursor.moveToFirst()){
            do{
                city.id =cursor.getInt(cursor.getColumnIndex(City.KEY_ID));
                city.province_id =cursor.getInt(cursor.getColumnIndex(City.KEY_province_id));
                city.name  =cursor.getString(cursor.getColumnIndex(City.KEY_name));
                city.city_num =cursor.getString(cursor.getColumnIndex(City.KEY_city_num));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return city;
    }
}
