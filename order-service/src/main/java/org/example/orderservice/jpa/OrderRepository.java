package org.example.orderservice.jpa;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

    OrderEntity findByOrderId(final String orderId);
    Iterable<OrderEntity> findByUserId(final String userId);
}
