package hello.core.web;


import hello.core.common.MyLogger2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService2 {


    private final MyLogger2 myLogger2;

    public void logic(String id) {
        myLogger2.log("service id = " + id);

    }
}
