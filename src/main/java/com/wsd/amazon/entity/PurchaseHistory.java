package com.wsd.amazon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "PURCHASE_HISTORY")
@Getter
@Setter
public class PurchaseHistory extends BaseEntity {
    @Id
    @SequenceGenerator(name = "PurchaseHistorySeq", sequenceName = "PURCHASE_HISTORY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PurchaseHistorySeq")
    @Column(name = "ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @Column(name = "USER_ID")
    private long userId;

    @Column(name = "PURCHASE_TIME")
    private Date purchaseTime;

    @Column(name = "COUNT")
    private int count;

    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;

    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;
}
