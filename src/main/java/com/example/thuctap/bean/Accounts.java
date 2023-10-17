package com.example.thuctap.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Account")
public class Accounts {
    @Id
    @Column(name = "Username")
    private String userName;

    @Column(name = "Name")
    private String nameUser;

    @Column(name = "NumberPhone")
    private String numberPhone;

    @Column(name = "Sex")
    private Boolean sex;

    @Column(name = "Birthday")
    private Date birthday;

    @Column(name = "Address")
    private String address;

    @Column(name = "Password")
    private String passWord;

    @Column(name = "Status")
    private Integer status;

    @JsonIgnore()
    @OneToMany(mappedBy = "accounts")
    List<Authorities> authoritiesList;

    @JsonIgnore
    @OneToMany(mappedBy = "accounts")
    List<Bill> bills;

    public String detailStatus() {
        if (this.status == 1) {
            return "Đang Hoạt Động";
        } else {
            return "Ngừng Hoạt Động";
        }
    }
}
