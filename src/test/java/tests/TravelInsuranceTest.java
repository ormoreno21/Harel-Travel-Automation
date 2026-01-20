package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.TravelPolicyPage;

public class TravelInsuranceTest {
    private WebDriver driver;
    private TravelPolicyPage travelPage;


    @BeforeClass
public void setup() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    
    
    options.addArguments("--headless=new"); 
    options.addArguments("--no-sandbox"); 
    options.addArguments("--disable-dev-shm-usage"); 
    options.addArguments("--window-size=1920,1080"); 

    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.get("https://digital.harel-group.co.il/travel-policy");
    travelPage = new TravelPolicyPage(driver);
}
    @Test
    public void testInsurancePurchaseFlow() {
        travelPage.startNewPurchase();
        travelPage.selectEurope();
        travelPage.proceedToDates();
        travelPage.selectTravelDates(7, 30);
        String days = travelPage.getTotalDays();
        Assert.assertTrue(days.contains("31"), "The total days count is incorrect!");
        travelPage.proceedToPassengers();
        Assert.assertTrue(driver.getCurrentUrl().contains("travelers"), "Did not reach passengers page");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
