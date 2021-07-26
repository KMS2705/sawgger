package com.example.swagger.controller;

import java.util.Arrays;
import java.util.List;

import com.example.swagger.dto.OrderRequest;
import com.example.swagger.dto.OrderResponse;
import com.example.swagger.entity.Order;
import com.example.swagger.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

@RestController
public class OrderController {
	@Autowired
	OrderService orderService;
	// DTO to Entity, Entity to DTO를 위해 ModelMapper 사용
	ModelMapper modelMapper = new ModelMapper();
	
	@PostMapping("/order")
	public int saveOrder(@RequestBody OrderRequest orderRequest) {
		// ModelMapper를 이용해 OrderRequest DTO의 값을 Order Entity로 복사한다.
		Order order = modelMapper.map(orderRequest, Order.class);
		return orderService.save(order);
	}

	@PutMapping("/order")
	public int changeOrder(@RequestBody OrderRequest orderRequest) {
		// ModelMapper를 이용해 OrderRequest DTO의 값을 Order Entity로 복사한다.
		Order order = modelMapper.map(orderRequest, Order.class);
		return orderService.modify(order);
	}

	@DeleteMapping("/order/{id}")
	public int deleteOrder(String id) {
		return orderService.delete(id);
	}

	@GetMapping("/order/{id}")
	public OrderResponse getOrder(@PathVariable String id) {
		Order order = orderService.findById(id);
		// ModelMapper를 이용해 order entity의 값을 OrderResponse DTO로 변환한다.
		OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);

		return orderResponse;
	}

	@GetMapping("/order")
	public List<OrderResponse> getOrders() {
		// Persistence에서 List<Order>로 생성된 Entity 리스트를 화면으로 전달하기 위해 List<OrderResponse>로
		// 변환
		List<OrderResponse> postDtoList = Arrays.asList(modelMapper.map(orderService.findAll(), OrderResponse[].class));
		return postDtoList;
	}
}
