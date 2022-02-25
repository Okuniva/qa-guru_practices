package qa_guru.practices.lesson_9;

import org.junit.jupiter.api.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class JunitCore {
    public static void main(String[] args) throws Exception {
        // lookup classes with annotation @Test
        // here we go with class SimpleTest.class

        Class<SimpleTest> simpleTestClass = SimpleTest.class;
        Method beforeAll = null;
        Method beforeEach = null;
        List<Method> tests = new ArrayList<>();
        Method afterEach = null;
        Method afterAll = null;
        for (Method method : simpleTestClass.getDeclaredMethods()) {
            BeforeAll beforeAllAnnotation = method.getAnnotation(BeforeAll.class);
            BeforeEach beforeEachAnnotation = method.getAnnotation(BeforeEach.class);
            Test testAnnotation = method.getAnnotation(Test.class);
            AfterEach afterEachAnnotation = method.getAnnotation(AfterEach.class);
            AfterAll afterAllAnnotation = method.getAnnotation(AfterAll.class);

            if(beforeAllAnnotation != null) beforeAll = method;
            if(beforeEachAnnotation != null) beforeEach = method;
            if(testAnnotation != null) tests.add(method);
            if(afterEachAnnotation != null) afterEach = method;
            if(afterAllAnnotation != null) afterAll = method;
        }
        if(!tests.isEmpty()) {
            if(beforeAll != null) beforeAll.invoke(simpleTestClass.getConstructor().newInstance());

            for(Method test : tests) {
                try {
                    if(beforeEach != null) {
                        try {
                            beforeEach.invoke(simpleTestClass.getConstructor().newInstance());
                        } catch (InvocationTargetException e) {
                            System.out.println("JunitCore_LOG: Exception error in " + beforeEach.getName());
                            continue;
                        }
                    }
                    test.invoke(simpleTestClass.getConstructor().newInstance());

                } catch (InvocationTargetException e) {
                    if (e.getCause() instanceof AssertionError) {
                        System.out.println("JunitCore_LOG: Test failed: " + test.getName());
                    } else {
                        System.out.println("JunitCore_LOG: Test broken: " + test.getName());
                    }
                    continue;
                }
                System.out.println("JunitCore_LOG: Test passed: " + test.getName());

                if(afterEach != null) {
                    try {
                        afterEach.invoke(simpleTestClass.getConstructor().newInstance());
                    } catch (InvocationTargetException e) {
                        System.out.println("JunitCore_LOG: Exception error in " + afterEach.getName());
                    }
                }
            }
            if(afterAll != null) afterAll.invoke(simpleTestClass.getConstructor().newInstance());
        }
    }
}
