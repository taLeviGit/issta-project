package issta.co.il;

import jdk.jfr.Description;
import org.testng.annotations.Test;

public class PickFlightPageTest extends BaseTest{

    @Test
    @Description("E2E")
    void tc_01_E2E(){

        HomePage hp = new HomePage(driver);
        PickFlightPage pickFlight = new PickFlightPage(driver);
        hp.clickOnFlightsButton();
        hp.getRandomFromAndToDestination();
        hp.chooseDepartureAndReturnDate(2);
        hp.approvePassngerNumber();
        hp.clickApproveAndFindMeAFlight(2);
        pickFlight.pickOneFlightFromList(2);

    }
}
