package com.example.sensorget

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Camera
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi

class BataPika : AppCompatActivity() ,SensorEventListener{
    private val threshold : Float = 10f
    private var oldValue : Float = 0f
    private lateinit var cameraManager: CameraManager
    private var cameraID : String? = null
    private var lightOn : Boolean = false


     fun onSensorChenged(event: SensorEvent?){
        if (event == null)return
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER){
            val zDiff = Math.abs(event.values[2]- oldValue)
            if (zDiff > threshold){
                torchOn()
            }
            oldValue = event.values[2]
        }
    }




    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bata_pika)

        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        cameraManager.registerTorchCallback(@RequiresApi(Build.VERSION_CODES.M)
        object : CameraManager.TorchCallback() {
            override fun onTorchModeChanged(cameraId: String, enabled: Boolean) {
                super.onTorchModeChanged(cameraId, enabled)
                cameraID = cameraId
                lightOn = enabled
            }
        })
    }

    override fun onResume() {
        super.onResume()
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onPause() {
        super.onPause()
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.unregisterListener(this)

        if (cameraID != null){
            try {
                if (lightOn){
                    cameraManager.setTorchMode(cameraID!!,false)
                }
            }catch (e: CameraAccessException){
                e.printStackTrace()
            }
        }
    }
    private fun torchOn(){
        try {
        if(cameraID != null){
                cameraManager.setTorchMode(cameraID,true)
            } else{
                cameraManager.setTorchMode(cameraID,false)
            }
        }catch (e:CameraAccessException){
            e.printStackTrace()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.sensorGet_Menu -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.accelSensor_menu -> {
                val intent = Intent(this, AccelGet::class.java)
                startActivity(intent)
                return true
            }
            return super.onOptionsItemSelected(item)-> return false
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        TODO("Not yet implemented")
    }
}

private fun Any.registerTorchCallback(torchCallback: CameraManager.TorchCallback) {

}

private fun CameraManager.registerTorchCallback() {

}
