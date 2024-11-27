package issta.co.il;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ApproveFlightPage extends ConnectionPage {
    public ApproveFlightPage(WebDriver driver) {
        super(driver);
    }

    // --------------------------------------------Approve Flight Page Selector ------------------------------------

    @FindBy(css = ".btn")
    WebElement approveBtn;

    @FindBy(css = ".modal-continue-action")
    WebElement acceptChangeOfPrice;

    //---------------------------------------------------------- Methods ----------------------------------------------------


    /**
     * Clicks the approve button after handling potential pop-ups and ensuring the page is ready.
     * This method waits for the page to be ready, checks for the presence of a "Change of Price"
     * acceptance button and clicks it if visible. It then checks if the approval button is visible
     * and scrolls down if necessary to bring the button into view. After ensuring the button is clickable,
     * it clicks on the approval button using JavaScript.
     *
     * @throws RuntimeException if any errors occur while interacting with elements, such as
     *         issues finding the "Change of Price" element or the approval button.
     */
    public void clickApproveBtn() {
        waitFor(15);
        waitUntilPageIsReady();
        int numberOfWindows = driver.getWindowHandles().size();
        moveToAnotherWindowByIndex(numberOfWindows);
        //if accept Change Of Price pops-up click ok
        try {
            if (acceptChangeOfPrice.isDisplayed()) {
                waitForElementToBeClickable(acceptChangeOfPrice);
                clickOn(acceptChangeOfPrice);
            }
        } catch (Exception e) {
            System.out.println("accept Change Of Price Element Do not Exist");
        }
        // if approve Button is not visible in the page scroll to it.
        if (!approveBtn.isDisplayed()) {
            scrollDown(500);
        }
        scrollToElement(approveBtn);
        waitForElementToBeClickable(approveBtn);
        javaScriptClick(approveBtn);
    }

    // ------------------------------------------------------------------------------ Assertions ------------------------------------------

    public String getApproveFlightUrl(){
        sleepFor(10000);
        waitUntilPageIsReady();
        int openWindowsSize = driver.getWindowHandles().size();
        moveToAnotherWindowByIndex(openWindowsSize);
        return getUrlAsString();
    }
}
