package com.example.thuctap.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "ProductItems")
public class ProductItems {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProductItems;

    @ManyToOne()
    @JoinColumn(name = "ProductID")
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "ColorID")
    private Color color;

    @ManyToOne()
    @JoinColumn(name = "SizeID")
    private Size size;

    @ManyToOne()
    @JoinColumn(name = "CategoryID")
    private Category category;

    @Column(name = "Images")
    private String imagesProduct;

    @Column(name = "AvaiableQuantity")
    private Integer availableQuantity;

    @Column(name = "PurchasePrice")
    private BigDecimal purchasePrice;

    @Column(name = "CostPrice")
    private BigDecimal costPrice;

    @Column(name = "Status")
    private Integer status;

    @JsonIgnore
    @OneToMany(mappedBy = "productItems")
    List<PromotionsProduct> promotionsProducts;

    @JsonIgnore
    @OneToMany(mappedBy = "productItems")
    List<CartItems> cartItems;

    @JsonIgnore
    @OneToMany(mappedBy = "productItems")
    List<BillItems> billItems;

    public String getStatusProductItems(){
        if (this.status == 1) {
            return "Đang Còn Hàng";
        } else {
            return "Tạm Thời Hết Hàng";
        }
    }
}
