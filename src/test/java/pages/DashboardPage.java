package pages;

import config.EmployeeModel;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashboardPage {
    @FindBy(className = "oxd-main-menu-item--name")
    public List<WebElement> menuItems;
    @FindBy(className = "oxd-button")
    List<WebElement> buttons;
    @FindBy(className = "oxd-input")
    List<WebElement> formTextFields;
    @FindBy(className = "--label-right")
    WebElement btnSwitch;
    @FindBy(css = "[type = 'submit']" ) //for searchId
    WebElement search;
    @FindBy(className = "oxd-autocomplete-text-input")
    WebElement input;
    @FindBy(className = "orangehrm-left-space")  //for searchName
    WebElement search1;
    WebDriver driver;
    public DashboardPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void createUser(EmployeeModel model){
        menuItems.get(1).click();
        buttons.get(2).click(); //click add button
        formTextFields.get(1).sendKeys(model.getFirstname());
        formTextFields.get(3).sendKeys(model.getLastname());
        formTextFields.get(4).sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
        formTextFields.get(4).sendKeys(model.getUserid());   //employeeId
        btnSwitch.click(); //toggle button
        formTextFields.get(5).sendKeys(model.getUsername());
        formTextFields.get(6).sendKeys(model.getPassword());
        formTextFields.get(7).sendKeys(model.getPassword()); //confirm password
        buttons.get(1).click(); //save data
    }

    public void createUserWithoutUsername(EmployeeModel model){
        menuItems.get(1).click();   //click pim
        buttons.get(2).click();     //click add button
        formTextFields.get(1).sendKeys(model.getFirstname());
        formTextFields.get(3).sendKeys(model.getLastname());
        formTextFields.get(4).sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
        formTextFields.get(4).sendKeys(model.getUserid());
        btnSwitch.click(); //toggle button
        formTextFields.get(6).sendKeys(model.getPassword());    //password
        formTextFields.get(7).sendKeys(model.getPassword());   //confirm password
        buttons.get(1).click(); // save data
    }

    public void searchByEmployeeId(String employeeId){
        menuItems.get(1).click();   //click PIM
        formTextFields.get(1).sendKeys(employeeId);  // employeeId
        search.click(); //click search
    }

    public void searchByEmployeeName(String firstname) throws InterruptedException {
        menuItems.get(8).click(); //click Directory
        Actions action = new Actions(driver);
        action.click(input);
        action.sendKeys(firstname).perform();
        Thread.sleep(5000);
        action.sendKeys(Keys.ARROW_DOWN).perform();
        action.sendKeys(Keys.ENTER).perform();
        search1.click(); //click search
    }
}
