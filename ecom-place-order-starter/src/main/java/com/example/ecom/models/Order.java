package com.example.ecom.models;

import lombok.Data;

import java.util.List;

@Data
public class Order extends BaseModel{
    private User user;
    private Address deliveryAddress;
    private List<OrderDetail> orderDetails;
    private OrderStatus orderStatus;
}
