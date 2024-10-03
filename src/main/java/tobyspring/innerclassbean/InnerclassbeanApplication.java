package tobyspring.innerclassbean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 왜 "내부클래스" 작성 시 정적 클래스로 만들어야 동작하는지 이유를 잘 모르겠습니다.
 * 내부 클래스는 정적 클래스가 될 수 없다.
 *
 */
@SpringBootApplication
public class InnerclassbeanApplication {

    // Static Nested Class
    @Component
    static class StaticInnerClass {
        public StaticInnerClass() {
            // Local Class
            class LocalClass {
                public LocalClass() {
                    System.out.println("LocalClass");
                }
            }

            // Anonymous Class
            var runnable = new Runnable(){
                @Override
                public void run() {
                    System.out.println("Runnable");
                }
            };

            System.out.println("StaticInnerClass");
        }
    }

    // (Non-Static Nested) Inner Class
    @Component
     class InnerClass {
        public InnerClass() {
            System.out.println("InnerClass");
        }
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(InnerclassbeanApplication.class, args);
        StaticInnerClass staticInnerClass = applicationContext.getBean(StaticInnerClass.class);
        InnerClass innerClass = applicationContext.getBean(InnerClass.class);
        System.out.println(staticInnerClass);
        System.out.println(innerClass);

        StaticInnerClass staticInnerClass1 = new StaticInnerClass();
        var outerClass = new InnerclassbeanApplication();
        InnerClass innerClass1 = outerClass.new InnerClass();

        TestOuterClass.TestInnerClass bean = applicationContext.getBean(TestOuterClass.TestInnerClass.class);
        System.out.println(bean);
    }
}
