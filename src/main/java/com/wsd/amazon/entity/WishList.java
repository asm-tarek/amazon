package com.wsd.amazon.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "WISH_LIST")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WishList extends BaseEntity {
    @Id
    @SequenceGenerator(name = "WishListSeq", sequenceName = "WISH_LIST_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WishListSeq")
    @Column(name = "ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @Column(name = "USER_ID")
    private long userId;

    @Temporal(TemporalType.DATE)
    @Column(name = "ADD_ON_DATE")
    private Date addOnDate;
}
