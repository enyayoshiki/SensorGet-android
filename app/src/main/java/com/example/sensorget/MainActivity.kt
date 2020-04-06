package com.example.sensorget

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.KITKAT_WATCH)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buffer = StringBuilder()

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sList : List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
        for (sensor : Sensor in sList ) {
            buffer.append("${sensor.stringType},${sensor.name},${sensor.vendor}\n")
        }
        txt01.text = buffer.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.accelSensor_menu -> {
             val intent = Intent(this,AccelGet::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
