package testrunner;

import com.github.javafaker.Faker;
import config.EmployeeModel;
import config.Setup;
import io.qameta.allure.Allure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.DashboardPage;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;

public class DashboardTestRunner extends Setup {
    DashboardPage dashboardPage;
    @BeforeTest(groups = "smoke")
    public void Login(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogin("Admin","admin123");
        dashboardPage=new DashboardPage(driver);
        dashboardPage.menuItems.get(1).click(); //click PIM
    }

    @Test(priority = 1, description = "Admin try to create user without Username")
    public void createUserWithoutUserName() throws InterruptedException {
        Faker faker =new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String employeeId = String.valueOf(faker.random().nextInt(1000,9999));
        String password = "a1"+faker.internet().password(8,12,true,true,true);

        EmployeeModel model = new EmployeeModel();
        model.setFirstname(firstName);
        model.setLastname(lastName);
        model.setUserid(employeeId);
        model.setPassword(password);
        dashboardPage.createUserWithoutUsername(model);

        Thread.sleep(2000);

        String actual = driver.findElement(By.className("oxd-input-field-error-message")).getText();
        String excepted = "Required";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actual,excepted);
        softAssert.assertEquals(driver.findElements(By.className("oxd-text--h6")).get(1).getText(),"Add Employee");
        softAssert.assertAll();
    }



    @Test(priority = 2, description = "Check if new user is created successfully")
    public void createUser() throws IOException, ParseException, InterruptedException {
        DashboardPage dashboardPage=new DashboardPage(driver);
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userid = String.valueOf(faker.random().nextInt(1000,9999));
        Thread.sleep(2000);
        String username = faker.name().username();
        String password = "a1"+faker.internet().password(8,12,true,true,true);

        EmployeeModel model=new EmployeeModel();
        model.setFirstname(firstName);
        model.setLastname(lastName);
        model.setUserid(userid);
        model.setUsername(username);
        model.setPassword(password);


        dashboardPage.createUser(model);
        String textTitleExpected = driver.findElement(By.xpath("//*[contains(text(),\"Personal Details\")]")).toString();
        Thread.sleep(5000);
        if(textTitleExpected.contains("Personal Details")) {
            Utils.saveEmployeeInfo(model);
        }
        Allure.description("User created successfully");
    }

    @Test(priority = 3, description = "Admin can not search user by wrong employee name")
    public void searchByWrongEmployeeName() throws InterruptedException {
        dashboardPage = new DashboardPage(driver);
        Faker faker =new Faker();
        String firstName = faker.name().firstName();
        dashboardPage.searchByEmployeeName(firstName);
        Thread.sleep(2000);

        String textActual = driver.findElement(By.className("oxd-input-field-error-message")).getText();
        String textExcepted ="Invalid";
        Assert.assertEquals(textActual,textExcepted);
    }

    @Test(priority = 4, description = "Admin successfully search user by employee name")
    public void searchByEmployeeName() throws IOException, ParseException, InterruptedException {
        Thread.sleep(5000);
        dashboardPage = new DashboardPage(driver);
        JSONArray empArray = Utils.readJSONList("./src/test/resources/employees.json");
        JSONObject empObj = (JSONObject) empArray.get(empArray.size()-1);
        String firstName = empObj.get("firstName").toString();
        dashboardPage.searchByEmployeeName(firstName);
        Thread.sleep(2000);

        String messageActual = driver.findElement(By.className("orangehrm-directory-card-header")).getText();
        Thread.sleep(2000);
        System.out.println(messageActual);
        Assert.assertTrue(messageActual.contains(firstName));

        Allure.description("Admin Successfully Search User by Employee Name");
    }

    @Test(priority =5, description = "Admin can not search user by wrong employee id")
    public void searchByWrongEmployeeId() throws InterruptedException {
        dashboardPage = new DashboardPage(driver);
        String employeeId ="12548";
        dashboardPage.searchByEmployeeId(employeeId);
        Thread.sleep(1500);

        String messageActual = driver.findElement(By.className("oxd-toast")).getText();
        Thread.sleep(1500);
        String messageExcepted ="No Records Found";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(messageActual.contains(messageExcepted));
        softAssert.assertAll();
    }

    @Test(priority = 6, description = "Admin successfully search user by employee id", groups = "smoke")
    public void searchByEmployeeId() throws IOException, ParseException, InterruptedException {
        dashboardPage = new DashboardPage(driver);
        JSONArray empArray = Utils.readJSONList("./src/test/resources/employees.json");
        JSONObject empObj = (JSONObject) empArray.get(empArray.size()-1);
        String employeeId = empObj.get("userid").toString();
        dashboardPage.searchByEmployeeId(employeeId);
        Thread.sleep(1500);

        String messageActual = driver.findElements(By.className("oxd-table-cell")).get(1).getText();
        Thread.sleep(1500);
        System.out.println(messageActual);
        Assert.assertTrue(messageActual.contains(employeeId));

        Allure.description("Admin Successfully Search User by Employee Id");
    }

    @Test(priority = 7, description = "Admin successfully logs out", groups = "smoke")
    public void Logout(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogout();
        String textActual = driver.findElement(By.className("orangehrm-login-title")).getText();
        String textExcepted ="Login";
        Assert.assertEquals(textActual,textExcepted);

        Allure.description("Admin Successfully Logs out");
    }

}
