package com.leogersen.alforno.domain.payment;

import org.springframework.data.jpa.repository.*;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
