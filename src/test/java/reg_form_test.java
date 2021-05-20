import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class reg_form_test {

    //static Logger log = LoggerFactory.getLogger(reg_form_test.class);

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

        String firstNameText = "John";
        String lastNameText  = "Dow";
        String firstNameFieldID  = "firstName";
        String lastNameFieldID   = "lastName";
        String userEmail = "nomail@nomail.org";
        String phoneNumber = "1234567890";

        //log.info("Starting form test....");
        System.out.println("Starting form test....");

        $(byId(firstNameFieldID)).setValue(firstNameText);
        $(byId(lastNameFieldID)).setValue(lastNameText);

        $("#userEmail").setValue(userEmail);
        $("#gender-radio-3").doubleClick();
        $("#userNumber").setValue(phoneNumber);

        // DoB selection
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__day--014").click();

        // Press button
        $(byId("submit")).pressEnter();

        System.out.println("Ending form test....");
    }
}