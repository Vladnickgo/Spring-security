package com.epam.esm.SpringSecurity.service;

import com.epam.esm.SpringSecurity.service.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderDto save(Integer userId, Integer certificateId);

//    OrderDto save(OrderDto orderDto);

    Page<OrderDto> getUserOrders(Integer id, Pageable pageable);

    OrderDto findById(Integer id);

    Page<OrderDto> findAll(Pageable pageable);
}
