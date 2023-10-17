package com.example.thuctap.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Promotions")
public class Promotions {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPromotions;

    @Column(name = "Code")
    private String codePromotions;

    @Column(name = "Name")
    private String namePromotions;

    @Column(name = "Persen")
    private BigDecimal person;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "StartDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @Column(name = "EndDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @Column(name = "Description")
    private String description;

    @Column(name = "Status")
    private Integer status;

    @JsonIgnore
    @OneToMany(mappedBy = "promotions")
    List<PromotionsProduct> promotionsProducts;

    public String getStatusPromotions() {
        if (this.status == 1) {
            return "Đang Hoạt Động";
        } else if (this.status == 0) {
            return "Đã Hết Hạn";
        } else {
            return "Sắp Ra Mắt";
        }
    }

    public void updateStatusPromotions() {
        Date now = new Date();
        if (this.startDate.after(now)) {
            this.status = 2; // Sắp Ra Mắt
        } else if (this.endDate.before(now)) {
            this.status = 0; // Đã Hết Hạn
        } else if (this.startDate.before(now) && this.endDate.after(now)) {
            this.status = 1; // Đang Hoạt Động
        }
    }
}
