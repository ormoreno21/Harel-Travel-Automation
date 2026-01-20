package pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TravelPolicyPage extends BasePage {


    private final By firstTimeBtn = By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[1]/div/div/div[1]/div/button");
    private final By europeBtn = By.id("destination-3");
    private final By nextStepDatesBtn = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[3]/div/button");
    private final By totalDaysCounter = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[2]/span"); 
    private final By nextToPassengersBtn = By.id("nextButton");
   
    public TravelPolicyPage(WebDriver driver) {
        super(driver);
    }

    public void startNewPurchase() {
        click(firstTimeBtn);
    }

    public void selectEurope() {
        click(europeBtn);
    }

    public void proceedToDates() {
        click(nextStepDatesBtn);
    }

    public void selectTravelDates(int startInDays, int durationDays) {
        LocalDate startDate = LocalDate.now().plusDays(startInDays);
        LocalDate endDate = startDate.plusDays(durationDays);

        
        pickDate(startDate);
      
        pickDate(endDate);
    }

    private void pickDate(LocalDate date) {
       
        String dateString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
       
        By dateLocator = By.xpath(String.format("//button[@data-hrl-bo='%s']", dateString));
        
        click(dateLocator);
    }

    public String getTotalDays() {
        return getText(totalDaysCounter);
    }

    public void proceedToPassengers() {
        click(nextToPassengersBtn);
    }
}
