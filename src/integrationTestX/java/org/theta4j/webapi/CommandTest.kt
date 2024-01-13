/*
 * Copyright (C) 2022 theta4j project
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

package org.theta4j.webapi

import enableDebugLog
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.delay
import pickOneFile
import javax.imageio.ImageIO

class CommandTest : FunSpec({
    enableDebugLog()

    val theta = Theta.createForPlugin()

    beforeEach {
        theta.reset()
        delay(3_000)
    }

    afterEach {
        delay(1_000)
    }

    test("camera._finishWlan") {
        theta.await(theta.finishWlan())
    }

    test("camera.takePicture") {
        theta.setOptions {
            put(Options.CAPTURE_MODE, CaptureMode.IMAGE)
            put(Options.SHOOTING_METHOD, ShootingMethod.NORMAL) // for THETA X
        }
        val res = theta.await(theta.takePicture())
        println(res)
        delay(1_000) // TODO: fix
    }

    test("camera.startCapture (Interval capture)") {
        theta.setOptions {
            put(Options.CAPTURE_MODE, CaptureMode.IMAGE)
            put(Options.SHOOTING_METHOD, ShootingMethod.INTERVAL) // for THETA X
            put(Options.CAPTURE_NUMBER, 2)
            put(Options.CAPTURE_INTERVAL, 6)
        }
        val res = theta.await(theta.startCapture())
        println(res)
        delay(3_000) // TODO: fix
    }

    test("camera.startCapture / camera.stopCapture (Limitless interval capture)") {
        theta.setOptions {
            put(Options.CAPTURE_MODE, CaptureMode.IMAGE)
            put(Options.SHOOTING_METHOD, ShootingMethod.INTERVAL) // for THETA X
            put(Options.CAPTURE_NUMBER, 0) // limitless
            put(Options.CAPTURE_INTERVAL, 6)
        }
        theta.await(theta.startCapture())
        delay(15_000)
        val res = theta.await(theta.stopCapture())
        println(res)
        delay(3_000) // TODO: fix
    }

    test("\"camera.startCapture / camera.stopCapture (Video capture)") {
        theta.setOption(Options.CAPTURE_MODE, CaptureMode.VIDEO)
        theta.await(theta.startCapture())
        delay(3_000)
        val res = theta.await(theta.stopCapture())
        println(res)
        delay(3_000) // TODO: fix
    }

    test("camera.listFiles") {
        val res = theta.listFiles(ListFiles.Parameter(ListFiles.FileType.ALL, 100, 0))
        println(res)
    }

    test("camera.delete") {
        val file = theta.pickOneFile()
        val result = theta.delete(file.fileUrl)
        println(result)
    }

    test("camera.getLivePreview") {
        theta.getLivePreview().use { stream ->
            repeat(10) {
                val frame = stream.nextFrame()
                val image = ImageIO.read(frame)
                image shouldNotBe null // ImageIO.read returns null on failure.
                frame.close() // for testing that frame#close is not propagated to stream#close
            }
        }
    }

    context("camera._getMetadata") {
        test("Image metadata") {
            val file = theta.pickOneFile(ListFiles.FileType.IMAGE)
            val res = theta.getMetadata(file.fileUrl)
            println(res)
        }

        test("Video metadata") {
            val file = theta.pickOneFile(ListFiles.FileType.VIDEO)
            val res = theta.getMetadata(file.fileUrl)
            println(res)
        }
    }

    test("camera.getOptions") {
        val captureMode = theta.getOption(Options.CAPTURE_MODE)
        println(captureMode)
    }

    test("camera.reset") {
        theta.await(theta.reset())
    }

    test("camera.setOptions") {
        theta.setOptions {
            put(Options.CAPTURE_MODE, CaptureMode.IMAGE)
        }
    }

    test("camera._getMySetting") {
        theta.getMySetting(CaptureMode.IMAGE)
    }

    test("camera._setMySetting") {
        theta.setMySetting(CaptureMode.IMAGE) {
            put(Options.EXPOSURE_PROGRAM, ExposureProgram.AUTO)
        }
    }

    test("camera._stopSelfTimer") {
        theta.setOptions {
            put(Options.CAPTURE_MODE, CaptureMode.IMAGE)
            put(Options.SHOOTING_METHOD, ShootingMethod.NORMAL)
            put(Options.EXPOSURE_DELAY, 5)
        }
        theta.takePicture()
        theta.stopSelfTimer()
    }

    test("camera._convertVideoFormats") {
        val file = theta.pickOneFile(ListFiles.FileType.VIDEO)
        val parameter = ConvertVideoFormats.Parameter(
                file.fileUrl,
                ConvertVideoFormats.Size.W3840_H1920,
        )
        theta.await(theta.convertVideoFormats(parameter))
    }

    test("camera._cancelVideoConvert") {
        theta.cancelVideoConvert()
    }

    // THETA X does not support camera._setBluetoothDevice

    test("camera._listAccessPoints") {
        val res = theta.await(theta.listAccessPoints())
        println(res)
    }

    test("camera._setAccessPoint") {
        val accessPoint = AccessPoint(
                ssid = "DUMMY_SSID",
                security = AccessPoint.Security.NONE, // security is a mandatory parameter on THETA X.
        )
        theta.await(theta.setAccessPoint(accessPoint))
    }

    test("camera._deleteAccessPoint") {
        theta.await(theta.deleteAccessPoint("DUMMY_SSID"))
    }

    test("camera._listPlugins") {
        val res = theta.await(theta.listPlugins())
        println(res)
    }

    // THETA X does not support camera._setPlugin

    test("camera._pluginControl") {
        val plugin = theta.await(theta.listPlugins()).result!!.plugins.first()
        theta.pluginControl(PluginAction.BOOT, plugin.packageName)
        delay(1_000)
        theta.pluginControl(PluginAction.FINISH)
    }

    test("camera._getPluginOrders") {
        val res = theta.await(theta.getPluginOrders())
        println(res)
    }

    test("camera._setPluginOrders") {
        val originalOrders = theta.await(theta.getPluginOrders()).result!!.pluginOrders
        val shuffledOrders = originalOrders.shuffled()
        theta.await(theta.setPluginOrders(shuffledOrders))
    }

    test("camera._deleteMySetting") {
        theta.deleteMySetting(CaptureMode.IMAGE)
    }
})
