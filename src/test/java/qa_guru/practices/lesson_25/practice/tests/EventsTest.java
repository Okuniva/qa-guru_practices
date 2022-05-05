package qa_guru.practices.lesson_25.practice.tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static qa_guru.practices.lesson_25.practice.Specs.BaseSpec.request;
import static qa_guru.practices.lesson_25.practice.Specs.BaseSpec.responseSpec;

public class EventsTest {
    @Test
    void verifyCurrentPriceTest() {
        given()
                .spec(request)
                .when()
                .get("/bpi/currentprice.json")
                .then()
                .spec(responseSpec)
                .body("bpi.EUR.findAll{it.symbol =~/^&.+$/}.",
                        hasItem("euro"));
        // ToDo complete groovy check
    }
}
