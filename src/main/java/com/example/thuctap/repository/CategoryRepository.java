package com.example.thuctap.repository;

import com.example.thuctap.bean.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "select * from Category where Status= 1", nativeQuery = true)
    List<Category> viewClientCategory();

    @Modifying
    @Transactional
    @Query(value = "update Category set Status= 0 where ID= :idCategory", nativeQuery = true)
    void deleteCategory(@Param("idCategory") Long idCategory);
}
