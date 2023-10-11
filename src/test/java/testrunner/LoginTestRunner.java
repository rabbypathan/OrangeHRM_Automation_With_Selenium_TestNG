package testrunner;


import config.Setup;
import io.qameta.allure.Allure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;

public class LoginTestRunner extends Setup {
   LoginPage loginPage;

   @Test(priority = 1, description = "Admin tries to login with wrong creds")
   public void LoginWrongCreds(){
       loginPage=new LoginPage(driver);
       loginPage.doLogin("Admin","WrongPass");
       String textActual=driver.findElement(By.className("oxd-alert-content-text")).getText();
       String textExpected="Invalid credentials";
       Assert.assertTrue(textActual.contains(textExpected));
   }

    @Test(priority = 2, description = "Admin tries to do login with valid creds",groups = "smoke")
    public void Login() {
        loginPage=new LoginPage(driver);
        loginPage.doLogin("admin","admin123");
//        String textActual=driver.findElement(By.className("oxd-text--h6")).getText();
//        String textExpected="Dashboard";
//        Assert.assertTrue(textActual.contains(textExpected));


//        loginPage = new LoginPage(driver);
//        JSONArray jsonArray = Utils.readJSONList("./src/test/resources/employees.json");
//        JSONObject empObj = (JSONObject) jsonArray.get(0);
//        //loginPage.doLogin(empObj.get("username").toString(), empObj.get("password").toString());
//        if(System.getProperty("username")!=null && (System.getProperty("password")!=null)) {
//            loginPage.doLogin(System.getProperty("username"), System.getProperty("password"));
//        }

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(driver.findElement(By.className("oxd-userdropdown-img")).isDisplayed());
        softAssert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
        softAssert.assertAll();

        Allure.description("Admin login successfully");
    }

   @Test(priority = 3, description = "Admin can successfully logs out")
   public void Logout(){
       loginPage = new LoginPage(driver);
       loginPage.doLogout();
       String textActual=driver.findElement(By.className("orangehrm-login-title")).getText();
       String textExpected="Login";
       Assert.assertEquals(textActual,textExpected);

       Allure.description("Admin logs out successfully");
   }
}
