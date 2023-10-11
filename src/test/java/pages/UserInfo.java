package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserInfo {

    @FindBy(className = "oxd-main-menu-item--name")
    public List<WebElement> menuItems;

    @FindBy(className = "oxd-radio-input")
    List<WebElement> btnRadio;

    @FindBy(className = "oxd-select-text-input")
    List<WebElement> dropdown;

    @FindBy(className = "oxd-button")
    List<WebElement> submit;

    public UserInfo (WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void selectGender(){

        btnRadio.get(0).click();
        submit.get(0).click();
    }

    public void selectBloodType(){
        dropdown.get(2).click();
        dropdown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropdown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropdown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropdown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropdown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropdown.get(2).sendKeys(Keys.ENTER);
        submit.get(1).click();
    }
    public void updateBloodType(){
        dropdown.get(2).click();
        dropdown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropdown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropdown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropdown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropdown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropdown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropdown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropdown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropdown.get(2).sendKeys(Keys.ENTER);
        submit.get(1).click();
    }

}