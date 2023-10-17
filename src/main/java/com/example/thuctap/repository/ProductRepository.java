package com.example.thuctap.repository;

import com.example.thuctap.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select * from Product where Status= 1", nativeQuery = true)
    List<Product> cbbProduct();

    @Modifying
    @Transactional
    @Query(value = "update Product set Status= 0 where ID= :idProduct", nativeQuery = true)
    void deleteProduct(@Param("idProduct") Long idProduct);
}
