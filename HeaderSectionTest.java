import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HeaderSection;
import pages.LoginPage;

import java.time.Duration;

public class HeaderSectionTest {
    public WebDriver driver;
    LoginPage loginPage;
    HeaderSection headerSection;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "PATH_TO_CHROMEDRIVER");
        driver = new ChromeDriver();
        driver.get("https://qajobbyapp.ccbp.tech/login");

        loginPage = new LoginPage(driver);
        headerSection = new HeaderSection(driver);

        loginPage.loginToApplication("rahul", "rahul@2021");
        String expectedUrl = "https://qajobbyapp.ccbp.tech/";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void testAppLogo() {
        Assert.assertTrue(headerSection.findAppLogo().isDisplayed(), "App logo is not displayed");
    }

    @Test(priority = 2)
    public void testNavigationByNavJobsLink() {
        headerSection.clickOnNavJobsLink();

        String expectedLoginUrl = "https://qajobbyapp.ccbp.tech/jobs";
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedLoginUrl, "URLs do not match");
    }

    @Test(priority = 3)
    public void testNavigationByAppLogoLink() {
        headerSection.clickOnNavJobsLink();

        headerSection.clickOnAppLogo();

        String expectedLoginUrl = "https://qajobbyapp.ccbp.tech/";
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedLoginUrl, "URLs do not match");
    }

    @Test(priority = 4)
    public void testNavigationByNavHomeLink() {
        headerSection.clickOnNavJobsLink();

        headerSection.clickOnNavHomeLink();

        String expectedLoginUrl = "https://qajobbyapp.ccbp.tech/";
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedLoginUrl, "URLs do not match");
    }

    @Test(priority = 5)
    public void testLogoutFunctionality() {
        headerSection.logout();

        String expectedLoginUrl = "https://qajobbyapp.ccbp.tech/login";
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedLoginUrl, "URLs do not match");
    }
}
