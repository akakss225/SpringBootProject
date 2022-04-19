package hello.core.lifecycle;

public class NetworkClient2 {

    private String url;

    public NetworkClient2() {
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


    // 설정 정보 사용 특징
    // 메소드 이름을 자유롭게 쓸 수 있음!
    // 스프링 빈이 스프링 코드에 의존하지 않음!
    // 코드가 아니라 설정 정보를 사용하기 떄문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화 / 종료 메소드를 적용할 수 있음!

    // 의존관계 주입이 끝나면 호출해 주는 메소드
    public void init() throws Exception {
        System.out.println("NetworkClient.init2");
        connect();
        call("초기화 연결 메세지");
    }

    // 소멸 직전 콜백
    public void close() throws Exception {
        System.out.println("NetworkClient.close2");
        disconnect();
    }
}
