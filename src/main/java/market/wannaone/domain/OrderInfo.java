package market.wannaone.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_info")
@Setter
@Getter
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(targetEntity = Cart.class)
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne(targetEntity = Item.class)
    @JoinColumn(name="item_id")
    private Item item;

    private Integer count;
    private String status;
}
