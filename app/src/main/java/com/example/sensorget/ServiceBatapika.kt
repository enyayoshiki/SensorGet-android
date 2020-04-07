package com.example.sensorget

import android.content.Intent
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_service_batapika.*

class ServiceBatapika : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_batapika)


        startBtn.setOnClickListener {
            val intent = Intent(this, TorchOnService::class.java)
            startActivity(intent)
        }
        endBtn.setOnClickListener {
            val intent = Intent(this,TorchOnService::class.java)
            stopService(intent)
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
            R.id.batapika_menu -> {
                val intent = Intent(this, BataPika::class.java)
                startActivity(intent)
                return true
            }
            R.id.accelSensor_menu -> {
                val intent = Intent(this,AccelGet::class.java)
                startActivity(intent)
                return true
            }
            return super.onOptionsItemSelected(item) -> return false
        }
    }

}
