package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
//         connect();
//         call("초기화 연결 메세지");
    }

    public void setUrl(String url){
        this.url = url;
    }

    // 서비스 시작시 호출

    public void connect() {
        System.out.println("connet : " + url);

    }

    public void call(String message) {
        System.out.println("call : " + url + "message : " + message);
    }

    // 서비스 종료시 호출

    public void disconnect() {
        System.out.println("close : " + url);
    }



    // *** 단점이 존재함 ***
    // 이러한 초기화 / 소멸 인터페이스의 경우, 해당 코드가 스프링 전용 인터페이스에 의존한다.
    // 즉 초기화 / 소멸 메소드의 이름을 변경할 수 없고, 내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없다.

    // 의존관계 주입이 끝나면 호출해 주는 메소드
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }

    // 소멸 직전 콜백
    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
