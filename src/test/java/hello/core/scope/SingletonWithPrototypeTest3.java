package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest3 {

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);

    }

    @Scope("singleton")
    static class ClientBean {

        // Provider 를 사용한 singleton + prototype 문제 해결
        // Provider 의 경우, DI 가 아닌 Dependency Lookup 이라는 DL 라고 불린다.
        // ObjectProvider 와는 달리, 자바 표준이기 때문에 스프링 의존적이지 않다.
        // 굉장히 Simple 하지만, 단점이 될 수 있다. >> 딱 DL 정도의 기능만 함. ++ 자바 라이브러리 build 를 따로 해줘야함.
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            // Provider 는 get() 를 호출하는 시점에 스프링 컨테이너에서 prototypeBean 을 찾아서 호출해줌.
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }

    }



    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
