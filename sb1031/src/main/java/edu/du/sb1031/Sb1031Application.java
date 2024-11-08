package edu.du.sb1031;

import edu.du.sb1031.dto.AuthInfo;
import edu.du.sb1031.dto.Define;
import edu.du.sb1031.entity.*;
import edu.du.sb1031.repository.ItemRepository;
import edu.du.sb1031.repository.ReviewRepository;
import edu.du.sb1031.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class Sb1031Application {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ItemRepository itemRepository;

    public static void main(String[] args) {
        SpringApplication.run(Sb1031Application.class, args);
    }

    @Autowired
    MemberService memberService;
    @Autowired
    ItemService itemService;
    @Autowired
    ReviewRepository rr;
    @Autowired
    OrderService os;
    @Autowired
    CartService cs;
    @Autowired
    OrderItemService ois;
    @Autowired
    DeliveryService ds;

    @PostConstruct
    public void init() {
        // 최상위 카테고리 저장
        itemService.createComputerPartsItems(categoryService.createComputerPartsCategories());
        Member member1 = new Member(null, "어드민", "admin", "1234", Define.ADMIN, Define.MALE, null, null, null, null);
        Member member2 = new Member(null, "홍길동", "hong", "1234", Define.USER, Define.MALE,null, null, null,null);
        Member member3 = new Member(null, "삭제된 유저", "delete", "1234", Define.DELETE, Define.MALE,null,null, null, null);
        Member member4 = new Member(null, "동결된 유저", "freeze", "1234", Define.FREEZE, Define.MALE,null,null, null, null);
        memberService.save(member1);
        memberService.save(member2);
        memberService.save(member3);
        memberService.save(member4);
        Item item1 = itemRepository.findById(1L).get();
        Item item2 = itemRepository.findById(2L).get();
        Review review = new Review(null, item1, member2, "리뷰 내용", LocalDateTime.now(), 5, 'N', 1);
        rr.save(review);

        //카트에 물품추가
        Cart cart1 = new Cart();
        cart1.setMember(member2);
        cart1.setItem(item1);
        cart1.setQuantity(10);    // 기본값, 수정 여부 불확실
        cs.add(cart1);
        Cart cart2 = new Cart();
        cart2.setMember(member2);
        cart2.setItem(item2);
        cart2.setQuantity(15);    // 기본값, 수정 여부 불확실
        cs.add(cart2);

        //구매
        Delivery delivery = new Delivery(null, null, "06245","서울특별시 강남구 역삼동 123-45", "테스트빌딩 101호", "홍길동", "010-1234-5678");
        ds.save(delivery);

        Order order = new Order(null, member2, delivery, null, "배송중");
        os.save(order);

        List<Cart> carts = cs.findByMember(member2);
        for(Cart cart : carts){
            OrderItem orderItem = new OrderItem(null, order, cart.getQuantity(), cart.getItem());
            ois.save(orderItem);
        }
    }

}
