package com.example.thuctap.repository;

import com.example.thuctap.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "select * from Role where Status = 1", nativeQuery = true)
    List<Role> getAllRole1();

    @Modifying
    @Transactional
    @Query(value = "update Role set Status= 0 where ID= :idRole", nativeQuery = true)
    void deleteRole(@Param("idRole") Long idRole);
}
