package issta.co.il;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class HomePage extends ConnectionPage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // -------------------------------------------- Flights Page Button Selector ------------------------------------
    @FindBy(css = "[class='ng-icon-flights ng-star-inserted']")
    WebElement flightsBtn;

    // -------------------------------------------- From Destination To Destination Tab Selectors --------------------------------------
    @FindBy(xpath = "//form[@class='ng-flights-search-form']/div[1]/div[1]/div[1]//se-flights-autocomplete/input")
    WebElement originDestinationField;

    @FindBy(xpath = "//form[@class='ng-flights-search-form']/div[1]/div[1]/div[1]//autocomplete-item//a")
    List<WebElement> listOfOriginDestinations;

    @FindBy(xpath = "//form[@class='ng-flights-search-form']/div[1]/div[1]/div[2]//se-flights-autocomplete/input")
    WebElement toDestinationField;

    @FindBy(xpath = "//form[@class='ng-flights-search-form']/div[1]/div[1]/div[2]//autocomplete-item//a")
    List<WebElement> listOfToDestination;

    // ------------------------------------------- Departure and return Date Tab Selectors --------------------------------
    @FindBy(xpath = "//form[@class='ng-flights-search-form']/div[1]/div[2]/div[1]")
    WebElement dateFiled;

    @FindBy(xpath = "//div[@class='day toMonth  valid  tmp']")
    List<WebElement> endDateList;


    // ------------------------------------------- Passengers Tab Selectors --------------------------------
    @FindBy(xpath = "*//se-flight-passengers-picker//div[2]/div//button")
    WebElement passngerApproveBtn;

    // ------------------------------------------- Approve all Selection Button Selector--------------------------------
    @FindBy(xpath = "//se-flights-container//div[@class='ng-form-body flight-options ng-star-inserted']//form-button/button")
    WebElement approveAllSelectionBtn;

    // -------------------------------------------- Assertions Selector ------------------------------------
    @FindBy(xpath = "//nav[@class='ng-nav-tabs ng-right-navigation ng-star-inserted']//li[@class='ng-star-inserted current']//a")
    WebElement currentNavigationStatus;

    // -------------------------------------------- Close Pop-Up Selector ------------------------------------
    @FindBy(css = "#ZA_CAMP_CLOSE_BUTTON")
    WebElement popUpCloseBtn;

    @FindBy(css = "ZA_CAMP_SLIDE_CLOSE_BUTTON")
    WebElement popUpCloseBtn1;

    @FindBy(css = "#ZAbanner_CLOSE_BUTTON")
    WebElement popUpCloseBtn2;


    //---------------------------------------------------------- Methods ----------------------------------------------------

    /**
     * Closes any existing pop-up windows by clicking the close button(s).
     * This method checks if any of the predefined pop-up close buttons are enabled. If any of them
     * are found to be enabled, it clicks the respective button to close the pop-up. If no pop-up
     * exists or an exception occurs, it logs a message indicating that the pop-up does not exist.
     */
    public void closePopUpIfExist(){
        try {
            if(popUpCloseBtn.isEnabled()){
                clickOn(popUpCloseBtn);
            }
            if(popUpCloseBtn1.isEnabled()){
                clickOn(popUpCloseBtn1);
            }
            if(popUpCloseBtn2.isEnabled()){
                clickOn(popUpCloseBtn2);
            }
        } catch (Exception e) {
            System.out.println("popUp Do not Exist");
        }
    }

    /**
     * Clicks on the "Flights" button after ensuring the page is ready and closing any pop-ups.
     * This method waits for the page to be fully loaded, checks and closes any pop-ups if present,
     * then waits for the "Flights" button to be clickable before clicking it. If any errors occur
     * during the process, a {@link RuntimeException} is thrown with an error message.
     *
     * @throws RuntimeException if the "Flights" button cannot be clicked or is not found.
     */
    public void clickOnFlightsButton() {
        try {
            waitFor(5);
            waitUntilPageIsReady();
            closePopUpIfExist();
            waitForElementToBeClickable(flightsBtn);
            clickOn(flightsBtn);
        } catch (Exception e) {
            throw new RuntimeException("clickOnFlightsButton Failed - no flight button to click");
        }
    }

    /**
     * Selects random "From" and "To" destinations from predefined lists.
     * This method interacts with the origin and destination fields to select random destinations
     * from the available options. It first waits for the origin field to be visible and clickable,
     * then selects a random "From" destination. Next, it selects a random "To" destination from the
     * available list and clicks the respective destinations. Pop-ups are closed if present.
     *
     * @throws RuntimeException if any error occurs while selecting destinations, such as not
     *         being able to find the list of destinations or any other interaction failure.
     */
    public void getRandomFromAndToDestination() {
        try {
            closePopUpIfExist();
            waitForElementToAppear(originDestinationField);
            clickOn(originDestinationField);
            waitForListToLoad(listOfOriginDestinations);
            // Fetch Random Origin Destination
            Random random = new Random();
            int indexOfOriginDestination = random.nextInt(listOfOriginDestinations.size());
            WebElement fromDestination = listOfOriginDestinations.get(indexOfOriginDestination);
            clickOn(fromDestination);
            clickOn(toDestinationField);
            waitForListToLoad(listOfToDestination);
            // Fetch Random To Destination
            WebElement toDestination = listOfToDestination.get(random.nextInt(listOfToDestination.size()));
            clickOn(toDestination);

        } catch (Exception e) {
            throw new RuntimeException("chooseDestination Failed - can not find list of destination");
        }
    }

    /**
     * Selects a departure and return date for a flight based on the specified number of days.
     * This method clicks on the date field, selects a valid departure date from the available list,
     * and then chooses the return date based on the provided number of days (relative to the departure date).
     * If any errors occur during this process, a {@link RuntimeException} is thrown.
     *
     * @param chooseNumberOfDays The number of days after the departure date to select as the return date.
     *
     * @throws RuntimeException if the departure or return date cannot be clicked, or if the dates
     *         cannot be found or selected properly.
     */
    public void chooseDepartureAndReturnDate(int chooseNumberOfDays) {
        try {
            clickOn(dateFiled);
            List<WebElement> departureDateList = driver.findElements(By.cssSelector(".day.toMonth.valid"));
            // Always get Tomorrow as a date of departure.
            clickOn(departureDateList.get(1));
            // choose the number of Days to be abroad.
            WebElement flightDuration = endDateList.get(chooseNumberOfDays);
            clickOn(flightDuration);
        } catch (RuntimeException e) {
            throw new RuntimeException("chooseDepartureAndReturnDate Failed - can not click on date element");
        }
    }

    /**
     * Approves the passenger number by clicking the approval button.
     * This method waits for the passenger approval button to appear, closes any pop-ups if present,
     * and then clicks on the approval button to approve the passenger number. If any error occurs
     * during this process, a {@link RuntimeException} is thrown.
     *
     * @throws RuntimeException if the passenger approval button cannot be clicked or found.
     */
    public void approvePassngerNumber() {
        try{
            closePopUpIfExist();
            waitForElementToAppear(passngerApproveBtn);
            clickOn(passngerApproveBtn);
        }catch (RuntimeException e) {
            throw new RuntimeException("approvePassngerNumber Failed - can not click on approve Passnger Number");
        }
    }

    /**
     * Approves all selections and initiates the process to find a flight, including handling alerts.
     * This method first approves all selections, waits for the page to be ready, and closes any pop-ups.
     * It then continuously handles potential alerts by approving them, refreshing the page, and retrying
     * the flight search process. It selects random destinations, departure and return dates, and approves
     * the passenger number. If any alerts are present, the process is retried until no more alerts exist.
     *
     * @param chooseNumberOfDays The number of days after the departure date to select as the return date.
     *
     * @throws RuntimeException if the process of finding and approving a flight fails or if any errors occur
     *         while interacting with elements or alerts.
     */
    public void clickApproveAndFindMeAFlight(int chooseNumberOfDays) {
        try{
            sleepFor(2000);
            waitUntilPageIsReady();
            closePopUpIfExist();
            waitForElementToBeClickable(approveAllSelectionBtn);
            clickOn(approveAllSelectionBtn);
            waitFor(4);
            // if the same origin destination and to destination as been chosen click on Alert ok and start from the begging.
            while(isAlertPresent()){
                // go to method reRunTest() to start from the Begging.
                reRunTest(chooseNumberOfDays);
            }
        }catch (RuntimeException e) {
            throw new RuntimeException("clickApproveAndFindMeAFlight Failed - can not click on Approve And Find Me A Flight");
        }
    }

    /**
     * Re-runs the flight selection and booking process after handling an alert and refreshing the page.
     * This method performs the following actions:
     * 1. Waits for a brief moment to ensure stability after handling an alert.
     * 2. Approves any alert that is present using the `alertApprove` method.
     * 3. Refreshes the page to ensure that the application state is reset.
     * 4. Waits for the page to be ready after the refresh.
     * 5. Repeats the process of selecting flights by clicking the "Flights" button, choosing random destinations, and selecting departure and return dates.
     * 6. Approves the passenger number and attempts to click the "Approve and Find Me A Flight" button again to continue the booking process.
     *
     * @param chooseNumberOfDays the number of days used for the departure and return date selection during the re-run.
     */
    private void reRunTest(int chooseNumberOfDays){
        sleepFor(200);
        alertApprove();
        refreshPage();
        waitUntilPageIsReady();
        clickOnFlightsButton();
        getRandomFromAndToDestination();
        chooseDepartureAndReturnDate(chooseNumberOfDays);
        approvePassngerNumber();
        clickApproveAndFindMeAFlight(chooseNumberOfDays);
        int openWindowsSize = driver.getWindowHandles().size();
        moveToAnotherWindowByIndex(openWindowsSize);
    }

    // -------------------------------------------------------------- Assertions ------------------------------------------------------------

    /**
     * Asserts that the current navigation bar's text contains the expected value.
     * This method compares the text of the current navigation bar with the provided expected name.
     * If the current navigation bar’s text contains the expected value, it returns true; otherwise,
     * it returns false.
     *
     * @param currentNavigationBarName The expected text to match in the current navigation bar.
     * @return {@code true} if the current navigation bar contains the expected text; {@code false} otherwise.
     */
    public boolean assertCurrentNavigationStatus(String currentNavigationBarName){
        String nameOfCurrentNavigationBar = getText(currentNavigationStatus);
        return nameOfCurrentNavigationBar.contains(currentNavigationBarName);
    }

    public boolean assertDatePickerVisible(){
        WebElement datePicker = null;
        try{
            datePicker =  driver.findElement(By.cssSelector("[class='ng-form-row ng-datepicker-row two-month ng-form-datepicker-visible']"));
        }catch (NoSuchElementException e){
            System.out.println("Element Do not Exist");
        }
        return datePicker != null;
    }

    public boolean assertPassengerPickerWidgetIsOpen(){
        WebElement passengerPickerWidget = null;
        try{
            passengerPickerWidget =  driver.findElement(By.cssSelector("[class='ng-dropdown ng-main-dropdown ng-dropdown-primary ng-passengers-dropdown ng-dropdown-open']"));
        }catch (NoSuchElementException e){
            System.out.println("Element Do not Exist");
        }
        return passengerPickerWidget != null;
    }

    public String getHomePageUrl(){
        return getUrlAsString();
    }


}
