package com.example.swagger.service;

import com.example.swagger.entity.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {
	public int save(Order order);

	public Order findById(String id);

	public int delete(String id);

	public int modify(Order order);

	public List<Order> findAll();
}