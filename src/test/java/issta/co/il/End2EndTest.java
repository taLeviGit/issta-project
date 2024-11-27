package issta.co.il;

import jdk.jfr.Description;
import org.testng.annotations.Test;

public class End2EndTest extends BaseTest{

    @Test
    @Description("End2End Test")
    void tc_04_End2EndTest(){
        HomePage hp = new HomePage(driver);
        hp.clickOnFlightsButton();
        hp.getRandomFromAndToDestination();
        hp.chooseDepartureAndReturnDate(6);
        hp.approvePassngerNumber();
        hp.clickApproveAndFindMeAFlight(6);
        PickFlightPage pickFlight = new PickFlightPage(driver);
        pickFlight.pickOneFlightFromList(6);
        ApproveFlightPage af = new ApproveFlightPage(driver);
        af.clickApproveBtn();
        OrderInformationPage oip = new OrderInformationPage(driver);
        oip.fillOrderInformationTab("שם ראשון","שם שני","just@email.com","0543684295");
        oip.fillPassengers_1_InformationTab("first","last","זכר","11061982");
        oip.fillPassengers_2_InformationTab("firstOne","lastOne","נקבה","12091987");
        oip.cancelExtraServices();
        oip.approvePayment();
    }

}
