# Testing

This project has automated unit testing and testing integrated with real THETA devices.

## Unit Test

To run a unit test, just run the `test` task.

```sh
$ ./gradlew test
```

## Integration Test

Integration tests requires a connection to the THETA. You have to establish connection manually before the test.

Integration tests are implemented for each models, such as V and Z1.

1. Connect to the THETA in AP mode.
2. Run the integration test task of the model connected.
    * For example: `./gradlew integrationTestX`

The `check` task does not depend on the `integrationTest` tasks. Therefore, you need to run each test manually.

The following command shows a list of integration test tasks.

```sh
$ ./gradlew tasks --group verification | grep integrationTest
integrationTestX - Runs the integration tests for THETA X
```
