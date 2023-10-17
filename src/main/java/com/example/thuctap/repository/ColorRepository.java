package com.example.thuctap.repository;

import com.example.thuctap.bean.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    @Query(value = "select * from Color where Status= 1", nativeQuery = true)
    List<Color> cbbColor();

    @Modifying
    @Transactional
    @Query(value = "update Color set Status= 0 where ID= :idColor", nativeQuery = true)
    void deleteColor(@Param("idColor") Long idColor);
}
