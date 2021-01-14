package com.leogersen.alforno.domain.order;

import org.springframework.data.jpa.repository.*;

public interface ItemOrderRepository extends JpaRepository<ItemOrder, ItemOrderPK> {
}
