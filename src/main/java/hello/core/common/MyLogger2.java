package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

// proxyMode 에서 대상 파일이 class 이면 class 로 준다.
// 이러한 설정을 Scope 에 넣어주면, MyLogger2 의 가짜 프록시 클래스를 만들어두고
// HTTP request 와 상관없이 가짜 프록시 클래스를 다른 빈에 미리 주입해둘 수 있다.
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger2 {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create : " + this);
    }

    @PreDestroy
    public void close() {
        // 빈이 종료되는 시점에 로그르 찍어줌.
        System.out.println("[" + uuid + "] request scope bean close : " + this);
    }
}
