package com.epam.esm.SpringSecurity.repository;


import com.epam.esm.SpringSecurity.repository.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderRepository extends CrudDao<Order, Integer> {

    Page<Order> findByUserId(Integer id, Pageable pageable);

    Order findLastAddedOrder();
}
