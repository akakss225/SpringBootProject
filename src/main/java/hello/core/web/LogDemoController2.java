package hello.core.web;


import hello.core.common.MyLogger;
import hello.core.common.MyLogger2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController2 {

    private final LogDemoService2 logDemoService2;
    private final MyLogger2 myLogger2;

    @RequestMapping("log-demo2")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        // HttpServletRequest >> 자바에서 제공하는 표준 서블릿 규약
        // 고객 요청 정보를 받을 수 있음

        String requestURL = request.getRequestURL().toString();

        System.out.println("myLogger2 = " + myLogger2.getClass());
        myLogger2.setRequestURL(requestURL);

        myLogger2.log("controller test");
        Thread.sleep(1000);
        logDemoService2.logic("testID");

        return "OK";

    }

}
