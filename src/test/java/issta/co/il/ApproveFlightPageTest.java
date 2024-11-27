package issta.co.il;

import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApproveFlightPageTest extends BaseTest{

    @Test()
    @Description("E2E")
    void tc_01_E2E(){
        HomePage hp = new HomePage(driver);
        hp.clickOnFlightsButton();
        hp.getRandomFromAndToDestination();
        hp.chooseDepartureAndReturnDate(7);
        hp.approvePassngerNumber();
        hp.clickApproveAndFindMeAFlight(7);
        PickFlightPage pfp = new PickFlightPage(driver);
        pfp.pickOneFlightFromList(7);
        ApproveFlightPage afp = new ApproveFlightPage(driver);
        //Check Url Before approving the flight information
        String beforeApproveUrl = afp.getApproveFlightUrl();
        afp.clickApproveBtn();
        OrderInformationPage oip = new OrderInformationPage(driver);
        //Check Url After approving the flight information and continue to next page
        String afterApproveUrl = oip.getOrderInformationPageUrl();
        Assert.assertNotEquals(beforeApproveUrl,afterApproveUrl,
                "url before Approval is = " + beforeApproveUrl + " Not equals to url after Approval = " + afterApproveUrl);
        System.out.println("url before Approval is = " + beforeApproveUrl + " Not equals to url after Approval = " + afterApproveUrl);

    }
}
