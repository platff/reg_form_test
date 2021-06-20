import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class RegFormTest {

    @BeforeAll
    static void setUpBrowser() {
        System.out.println("Starting tests; setting up browser....");

        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
    }

    @BeforeEach
    void openTestPage(){
        String PageURL = "https://demoqa.com/automation-practice-form";

        System.out.println("Test page opening...");

        open(PageURL);
    }

    @AfterEach
    void closeBrowser() {
        System.out.println("Test ended. Closing browser...");
        closeWebDriver();
    }

    @Test
    void selenideSearchTest() {
        String firstNameText        = "James";
        String lastNameText         = "Wilson";
        String firstNameFieldID     = "firstName";
        String lastNameFieldID      = "lastName";
        String userEmail            = "nomail@nomail.org";
        String phoneNumber          = "1234567890";

        //log.info("Starting form test....");
        System.out.println("Starting form test....");

        $(byId(firstNameFieldID)).setValue(firstNameText);
        $(byId(lastNameFieldID)).setValue(lastNameText);

        $("#userEmail").setValue(userEmail);
        $("#gender-radio-3").doubleClick();
        $("#userNumber").setValue(phoneNumber);

        System.out.println("User details are entered...");

        // DoB selection
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("1999");
        $(".react-datepicker__day--014").click();

        System.out.println("Date of birth is chosen...");

        $("#subjectsInput").setValue("Biology").pressEnter();

        System.out.println("Subjects are added...");

        $("[for=hobbies-checkbox-2]").click();

        System.out.println("Hobbies are chosen...");

        $("#uploadPicture").uploadFile(new File("src/recources/picture.jpg"));

        System.out.println("Picture is uploaded...");

        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").setValue("Delhi").pressEnter();

        System.out.println("City and State are chosen...");

        // Press button
        $(byId("submit")).pressEnter();

        System.out.println("Form is submitted...");

        //Check table
        $(".table-responsive").shouldHave(
                text("Student Name"), text(firstNameText + " " + lastNameText),
                text("Student Email"), text(userEmail),
                text("Gender"), text("Other"),
                text("Mobile"), text(phoneNumber),
                text("Date of Birth"), text("14 June,2000"),
                text("Hobbies"), text("Reading\n"),
                text("Picture"), text("picture.jpg"),
                text("State and City"), text("NCR Delhi"),
                text("Subjects"), text("Biology")
        );

        System.out.println("Ending form test....");
    }
}