## Project Overview
This project is a **comprehensive automation framework** for **ParaBank**, covering **UI automation** and **API automation using Postman**.  

- **UI Automation:** Automates ParaBank web application flows using **Selenium WebDriver, Page Object Model (POM), and TestNG**.  
- **API Automation:** Automates ParaBank REST API endpoints using **Postman collections** and **Newman CLI** for execution and reporting.  

The framework is designed for **scalability, maintainability**, and integrates **detailed reporting** and **CI/CD support**.

---

## Features
### UI Automation
- Automates **ParaBank web application workflows**.
- Implements **Page Object Model (POM)** for easy maintenance.
- Supports **cross-browser testing** using Selenium WebDriver.
- Captures **screenshots** on test failure.
- Generates **Extent Reports** for UI test execution.

### API Automation (Postman)
- Automates **ParaBank REST API endpoints** using Postman collections.
- Supports **positive and negative test scenarios**.
- Uses **environment and collection variables** for dynamic testing.
- Generates **detailed reports** using **Newman CLI**.
- Can be integrated into **CI/CD pipelines** for automated API testing.

---

```plaintext

## Project Structure

###UI Automation

ParaBanking_CapstoneProject Framework/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── drivers/               # WebDriver related classes
│   │   │   │   ├── DriverFactory.java
│   │   │   │   └── DriverManager.java
│   │   │   │
│   │   │   ├── pages/                 # Page Object classes
│   │   │   │   ├── BasePage.java
│   │   │   │   ├── HomePage.java
│   │   │   │   ├── RegisterPage.java
│   │   │   │   ├── TransferPage.java
│   │   │   │   ├── LoanRequestPage.java
│   │   │   │   ├── UpdateProfilePage.java
│   │   │   │   ├── OpenAccountPage.java
│   │   │   │   ├── OverViewPage.java
│   │   │   │   └── UserDetails.java
│   │   │   │
│   │   │   └── Utils/                  # Utility/helper classes
│   │   │       └── (utility classes)
│   │   │
│   │   └── resources/                  # Config and log files
│   │       ├── config.properties
│   │       └── log4j2.xml
│   │
│   └── test/
│       ├── java/
│       │   ├── Tests/                  # Test classes
│       │   │   ├── BaseTest.java
│       │   │   ├── TS01_Verify_New_User_Registration.java
│       │   │   ├── TS02_Verify_Login_Functionality.java
│       │   │   ├── TS03_Verify_Fund_Transfer_Between_Accounts.java
│       │   │   ├── TS04_Verify_Loan_Request.java
│       │   │   └── TS05_Verify_Profile_Update.java
│       │   │
│       │   ├── DataProviders/         # Data provider classes for data-driven testing
│       │   │   └── DataProviders.java
│       │   │
│       │   └── Listeners/             # TestNG listeners for reporting/logging
│       │       └── TestListeners.java
│       │
│       └── resources/                  # Test resources
│           ├── TestData/               # Test data files (Excel, JSON, etc.)
│           │   └── (data files)
│           ├── CrossBrowser.xml        # Cross-browser TestNG suite
│           └── testng.xml              # Main TestNG suite
│
├── logs/                               # Log files generated during execution
├── test-output/                        # TestNG reports output
├── target/                             # Maven build output
├── pom.xml                              # Maven project file


### API Automation (Postman)

BankingApiTesting/
│
│--ParaBank_API.postman_collection.json 
│--ParabankTest.postman_environment.json
│--ParaBank_Report.html



```

---

## Technologies Used
- Java
- Maven
- TestNG
- RestAssured
- Apache POI (Excel integration)
- Postman
- Extent Reports / Allure Reports
- Jenkins (for CI/CD integration)

---



## Setup & Installation

### Clone the Repository
```bash
git clone https://github.com/Sireesha319/CapstoneProject_ParaBank.git
cd <CapstoneProject_ParaBank>
```

### UI Automation Setup

   1. Open the project in your IDE.

   2. Update config.properties for browser and URL configuration.

   3. Install Maven dependencies:

  ```bash
       mvn clean install
  ```

### API Automation Setup

  1. Open Postman.

  2. Import ParaBank_API.postman_collection.json.

  3. Import ParabankTest.postman_environment.json as environment.


## Running the Tests

  ### UI Automation

  Run TestNG suites:

   ```bash
    mvn test
```
  ### API Automation
  ```bash  
newman run ParaBank_API.postman_collection.json -e ParabankTest.postman_environment.json -r html
```


