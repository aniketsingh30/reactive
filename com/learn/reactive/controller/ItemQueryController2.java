package com.learn.reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.juint.service.IItemQueryService;

import reactor.core.publisher.Mono;

@RestController
public class ItemQueryController2 {
	@Autowired
	IItemQueryService itemQueryService;
	@GetMapping("/hello")
	public Mono<String> getHello() throws Exception {
		return itemQueryService.getHello();
	}
}
