package org.theta4j.webapi.example.plugin

import android.os.Bundle
import android.view.KeyEvent
import com.theta360.pluginlibrary.activity.PluginActivity
import com.theta360.pluginlibrary.callback.KeyCallback
import com.theta360.pluginlibrary.receiver.KeyReceiver
import org.theta4j.webapi.CaptureMode
import org.theta4j.webapi.Options.CAPTURE_MODE
import org.theta4j.webapi.Theta
import java.util.concurrent.Executors

class MainActivity : PluginActivity() {

    private val executor = Executors.newSingleThreadExecutor()

    private val theta = Theta.createForPlugin()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setAutoClose(true);
    }

    override fun onResume() {
        super.onResume()
        setKeyCallback(keyCallback)

        executor.submit {
            theta.setOption(CAPTURE_MODE, CaptureMode.IMAGE)
        }
    }

    override fun onPause() {
        super.onPause()
        setKeyCallback(null)
    }

    private val keyCallback = object : KeyCallback {
        override fun onKeyDown(keyCode: Int, keyEvent: KeyEvent) {
            if (keyCode == KeyReceiver.KEYCODE_CAMERA) {
                executor.submit {
                    theta.takePicture()
                }
            }
        }

        override fun onKeyUp(keyCode: Int, keyEvent: KeyEvent) {
        }

        override fun onKeyLongPress(keyCode: Int, keyEvent: KeyEvent) {
        }
    }
}
