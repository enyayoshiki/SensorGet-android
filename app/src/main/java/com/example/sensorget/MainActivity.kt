package com.example.sensorget

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buffer = StringBuilder()

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sList : List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
        for (sensor : Sensor in sList ) {
            buffer.append("${sensor.type},${sensor.name},${sensor.vendor}")
        }
        txt01.text = buffer.toString()
    }
}
