# BETIKA INTERVIEW ASSESSMENT

## Technologies Used
### Java, Selenium, Junit, Cucumber, RestAssured

## Setup & Running Instructions
1. [ ] After cloning the repository, download all the dependencies from the pom.xml file.
2. [ ] Ensure that you are using JDK11 or JDK8.
3. [ ] To run all the tests, use the `TestRunner.java` file, in this path `src/test/java/com/betika/runner/TestRunner.java`
    - Alternatively, you can run the tests via the Maven lifecycle test script
4. [ ] Ensure that you have copied the config.properties file provided in the email in the root directory. Failure to do so will cause the framework to throw errors

* Once the test is run, you can view the test report via this path `target/cucumber.html`

## Expected Issues
1. The DOM loading may hinder some test cases or the implementation of a broader set of test data. This is, however, fixable.
2. String editing in the website might raise some exceptions in the code if encountered and not caught.
3. UI change in login isn't handled in the code but can be handled.
4. Too many login attempts from the same credentials will cause the credentials to be blocked. This is however fixable by having another set of credentials that can be used to login
   - In the case that the login credentials in the config file are blocked by too many login attempts, change the credentials to one that isn't block. This can also be implemented programmatically.

## Other Possible Test Cases (Not limited to this list)
1. [ ] Checking odds placed in a bet
2. [ ] Placing odds in other markets
3. [ ] Removing odds from bet slip
4. [ ] Loading a bet slip
5. [ ] Placing a bet