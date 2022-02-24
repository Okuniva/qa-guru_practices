package qa_guru.practices.lesson_5.pages.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CalendarComponent {
    public static void setDate(String day, String month, String year) {
        $(".react-datepicker-popper").shouldBe(visible);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__month-select").selectOption(month);
        $$(".react-datepicker__day").find(text(day)).click();
        $(".react-datepicker-popper").shouldNotBe(visible);
    }
}
