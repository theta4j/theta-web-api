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

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ShutterSpeedTest : FunSpec({
    listOf(
        0.00004 to "1/25000",
        0.00005 to "1/20000",
        0.0000625 to "1/16000",
        0.00007812 to "1/12800",
        0.00008 to "1/12500",
        0.0001 to "1/10000",
        0.000125 to "1/8000",
        0.00015625 to "1/6400",
        0.0002 to "1/5000",
        0.00025 to "1/4000",
        0.0003125 to "1/3200",
        0.0004 to "1/2500",
        0.0005 to "1/2000",
        0.000625 to "1/1600",
        0.0008 to "1/1250",
        0.001 to "1/1000",
        0.00125 to "1/800",
        0.0015625 to "1/640",
        0.002 to "1/500",
        0.0025 to "1/400",
        0.003125 to "1/320",
        0.004 to "1/250",
        0.005 to "1/200",
        0.00625 to "1/160",
        0.008 to "1/125",
        0.01 to "1/100",
        0.0125 to "1/80",
        0.01666666 to "1/60",
        0.02 to "1/50",
        0.025 to "1/40",
        0.03333333 to "1/30",
        0.04 to "1/25",
        0.05 to "1/20",
        0.06666666 to "1/15",
        0.07692307 to "1/13",
        0.1 to "1/10",
        0.125 to "1/8",
        0.16666666 to "1/6",
        0.2 to "1/5",
        0.25 to "1/4",
        0.33333333 to "1/3",
        0.4 to "1/2.5",
        0.5 to "1/2",
        0.625 to "1/1.6",
        0.76923076 to "1/1.3",
        1.0 to "1",
        1.3 to "1.3",
        1.6 to "1.6",
        2.0 to "2",
        2.5 to "2.5",
        3.2 to "3.2",
        4.0 to "4",
        5.0 to "5",
        6.0 to "6",
        8.0 to "8",
        10.0 to "10",
        13.0 to "13",
        15.0 to "15",
        20.0 to "20",
        25.0 to "25",
        30.0 to "30",
        40.0 to "40",
        50.0 to "50",
        60.0 to "60",
    ).forEach { (value, string) ->
        test("ShutterSpeed($value).toString() should be $string") {
            ShutterSpeed(value).toString() shouldBe string
        }
    }
})
