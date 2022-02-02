package com.hotelbooking.Payment.repository;

import com.hotelbooking.Payment.entity.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<TransactionDetailsEntity, Integer> {
}
