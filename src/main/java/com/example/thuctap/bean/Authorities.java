package com.example.thuctap.bean;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Authorities")
public class Authorities {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAuthorities;

    @ManyToOne()
    @JoinColumn(name = "Username")
    private Accounts accounts;

    @ManyToOne()
    @JoinColumn(name = "RoleID")
    private Role role;
}
