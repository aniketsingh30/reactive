package com.learn.reactive.controller;

import static com.learn.reactive.constant.ItemConstants.ALL;
import static com.learn.reactive.constant.ItemConstants.ITEM_REQUEST_MAPPING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.reactive.bean.SubItem;
import com.learn.reactive.constant.ItemConstants;
import com.learn.reactive.service.IItemQueryService;
import com.learn.reactive.service.OrderResponseService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ITEM_REQUEST_MAPPING)
public class ItemQueryController {

	@Autowired
	private IItemQueryService itemQueryService;

	@GetMapping(ALL)
	public Mono<ResponseEntity<OrderResponseService>> findAll() throws Exception {
		return itemQueryService.findAll();
	}

	@GetMapping(ItemConstants.FINDBYID)
	public Mono<ResponseEntity<OrderResponseService>> findItemById(@PathVariable String id) throws Exception {
		return itemQueryService.getItemById(id);

	}

	@GetMapping(ItemConstants.FINDBYDESCRIPTION)
	public Mono<ResponseEntity<OrderResponseService>> findItemByDescription(@PathVariable String description)
			throws Exception {
		return itemQueryService.getItemByDescription(description);

	}

	@GetMapping(ItemConstants.FINDSUBITEMSBYID)
	public Flux<SubItem> findSubItemsById(@PathVariable String id) throws Exception {
		return itemQueryService.findSubItemsById(id);

	}

}
