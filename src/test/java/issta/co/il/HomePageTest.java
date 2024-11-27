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
        //Assert flight button is selected
        Assert.assertTrue(isFlightPageCurrent);
    }

    @Test
    @Description("Choose Random flights Destinations")
    void tc_02_ChooseRandomDestination(){
        HomePage hp = new HomePage(driver);
        hp.clickOnFlightsButton();
        boolean before = hp.assertDatePickerVisible();
        //Assert datePicker widget do not appear
        Assert.assertFalse(before);
        hp.getRandomFromAndToDestination();
        boolean after = hp.assertDatePickerVisible();
        //Assert datePicker widget appear after selecting the Origin and Destination of flight
        Assert.assertTrue(after);
    }

    @Test
    @Description("Choose Departure And Return Date")
    void tc_03_ChooseDepartureAndReturnDate(){
        HomePage hp = new HomePage(driver);
        hp.clickOnFlightsButton();
        boolean before = hp.assertPassengerPickerWidgetIsOpen();
        //Assert Passenger is not opened
        Assert.assertFalse(before);
        hp.getRandomFromAndToDestination();
        hp.chooseDepartureAndReturnDate(3);
        boolean after = hp.assertPassengerPickerWidgetIsOpen();
        //Assert Passenger is opened after selecting Departure Return Date
        Assert.assertTrue(after);
    }

    @Test
    @Description("Approve The Number Of Passengers And Continue")
    void tc_04_ApproveNumberOfPassengers(){
        HomePage hp = new HomePage(driver);
        hp.clickOnFlightsButton();
        hp.getRandomFromAndToDestination();
        hp.chooseDepartureAndReturnDate(2);
        boolean before = hp.assertPassengerPickerWidgetIsOpen();
        //Passenger Dropdown will be avilable and open
        Assert.assertTrue(before);
        hp.approvePassngerNumber();
        boolean after = hp.assertPassengerPickerWidgetIsOpen();
        //After Approving the Passenger number it will be closed
        Assert.assertFalse(after);
    }

    @Test
    @Description("Approve And click Submit")
    void tc_05_approveAndClickSubmit(){
        HomePage hp = new HomePage(driver);
        hp.clickOnFlightsButton();
        hp.getRandomFromAndToDestination();
        hp.chooseDepartureAndReturnDate(5);
        hp.approvePassngerNumber();
        String urlBeforeSubmit = hp.getHomePageUrl();
        hp.clickApproveAndFindMeAFlight(5);
        PickFlightPage pfp = new PickFlightPage(driver);
        String urlAfterSubmit = pfp.getPickAFlightUrl();
        Assert.assertNotEquals(urlBeforeSubmit,urlAfterSubmit,
                "url before submit is = " + urlBeforeSubmit + " Not equals to url after submit = " + urlAfterSubmit);
        System.out.println("url before submit is = " + urlBeforeSubmit + " Not equals to url after submit = " + urlAfterSubmit);
    }
}
