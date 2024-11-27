package issta.co.il;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

public class ConnectionPage extends BasePage {


    public ConnectionPage(WebDriver driver) {
        super(driver);
    }

    // ----------------  Usage - All pages : Waiting for Elements/Alerts/Refresh ----------------

    /**
     * Waits until the specified web element is clickable.
     * This method waits for the given element to become clickable (i.e., visible and enabled)
     * before allowing further interaction with it.
     *
     * @param element The {@link WebElement} to wait for. Must not be null.
     */
    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits until all elements in the provided list are visible.
     * This method waits for the visibility of all elements in the given list, ensuring
     * that the entire list of elements is rendered and visible on the page before proceeding.
     *
     * @param elements The list of {@link WebElement}s to wait for. Must not be null or empty.
     */
    public void waitForListToLoad(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    /**
     * Waits until the specified frame is available and switches to it.
     * This method waits for the given frame to be available and then switches the driver's focus to it.
     *
     * @param element The {@link WebElement} representing the frame to wait for. Must not be null.
     */
    public void waitForFrameToBeAvilable(WebElement element) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    /**
     * Waits until the specified web element is visible.
     * This method waits for the given element to be visible on the page, ensuring that the element
     * is rendered and can be interacted with before proceeding.
     *
     * @param element The {@link WebElement} to wait for. Must not be null.
     */
    public void waitForElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until the specified web element is no longer visible.
     * This method waits for the given element to become invisible on the page, ensuring that the
     * element is no longer displayed before proceeding.
     *
     * @param element The {@link WebElement} to wait for to disappear. Must not be null.
     */
    public void waitForElementToDisappear(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * Waits until an alert is present on the page.
     * This method waits for the presence of an alert, ensuring that an alert box is available
     * before proceeding with further interactions.
     */
    public void waitForAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
    }

    /**
     * Waits until the specified condition is refreshed.
     * This method waits for the condition to be refreshed, which typically means waiting for an element
     * or state to change after a page refresh or dynamic update.
     *
     * @param condition The {@link ExpectedCondition} to wait for to be refreshed. Must not be null.
     */
    public void waitForRefresh(ExpectedCondition<?> condition) {
        wait.until(ExpectedConditions.refreshed(condition));
    }

    /**
     * Waits until the page is fully loaded and ready.
     * This method waits for the document's ready state to be "complete", indicating that the page
     * has fully loaded and all resources have been processed before proceeding.
     */
    public void waitUntilPageIsReady(){
        wait.until(driver -> javascriptExecutor.executeScript("return document.readyState").equals("complete"));
    }

    // ----------------  Usage - All pages : Pick Item in List ----------------

    /**
     * Picks and clicks an item from a list of web elements based on the provided index.
     * This method attempts to move to and click the element at the specified index in the list.
     * If the provided index is out of bounds, an {@link IndexOutOfBoundsException} is thrown
     * with a custom message indicating the error.
     *
     * @param listName The list of {@link WebElement}s to pick from. Must not be null or empty.
     * @param numOfAnswer The index of the item in the list to pick and click. Must be within the list bounds.
     * @throws IndexOutOfBoundsException if the provided index is out of range of the list.
     */
    public void pickItemInList(List<WebElement> listName, int numOfAnswer) {
        try{
            WebElement element = listName.get(numOfAnswer);
            moveTO(element);
            clickOnce(element);
            waitFor(5);
        }catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("Item picked is out of listName Bound");
        }
    }

    // ----------------  Usage - All pages : Switch between tabs/windows ----------------

    /**
     * Switches the driver's focus to a different window, excluding the main window.
     * This method captures the current main window's handle and then switches to the first
     * available window that is not the main window.
     *
     * @param mainWindow The handle of the main window to be excluded from the switching process.
     *                   It is captured before switching to another window.
     */
    public void switchWindow(String mainWindow) {
        mainWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        for (String win : windows) {
            if (!win.equals(mainWindow)) {
                driver.switchTo().window(win);
                break;
            }
        }
    }


    // ----------------  Usage - All pages : Assertions ----------------

    /**
     * Verifies if the text of a given web element matches the expected error message.
     * This method compares the text of the provided web element with the specified error message
     * in a case-insensitive manner. It returns true if the text matches the error message,
     * otherwise returns false.
     *
     * @param element The {@link WebElement} whose text will be compared. Must not be null.
     * @param errorMessage The expected error message to compare against. Must not be null.
     * @return {@code true} if the element's text matches the error message, ignoring case;
     *         {@code false} otherwise.
     */
    public boolean isMsgCorrect(WebElement element, String errorMessage) {
        return getText(element).equalsIgnoreCase(errorMessage);
    }
}
