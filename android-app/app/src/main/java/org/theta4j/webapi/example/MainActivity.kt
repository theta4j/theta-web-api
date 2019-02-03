package org.theta4j.webapi.example

import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.theta4j.webapi.Theta
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    companion object {
        private val TAG = "THETA_WEB_API_EXAMPLE"
    }

    private val theta = Theta.create()

    private val previewExecutor = Executors.newSingleThreadExecutor()

    private var connectionManager: ConnectionManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_button.setOnClickListener { startPreview() }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            connectionManager = ConnectionManager(applicationContext)
        }
    }

    override fun onDestroy() {
        super.onDestroy()


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            connectionManager?.close()
            connectionManager = null
        }
    }

    private fun startPreview() {
        previewExecutor.submit {
            theta.livePreview.use {
                Log.d(TAG, "start preview")
                try {
                    while (true) {
                        val bmp = BitmapFactory.decodeStream(it.nextFrame())
                        runOnUiThread {
                            live_preview.setImageBitmap(bmp)
                        }
                    }
                } catch (e: Exception) {
                    runOnUiThread {
                        live_preview.setImageResource(android.R.color.black)
                    }
                    Log.d(TAG, "stop preview")
                }
            }
        }
    }
}
