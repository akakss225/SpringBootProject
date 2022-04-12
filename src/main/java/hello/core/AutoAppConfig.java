package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// ComponentScan은 Component 어노테이션이 붙은 모든 클래스를 Spring Bean으로 등록한다.
// ComponentScan 을 통해 일괄 Bean등록을 해주는데, 선택적으로 제외시키는 문법
// AppConfig 를 제외시켜주기위함. >> 기존에 작성한 코드를 살리기 위함.
@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
