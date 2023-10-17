package com.example.thuctap.repository;

import com.example.thuctap.bean.Promotions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionsRepository extends JpaRepository<Promotions, Long> {
}
