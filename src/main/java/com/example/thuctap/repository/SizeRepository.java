package com.example.thuctap.repository;

import com.example.thuctap.bean.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {
    @Query(value = "select * from Size where Status= 1", nativeQuery = true)
    List<Size> cbbSize();

    @Modifying
    @Transactional
    @Query(value = "update Size set Status= 0 where ID= :idSize", nativeQuery = true)
    void deleteSize(@Param("idSize") Long idSize);
}
