package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request")
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        // MyLogger 빈 객체가 컨테이너에 등록이 되는 시점에, uuid 를 생성해 저장하게끔 해주는 PostConstruct
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create : " + this);
    }

    @PreDestroy
    public void close() {
        // 빈이 종료되는 시점에 로그르 찍어줌.
        System.out.println("[" + uuid + "] request scope bean close : " + this);
    }
}
