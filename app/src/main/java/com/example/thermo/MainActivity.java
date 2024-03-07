package com.example.thermo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager manager;
    private Sensor tempSensor;
    private Thermometre t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        Button btnCelsius = new Button(this);
        btnCelsius.setText("Celsius");
        btnCelsius.setOnClickListener(view -> t.setUnit("C"));

        Button btnFahrenheit = new Button(this);
        btnFahrenheit.setText("Fahrenheit");
        btnFahrenheit.setOnClickListener(view -> t.setUnit("F"));

        Button btnKelvin = new Button(this);
        btnKelvin.setText("Kelvin");
        btnKelvin.setOnClickListener(view -> t.setUnit("K"));

        layout.addView(btnCelsius);
        layout.addView(btnFahrenheit);
        layout.addView(btnKelvin);

        t = new Thermometre(this);
        layout.addView(t);

        setContentView(layout);

        this.manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        this.tempSensor = manager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        manager.registerListener(this, tempSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            t.setTemp(sensorEvent.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
