package com.example.thuctap.bean;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "PromotionsProduct")
public class PromotionsProduct {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPromotionsProduct;

    @ManyToOne()
    @JoinColumn(name = "PromotionsID")
    private Promotions promotions;

    @ManyToOne()
    @JoinColumn(name = "ProductItemsID")
    private ProductItems productItems;

    @Column(name = "Status")
    private Integer status;

    public String getStatusPromotionsProduct() {
        if (this.status == 1) {
            return "Đang Hoạt Động";
        } else {
            return "Ngừng Hoạt Động";
        }
    }
}
