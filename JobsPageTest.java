import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

import org.testng.annotations.Test;
import pages.*;

public class JobsPageTest {
    public WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    JobsPage jobsPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "PATH_TO_CHROMEDRIVER");
        driver = new ChromeDriver();
        driver.get("https://qajobbyapp.ccbp.tech/login");

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        jobsPage = new JobsPage(driver);

        loginPage.loginToApplication("rahul", "rahul@2021");
        String expectedUrl = "https://qajobbyapp.ccbp.tech/";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        homePage.clickOnFindJobsButton();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @DataProvider
    public Object[][] JobsCountData() {
        return new Object[][] {
                {"Devops Engineer", 9},
                {"Backend Engineer", 11},
                {"Frontend Engineer", 13},
                {"Fullstack Developer", 6},
                {"Data Scientist", 11},
                {"ML Engineer", 10}
        };
    }

    @Test(priority = 1)
    public void testProfileContainer() {
        Assert.assertTrue(jobsPage.findProfileImage().isDisplayed(), "Profile image is not displayed");

        String actualText = jobsPage.getProfileName();
        Assert.assertEquals(actualText, "Rahul Attluri", "Profile name does not match");

        actualText = jobsPage.getShortBio();
        Assert.assertEquals(actualText, "Lead Software Developer and AI-ML expert", "Bio does not match");
    }

    @Test(priority = 2, dataProvider = "JobsCountData")
    public void testSuccessfulSearch(String searchText, int expectedCount) {
        jobsPage.search(searchText);

        int actualCount = jobsPage.getJobsCount();
        Assert.assertEquals(actualCount, expectedCount, "Jobs count does not match");
    }

    @Test(priority = 3)
    public void testJobsNotFound() {
        jobsPage.search("Netflix");

        Assert.assertTrue(jobsPage.findJobsNotFoundImage().isDisplayed(), "Jobs not found image is not displayed");

        String actualText = jobsPage.getJobsNotFoundHeading();
        Assert.assertEquals(actualText, "No Jobs Found", "Jobs not found heading does not match");

        actualText = jobsPage.getJobsNotFoundDescription();
        Assert.assertEquals(actualText, "We could not find any jobs. Try other filters.", "Jobs not found description does not match");
    }
}
