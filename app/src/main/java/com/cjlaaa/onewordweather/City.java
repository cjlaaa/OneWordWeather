package com.cjlaaa.onewordweather;

/**
 * Created by cjlaaa on 2017/6/11.
 */

public class City {
    //表名
    public static final String TABLE="citys";

    //表的各域名
    public static final String KEY_ID="_id";
    public static final String KEY_province_id="province_id";
    public static final String KEY_name="name";
    public static final String KEY_city_num="city_num";

    //属性
    public int id;
    public int province_id;
    public String name;
    public String city_num;
}