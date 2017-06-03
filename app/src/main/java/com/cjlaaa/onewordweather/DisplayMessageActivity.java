package com.cjlaaa.onewordweather;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.URL;

public class DisplayMessageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);
        textView.setId(1001);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);

        new Thread(runnable).start();
    }

    private String getUrl() {
        int latitude = 0;
        int longitude = 0;
        String locale = "zh_cn";
        String isGlobal = "false";
        String appKey = "weather20151024";
        String sign = "zUFJoAR2ZVrDy1vF3D07";
        String locationKey = "weathercn%3A101010100";

        String url = "https://weatherapi.market.xiaomi.com/wtr-v3/weather/all?";
        url = url + "latitude=" + latitude + "&";
        url = url + "longitude=" + longitude + "&";
        url = url + "locationKey=" + locationKey + "&";
        url = url + "locale=" + locale + "&";
        url = url + "isGlobal=" + isGlobal + "&";
        url = url + "appKey=" + appKey + "&";
        url = url + "sign=" + sign + "&";

        return url;
    }

    Runnable runnable = new Runnable() {
        public void run() {
            try {
                URL url = new URL(getUrl());

                InputStream inputStream = url.openStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                Message msg = new Message();
                Bundle data = new Bundle();
                data.putString("str", stringBuilder.toString());
                msg.setData(data);
                mHandler.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private final MyHandler mHandler = new MyHandler(this);

    private static class MyHandler extends Handler {
        private final WeakReference<DisplayMessageActivity> mActivity;

        public MyHandler(DisplayMessageActivity activity) {
            mActivity = new WeakReference<DisplayMessageActivity>(activity);
        }

        public void handleMessage(Message msg) {
            DisplayMessageActivity activity = mActivity.get();

            Bundle data = msg.getData();
            String val = data.getString("str");
            activity.refreshUI(val);
        }
    }

    public void refreshUI(String val) {
        Log.v("str", val);
        Gson gson = new Gson();
        WeatherData data = gson.fromJson(val, WeatherData.class);

        TextView textView = (TextView) findViewById(1001);
        textView.setText(data.current.feelsLike.value + " " + data.current.feelsLike.unit);
//        textView.setText(val);
    }

    class WeatherData {
        public Current current;

        public class Current {
            public FeelsLike feelsLike;

            public class FeelsLike {
                public String unit;
                public String value;
            }
        }
    }
}

