# OrangeHRM Automation With Selenium TestNG
In this project, Selenium WebDriver and TestNG framework are used to automate the Orange HRM website. Faker library is used to generate random users. Here I have automated login, logout, create new user, search user & update user.

## Tools & Technology used:
- IntellIJ IDEA
- Selenium
- TestNG Framework
- Gradle
- Allure

## Prerequisites:
- jdk
- gradle

## How to run this project:
- Clone this project
- Open build.gradle file in IntelliJ IDEA
- Open terminal
- Give this following command for smoke test: ```gradle clean test -Pfilesuite="SmokeMasterSuite.xml"```
- Give this following command for regression test: ```gradle clean test -Pfilesuite="RegressionMasterSuite.xml"```
- Give this following command for generating Allure Report: ```allure generate allure-results --clean -output``` & ```allure serve allure-results```

## Test Cases:
https://docs.google.com/spreadsheets/d/1xQQF3ndqf3QDY_6dUOe_MUGIJcyqIU7z2aKrMY-0N_4/edit?usp=sharing

## Smoke_Test Automation Video:
https://drive.google.com/file/d/13HuR8JboLOWjzwwJPhGuzKDmI2aw6s6T/view?usp=drive_link

## Regression_Test Automation Video:
https://drive.google.com/file/d/1yv5MrcsUMvZEplXPxW9T-Qsh8XYhZdH1/view?usp=drive_link

## Allure report screenshot for Smoke_Test:
![smoke1](https://github.com/rabbypathan/OrangeHRM_Selenium_TestNG_Automation/assets/70917088/04be3503-2c6b-446a-91bd-876a643a94de)

![smoke2](https://github.com/rabbypathan/OrangeHRM_Selenium_TestNG_Automation/assets/70917088/6dc007b0-a7a6-45ae-beac-7c52736169c6)

## Allure report screenshot for Regression_Test:
![regression1](https://github.com/rabbypathan/OrangeHRM_Selenium_TestNG_Automation/assets/70917088/053e3a71-f75c-4007-ac11-1509e3a1a1be)

![regression2](https://github.com/rabbypathan/OrangeHRM_Selenium_TestNG_Automation/assets/70917088/e2b32202-cd52-4e69-86e4-002d2e095d5d)

## Secnario
1. Login as a admin to https://opensource-demo.orangehrmlive.com/
2. Go to PIM menu and create a new employee. Save the employee firstname, lastname, employeeid, username and password into JSONArray file. Generate random password which meets following criteria:
For a strong password, please use a hard to guess combination of text wpp=ith upper and lower case characters, symbols and numbers. Assert if employee is created successfully.

3. Now go to the dashboard again and search by the employee id to check if the employee is found
4. Now go to the Directory menu and search by employee name and check if the employee is found
5. Logout the session.
6. Now login with the newly created employee creds
7. Assert your full name is showing besides the profile icon.
8. Go to my info
9. Scroll down and select Gender and Blood Type as O+ and save it. Then logout the user.
10. Create a smoke suite configuration which will run only following features (positive cases only):
- Login to admin
- Search by the employee id if found
- logout admin and login to the employee id you created last
- Update the blood Group as AB-
- Logout the user
