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

package org.theta4j.webapi.example

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import org.theta4j.webapi.Theta
import org.theta4j.webapi.example.ui.theme.MyTheme

class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "THETA_WEB_API_EXAMPLE"
    }

    private val theta = Theta.create()

    private var connectionManager: ConnectionManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { AppScreen() }

        connectionManager = ConnectionManager(applicationContext)
    }

    override fun onDestroy() {
        super.onDestroy()

        connectionManager?.close()
        connectionManager = null
    }

    @Composable
    private fun AppScreen() = MyTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "",
            )
            Button(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = { startPreview() },
            ) {
                Text(text = stringResource(id = R.string.start_label))
            }
        }
    }

    private fun startPreview() {
        theta.livePreview.use {
            Log.d(TAG, "start preview")
            try {
                while (true) {
                    val bmp = BitmapFactory.decodeStream(it.nextFrame())
                    runOnUiThread {
                        //binding.livePreview.setImageBitmap(bmp)
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    //binding.livePreview.setImageResource(android.R.color.black)
                }
                Log.d(TAG, "stop preview")
            }
        }
    }
}
