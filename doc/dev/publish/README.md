# Publishing

This project using Maven Central Repository to publish artifacts.

## Upload Artifacts to Maven Central

1. Run all tests to make sure everything was passed.
    * The `check` task runs unit tests only. therefore you need to run all integration tests manually.
    * See [Testing](../test) for more details.
2. Create `~/.gradle/gradle.properties` file using the following template and fill the values.
    Please refer [The Signing Plugin](https://docs.gradle.org/current/userguide/signing_plugin.html) for more details.
    ```properties
    signing.keyId=
    signing.password=
    signing.secretKeyRingFile=
    ossrh.user=
    ossrh.password=
    ```
3. Run `./gradlew publish`
4. Open [Sonatype OSS Repository Manager](https://oss.sonatype.org).
    1. See "Staging Repositories" section.
    2. Check the artifact to publish and click "Close".
    3. Check the artifact to publish and click "Release".
        - It takes a few minutes for the "Release" button to be enabled.
