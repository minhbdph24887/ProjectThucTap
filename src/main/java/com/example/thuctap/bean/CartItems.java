package com.example.thuctap.bean;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "CartItems")
public class CartItems {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCartItems;

    @ManyToOne()
    @JoinColumn(name = "CartID")
    private Cart cart;

    @ManyToOne()
    @JoinColumn(name = "ProductItemsID")
    private ProductItems productItems;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "Price")
    private BigDecimal price;
}
