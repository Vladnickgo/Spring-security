package com.epam.esm.SpringSecurity.service.mapper;

import com.epam.esm.SpringSecurity.repository.entity.Order;
import com.epam.esm.SpringSecurity.service.dto.OrderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order mapDtoToEntity(OrderDto orderDto);


    OrderDto mapEntityToDto(Order order);
}

