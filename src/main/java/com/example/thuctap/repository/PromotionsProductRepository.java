package com.example.thuctap.repository;

import com.example.thuctap.bean.PromotionsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionsProductRepository extends JpaRepository<PromotionsProduct, Long> {
    @Query(value = "select * from PromotionsProduct where PromotionsID= :idPromotions", nativeQuery = true)
    Page<PromotionsProduct> getAllPromotionsProduct(Pageable pageable, @Param("idPromotions") Long idPromotions);
}
