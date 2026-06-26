# Date Time Checker (Spring Boot + Percy/Cypress)

A web application that helps users validate dates and check the number of days in a specific month. It includes a robust testing suite featuring **Unit Testing (JUnit)**, **Performance Testing (JMeter)**, and **Visual E2E Testing (Cypress & Percy)**.

## Features
1. **Check Date Mode:** Validates if a given Day, Month, and Year form a correct logical date.
2. **Day In Month Mode:** Calculates the total number of days in a given Month and Year (handling leap years automatically).

## Getting Started

### 1. Run the Application
Start the Spring Boot server using your IDE or Maven:
```bash
mvn spring-boot:run
```
The application will be available at `http://localhost:8080`.

### 2. Run Unit Tests & Performance Tests
We use JUnit for unit testing core logic and a JMeter Maven Plugin for load testing the API endpoints.
To execute both unit tests and the JMeter load test suite (10,000 requests), run:
```bash
mvn verify
```
*Note: Make sure the Spring Boot server is running when executing performance tests, as JMeter will hit `localhost:8080`.*

### 3. Run E2E & Visual Tests (Cypress + Percy)
The project is configured with Cypress for End-to-End UI testing and Percy for visual snapshot testing.

- **Open Cypress UI (for writing/debugging tests):**
  ```bash
  npx cypress open
  ```
- **Run Percy + Cypress Headless:**
  ```bash
  npm run test:percy
  ```
  *(Requires `PERCY_TOKEN` to be set in your environment variables)*

## Project Structure
- **Application Code:** `src/main/java/com/tannx/swt/`
- **Static UI Files:** `src/main/resources/static/`
- **JUnit Tests:** `src/test/java/com/tannx/swt/DateTimeCheckerTest.java`
- **JMeter Test Plan:** `src/test/jmeter/DateTimeChecker_PerfTest.jmx`
- **Cypress/Percy Test Plan:** `cypress/e2e/datetime-checker.cy.js`
