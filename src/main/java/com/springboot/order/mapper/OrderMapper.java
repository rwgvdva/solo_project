package com.springboot.order.mapper;

import com.springboot.coffee.entity.Coffee;
import com.springboot.member.entity.Member;
import com.springboot.order.dto.OrderPatchDto;
import com.springboot.order.dto.OrderPostDto;
import com.springboot.order.dto.OrderResponseDto;
import com.springboot.order.entity.Order;
import com.springboot.order.entity.OrderCoffee;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {
//    Order orderPostDtoToOrder(OrderPostDto orderPostDto);
    default Order orderPostDtoToOrder(OrderPostDto orderPostDto){
        Order order = new Order();
        Member member = new Member();
        member.setMemberId(orderPostDto.getMemberId());
        List<OrderCoffee> orderCoffees = orderPostDto.getOrderCoffees().stream()
                .map(orderCoffeeDto -> {
                    OrderCoffee orderCoffee = new OrderCoffee();
                    orderCoffee.setQuantity(orderCoffeeDto.getQuantity());
                    Coffee coffee = new Coffee();
                    coffee.setCoffeeId(orderCoffeeDto.getCoffeeId());
                    orderCoffee.setOrder(order);
                    orderCoffee.setCoffee(coffee);
                    return orderCoffee;
                }).collect(Collectors.toList());
        order.setOrderCoffees(orderCoffees);
        order.setMember(member);

        return order;
    }
    Order orderPatchDtoToOrder(OrderPatchDto orderPatchDto);
    OrderResponseDto orderToOrderResponseDto(Order order, List<Coffee> coffees);
    List<OrderResponseDto> ordersToOrderResponseDtos(List<Order> orders);
}
