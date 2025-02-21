package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {

    @FindBy(className = "home-heading")
    WebElement headingTextElement;

    @FindBy(className = "home-description")
    WebElement descriptionTextElement;

    @FindBy(className = "find-jobs-button")
    WebElement findJobsButtonElement;

    WebDriver driver;
    WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getHeadingText() {
        return headingTextElement.getText();
    }

    public String getDescriptionText() {
        return descriptionTextElement.getText();
    }

    public void clickOnFindJobsButton(){
        findJobsButtonElement.click();
    }
}