# SEP2 Week 2 - UI Localization (JavaFX)

Fuel Consumption and Trip Cost Calculator with multi-language support.

## Features

- JavaFX UI defined in FXML
- Input fields: distance, consumption, fuel price
- Calculation:
  - Total fuel = (consumption / 100) * distance
  - Total cost = total fuel * fuel price
- Runtime language switch buttons:
  - EN (`en_US`)
  - FR (`fr_FR`)
  - JP (`ja_JP`)
  - IR (`fa_IR`)

## Project Structure

- `src/main/java/org/otp/Main.java` - JavaFX application launcher
- `src/main/java/org/otp/FuelCalculatorController.java` - Controller logic
- `src/main/resources/org/otp/fuel-calculator-view.fxml` - UI layout
- `src/main/resources/org/otp/messages_*.properties` - localized resources
- `src/test/java/org/otp/LocalizationBundleTest.java` - localization key consistency test
- `Jenkinsfile` - Jenkins CI pipeline (test + package + artifact/archive)
- `Dockerfile` - CI build/test container image
- `deployment.yaml` - optional Kubernetes Job for CI-style Maven build/test

## Run

```bash
mvn javafx:run
```

In IntelliJ, run `org.otp.Launcher` as the main class.

## Test

```bash
mvn test
```

## Docker (CI Validation)

```bash
docker build -t otp2-week2 .
docker run --rm otp2-week2
```

## Jenkins

The pipeline in `Jenkinsfile` runs:

1. Checkout
2. `mvn clean test`
3. `mvn -DskipTests package`
4. Test report + JAR archiving

## Optional Kubernetes Job

This project includes `deployment.yaml` as an optional CI-style Kubernetes Job.
It runs Maven validation inside the project image and does not launch the JavaFX GUI.

```bash
kubectl apply -f deployment.yaml
kubectl logs job/otp2-week2-ci
kubectl delete -f deployment.yaml
```

## Assignment Notes

For submission, add screenshots for EN/FR/JP/IR views and include your name tag in each screenshot.

