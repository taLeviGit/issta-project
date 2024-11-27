package issta.co.il;

import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrderInformationPageTest extends BaseTest{

    @Test
    @Description("Fill Personal Information in the first section of Order Information Page")
    void tc_01_firstInformationSection(){
        HomePage hp = new HomePage(driver);
        hp.clickOnFlightsButton();
        hp.getRandomFromAndToDestination();
        hp.chooseDepartureAndReturnDate(7);
        hp.approvePassngerNumber();
        hp.clickApproveAndFindMeAFlight(7);
        PickFlightPage pfp = new PickFlightPage(driver);
        pfp.pickOneFlightFromList(7);
        ApproveFlightPage afp = new ApproveFlightPage(driver);
        afp.clickApproveBtn();
        OrderInformationPage oip = new OrderInformationPage(driver);
        //Check Url before Filling Personal information
        String beforeFillingFirstInformationSection = oip.getOrderInformationPageUrl();
        oip.fillOrderInformationTab("שם ראשון","שם שני","just@email.com","0543684295");
        //Check Url After Filling Personal information And submitting
        String AfterFillingFirstInformationSection = oip.getOrderInformationPageUrl();
        Assert.assertNotEquals(beforeFillingFirstInformationSection,AfterFillingFirstInformationSection,
                "url before Filling first Personal Information section is = " + beforeFillingFirstInformationSection +
                        " Not equals to url after Filling first Personal Information section = " + AfterFillingFirstInformationSection);
        System.out.println("url before before Filling first Personal Information section is = " + beforeFillingFirstInformationSection +
                " Not equals to url after Filling first Personal Information section = " + AfterFillingFirstInformationSection);
    }

    @Test
    @Description("Fill Personal Information in the second section of Order Information Page")
    void tc_02_secondInformationSection(){
        HomePage hp = new HomePage(driver);
        hp.clickOnFlightsButton();
        hp.getRandomFromAndToDestination();
        hp.chooseDepartureAndReturnDate(6);
        hp.approvePassngerNumber();
        hp.clickApproveAndFindMeAFlight(6);
        PickFlightPage pfp = new PickFlightPage(driver);
        pfp.pickOneFlightFromList(6);
        ApproveFlightPage afp = new ApproveFlightPage(driver);
        afp.clickApproveBtn();
        OrderInformationPage oip = new OrderInformationPage(driver);
        oip.fillOrderInformationTab("שם ראשון","שם שני","just@email.com","0543684295");
        //Check Url before Filling second information section.
        String beforeFillingSecondInformationSection = oip.getOrderInformationPageUrl();
        oip.fillPassengers_1_InformationTab("first","last","זכר","11061982");
        oip.fillPassengers_2_InformationTab("firstOne","lastOne","נקבה","12091987");
        //Check Url After Filling second information section.
        String AfterFillingSecondInformationSection = oip.getOrderInformationPageUrl();
        Assert.assertNotEquals(beforeFillingSecondInformationSection,AfterFillingSecondInformationSection,
                "url before Filling Second Personal Information section is = " + beforeFillingSecondInformationSection +
                        " Not equals to url after Filling Second Information section = " + AfterFillingSecondInformationSection);
        System.out.println("url before before Filling Second Information section is = " + beforeFillingSecondInformationSection +
                " Not equals to url after Filling Second Information section = " + AfterFillingSecondInformationSection);
    }

    @Test
    @Description("Decline Extra services")
    void tc_03_declineExtraServices(){
        HomePage hp = new HomePage(driver);
        hp.clickOnFlightsButton();
        hp.getRandomFromAndToDestination();
        hp.chooseDepartureAndReturnDate(6);
        hp.approvePassngerNumber();
        hp.clickApproveAndFindMeAFlight(6);
        PickFlightPage pfp = new PickFlightPage(driver);
        pfp.pickOneFlightFromList(6);
        ApproveFlightPage afp = new ApproveFlightPage(driver);
        afp.clickApproveBtn();
        OrderInformationPage oip = new OrderInformationPage(driver);
        oip.fillOrderInformationTab("שם ראשון","שם שני","just@email.com","0543684295");
        oip.fillPassengers_1_InformationTab("first","last","זכר","11061982");
        oip.fillPassengers_2_InformationTab("firstOne","lastOne","נקבה","12091987");
        //Check Url Before declining extra services.
        String beforeDecliningExtraServices = oip.getOrderInformationPageUrl();
        oip.cancelExtraServices();
        //Check Url After declining extra services.
        String AfterDecliningExtraServices = oip.getOrderInformationPageUrl();
        Assert.assertNotEquals(beforeDecliningExtraServices,AfterDecliningExtraServices,
                "url before Declining extra services is = " + beforeDecliningExtraServices + " Not equals to url after Declining extra services = " + AfterDecliningExtraServices);
        System.out.println("url before Declining extra services is = " + beforeDecliningExtraServices + " Not equals to url after Declining extra services= " + AfterDecliningExtraServices);
    }

    @Test
    @Description("Fill Personal Information in the second section of Order Information Page")
    void tc_04_payment(){
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
        //Check if iframe exist before submitting and continue to payment.
        boolean beforeApproveAndContinueToPayment = oip.isIframePaymentExist();
        Assert.assertFalse(beforeApproveAndContinueToPayment);
        oip.approvePayment();
        //Check if iframe exist After submitting and continue to payment.
        boolean afterApproveAndContinueToPayment = oip.isIframePaymentExist();
        Assert.assertTrue(afterApproveAndContinueToPayment);

    }

}
