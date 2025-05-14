
# 🧪 Swag Lab Automation: Selenium + TestNG + ChainTest Report

This repository is an automated test framework for the Swag Labs web application using **Selenium WebDriver**, **TestNG**, and **Java**, enhanced with **ChainTest Reports** for result analysis.

---

## 📁 Project Structure

```
project-root/
├── src/
│   └── main/java/com/saucedemo/
│       ├── constants/              # Static application constants
│       ├── factory/                # WebDriver setup using SeleniumFactory
│       ├── javautility/           # Utility functions (e.g., waits, screenshots)
│       ├── pages/                 # Page Object Model classes
│       └── resources/
│           └── config/            # config.properties & chaintest.properties
│
├── src/test/java/com/saucedemo/
│   ├── basetest/                  # Common BaseTest setup
│   ├── tests/                     # TestNG test classes per page
│   └── testutility/              # Utility for test annotations/reporting
│
├── test_suites/
│   └── regression.xml            # TestNG suite definition
│
├── .gitignore
├── pom.xml                       # Maven project configuration
└── README.md
```

---

## ✨ Features

- ✅ Page Object Model for reusable and maintainable UI actions  
- 🧪 TestNG Framework to define and manage test cases  
- 🔧 Maven-based build and dependency management  
- 🌐 Selenium WebDriver for browser interaction  
- 📊 ChainTest Report integration for advanced reporting  
- ⚙️ Property-driven config (URL, browser type, etc.)  
- 🧰 Utility classes for reusable logic and test annotations  

---

## 📦 Installation

**Clone the repository:**

```bash
git clone https://github.com/sandipchopkar95/SwaglabSeleniumTestNgWithChainTest.git
cd SwaglabSeleniumTestNgWithChainTest
```

Import as a **Maven project** in your IDE (IntelliJ IDEA or Eclipse recommended).

**Install dependencies:**

```bash
mvn clean install
```

---

## 🚀 Running Tests

### ✅ Option 1: Via TestNG Suite

Run the `regression.xml` suite file from your IDE or use:

```bash
mvn test -DsuiteXmlFile=test_suites/regression.xml
```

### ✅ Option 2: Single Test Class

```bash
mvn -Dtest=LoginPageTest test
```

### ⚙️ Browser Config

Update `config.properties`:

```properties
browser=chrome
baseUrl=https://www.saucedemo.com/
```

---

## 📊 Reports

### 📈 ChainTest Report

**After test execution:**

1. Navigate to: `target/chaintest-report/`  
2. Open `index.html` in your browser

**This report includes:**

- Test pass/fail summaries  
- Execution logs  
- Screenshots on failure  
- Test case metadata from `AdditionalDescriptions.java`

---

## ⚙️ Configuration Files

Located at: `src/main/resources/config/`

- `config.properties`: Controls base URL, browser, and wait time  
- `chaintest.properties`: Configuration for ChainTest report (e.g., branding, title)  

---

## 🧪 Sample Test Case Structure

```java
public class LoginPageTest extends BaseTest {

    @Test(description = "Verify user login with valid credentials")
    public void testValidLogin() {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(productPage.isPageLoaded());
    }
}
```

---

## 📦 Dependencies

**Managed in `pom.xml`:**

- Selenium Java  
- TestNG  
- WebDriverManager  
- ChainTest Reporter  
- Apache Commons, Log4j, etc.

---

