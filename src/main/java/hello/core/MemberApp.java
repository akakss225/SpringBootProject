package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        // 자바코드를 활용한 의존성 주입.
//        AppConfig appConfig = new AppConfig();
        // MemberServiceImpl이 들어오게됨.
//        MemberService memberService = appConfig.memberService();

        // 스프링을 활용한 의존성 주입.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // 메소드 이름 , 반환 타입 << 매개변수
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


//        MemberService memberService = new MemberServiceImpl();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());


    }
}
