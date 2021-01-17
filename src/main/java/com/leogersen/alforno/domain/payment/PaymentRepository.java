package com.leogersen.alforno.domain.payment;

import com.leogersen.alforno.domain.order.*;
import org.springframework.data.jpa.repository.*;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
