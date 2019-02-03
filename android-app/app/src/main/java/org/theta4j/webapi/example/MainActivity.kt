package org.theta4j.webapi.example

import android.graphics.BitmapFactory
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_button.setOnClickListener { startPreview() }
    }

    private fun startPreview() {
        Log.d(TAG, "start preview")
        previewExecutor.submit {
            Log.d(TAG, "THETA INFO : ${theta.info()}")
            theta.livePreview.use {
                Log.d(TAG, "got preview stream")
                while (true) {
                    val bmp = BitmapFactory.decodeStream(it.nextFrame())
                    Log.d(TAG, "frame received")
                    runOnUiThread {
                        live_preview.setImageBitmap(bmp)
                    }
                }
            }
        }
    }
}
