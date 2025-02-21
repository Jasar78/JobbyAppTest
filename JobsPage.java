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

public class JobsPage {

    @FindBy(className = "profile-img")
    WebElement profileImageElement;

    @FindBy(className = "profile-name")
    WebElement profileNameElement;

    @FindBy(className = "short-bio")
    WebElement shortBioElement;

    @FindBy(css = "div[class ^='desktop'] input.search-input")
    WebElement searchInputFieldElement;

    @FindBy(css = "div[class ^='desktop'] button.search-button")
    WebElement searchButtonElement;

    @FindBy(className = "link-item")
    List<WebElement> jobsListElements;

    @FindBy(className = "jobs-not-found-img")
    WebElement jobsNotFoundImageElement;

    @FindBy(className = "jobs-not-found-heading")
    WebElement jobsNotFoundHeadingElement;

    @FindBy(className = "jobs-not-found-description")
    WebElement jobsNotFoundDescriptionElement;

    WebDriver driver;
    WebDriverWait wait;

    public JobsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement findProfileImage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("profile-img")));
        return profileImageElement;
    }

    public String getProfileName() {
        return profileNameElement.getText();
    }

    public String getShortBio() {
        return shortBioElement.getText();
    }

    public int getJobsCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("link-item")));
        return jobsListElements.size();
    }

    public void enterSearchText(String searchtext) {
        searchInputFieldElement.sendKeys(searchtext);
    }

    public void clickSearchButton() {
        searchButtonElement.click();
    }

    public void search(String searchText) {
        enterSearchText(searchText);
        clickSearchButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("results-text")));
    }

    public WebElement findJobsNotFoundImage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("jobs-not-found-img")));
        return jobsNotFoundImageElement;
    }

    public String getJobsNotFoundHeading() {
        return jobsNotFoundHeadingElement.getText();
    }

    public String getJobsNotFoundDescription() {
        return jobsNotFoundDescriptionElement.getText();
    }
}
