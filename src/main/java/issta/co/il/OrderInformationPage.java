package issta.co.il;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OrderInformationPage extends ConnectionPage {
    public OrderInformationPage(WebDriver driver) {
        super(driver);
    }

    // ------------------------------------- Ordering details Tab Selectors -------------------------------------
    @FindBy(css = "#checkout-first-name")
    WebElement sectionOneFirstNameField;
    @FindBy(css = "#checkout-last-name")
    WebElement sectionOneLastNameField;
    @FindBy(css = "#checkout-email")
    WebElement sectionOneEmailField;
    @FindBy(css = "#checkout-phone")
    WebElement sectionOnePhoneNumberField;
    @FindBy(css = ".form__actions .checkbox")
    WebElement checkBox;
    @FindBy(css = ".form__actions button")
    WebElement sectionOneApproveBtn;

    // ------------------------------------- Passengers details Tab Selectors - First Passenger Selectors -------------------------------------

    @FindBy(css = "[name='PassengerDetailsVM.PassengersVM.Passengers[0].FirstName']")
    WebElement firstPassengerEnglishFirstNameField;
    @FindBy(css = "[name='PassengerDetailsVM.PassengersVM.Passengers[0].LastName']")
    WebElement firstPassengerEnglishLastNameField;
    @FindBy(css = "div[data-pax-prefix='PassengerDetailsVM.PassengersVM.Passengers[0].'] div.selectric b")
    WebElement firstPassengerPickGenderBtn;
    @FindBy(css = "div[data-pax-prefix='PassengerDetailsVM.PassengersVM.Passengers[0].'] div.selectric-items ul li")
    List<WebElement> firstPassengerListOfGendersDropDown;
    @FindBy(xpath = "//input[@id='PassengerDetailsVM.PassengersVM.Passengers[0].Birthdate']")
    WebElement firstPassengerDateOfBirthField;
    @FindBy(css = "[data-pax-index='1'] i.icon-check")
    List<WebElement> firstPassengerBaggageSelectOptionList;

    // ------------------------------------- Passengers details Tab - Second Passenger Selectors -------------------------------------

    @FindBy(css = "[name='PassengerDetailsVM.PassengersVM.Passengers[1].FirstName']")
    WebElement secondPassengerEnglishFirstNameField;
    @FindBy(css = "[name='PassengerDetailsVM.PassengersVM.Passengers[1].LastName']")
    WebElement secondPassengerEnglishLastNameField;
    @FindBy(css = "div[data-pax-prefix='PassengerDetailsVM.PassengersVM.Passengers[1].'] div.selectric b")
    WebElement secondPassengerPickGenderBtn;
    @FindBy(css = "div[data-pax-prefix='PassengerDetailsVM.PassengersVM.Passengers[1].'] div.selectric-items ul li")
    List<WebElement> secondPassengerListOfGendersDropDown;
    @FindBy(xpath = "//input[@id='PassengerDetailsVM.PassengersVM.Passengers[1].Birthdate']")
    WebElement secondPassengerDateOfBirthField;

    @FindBy(css = ".form__actions button")
    WebElement sectionTwoApproveBtn;

    @FindBy(css = "[data-pax-index='2'] i.icon-check")
    List<WebElement> secondPassengerBaggageSelectOptionList;

    // --------------------------------------------------------------- Extra services Tab Selectors ---------------------------------------------------

    @FindBy(css = "input[class='generalservice-button remove-generalservices-button']")
    List<WebElement> cancelExtraServiceRadioBtnList;

    @FindBy(css = "button[class=' btn btn--default']")
    WebElement sectionThreeApproveBtn;

    // ---------------------------------------------------------------- Payment Tab Selectors ----------------------------------------------------------------

    @FindBy(css = "label[for='ApproveCheckbox']")
    WebElement approveCheckBox;

    @FindBy(css = "div.btn.btn--default.btn--large.payment-btn")
    WebElement sectionFourApproveBtn;

    // --------------------------------------------------------------  Ordering details Tab --------------------------------------

    /**
     * Fills out the order information form with provided personal details and submit.
     * This method populates the form fields such as first name, last name, email, phone number, and
     * approves the submission. It validates the input for each field, ensuring that the Hebrew first and last names
     * have at least 2 characters, the email contains "@" and ".", and the phone number has exactly 9 characters.
     * It also checks that the user accepts the terms by clicking the checkbox before submitting the form.
     *
     * @param hebrewFirstName_Min2char The user's first name in Hebrew, must be at least 2 characters.
     * @param hebrewLastName_Min2char  The user's last name in Hebrew, must be at least 2 characters.
     * @param email                    The user's email address, must contain "@" and ".".
     * @param phoneNumber_only10char   The user's phone number, must be exactly 9 characters long.
     * @throws IllegalArgumentException if any of the provided parameters do not meet the required criteria:
     *                                  - First name or last name is less than 2 characters.
     *                                  - Email does not contain "@" or ".".
     *                                  - Phone number is not exactly 9 characters long.
     */

    public void fillOrderInformationTab(String hebrewFirstName_Min2char, String hebrewLastName_Min2char, String email, String phoneNumber_only10char) {

        waitFor(10);
        waitUntilPageIsReady();
        waitForElementToAppear(sectionOneFirstNameField);
        clickOn(sectionOneFirstNameField);
        //Check if the hebrew first name parameter is less than 2 characters
        if (hebrewFirstName_Min2char.length() < 2) {
            throw new IllegalArgumentException("Warning: hebrew First Name Parameter size is less than 2!");
        }
        enterText(sectionOneFirstNameField, hebrewFirstName_Min2char);
        sleepFor(200);
        //Check if the hebrew last name parameter is less than 2 characters
        if (hebrewLastName_Min2char.length() < 2) {
            throw new IllegalArgumentException("Warning: hebrew Last Name Parameter size is less than 2!");
        }
        waitForElementToBeClickable(sectionOneLastNameField);
        clickOn(sectionOneLastNameField);
        enterText(sectionOneLastNameField, hebrewLastName_Min2char);
        sleepFor(200);
        //Check if email parameter contains the characters "@" and "."
        if (!email.contains("@") && !email.contains(".")) {
            throw new IllegalArgumentException("Warning: email Parameter Must Contain @ and . !");
        }
        waitForElementToBeClickable(sectionOneEmailField);
        clickOn(sectionOneEmailField);
        enterText(sectionOneEmailField, email);
        sleepFor(200);
        //Check if Phone number is
        if (phoneNumber_only10char.length() != 10) {
            throw new IllegalArgumentException("Warning: phoneNumber Parameter size Must be 10 !");
        }
        waitForElementToBeClickable(sectionOnePhoneNumberField);
        clickOn(sectionOnePhoneNumberField);
        enterText(sectionOnePhoneNumberField, phoneNumber_only10char);
        sleepFor(200);
        waitForElementToBeClickable(checkBox);
        clickOn(checkBox);
        sleepFor(200);
        waitForElementToBeClickable(sectionOneApproveBtn);
        clickOn(sectionOneApproveBtn);
    }

    // ------------------------------------- Passengers details Tab - First Passenger -------------------------------------

    /**
     * Fills out the passenger 1 information form with provided details and selects baggage options.
     * --
     * This method populates the passenger form fields such as first name, last name, gender, date of birth,
     * and selects baggage options. It validates the input for each field, ensuring that the first name and last name
     * in English have at least 2 characters, the gender is provided in Hebrew with a minimum length of 3 characters,
     * and the date of birth is entered correctly.
     * It also checks and selects baggage options for the passenger if available.
     *
     * @param firstNameInEnglish_Min2char The first name of the passenger in English, must be at least 2 characters.
     * @param lastNameInEnglish_Min2char  The last name of the passenger in English, must be at least 2 characters.
     * @param genderInHebrew              The gender of the passenger in Hebrew, must be at least 3 characters.
     * @param dateOfBirth                 The date of birth of the passenger in the format "dd/MM/yyyy".
     * @throws IllegalArgumentException if any of the provided parameters do not meet the required criteria:
     *                                  - First name or last name is less than 2 characters.
     *                                  - Gender is less than 3 characters in Hebrew.
     */

    public void fillPassengers_1_InformationTab(String firstNameInEnglish_Min2char, String lastNameInEnglish_Min2char, String genderInHebrew, String dateOfBirth) {
        waitFor(4);
        waitForElementToBeClickable(firstPassengerEnglishFirstNameField);
        clickOn(firstPassengerEnglishFirstNameField);
        //Check if the English first name parameter is less than 2 characters
        if (firstNameInEnglish_Min2char.length() < 2) {
            throw new IllegalArgumentException("Warning: first Name In English Parameter size is less than 2!");
        }
        enterText(firstPassengerEnglishFirstNameField, firstNameInEnglish_Min2char);
        sleepFor(200);
        waitForElementToBeClickable(firstPassengerEnglishLastNameField);
        clickOn(firstPassengerEnglishLastNameField);
        //Check if the English last name parameter is less than 2 characters
        if (lastNameInEnglish_Min2char.length() < 2) {
            throw new IllegalArgumentException("Warning: first Name In English Parameter size is less than 2!");
        }
        enterText(firstPassengerEnglishLastNameField, lastNameInEnglish_Min2char);
        sleepFor(200);
        waitForElementToBeClickable(firstPassengerPickGenderBtn);
        clickOn(firstPassengerPickGenderBtn);
        // Check if Gender Parameter is less than 3 characters ( it must be in hebrew זכר או נקבה )
        if (genderInHebrew.length() < 3) {
            throw new IllegalArgumentException("Warning: gender In Hebrew Parameter size is less than 3!");
        }
        //Choose the Gender in the dropdown list by the name user provide in test
        for (WebElement element : firstPassengerListOfGendersDropDown) {
            if (getText(element).equalsIgnoreCase(genderInHebrew)) {
                clickOn(element);
                break;
            }
        }
        sleepFor(200);
        waitForElementToBeClickable(firstPassengerDateOfBirthField);
        clickOn(firstPassengerDateOfBirthField);
        Actions actions = new Actions(driver);
        //DateOfBirth is masked in the html - only one character at a time can be input into the field, loop through the characters and insert them one by one.
        char[] dateOfBirthChar = dateOfBirth.toCharArray();
        actions.moveToElement(firstPassengerDateOfBirthField).click();
        for (char c : dateOfBirthChar) {
            actions.sendKeys(String.valueOf(c)).perform();
            sleepFor(100);
        }
        sleepFor(200);
        // only check the radio button that is not already selected, if there is no selection - skip.
        for (WebElement element : firstPassengerBaggageSelectOptionList) {
            try {
                if (!element.isSelected()) {
                    clickOn(element);
                }
            } catch (Exception e) {
                System.out.println("element Do not Exist");
            }
        }
    }

    // ------------------------------------- Passengers details Tab - Second Passenger -------------------------------------

    /**
     * Fills out the passenger 2 information form with provided details and selects baggage options.
     * --
     * This method populates the second passenger's form fields such as first name, last name, gender, date of birth,
     * and selects baggage options. It validates the input for each field, ensuring that the first name and last name
     * in English have at least 2 characters, the gender is provided in Hebrew with a minimum length of 3 characters,
     * and the date of birth is entered correctly.
     * It also checks and selects baggage options for the passenger if available.
     *
     * @param firstNameInEnglish_Min2char The first name of the second passenger in English, must be at least 2 characters.
     * @param lastNameInEnglish_Min2char  The last name of the second passenger in English, must be at least 2 characters.
     * @param genderInHebrew              The gender of the second passenger in Hebrew, must be at least 3 characters.
     * @param dateOfBirth                 The date of birth of the second passenger in the format "dd/MM/yyyy".
     * @throws IllegalArgumentException if any of the provided parameters do not meet the required criteria:
     *                                  - First name or last name is less than 2 characters.
     *                                  - Gender is less than 3 characters in Hebrew.
     */
    public void fillPassengers_2_InformationTab(String firstNameInEnglish_Min2char, String lastNameInEnglish_Min2char, String genderInHebrew, String dateOfBirth) {
        waitFor(2);
        scrollDown(1500);
        waitForElementToBeClickable(secondPassengerEnglishFirstNameField);
        clickOn(secondPassengerEnglishFirstNameField);
        //Check if the English first name parameter is less than 2 characters
        if (firstNameInEnglish_Min2char.length() < 2) {
            throw new IllegalArgumentException("Warning: first Name In English Parameter size is less than 2!");
        }
        enterText(secondPassengerEnglishFirstNameField, firstNameInEnglish_Min2char);
        sleepFor(200);
        waitForElementToBeClickable(secondPassengerEnglishLastNameField);
        clickOn(secondPassengerEnglishLastNameField);
        //Check if the English last name parameter is less than 2 characters
        if (lastNameInEnglish_Min2char.length() < 2) {
            throw new IllegalArgumentException("Warning: first Name In English Parameter size is less than 2!");
        }
        enterText(secondPassengerEnglishLastNameField, lastNameInEnglish_Min2char);
        sleepFor(200);
        waitForElementToBeClickable(secondPassengerPickGenderBtn);
        clickOn(secondPassengerPickGenderBtn);
        // Check if Gender Parameter is less than 3 characters ( it must be in hebrew זכר או נקבה )
        if (genderInHebrew.length() < 3) {
            throw new IllegalArgumentException("Warning: gender In Hebrew Parameter size is less than 3!");
        }
        //Choose the Gender in the dropdown list by the name user provide in test
        for (WebElement element : secondPassengerListOfGendersDropDown) {
            if (getText(element).equalsIgnoreCase(genderInHebrew)) {
                clickOn(element);
                break;
            }
        }
        sleepFor(200);
        waitForElementToBeClickable(secondPassengerDateOfBirthField);
        clickOn(secondPassengerDateOfBirthField);
        Actions actions = new Actions(driver);
        //DateOfBirth is masked in the html - only one character at a time can be input into the field, loop through the characters and insert them one by one.
        char[] dateOfBirthChar = dateOfBirth.toCharArray();
        actions.moveToElement(secondPassengerDateOfBirthField).click();
        for (char c : dateOfBirthChar) {
            actions.sendKeys(String.valueOf(c)).perform();
            sleepFor(100);
        }
        sleepFor(200);
        // only check the radio button that is not already selected, if there is no selection - skip.
        for (WebElement element : secondPassengerBaggageSelectOptionList) {
            try {
                if (!element.isSelected()) {
                    clickOn(element);
                }
            } catch (Exception e) {
                System.out.println("element is Not Selectable");
            }
        }
        sleepFor(200);
        scrollDown(1500);
        scrollToElement(sectionTwoApproveBtn);
        waitForElementToBeClickable(sectionTwoApproveBtn);
        clickOn(sectionTwoApproveBtn);
    }

    // --------------------------------------------------------------- Extra services Tab ---------------------------------------------------

    /**
     * Cancels any extra services selected by unchecking the corresponding radio buttons.
     * This method iterates through a list of radio buttons representing extra services and unchecks
     * any that are selected. It waits for the list to load, clicks on each radio button if it is not selected,
     * and then scrolls down to approve the cancellation of extra services by clicking the approval button.
     *
     * @throws IllegalStateException if there is an issue with clicking the radio button or the approval button.
     */
    public void cancelExtraServices() {
        waitFor(5);
        waitForListToLoad(cancelExtraServiceRadioBtnList);
        //click on the radio button with no added price.
        cancelExtraServiceRadioBtnList
                .forEach(element -> {
                    try {
                        if (!element.isSelected()) {
                            waitForElementToBeClickable(element);
                            clickOn(element);
                            sleepFor(3000);
                        }
                    } catch (Exception e) {
                        System.out.println("element is not Selectable");
                    }
                });
        waitFor(2);
        scrollDown(2500);
        scrollToElement(sectionThreeApproveBtn);
        moveTO(sectionThreeApproveBtn);
        waitForElementToBeClickable(sectionThreeApproveBtn);
        clickOn(sectionThreeApproveBtn);
    }

    // --------------------------------------------------------------- Payment Tab ---------------------------------------------------

    /**
     * Approves the payment by interacting with checkboxes and buttons in the payment section.
     * This method performs the following steps:
     * 1. Waits for the approval checkbox to be clickable and clicks on it to approve the payment.
     * 2. Waits for the "Approve" button in the payment section and clicks it using JavaScript.
     * 3. Switches back to the main frame and waits for the payment button to be clickable.
     * 4. Clicks the payment button to complete the payment process.
     * 5. Prints a "Test Complete" message to the console upon successful completion.
     *
     * @throws RuntimeException if there is an issue with the payment approval process, such as an element not being clickable.
     */
    public void approvePayment() {
        waitFor(5);
        waitForElementToBeClickable(approveCheckBox);
        clickOn(approveCheckBox);
        sleepFor(200);
        waitForElementToBeClickable(sectionFourApproveBtn);
        javaScriptClick(sectionFourApproveBtn);
        sleepFor(5000);
        // Frame Handling
        switchToFrameByIndex(0);
        WebElement payment = driver.findElement(By.cssSelector("input[id='btOk']"));
        waitForElementToBeClickable(payment);
        javaScriptClick(payment);
        System.out.println("Test Complete");
    }


    // ------------------------------------------------------------------ Assertions --------------------------------------------------

    public String getOrderInformationPageUrl() {
        sleepFor(5000);
        waitUntilPageIsReady();
        int openWindowsSize = driver.getWindowHandles().size();
        moveToAnotherWindowByIndex(openWindowsSize);
        return getUrlAsString();
    }

    public boolean isIframePaymentExist(){
        sleepFor(2000);
        WebElement iframe = null;
        try{
            iframe = driver.findElement(By.cssSelector("#form2"));
        }catch (NoSuchElementException e){
            System.out.println("Element iframe do not exist");
        }
        return iframe != null;
    }
}
