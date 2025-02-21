package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderSection {

    @FindBy(css = "div[class $= large-container] img.website-logo")
    WebElement appLogoElement;

    @FindBy(css = "div[class $= large-container]>a")
    WebElement appLogoLinkElement;

    @FindBy(linkText = "Home")
    WebElement navHomeLinkElement;

    @FindBy(linkText = "Jobs")
    WebElement navJobsLinkElement;

    @FindBy(className = "logout-desktop-btn")
    WebElement logoutButtonElement;

    WebDriver driver;

    public HeaderSection(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement findAppLogo() {
        return appLogoElement;
    }

    public void clickOnAppLogo() {
        appLogoLinkElement.click();
    }

    public void clickOnNavHomeLink() {
        navHomeLinkElement.click();
    }

    public void clickOnNavJobsLink() {
        navJobsLinkElement.click();
    }

    public void clickOnLogoutButton(){
        logoutButtonElement.click();
    }

    public void logout() {
        clickOnLogoutButton();
        driver.switchTo().alert().accept();
    }
}