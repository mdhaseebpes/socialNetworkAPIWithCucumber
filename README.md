# Social Network API Automation Framework :)

This is an Social Network API automation framework built using Cucumber, RestAssured, Maven, and Testng.

# Prerequisites

Before running the tests, make sure you have the following installed:

* Java JDK (version 8 or higher)
* Maven
* Git

# Getting Started

To get started, clone the repository from GitHub:
git clone https://github.com/your-username/api-automation-framework.git

# Running the Tests

o run the smoke tests parallel using thread count, use the following command:
mvn clean test -Denv=jsonplaceholder -Dcucumber.filter.tags=@smoke -Ddataproviderthreadcount=2

To run the regression tests parallel using thread count, use the following command:
mvn clean test -Denv=jsonplaceholder -Dcucumber.filter.tags=@regression -Ddataproviderthreadcount=4

To run the sanity tests parallel using thread count, use the following command:
mvn clean test -Denv=jsonplaceholder -Dcucumber.filter.tags=@sanity -Ddataproviderthreadcount=2

This will execute the Cucumber test runner and generate the test reports.

# Test Reports

After running the tests, the test reports will be generated in the output/report directory and allure-results directly. Open
currently we have reports 
* Cucumber extent reports
* Cucumber pdf reports
* Allure reports


If you encounter any issues or errors while using the framework, please refer to the Troubleshooting section in the
README.md file on the GitHub repository. If the issue persists, feel free to open an issue on the repository page.

# Contributions

Contributions to the framework are welcome! If you have any improvements, bug fixes, or new features to suggest, please
fork the repository, make your changes, and submit a pull request.

# Contact
If you have any questions or need further assistance, please feel free to reach out to the project maintainer:

* Name: [MOHAMMED HASEEB ALI KHAN]
* Email: [mdhaseebpes@gmail.com]
  Thank you for using the Social Network API Automation Framework! Happy testing!




