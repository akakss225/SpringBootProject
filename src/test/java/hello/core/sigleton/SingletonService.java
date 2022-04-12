package hello.core.sigleton;

public class SingletonService {
    // 객체를 미리 생성해두는 방법
    // 자기자신을 내부 private으로 하나 가지고있음.
    // static 으로, 클래스 레벨로 올라가게 해 딱 하나만 가질 수 있게함.
    private static final SingletonService instance = new SingletonService();
    public static SingletonService getInstance() {
        return instance;
    }
    private SingletonService() {}


    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");

    }


}
