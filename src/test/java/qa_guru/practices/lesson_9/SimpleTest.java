package qa_guru.practices.lesson_9;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Класс с простыми тестами")
public class SimpleTest {
    @BeforeAll
    public static void beforeAll() {
        System.out.println("BeforeAll");
    }

    @BeforeEach
    void setUp() {
        System.out.println("BeforeEach");
    }

    @AfterEach
    void endEach() {
        System.out.println("AfterEach");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("AfterAll");
    }

    @Test
    @DisplayName("Ожидаемо зеленый тест")
    void simpleGreenTest() {
        System.out.println("simpleGreenTest");
        assertTrue(3 > 2);
    }

    @Test
    @DisplayName("Ожидаемо красный тест")
    void simpleRedTest() {
        System.out.println("simpleRedTest");
        assertTrue(3 < 2);
    }

    @Test
    @Disabled("bug: JIRA-12232")
    void simpleBrokenTest() {
        System.out.println("simpleBrokenTest");
        throw new IllegalStateException("Broken :(");
    }
}
