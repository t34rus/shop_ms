package com.example.orderservice.rest.dto;

import com.example.orderservice.domain.model.Order;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface OrderMapper {
    OrderResponse toResponse(Order order);

    @Mapping(target = "status", constant = "NEW")
    Order toNewEntity(OrderCreateRequest request);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "status", source = "request.status")
    Order applyStatusUpdate(@MappingTarget Order order, OrderStatusUpdateRequest request);
}
