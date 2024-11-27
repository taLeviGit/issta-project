package issta.co.il;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BasePage {
    WebDriver driver;
    Actions action;
    WebDriverWait wait;
    JavascriptExecutor javascriptExecutor;
    String windowName;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        action = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        javascriptExecutor = (JavascriptExecutor) driver;
    }


    // ---------------------------------------------------------------  Actions ---------------------------------------------------------------

    /**
     * Highlights the specified web element and clicks on it.
     *
     * @param element The {@link WebElement} to be clicked. Must not be null.
     */
    public void clickOn(WebElement element) {
        highlightElement(element, "blue", "grey");
        element.click();
    }

    /**
     * Highlights the specified web element, clears it, and enters the given text.
     *
     * @param element The {@link WebElement} where text will be entered. Must not be null.
     * @param text    The text to be entered into the element. Must not be null.
     */
    public void enterText(WebElement element, String text) {
        highlightElement(element, "purple", "grey");
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Pauses the execution for the specified duration.
     *
     * @param time The duration to sleep in milliseconds. Must be a positive number.
     */
    public void sleepFor(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets an implicit wait for the specified duration.
     *
     * @param seconds The duration to wait in seconds. Must be a positive number.
     */
    public void waitFor(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    /**
     * Clicks on the specified web element using JavaScript.
     *
     * @param element The {@link WebElement} to be clicked. Must not be null.
     */
    public void javaScriptClick(WebElement element) {
        javascriptExecutor.executeScript("arguments[0].click();", element);
    }

    // ---------------------------------------------------------------  Navigation ---------------------------------------------------------------

    /**
     * Navigates the browser to the previous page in the history.
     */
    public void navigateBack() {
        driver.navigate().back();
    }

    /**
     * Refreshes the current page in the browser.
     */
    public void refreshPage() {
        driver.navigate().refresh();

    }

    /**
     * Scrolls the page down by the specified number of pixels.
     *
     * @param numOfPixels The number of pixels to scroll down. Must be a positive integer.
     */
    public void scrollDown(int numOfPixels) {
        javascriptExecutor.executeScript("window.scrollBy(0," + numOfPixels + ")");
    }

    /**
     * Scrolls the page to bring the specified element into view.
     *
     * @param element The {@link WebElement} to scroll to. Must not be null.
     */
    public void scrollToElement(WebElement element) {
        javascriptExecutor.executeScript("window.scrollTo(0, arguments[0].getBoundingClientRect().top + window.pageYOffset);", element);
    }

    /**
     * Scrolls the page to the specific position of the given element.
     * This method calculates the vertical position of the element relative to the top of the page
     * and scrolls the page to that position.
     *
     * @param element The {@link WebElement} to scroll to. Must not be null.
     */
    public void scrollToElementByPositionInPage(WebElement element) {

        long elementPosition = (long) javascriptExecutor.executeScript("return arguments[0].getBoundingClientRect().top;", element);
        long scrollPosition = (long) javascriptExecutor.executeScript("return window.pageYOffset;");
        long scrollDistance = elementPosition - scrollPosition;
        javascriptExecutor.executeScript("window.scrollTo(0, arguments[0]);", scrollDistance);

    }
    // ---------------------------------------------------------------  Alerts ---------------------------------------------------------------

    /**
     * Sends the specified text to an alert and clicks the "OK" button.
     * This method switches to the alert, enters the provided text, and then accepts (clicks OK) on the alert.
     *
     * @param text The text to be sent to the alert. Must not be null.
     */
    public void alertSendTextAndClickOk(String text) {
        driver.switchTo().alert().sendKeys(text);
        driver.switchTo().alert().accept();
    }

    /**
     * Accepts (clicks "OK") on the currently displayed alert.
     * This method switches to the alert and clicks the "OK" button to approve the alert.
     */
    public void alertApprove() {
        driver.switchTo().alert().accept();
    }

    /**
     * Dismisses (clicks "Cancel") on the currently displayed alert.
     * This method switches to the alert and clicks the "Cancel" button to dismiss the alert.
     */
    public void alertDismiss() {
        driver.switchTo().alert().dismiss();
    }

    /**
     * Retrieves the text from the currently displayed alert.
     *
     * @return The text of the alert as a {@link String}.
     */
    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    /**
     * Checks if an alert is currently present.
     *
     * @return {@code true} if an alert is present, {@code false} otherwise.
     */
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    // ---------------------------------------------------------------  Select By ---------------------------------------------------------------

    /**
     * Selects an option in a dropdown element by its value.
     * This method highlights the specified dropdown element and selects the option
     * that matches the provided value.
     *
     * @param element The {@link WebElement} representing the dropdown. Must not be null.
     * @param value   The value of the option to be selected. Must not be null.
     */
    public void selectByValue(WebElement element, String value) {
        highlightElement(element, "red", "yellow");
        selectElement(element).selectByValue(value);
    }

    /**
     * Selects an option in a dropdown element by its visible text.
     * <p>
     * This method highlights the specified dropdown element, waits briefly, selects
     * the option that matches the provided text, and waits again.
     *
     * @param element The {@link WebElement} representing the dropdown. Must not be null.
     * @param text    The visible text of the option to be selected. Must not be null.
     */
    public void selectByText(WebElement element, String text) {
        highlightElement(element, "red", "yellow");
        waitFor(1);
        selectElement(element).selectByVisibleText(text);
        waitFor(1);
    }

    /**
     * Selects an option in a dropdown element by its index.
     * This method highlights the specified dropdown element and selects the option
     * at the provided index.
     *
     * @param element The {@link WebElement} representing the dropdown. Must not be null.
     * @param index   The index of the option to be selected. Must be a non-negative integer.
     */

    public void selectByIndex(WebElement element, int index) {
        highlightElement(element, "red", "yellow");
        selectElement(element).selectByIndex(index);
    }

    // ---------------------------------------------------------------  Mouse Actions ---------------------------------------------------------------

    /**
     * Moves the mouse cursor to the specified web element.
     * This method highlights the element and performs a mouse hover action over it.
     *
     * @param element The {@link WebElement} to move the mouse to. Must not be null.
     */
    public void moveTO(WebElement element) {
        highlightElement(element, "yellow", "grey");
        action.moveToElement(element).build().perform();
    }

    /**
     * Performs a double click on the specified web element.
     * This method highlights the element and performs a double click action on it.
     *
     * @param element The {@link WebElement} to double-click on. Must not be null.
     */
    public void doubleClick(WebElement element) {
        highlightElement(element, "yellow", "green");
        action.doubleClick(element).build().perform();
    }

    /**
     * Performs a single click on the specified web element.
     * This method highlights the element and performs a click action on it.
     *
     * @param element The {@link WebElement} to click on. Must not be null.
     */

    public void clickOnce(WebElement element) {
        highlightElement(element, "yellow", "green");
        action.click(element).build().perform();
    }

    /**
     * Clicks and holds the specified web element.
     * This method highlights the element and performs a click-and-hold action on it.
     *
     * @param element The {@link WebElement} to click and hold. Must not be null.
     */
    public void clickAndHold(WebElement element) {
        highlightElement(element, "yellow", "green");
        action.clickAndHold(element).build().perform();
    }

    /**
     * Releases a previously clicked and held web element.
     * This method highlights the element and performs the release action after a click-and-hold.
     *
     * @param element The {@link WebElement} to release. Must not be null.
     */
    public void release(WebElement element) {
        highlightElement(element, "yellow", "green");
        action.release(element).build().perform();
    }

    /**
     * Drags an element and drops it onto another specified element.
     * This method highlights both the element to drag and the target element to drop,
     * and performs the drag-and-drop action.
     *
     * @param elementToDrag The {@link WebElement} to be dragged. Must not be null.
     * @param elementToDrop The {@link WebElement} to drop the dragged element onto. Must not be null.
     */
    public void dragAndDrop(WebElement elementToDrag, WebElement elementToDrop) {
        highlightElement(elementToDrag, "yellow", "green");
        highlightElement(elementToDrop, "green", "yellow");
        action.dragAndDrop(elementToDrag, elementToDrop).build().perform();
    }

    /**
     * Drags an element and drops it at a specific location defined by the offset values.
     * This method highlights the element to be dragged and performs the drag-and-drop action
     * by moving the element by the specified horizontal (xOffset) and vertical (yOffset) distances.
     *
     * @param elementToDrag The {@link WebElement} to be dragged. Must not be null.
     * @param xOffset The horizontal offset (in pixels) to move the element.
     * @param yOffset The vertical offset (in pixels) to move the element.
     */
    public void dragAndDropByLocation(WebElement elementToDrag, int xOffset, int yOffset) {
        highlightElement(elementToDrag, "yellow", "green");
        action.dragAndDropBy(elementToDrag, xOffset, yOffset).build().perform();
    }

    /**
     * Deletes text from a specified text input field by simulating multiple clicks and a delete action.
     * This method highlights the element, simulates three clicks to focus the input field,
     * and then sends a DELETE key press to remove the text.
     *
     * @param element The {@link WebElement} representing the text field. Must not be null.
     */
    public void deleteTextFromField(WebElement element) {
        highlightElement(element, "yellow", "green");
        action.click(element).build().perform();
        action.click(element).build().perform();
        action.click(element).build().perform();
        element.sendKeys(Keys.DELETE);
    }

    // --------------------------------------------------------------- Keyboard Actions ---------------------------------------------------------------

    /**
     * Simulates pressing the "Arrow Down" key.
     * This method performs a "down arrow" key press action on the currently focused element.
     */
    public void ArrowDownKey() {
        action.sendKeys(Keys.ARROW_DOWN).build().perform();
    }

    /**
     * Simulates pressing the "Arrow Right" key.
     * This method performs a "right arrow" key press action on the currently focused element.
     */
    public void ArrowRightKey() {
        action.sendKeys(Keys.ARROW_RIGHT).build().perform();
    }

    /**
     * Simulates pressing the "Enter" key.
     * This method performs an "Enter" key press action on the currently focused element.
     */
    public void EnterKey() {
        action.sendKeys(Keys.ENTER).build().perform();
    }

    /**
     * Simulates pressing the "Delete" key.
     * This method performs a "Delete" key press action on the currently focused element.
     */
    public void DeleteKey() {
        action.sendKeys(Keys.DELETE).build().perform();
    }

    // --------------------------------------------------------------- Highlight Chosen Element ---------------------------------------------------------------

    /**
     * Highlights the specified web element by changing its border and background color,
     * and then reverts the changes after a short delay.
     * This method temporarily changes the element's border to a specified color and
     * its background color, then restores the original style after a brief period.
     *
     * @param element The {@link WebElement} to highlight. Must not be null.
     * @param color The border color to apply. Must not be null.
     * @param backColor The background color to apply. Must not be null.
     */
    private void highlightElement(WebElement element, String color, String backColor) {

        //keep the old style to change it back
        String originalStyle = element.getAttribute("style");
        String newStyle = "background-color:" + backColor + ";" + " border: 3px solid " + color + ";" + originalStyle;

        // Change the style
        javascriptExecutor.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '" + newStyle + "');},0);",
                element);

        // Change the style back after few milliseconds
        javascriptExecutor.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
                + originalStyle + "');},400);", element);
    }

    // --------------------------------------------------------------- I-Frame ---------------------------------------------------------------

    /**
     * Switches to the frame identified by the specified web element.
     * This method switches the driver's focus to the frame that is represented by the given
     * {@link WebElement}.
     *
     * @param element The {@link WebElement} representing the frame to switch to. Must not be null.
     */
    public void switchToFrameByElement(WebElement element) {
        driver.switchTo().frame(element);
    }

    /**
     * Switches to the frame at the specified index.
     * This method switches the driver's focus to the frame at the given index in the page.
     *
     * @param index The index of the frame to switch to. The index is zero-based.
     */
    public void switchToFrameByIndex(int index) {
        driver.switchTo().frame(index);
    }

    /**
     * Switches the driver's focus back to the main content from a frame.
     * This method exits the current frame and switches the focus back to the default (main) content of the page.
     */
    public void closeFrame() {
        driver.switchTo().defaultContent();
    }

    // --------------------------------------------------------------- Assertion Helper ---------------------------------------------------------------

    /**
     * Retrieves the current URL of the page as a string.
     * This method waits for a brief moment and then returns the URL of the currently loaded page.
     *
     * @return The current URL of the page as a {@link String}.
     */
    public String getUrlAsString() {
        waitFor(1);
        return driver.getCurrentUrl();
    }

    /**
     * Retrieves the visible text of the specified web element.
     * This method highlights the element and then returns the text it contains.
     *
     * @param el The {@link WebElement} from which to retrieve the text. Must not be null.
     * @return The visible text of the element as a {@link String}.
     */
    public String getText(WebElement el) {
        highlightElement(el, "orange", "grey");
        return el.getText();
    }

    // --------------------------------------------------------------- Move Between windows ---------------------------------------------------------------

    /**
     * Sets the main window handle to the current window's handle.
     * This method stores the handle of the current window as the main window for future reference.
     */
    public void setMainWindow() {
        windowName = driver.getWindowHandle();
    }

    /**
     * Switches the driver's focus to a different window based on the specified index.
     * This method switches to a window from the list of open windows by the given index.
     * The index is 1-based, so index 1 refers to the first window, index 2 to the second, and so on.
     * If the specified index is out of range, an {@link IndexOutOfBoundsException} is thrown with a custom message.
     *
     * @param windowIndex The 1-based index of the window to switch to.
     * @throws IndexOutOfBoundsException if the specified windowIndex is out of range of the available windows.
     */
    public void moveToAnotherWindowByIndex(int windowIndex) {
        try{
            Set<String> list = driver.getWindowHandles();
            List<String> listNumber = new ArrayList<>(list);
            for (int i = 0; i < listNumber.size(); i++) {
                driver.switchTo().window(listNumber.get(windowIndex - 1));
            }
        }catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("cannot get specific window");
        }
    }

    /**
     * Closes the currently opened window.
     * This method closes the window that the WebDriver is currently focused on. It does not
     * switch to another window; after closing, the WebDriver will remain in the previous window
     * or will be in an undefined state if no other windows are open.
     */
    public void closeOpenedWindow() {
        driver.close();
    }

    /**
     * Switches the driver's focus back to the specified main window.
     * This method switches the WebDriver's focus to the window identified by the given window handle.
     *
     * @param nameOfWindow The handle of the main window to switch back to. Must not be null.
     */
    public void returnToMainWindow(String nameOfWindow) {
        driver.switchTo().window(nameOfWindow);
    }

    // --------------------------------------------------------------- Instant Helper  ---------------------------------------------------------------

    /**
     * Creates a {@link Select} object to interact with a dropdown element.
     * This method initializes a new {@link Select} object for the given web element,
     * allowing for interactions such as selecting options in a dropdown menu.
     *
     * @param element The {@link WebElement} representing the dropdown. Must not be null.
     * @return A {@link Select} object that can be used to interact with the dropdown.
     */
    public Select selectElement(WebElement element) {
        return new Select(element);
    }
}
