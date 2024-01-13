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

import okhttp3.Headers
import org.apache.commons.io.input.BoundedInputStream
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

class MJpegInputStream(private val boundary: String, source: InputStream) : AutoCloseable {

    companion object {
        private const val HEADER_CONTENT_LENGTH = "Content-Length"
    }

    private val inputStream: InputStream = BufferedInputStream(source)

    fun nextFrame(): InputStream {
        skipBoundaryLines()
        val headers = readHeaders()
        val contentLength = headers[HEADER_CONTENT_LENGTH]!!.toInt()
        val bis = BoundedInputStream(inputStream, contentLength.toLong())
        bis.isPropagateClose = false
        return bis
    }

    override fun close() {
        inputStream.close()
    }

    private fun skipBoundaryLines() {
        while (true) {
            val line = readLine()
            when {
                line.isEmpty() -> continue
                line == boundary -> return
            }
            throw IOException("expect $boundary, but got $line")
        }
    }

    private fun readHeaders(): Headers {
        val builder = Headers.Builder()
        while (true) {
            val line = readLine()
            if (line.isEmpty()) {
                break
            }
            builder.add(line)
        }
        return builder.build()
    }

    private fun readLine(): String {
        val baos = ByteArrayOutputStream()
        while (true) {
            val c = inputStream.read()
            if (c == '\r'.code) continue
            if (c == '\n'.code) break
            baos.write(c.toChar().code)
        }
        return baos.toString(Charsets.UTF_8)
    }
}
