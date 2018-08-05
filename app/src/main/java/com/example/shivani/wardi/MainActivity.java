package com.example.shivani.wardi;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer, gyroscope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;
        TextView xa, ya, za, xg, yg, zg, text;
        xa = (TextView) findViewById(R.id.xAxis);
        ya = (TextView) findViewById(R.id.yAxis);
        za = (TextView) findViewById(R.id.zAxis);
        text = (TextView) findViewById(R.id.msg);

        xg = (TextView) findViewById(R.id.xgAxis);
        yg = (TextView) findViewById(R.id.ygAxis);
        zg = (TextView) findViewById(R.id.zgAxis);
        float x, y, z;
        float a, b, c;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x = sensorEvent.values[0];
            y = sensorEvent.values[1];
            z = sensorEvent.values[2];
            //xa.setText(String.valueOf(x));
            //ya.setText(String.valueOf(y));
            //za.setText(String.valueOf(z));

                if (z < 0)
                    text.setText("Don't you get bored of me");
                else
                        text.setText("See, I told you");

        }
        if(mySensor.getType() == Sensor.TYPE_GYROSCOPE)
        {
            a = sensorEvent.values[0];
            b = sensorEvent.values[1];
            c = sensorEvent.values[2];
            //xg.setText(String.valueOf(a));
            //yg.setText(String.valueOf(b));
            //zg.setText(String.valueOf(c));

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


}

