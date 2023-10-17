package com.example.thuctap.repository;

import com.example.thuctap.bean.Accounts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, String> {
    // truy van Danh Sach Admin
    @Query(value = "select Account.Username, Account.Name, Account.NumberPhone, Account.Sex, Account.Birthday, Account.Address, Account.Password, Account.Status from Account inner join Authorities on Account.Username = Authorities.Username inner join Role on Authorities.RoleID = Role.ID where Role.ID = 1", nativeQuery = true)
    Page<Accounts> getAllAdmin(Pageable pageable);

    // truy van danh sach Khach Hang
    @Query(value = "select Account.Username, Account.Name, Account.NumberPhone, Account.Sex, Account.Birthday, Account.Address, Account.Password, Account.Status from Account inner join Authorities on Account.Username = Authorities.Username inner join Role on Authorities.RoleID = Role.ID where Role.ID = 2", nativeQuery = true)
    Page<Accounts> getAllClient(Pageable pageable);

    // Hien thi cac Account Hoat Dong
    @Query(value = "select * from Account where Status = 1", nativeQuery = true)
    List<Accounts> getAllAccount1();

    // update trạng thái của Admin
    @Modifying
    @Transactional
    @Query(value = "update Account set Status= 0 where Username= :username", nativeQuery = true)
    void deleteAccount(@Param("username") String username);
}
