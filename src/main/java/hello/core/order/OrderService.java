package hello.core.order;

public interface OrderService {
    // 회원 id 와 상품명, 상품 가격을 파라미터로 넘기면, 최종 order결과를 반환
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
