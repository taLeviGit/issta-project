package issta.co.il;

import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest{

    @Test
    @Description("Click on flights Button in HomePage")
    void tc_01_ClickOnFlights() {
        HomePage hp = new HomePage(driver);
        hp.clickOnFlightsButton();
        boolean isFlightPageCurrent = hp.assertCurrentNavigationStatus("טיסות");
        Assert.assertTrue(isFlightPageCurrent);
    }

    @Test
    @Description("Choose Random flights Destinations")
    void tc_02_ChooseRandomDestination(){
        HomePage hp = new HomePage(driver);
        hp.clickOnFlightsButton();
        hp.getRandomFromAndToDestination();

    }

    @Test
    @Description("Choose Departure And Return Date")
    void tc_03_ChooseDepartureAndReturnDate(){
        HomePage hp = new HomePage(driver);
        hp.clickOnFlightsButton();
        hp.getRandomFromAndToDestination();
        hp.chooseDepartureAndReturnDate(3);
    }

    @Test
    @Description("Approve The Number Of Passengers And Continue")
    void tc_04_ApproveNumberOfPassengers(){
        HomePage hp = new HomePage(driver);
        hp.clickOnFlightsButton();
        hp.getRandomFromAndToDestination();
        hp.chooseDepartureAndReturnDate(2);
        hp.approvePassngerNumber();
    }

    @Test
    @Description("Home Page E2E")
    void tc_05_HomePageE2E(){
        HomePage hp = new HomePage(driver);
        hp.clickOnFlightsButton();
        hp.getRandomFromAndToDestination();
        hp.chooseDepartureAndReturnDate(5);
        hp.approvePassngerNumber();
        hp.clickApproveAndFindMeAFlight(5);
    }
}
