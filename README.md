# Date Time Checker (Spring Boot + Percy/Cypress/REST Assured)

A web application that helps users validate dates and check the number of days in a specific month. It includes a comprehensive, fully automated full-stack testing suite covering:

- **Unit Testing**: JUnit
- **API Testing**: REST Assured
- **Performance Testing**: JMeter
- **Web E2E Testing**: Cypress
- **Mobile E2E Testing**: Cypress (Mobile Viewport)
- **Visual Regression Testing**: Percy
- **AI-Assisted Testing**: GitHub Copilot
- **CI/CD**: GitHub Actions

## Features
1. **Check Date Mode:** Validates if a given Day, Month, and Year form a correct logical date.
2. **Day In Month Mode:** Calculates the total number of days in a given Month and Year (handling leap years automatically).

---

## 🚀 Getting Started

### 1. Run the Application
Start the Spring Boot server using your IDE or Maven:
```bash
mvn spring-boot:run
```
The application will be available at `http://localhost:8080`.

---

## 🧪 Testing Guide

This project contains multiple testing suites. Please follow the instructions below to run each type of test.

### 2. Unit Tests & API Tests (Backend)
We use **JUnit** for testing the core logic independently and **REST Assured** for testing the RESTful API endpoints. 

To execute both Unit Tests and API Tests, you don't need to start the application beforehand (Spring Boot Test will mock/start a random port server automatically):
```bash
mvn test
```

### 3. Performance Tests (JMeter)
We use the **JMeter Maven Plugin** to run load/stress tests simulating multiple concurrent users hitting the API.

> **⚠️ IMPORTANT:** The Spring Boot server **MUST** be running locally on port 8080 before you execute the performance tests.

1. Open a terminal and run the server: `mvn spring-boot:run`
2. Open a second terminal and run JMeter tests:
```bash
mvn jmeter:jmeter
```
*Alternatively, you can run `mvn verify`, but ensure the application is running.*

### 4. Web & Mobile E2E Tests (Cypress)
We use **Cypress** to test the actual browser UI behavior on both Desktop and Mobile (iPhone X viewport). 

> **⚠️ IMPORTANT:** Ensure the Spring Boot server is running on `http://localhost:8080`.

To open the Cypress Test Runner (UI mode for debugging):
```bash
# First, install Node.js dependencies if you haven't yet
npm install

# Open Cypress
npx cypress open
```
*In the Cypress window, you can choose to run `datetime-checker.cy.js` (Web) or `mobile-datetime-checker.cy.js` (Mobile).*

### 5. Visual Regression Tests (Percy)
We integrate **Percy** with Cypress to take visual snapshots of the application on both Desktop and Mobile views, checking for unexpected CSS/Layout changes.

To run tests headlessly and send snapshots to the Percy dashboard:
```bash
# Set your Percy token (Ask your lead for the token or get it from your Percy project)
# On Windows (CMD): set PERCY_TOKEN=your_token_here
# On Windows (PowerShell): $env:PERCY_TOKEN="your_token_here"
# On Mac/Linux: export PERCY_TOKEN=your_token_here

npm run test:percy
```

### 6. AI-Assisted Testing (GitHub Copilot)
This project encourages the use of **AI-Assisted Testing** to speed up development and self-healing test automation.

**How the team should use it:**
1. **Test Generation:** Ensure you have the **GitHub Copilot** extension installed in your IDE (VSCode / IntelliJ). You can prompt Copilot to generate missing test cases, mock data, or edge cases for both Java (JUnit) and JavaScript (Cypress).
2. **Self-Healing & Debugging:** If a Cypress test or API test fails, use Copilot Chat (or similar AI tools) to analyze the error logs and suggest quick fixes for element selectors or API payload changes.
3. **Advanced AI Tools:** In the future, the project can be integrated with tools like **testRigor** or **Katalon AI** for natural language test creation.

---

## 📁 Project Structure

- **Application Code:** `src/main/java/com/tannx/swt/`
- **Static UI Files:** `src/main/resources/static/`
- **Unit Tests:** `src/test/java/com/tannx/swt/DateTimeCheckerTest.java`
- **API Tests:** `src/test/java/com/tannx/swt/DateCheckerApiControllerTest.java`
- **JMeter Test Plan:** `src/test/jmeter/DateTimeChecker_PerfTest.jmx`
- **Desktop E2E & Percy:** `cypress/e2e/datetime-checker.cy.js`
- **Mobile E2E & Percy:** `cypress/e2e/mobile-datetime-checker.cy.js`
- **CI/CD Pipeline:** `.github/workflows/ci.yml`
