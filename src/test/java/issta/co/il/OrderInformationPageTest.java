package issta.co.il;

import jdk.jfr.Description;
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
        PickFlightPage pickFlight = new PickFlightPage(driver);
        pickFlight.pickOneFlightFromList(7);
        ApproveFlightPage af = new ApproveFlightPage(driver);
        af.clickApproveBtn();
        OrderInformationPage ui = new OrderInformationPage(driver);
        ui.fillOrderInformationTab("שם ראשון","שם שני","just@email.com","0543684295");
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
        PickFlightPage pickFlight = new PickFlightPage(driver);
        pickFlight.pickOneFlightFromList(6);
        ApproveFlightPage af = new ApproveFlightPage(driver);
        af.clickApproveBtn();
        OrderInformationPage oip = new OrderInformationPage(driver);
        oip.fillOrderInformationTab("שם ראשון","שם שני","just@email.com","0543684295");
        oip.fillPassengers_1_InformationTab("first","last","זכר","11061982");
        oip.fillPassengers_2_InformationTab("firstOne","lastOne","נקבה","12091987");
    }

    @Test
    @Description("Fill Personal Information in the second section of Order Information Page")
    void tc_03_extraServices(){
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
        oip.approvePayment();
    }

}
