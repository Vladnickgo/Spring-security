package com.epam.esm.SpringSecurity.service.impl;

import com.epam.esm.SpringSecurity.exception.NotFoundException;
import com.epam.esm.SpringSecurity.repository.entity.GiftCertificate;
import com.epam.esm.SpringSecurity.repository.entity.Order;
import com.epam.esm.SpringSecurity.repository.entity.User;
import com.epam.esm.SpringSecurity.repository.impl.GiftCertificateRepositoryImpl;
import com.epam.esm.SpringSecurity.repository.impl.OrderRepositoryImpl;
import com.epam.esm.SpringSecurity.repository.impl.UserRepositoryImpl;
import com.epam.esm.SpringSecurity.service.OrderService;
import com.epam.esm.SpringSecurity.service.dto.OrderDto;
import com.epam.esm.SpringSecurity.service.mapper.OrderMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepositoryImpl orderRepository;
    private final GiftCertificateRepositoryImpl certificateRepository;
    private final UserRepositoryImpl userRepository;
    private final OrderMapper orderMapper;


    @Autowired
    public OrderServiceImpl(OrderRepositoryImpl orderRepository, GiftCertificateRepositoryImpl certificateRepository, UserRepositoryImpl userRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.certificateRepository = certificateRepository;
        this.userRepository = userRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional
    public OrderDto save(Integer userId, Integer certificateId) {
        GiftCertificate giftCertificate = certificateRepository.findById(certificateId)
                .orElseThrow(() -> new NotFoundException("Certificate with id=" + certificateId + " not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with id=" + userId + " not found"));
        Order order = Order.builder()
                .certificateId(giftCertificate.getId())
                .userId(user.getId())
                .orderDate(LocalDate.now())
                .orderPrice(giftCertificate.getPrice())
                .build();
        orderRepository.save(order);
        Order lastAddedOrder = orderRepository.findLastAddedOrder();
        return orderMapper.mapEntityToDto(lastAddedOrder);
    }

    @Override
    public Page<OrderDto> getUserOrders(Integer userId, Pageable pageable) {
        Integer total = orderRepository.countAllByUserId(userId);
        List<OrderDto> orderDtoList = orderRepository.findByUserId(userId, pageable).stream()
                .map(orderMapper::mapEntityToDto)
                .toList();
        return new PageImpl<>(orderDtoList, pageable, total);
    }

    @Override
    public OrderDto findById(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order with id=" + id + " not found"));
        return orderMapper.mapEntityToDto(order);
    }

    @Override
    @Transactional
    public Page<OrderDto> findAll(Pageable pageable) {
        Integer total = orderRepository.countAll();
        List<OrderDto> list = orderRepository.findAll(pageable).stream()
                .map(orderMapper::mapEntityToDto)
                .toList();
        return new PageImpl<>(list, pageable, total);
    }
}
