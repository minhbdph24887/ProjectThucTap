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
@Table(name = "BillItems")
public class BillItems {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBillItems;

    @ManyToOne()
    @JoinColumn(name = "BillID")
    private Bill bill;

    @ManyToOne()
    @JoinColumn(name = "ProductItemsID")
    private ProductItems productItems;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "Price")
    private BigDecimal price;
}
