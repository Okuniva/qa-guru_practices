package qa_guru.practices.lesson_23.practice.tests.demowebshop;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import qa_guru.practices.lesson_23.helpers.AllureRestAssuredFilter;
import qa_guru.practices.lesson_23.practice.config.demowebshop.App;
import qa_guru.practices.lesson_23.practice.tests.TestBase;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.CoreMatchers.is;

@Story("WishList tests")
public class WishListTest extends TestBase {
    @BeforeAll
    static void configureBaseUrl() {
        RestAssured.baseURI = App.config.apiUrl();
        Configuration.baseUrl = App.config.webUrl();
        // ToDo set filter
    }

    @Test
    @Tag("demowebshop")
    void addGoodsToWishlistTest() {
        step("Get cookie by api and set it to browser", () -> {
            String authorizationCookie =
                    given()
                            .filter(AllureRestAssuredFilter.withCustomTemplates())
                            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                            .formParam("Email", App.config.userLogin())
                            .formParam("Password", App.config.userPassword())
                            .when()
                            .post("/login")
                            .then()
                            .statusCode(302)
                            .extract()
                            .cookie("NOPCOMMERCE.AUTH");

            step("Open minimal content, because cookie can be set when site is opened", () ->
                    open("/Themes/DefaultClean/Content/images/logo.png"));

            step("Set cookie to to browser", () ->
                    getWebDriver().manage().addCookie(
                            new Cookie("NOPCOMMERCE.AUTH", authorizationCookie)));
        });


        step("Add goods to wishlist by api", () -> {
            ValidatableResponse response = given()
                    .filter(AllureRestAssuredFilter.withCustomTemplates())
                    .cookie("Nop.customer=88f590c6-59e9-4a55-b243-7395b35f0ce2;")
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .body("product_attribute_80_2_37=112" +
                            "&product_attribute_80_1_38=115" +
                            "&addtocart_80.EnteredQuantity=1")
                    .when()
                    .post("/addproducttocart/details/80/2")
                    .then()
                    .log().all()
                    .statusCode(200)
                    .body("success", is(true))
                    .body("message", is("The product has been added to your " +
                            "<a href=\"/wishlist\">wishlist</a>"));

            Integer actualWishListSize = NumberUtils.toInt(response
                    .extract().path("updatetopwishlistsectionhtml")
                    .toString()
                    .replaceAll("[^0-9.]", ""));
            assertThat(actualWishListSize).isGreaterThanOrEqualTo(1);
        });

        step("Verify wishlist contains goods", () -> {
            step("Open wishlist page", () ->
                    open("/wishlist"));

            $(byTagAndText("a", "Phone Cover")).shouldBe(visible);
            $(byTagAndText("div", "Custom Text: Samsung")).shouldBe(visible);
            $(byTagAndText("div", "Color: White")).shouldBe(visible);
            $(".product-unit-price").shouldHave(text("10.00"));
        });

    }
}
