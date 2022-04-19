package hello.core.lifecycle;

// javax >> 자바에서 공식적으로 지원한다는 의미 >> 다른 프레임워크에서도 사용 가능
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient3 {

    private String url;

    public NetworkClient3() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + "message : " + message);
    }

    public void disconnect() {
        System.out.println("close : " + url);
    }

    // 어노테이션 사용 방법
    // 최신 스프링에서 가장 권장하는 방법
    // 어노테이션 하나만 붙이면 끝
    // 자바 표준이기 때문에, 스프링이 아닌 다른 컨테이너에서도 동작한다.
    // 컴포넌트 스캔과 잘 어울린다.
    // 유일한 단점은 외부 라이브러리에 적용하지 못한다는 점이다. >> 외부 라이브러리 적용시 사용자 설정 방법 사용!

    // 의존관계 주입이 끝나면 호출해 주는 메소드
    @PostConstruct
    public void init() throws Exception {
        System.out.println("NetworkClient.init3");
        connect();
        call("초기화 연결 메세지");
    }

    // 소멸 직전 콜백
    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.close3");
        disconnect();
    }
}
