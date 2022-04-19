package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 스프링 빈은 기본적으로 "객체 생성" -> "의존관계주입" 라이프 사이클을 갖는다.

// 스프링 빈은 객체를 생성하고, 의존관계 주입이 다 끝난 다음에야 필요한 데이터를 사용할 수 있는 준비가 완료된다.
// 따라서 초기화 작업은 의존관계 주입이 모두 완료되고 난 다음에 호출해야한다.
// 그런데 개발자가 의존관계 주입이 모두 완료된 시점을 어떻게 알 수 있을까?
// >> 스프링은 의존관계 주입이 완료가 되면, 스프링 빈에게 콜백 메소드를 통해 초기화 시점을 알려주는 다양한 기능을 제공한다.
// >> 또한 스프링은 스프링 컨테이너가 종료되기 직전에 소멸 콜백을 준다. 따라서 안전하게 종료 작업을 진행할 수 있다.

// 스프링 빈의 이벤트 라이프사이클
// "스프링 컨테이너 생성" -> "스프링 빈 생성" -> "의존관계 주입" -> "초기화 콜백" -> "사용" -> "소멸전 콜백" -> "스프링 종료"
public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
//        NetworkClient client = ac.getBean(NetworkClient.class);
        NetworkClient2 client2 = ac.getBean(NetworkClient2.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            // 아직 url정보가 들어오기 전이기 때문에, 생성자 호출시에는 url == null로 호출됨.

            // 객체의 생성과 초기화를 분리하는것이 굉장히 중요하다!!
            // 생성자는 필수 정보(파라미터)를 받고, 메모리를 할당해서 객체를 생성하는 책임을 가진다.
            // 반면 초기화는 이렇게 생성된 값을 활용해 외부 커넥션을 연결하는 등 무거운 동작을 수행한다.
            // 따라서 생성자 안에서 무거운 초기화 작업을 함께 하는것 보다는 객체를 생성하는 부분과 초기화를 하는 부분을 명확하게 나누는 것이 유지보수 관점에서 더 좋다.
            // 물론 초기화 작업이 내부 값들만 약간 변경하는 정도로 단순한 경우에는 생성자에서 한번에 다 처리하는 것이 나을 수 있다.
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }

        // destroyMethod 의 default 값은 "(inferred)" 라는 값임. >> 라이브러리들은 보편적으로 close / shutdown 이라는 이름을 사용.
        // inferred 는 단어 그대로 "추론" 이라는 의미를 갖는다. 즉, close 혹은 shutdown 이라는 이름의 메소드를 자동으로 호출해준다.
        // 이 추론기능을 사용하지 않으려면, destroyMethod = "" 로 공백설정을 하면 된다.
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient2 networkClient2() {
            NetworkClient2 networkClient2 = new NetworkClient2();
            networkClient2.setUrl("http://hello-spring.dev");
            return networkClient2;

        }

    }


}
