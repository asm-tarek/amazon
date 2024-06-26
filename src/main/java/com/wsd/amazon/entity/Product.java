package com.wsd.amazon.entity;

import com.wsd.amazon.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "PRODUCT")
@Data
public class Product extends BaseEntity {
    @Id
    @SequenceGenerator(name = "ProductSeq", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ProductSeq")
    @Column(name = "ID")
    private long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "WIGHT")
    private double weight;

    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;
}
