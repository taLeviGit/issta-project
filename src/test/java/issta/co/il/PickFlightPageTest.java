package issta.co.il;

import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PickFlightPageTest extends BaseTest{

    @Test
    @Description("Pick a random Flight")
    void tc_01_pickARandomFlight(){

        HomePage hp = new HomePage(driver);
        PickFlightPage pfp = new PickFlightPage(driver);
        hp.clickOnFlightsButton();
        hp.getRandomFromAndToDestination();
        hp.chooseDepartureAndReturnDate(2);
        hp.approvePassngerNumber();
        hp.clickApproveAndFindMeAFlight(2);
        //Check Url Before Picking a Flight
        String beforePickingFlightUrl = pfp.getPickAFlightUrl();
        pfp.pickOneFlightFromList(2);
        ApproveFlightPage afp = new ApproveFlightPage(driver);
        //Check Url After Picking a Flight and continue to next page
        String AfterPickFlightUrl = afp.getApproveFlightUrl();
        Assert.assertNotEquals(beforePickingFlightUrl,AfterPickFlightUrl,
                "url before Picking a flight is = " + beforePickingFlightUrl + " Not equals to url after Picking a flight = " + AfterPickFlightUrl);
        System.out.println("url before Picking a flight is = " + beforePickingFlightUrl + " Not equals to url after Picking a flight = " + AfterPickFlightUrl);

    }
}
