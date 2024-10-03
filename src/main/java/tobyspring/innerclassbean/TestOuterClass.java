package tobyspring.innerclassbean;


import org.springframework.stereotype.Component;

public class TestOuterClass {

    @Component
    class TestInnerClass {
        public TestInnerClass() {
        }
    }
}
