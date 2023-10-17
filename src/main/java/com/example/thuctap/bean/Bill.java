package com.example.thuctap.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Bill")
public class Bill {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBill;

    @ManyToOne()
    @JoinColumn(name = "Username")
    private Accounts accounts;

    @Column(name = "CreateDate")
    private Date createDate;

    @Column(name = "DateOfPayment")
    private Date dateOfPayment;

    @Column(name = "TotalAmount")
    private BigDecimal totalAmount;

    @Column(name = "MoneyPaidByCustomers")
    private BigDecimal moneyPaidByCustomers;

    @Column(name = "Change")
    private BigDecimal change;

    @Column(name = "DeliveryAddress")
    private String deliveryAddress;

    @Column(name = "Note")
    private String note;

    @Column(name = "Status")
    private Integer status;

    @JsonIgnore
    @OneToMany(mappedBy = "bill")
    List<BillItems> billItems;

    public String getStatusBill() {
        if (this.status == 1) {
            return "Đang Chờ Thanh Toán";
        } else if (this.status == 0) {
            return "Đã Hủy";
        } else {
            return "Thanh Toán Thành Công";
        }
    }
}
