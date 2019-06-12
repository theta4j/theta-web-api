# THETA Web API Client

[![Javadocs](https://javadoc.io/badge/org.theta4j/theta-web-api.svg)](https://javadoc.io/doc/org.theta4j/theta-web-api)

Client implementation of [RICOH THETA API v2.1](https://developers.theta360.com/en/docs/v2.1/api_reference/).

Supported environments are Java, JVM languages, Android, and THETA Plug-in.

Tested on RICOH THETA V and THETA Z1. Some features for THETA S and SC are not tested.

## Getting Started

Modify your `build.gradle` to include this library.

```groovy
repositories {
    ...
    jcenter() // insert this line
}

dependencies {
    ...
    implementation 'org.theta4j:theta-web-api:1.3.0' // insert this line
}
```

## Usage for Android and THETA Plug-in

This library takes network access, so your application needs `android.permission.INTERNET` permission.

Insert the following line into your `AndroidManifest.xml`.

```xml
<uses-permission android:name="android.permission.INTERNET"/>
```

Network access on UI thread causes an error on Android system. You need to call API on other thread.

For more details, see [the official document](https://developer.android.com/guide/components/processes-and-threads).

```kotlin
// Bad example in Kotlin
class MyActivity : Activity() {
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        // UI event is invoked on UI thread!!
        theta.takePicture() // this line causes error!!
        ...
    }
}
```

## Example

These examples are written in Java.

### Create Theta instance

`org.theta4j.webapi.Theta` is central object of this library.

When use from external device and THETA is Wi-Fi access point mode.

```java
final Theta theta = Theta.create(); // endpoint is set to http://192.168.1.1
```

When use from external device and THETA is Wi-Fi client mode.
Digest Authentication is required in this mode.

```java
final String endpoint = "http://192.168.100.42"; // just an example
final String username = "THETAYL00000000";       // just an example
final String password = "p@55w0rd";              // just an example
final Theta theta = Theta.create(endpoint, username, password); // specify username and password
```

When use from THETA Plug-in.

```java
final Theta theta = Theta.createForPlugin(); // endpoint is set to http://127.0.0.1:8080
```

### Take a picture

```java
theta.takePicture();
```

### Take a picture and wait for done

```java
import org.theta4j.osc.CommandResponse;
import org.theta4j.osc.CommandState;
import org.theta4j.webapi.TakePicture;
...
CommandResponse<TakePicture.Result> response = theta.takePicture();
while(response.getState() != CommandState.DONE) {
    response = theta.commandStatus(response);
    Thread.sleep(100);
}
System.out.println("fileUrl: " + response.getResults().getFileUrl());
```

### Get option value

```java
import static org.theta4j.webapi.Options.*;
...
final ISOSpeed iso = theta.getOption(ISO);
System.out.println("ISO: " + iso);
```

### Get multiple option values on one HTTP request

```java
import org.theta4j.osc.OptionSet;
import static org.theta4j.webapi.Options.*;
...
final OptionSet optionSet = theta.getOptions(ISO, ISO_SUPPORT, SHUTTER_SPEED);
System.out.println("ISO: " + optionSet.get(ISO));
System.out.println("ISO Support: " + optionSet.get(ISO_SUPPORT));
System.out.println("Shutter Speed: " + optionSet.get(SHUTTER_SPEED));
```

### Set option value

```java
import static org.theta4j.webapi.Options.*;
import org.theta4j.webapi.ISOSpeed;
...
theta.setOption(ISO, ISOSpeed._200);
```

### Set multiple option values on one HTTP request

```java
import org.theta4j.osc.OptionSet;
import static org.theta4j.webapi.Options.*;
import org.theta4j.webapi.ExposureProgram;
import org.theta4j.webapi.ISOSpeed;
...
final OptionSet optionSet = new OptionSet.Builder()
        .set(EXPOSURE_PROGRAM, ExposureProgram.ISO_SPEED)
        .set(ISO, ISOSpeed._200)
        .build();          
theta.setOptions(optionSet);
```
