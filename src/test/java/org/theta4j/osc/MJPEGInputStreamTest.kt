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

package org.theta4j.osc

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

class MJPEGInputStreamTest : FunSpec({

    val boundary = "---osclivepreview---"

    test("Read frames") {
        val imageBytes = javaClass.getResourceAsStream("/jin.jpg")!!.use { it.readBytes() }
        val streamBytes = ByteArrayOutputStream().use { baos ->
            val writer = baos.writer()
            repeat(3) {
                writer.run {
                    write("$boundary\r\n")
                    write("Content-Type: image/jpeg\r\n")
                    write("Content-Length: ${imageBytes.size}\r\n")
                    write("Foo: bar\r\n")
                    write("\r\n")
                    flush()
                }
                baos.write(imageBytes)
                baos.flush()
            }
            baos.toByteArray()
        }
        val inputStream = ByteArrayInputStream(streamBytes)
        MJpegInputStream(boundary, inputStream).use { mjpegInputStream ->
            repeat(3) {
                val frameBytes = mjpegInputStream.nextFrame().readAllBytes()
                val image = ImageIO.read(ByteArrayInputStream(frameBytes))
                image.width shouldBe 300
                image.height shouldBe 200
            }
        }
    }
})
