package issta.co.il;

import jdk.jfr.Description;
import org.testng.annotations.Test;

public class ApproveFlightPageTest extends BaseTest{

    @Test()
    @Description("E2E")
    void tc_01_E2E(){

        HomePage hp = new HomePage(driver);
        PickFlightPage pickFlight = new PickFlightPage(driver);
        ApproveFlightPage af = new ApproveFlightPage(driver);

        hp.clickOnFlightsButton();
        hp.getRandomFromAndToDestination();
        hp.chooseDepartureAndReturnDate(7);
        hp.approvePassngerNumber();
        hp.clickApproveAndFindMeAFlight(7);
        pickFlight.pickOneFlightFromList(7);
        af.clickApproveBtn();
    }
}
