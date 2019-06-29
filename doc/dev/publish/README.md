# Publishing

This project using [Bintray](https://bintray.com/theta4j/maven/theta-web-api) to publish artifacts to the public maven repositories.

Bintray synchronizes the artifacts into JCenter and Maven Central Repository automatically.

## Upload Artifacts to Bintray

1. Run all tests to make sure everything was passed.
    * The `check` task runs unit tests only. therefore you need to run all integration tests manually.
    * See [Testing](../test) for more details.
2. Create `local.properties` file in the project root using the following template and fill the values.
    ```properties
    bintray.user=
    bintray.key=
    ossrh.user=
    ossrh.password=
    ```
3. Run `./gradlew bintrayUpload`
4. Open the [project page](https://bintray.com/theta4j/maven/theta-web-api) on Bintray in a web browser
    1. Confirm the artifacts uploaded.
    2. Click `Publish` button if the artifacts is OK.
5. When publishing is successful, the artifacts are automatically synchronized to JCenter and Maven Central.
