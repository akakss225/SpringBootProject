package hello.core.member;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join(){
        // given >> 주어진 데이터
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when >> 상황
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then >> 결과
        Assertions.assertThat(member).isEqualTo(findMember);

    }
}

