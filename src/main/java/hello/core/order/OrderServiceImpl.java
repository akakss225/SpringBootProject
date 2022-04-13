package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 생성자주입 > lombok 버전
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // =============================== 의존성 주입 start =================================

    // 생성자를 통한 의존성 주입 : 불변 / 필수 의존관계에 사용.
    // lombok > RequiredArgsConstructor 을 통해 생성가능. > 생략!
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        System.out.println("memberRepository = " + memberRepository);
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }


    // 수정자 주입 : setter를 통한 의존성 주입.. 선택 / 변경 가능성이 있는 의존관계에 사용.
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }


    // 필드 주입 : 필드에 직접적으로 의존성을 주입.. 코드는 가장 간결. 외부 데이터 변경이 불가. >> 테스트같은 유연한 코딩 불가.
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private DiscountPolicy discountPolicy;

    // =============================== 의존성 주입 end =================================

    // =============================== 빈 중복 수정 start =================================

    // 동일한 이름의 빈이 두개이상 등록되었을 때
    // Bean 이름을 특정지으면 됨!
//    private final DiscountPolicy rateDiscountPolicy;


    // @Qualifier 를 사용한 빈 중복 수정
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }


    // 재정의 어노테이션을 활용한 빈 중복 수정
//    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    // =============================== 빈 중복 수정 end =================================



    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // Test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
