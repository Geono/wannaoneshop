package market.wannaone.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Setter
@Getter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name="member_id")
    private Member member;

    private String name;

    private int price;

    private String info;

    private String descriptionImageFilename;

    private String representativeImageFilename;

    private int totalCount;

    private int soldCount;
}
