package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void autowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);


    }

    static class TestBean {

        // required = false >> 자동주입할 대상이 없으면, 메소드 자체가 호출이 안됨.
        @Autowired(required = false) // 기본값은 true. 만일 false를 안하면 예외가 생김 >> Member가 빈으로 등록된게 아니기 때문.
        public void setNoBean1(Member member) {
            System.out.println("member1 = " + member);
        }

        // 대상이 없어도 호출은 되지만, null이 출력됨.
        @Autowired
        public void setNoBean2(@Nullable Member member) {
            System.out.println("member2 = " + member);
        }

        // 빈 객체가 없으면, Optional.empty로 출력됨.
        @Autowired(required = false)
        public void setNoBean3(Optional<Member> member) {
            System.out.println("member3 = " + member);
        }

    }


}
