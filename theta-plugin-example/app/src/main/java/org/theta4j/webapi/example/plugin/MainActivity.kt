/*
 * Copyright (C) 2019 theta4j project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
        setAutoClose(true)
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
