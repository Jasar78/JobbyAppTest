package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class LoginPage {

    @FindBy(className = "login-website-logo")
    WebElement loginAppLogoElement;

    @FindBy(className = "input-label")
    List<WebElement> labelElements;

    @FindBy(id = "userNameInput")
    WebElement usernameInputElement;

    @FindBy(id = "passwordInput")
    WebElement passwordInputElement;

    @FindBy(className = "login-button")
    WebElement loginButtonElement;

    @FindBy(className = "error-message")
    WebElement errorMessageElement;

    WebDriver driver;
    WebDriverWait wait;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement findLoginAppLogo() {
        return loginAppLogoElement;
    }

    public String getLabelTextAt(int index) {
        return labelElements.get(index).getText();
    }

    public void enterUsername(String username){
        usernameInputElement.sendKeys(username);
    }

    public void enterPassword(String password){
        passwordInputElement.sendKeys(password);
    }

    public void clickOnLoginButton(){
        loginButtonElement.click();
    }

    public void loginToApplication(String username, String password){
        enterUsername(username);
        enterPassword(password);
        clickOnLoginButton();
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
        return errorMessageElement.getText();
    }
}