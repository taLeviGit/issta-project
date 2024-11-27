package issta.co.il;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class PickFlightPage extends ConnectionPage {

    public PickFlightPage(WebDriver driver) {
        super(driver);
    }

    // -------------------------------------------- pick flight Page Selector ------------------------------------
    @FindBy(xpath = ".//a[contains(@href,'/loader.aspx?url=/flights/details.aspx')]")
    List<WebElement> flightsList;

    @FindBy(css = "img[class='main-logo']")
    WebElement mainLogoBtn;

    //---------------------------------------------------------- Methods ----------------------------------------------------

    /**
     * Picks a random flight from the available list and handles error conditions or re-selection if needed.
     * This method first waits for the page to be ready and checks for any error message indicating
     * the unavailability of flights. If an error is detected, it navigates back to the homepage,
     * selects new flight options, and retries picking a flight. If no error occurs, it selects a random
     * flight from the list, scrolls to it, and clicks on it.
     *
     * @param chooseNumberOfDays The number of days after the departure date to select as the return date.
     *
     * @throws RuntimeException if there is an issue navigating or selecting a flight, or if the flight list
     *         is empty or inaccessible.
     */
    public void  pickOneFlightFromList(int chooseNumberOfDays){
        waitFor(20);
        waitUntilPageIsReady();
        // if a new window open, move to the new window.
        int numberOfWindows = driver.getWindowHandles().size();
        moveToAnotherWindowByIndex(numberOfWindows);
        try{
            // If no flights as been found, go to method startTestAgain() and rerun the test.
            WebElement errorMessage = driver.findElement(By.className("title-1"));
            if(errorMessage.isDisplayed()){
                startTestAgain(chooseNumberOfDays);
            }
        }catch (NoSuchElementException e){
            System.out.println("Flights available, No need to start over");
        }
        // Fetch random flight from the list
        Random random = new Random();
        WebElement flight = flightsList.get(random.nextInt(flightsList.size()));
        scrollToElement(flight);
        scrollDown(1500);
        waitForElementToBeClickable(flight);
        javaScriptClick(flight);
    }

    /**
     * Restarts the flight selection and booking process from the beginning.
     * This method performs the following actions:
     * 1. Clicks on the main logo button to navigate to the home page.
     * 2. Waits for a brief moment to ensure that the page is ready.
     * 3. Checks the number of open browser windows, and if it is not equal to 2, switches to the appropriate window.
     * 4. Creates an instance of `HomePage` and clicks on the "Flights" button to initiate the flight search.
     * 5. Selects random origin and destination locations using the `getRandomFromAndToDestination` method.
     * 6. Chooses departure and return dates based on the provided number of days (`chooseNumberOfDays`).
     * 7. Approves the number of passengers.
     * 8. Clicks the "Approve and Find Me A Flight" button to initiate the search for available flights.
     * 9. Selects a flight from the available list by calling the `pickOneFlightFromList` method.
     *
     * @param chooseNumberOfDays the number of days used for the departure and return date selection during the test restart.
     */
    private void startTestAgain(int chooseNumberOfDays){
        waitForElementToBeClickable(mainLogoBtn);
        clickOn(mainLogoBtn);
        waitFor(10);
        int numberOfWindows = driver.getWindowHandles().size();
        // until now there should be 2 windows opened if there is a new window move to it.
        if(numberOfWindows != 2){
            moveToAnotherWindowByIndex(numberOfWindows);
        }
        HomePage hp = new HomePage(driver);
        hp.clickOnFlightsButton();
        hp.getRandomFromAndToDestination();
        hp.chooseDepartureAndReturnDate(chooseNumberOfDays);
        hp.approvePassngerNumber();
        hp.clickApproveAndFindMeAFlight(chooseNumberOfDays);
        pickOneFlightFromList(chooseNumberOfDays);
    }

    // ---------------------------------------------------------------- Assertions --------------------------------

    public String getPickAFlightUrl(){
        sleepFor(10000);
        waitUntilPageIsReady();
        int openWindowsSize = driver.getWindowHandles().size();
        moveToAnotherWindowByIndex(openWindowsSize);
        return getUrlAsString();
    }

}
