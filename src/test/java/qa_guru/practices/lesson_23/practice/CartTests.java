package qa_guru.practices.lesson_23.practice;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import qa_guru.practices.lesson_23.examples.tests.TestBase;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.is;

@Story("CartTests test")
@Owner("Valeev_A_A")
@Tag("lesson_23")
public class CartTests extends TestBase {

}
