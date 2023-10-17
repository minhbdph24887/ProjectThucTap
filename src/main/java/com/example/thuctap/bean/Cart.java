package com.example.thuctap.bean;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCart;

    @ManyToOne()
    @JoinColumn(name = "Username")
    private Accounts accounts;

    @Column(name = "Description")
    private String description;

    @Column(name = "Status")
    private Integer status;

    public String getStatusCart() {
        if (this.status == 1) {
            return "Còn Hàng";
        } else {
            return "Hết Hàng";
        }
    }
}
