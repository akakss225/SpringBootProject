package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// ComponentScan은 Component 어노테이션이 붙은 모든 클래스를 Spring Bean으로 등록한다.
// ComponentScan 을 통해 일괄 Bean등록을 해주는데, 선택적으로 제외시키는 문법
// AppConfig 를 제외시켜주기위함. >> 기존에 작성한 코드를 살리기 위함.
@Configuration
@ComponentScan(
        basePackages = "hello.core", // 지정을 안하면, default로 모든 자바 파일을 확인한다.
        basePackageClasses = AutoAppConfig.class, // 지정을 안하면, 현재 config 의 패키지인 hello.core부터 하위까지 다 확인.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // 수동 빈 등록시, 자동 빈 등록과 이름이 겹쳐도 우선순위를 갖기 때문에, overriding됨.
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
